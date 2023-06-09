package com.lhf.library.mapper;

import com.lhf.library.entity.TextbookOrder;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.soap.Text;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 14:36
 */
@Mapper
public interface TextbookOrderMapper {
    /**
     * 添加教材订单
     *
     * @param textbookOrder 教材订单对象
     * @return 返回受影响的行数
     */
    Integer insertTextbook(TextbookOrder textbookOrder);

    List<TextbookOrder> selectOrderBookByCode(String code);
    List<TextbookOrder> selectOrderBookByNormal(String code);

    TextbookOrder selectOrderBookById(Integer id);
    Integer updateOrderStatusByPay(Integer id);

    Integer deleteOrderById(Integer id);
    Integer deleteOrderByBId(Integer bid);

    void updateOrderByIdTo2(Integer id);
    void updateOrderByIdTo3(Integer id);

    void updateOrderByIdTo4(Integer id);
    List<TextbookOrder> selectOrder();



}