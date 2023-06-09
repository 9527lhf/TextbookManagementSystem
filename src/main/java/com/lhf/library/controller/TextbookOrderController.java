package com.lhf.library.controller;

import com.lhf.library.common.JsonResult;
import com.lhf.library.entity.TextbookOrder;
import com.lhf.library.service.ex.PermissionsException;
import com.lhf.library.service.impl.TextbookOrderServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 15:54
 */
@RestController
@RequestMapping("/order")
public class TextbookOrderController extends ExceptionController {

    @Resource
    private TextbookOrderServiceImpl orderService;

    /**
     * 订购教材
     *
     * @param id      教材id
     * @param num     订购数量
     * @param request 请求对象(获取session中的code)
     * @return json
     */
    @RequestMapping("/orderbook")
    public JsonResult<Void> orderbook(Integer id, Integer num, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        orderService.orderBook(id, code, num);
        return new JsonResult<>(200, "订购成功");
    }

    /**
     * 获取订购教材订单（未退订+待支付）
     *
     * @param request 请求对象
     * @return json
     */
    @RequestMapping("/getOrderBook")
    public JsonResult<Void> getOrderBook(HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        List<TextbookOrder> res = orderService.getAllTextBookNormal(code);
        return new JsonResult<>(200, res);
    }

    /**
     * 获取订购教材订单记录(所有)
     *
     * @param request 请求对象
     * @return json
     */
    @RequestMapping("/getAllRecords")
    public JsonResult<Void> getAllRecords(HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        List<TextbookOrder> res = orderService.getAllTextBook(code);
        return new JsonResult<>(200, res);
    }



    /**
     * 支付教材订单金额
     *
     * @param request 请求对象
     * @param id
     * @return json
     */
    @RequestMapping("/pay")
    public JsonResult<Void> pay(HttpServletRequest request, Integer id) {
        String code = (String) request.getSession().getAttribute("code");
        orderService.updateOrderStatusByPay(code, id);
        return new JsonResult<>(200, "支付成功");
    }

    /**
     * 退订教材
     *
     * @param id
     * @return json
     */
    @RequestMapping("/orderback")
    public JsonResult<Void> orderback( Integer id) {
        orderService.updateOrderBack(id);
        return new JsonResult<>(200, "退订成功");
    }

    /**
     * 获取所有教材订单
     *
     * @param request
     * @return json(list)
     */
    @RequestMapping("/getOrders")
    public JsonResult<Void> getOrderBookById(HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        if (!code.equals("admin")) {
            throw new PermissionsException("无管理员权限");
        }
        List<TextbookOrder> res = orderService.getAllOrder();
        return new JsonResult<>(200, res);
    }

    /**
     * 管理员操作:审核通过
     *
     * @param id
     * @return json
     */
    @RequestMapping("/orderSuccess")
    public JsonResult<Void> orderSuccess(Integer id) {
        orderService.updateOrderSuccess(id);
        return new JsonResult<>(200, "审核通过");
    }

    /**
     * 管理员操作:审核不通过
     *
     * @param id
     * @return json
     */
    @RequestMapping("/orderFailed")
    public JsonResult<Void> orderFailed(Integer id) {
        orderService.updateOrderFailed(id);
        return new JsonResult<>(200, "操作成功");
    }


}