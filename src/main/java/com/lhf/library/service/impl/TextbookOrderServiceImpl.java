package com.lhf.library.service.impl;

import com.lhf.library.entity.Textbook;
import com.lhf.library.entity.TextbookOrder;
import com.lhf.library.entity.User;
import com.lhf.library.mapper.TextbookMapper;
import com.lhf.library.mapper.TextbookOrderMapper;
import com.lhf.library.mapper.UserMapper;
import com.lhf.library.service.TextbookOrderService;
import com.lhf.library.service.ex.InsufficientBalance;
import com.lhf.library.service.ex.OrderException;
import com.lhf.library.service.ex.UserException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 15:41
 */
@Service
public class TextbookOrderServiceImpl implements TextbookOrderService {
    @Resource
    private TextbookOrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TextbookMapper textbookMapper;

    @Override
    public void updateOrderStatusByPay(String code, Integer id) {
        //首先判断是否已经支付
        TextbookOrder order = orderMapper.selectOrderBookById(id);
        if (order.getStatus() == 1) {
            throw new OrderException("账户已支付,切勿重复提交");
        } else if (order.getStatus() == 2) {
            throw new OrderException("管理员审核已通过,支付失败");
        } else if (order.getStatus() == 3) {
            throw new OrderException("管理员审核未通过,支付失败");
        }
        User user = userMapper.selectUserByCode(code);
        if (user.getStatus() != 1) {
            throw new UserException("用户状态被锁定,支付失败");
        }
        if (user.getMoney() < order.getPrice()) {
            //新建异常,用于前端根据状态码跳转到充值页面
            throw new InsufficientBalance("账户余额不足,请充值");
        }
        //支付
        Double userMoney = user.getMoney() - order.getPrice();
        userMapper.updateUserMoney(userMoney, code);

        Integer res = orderMapper.updateOrderStatusByPay(id);
        if (res != 1) {
            throw new OrderException("支付失败");
        }
    }

    @Override
    public void deleteOrderbyId(Integer id, String code) {
        User user = userMapper.selectUserByCode(code);
        if (user.getStatus() != 1) {
            throw new UserException("用户状态被锁定,退订失败");
        }
        TextbookOrder order = orderMapper.selectOrderBookById(id);
        if (order.getStatus() == 2) {
            throw new OrderException("审核通过,退订失败");
        }
        Integer res = orderMapper.deleteOrderById(id);
        if (res != 1) {
            throw new OrderException("退订失败");
        }
        //教材入库
        Textbook textbook = textbookMapper.selectById(order.getBid());
        Integer bookNums = textbook.getInventory() + order.getNum();
        textbookMapper.updateInventory(textbook.getId(), bookNums);
        //账户退款
        Double userMoney = user.getMoney() + order.getPrice();
        userMapper.updateUserMoney(userMoney, code);
    }

    @Override
    public List<TextbookOrder> getAllOrder() {
        return orderMapper.selectOrder();
    }

    @Override
    public void updateOrderSuccess(Integer id) {
        //管理员无需验证账户状态
        orderMapper.updateOrderByIdTo2(id);
    }

    @Override
    public void updateOrderFailed(Integer id) {
        orderMapper.updateOrderByIdTo3(id);
    }

    @Override
    public void updateOrderBack(Integer id) {
        orderMapper.updateOrderByIdTo4(id);
    }

    @Override
    public void orderBook(Integer bid, String code, Integer num) {
        //根据bid获取图书信息
        Textbook textbook = textbookMapper.selectById(bid);
        //首先判断数量是否大于库存
        if (!(textbook.getInventory() - num >= 0)) {
            throw new OrderException("库存不足,订购失败");
        }
        //根据code获取教师信息
        User user = userMapper.selectUserByCode(code);
        //其次判断用户状态是否正常
        if (user.getStatus() != 1) {
            throw new UserException("用户状态被锁定,订购失败");
        }
        //实例化TextbookOrder对象,准备封装数据
        TextbookOrder order = new TextbookOrder();
        order.setBid(bid);
        order.setBname(textbook.getBname());
        order.setBimg(textbook.getImg());
        order.setCode(code);
        order.setName(user.getName());
        //获取当前的时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化后的当前时间
        String date = formatter.format(new Date(System.currentTimeMillis()));
        order.setOrderTime(date);
        order.setNum(num);
        order.setPrice(Double.valueOf(String.format("%.2f", num * textbook.getPrice())));
        order.setStatus(0);
        Integer res = orderMapper.insertTextbook(order);
        if (res != 1) {
            throw new OrderException("订购失败");
        }
        //订购成功后修改库存(数量-num)
        textbookMapper.updateInventory(bid, (textbook.getInventory() - num));
    }

    @Override
    public List<TextbookOrder> getAllTextBook(String code) {
        return orderMapper.selectOrderBookByCode(code);
    }

    @Override
    public List<TextbookOrder> getAllTextBookNormal(String code) {
        return orderMapper.selectOrderBookByNormal(code);
    }
}
