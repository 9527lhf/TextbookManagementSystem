package com.lhf.library.service;

import com.lhf.library.entity.TextbookOrder;

import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 15:37
 */
public interface TextbookOrderService {

    void orderBook(Integer bid,String code,Integer num);

    List<TextbookOrder> getAllTextBook(String code);
    List<TextbookOrder> getAllTextBookNormal(String code);


    void updateOrderStatusByPay(String code,Integer id);

    void deleteOrderbyId(Integer id,String code);

    List<TextbookOrder> getAllOrder();

    void updateOrderSuccess(Integer id);
    void updateOrderFailed(Integer id);

    void updateOrderBack(Integer id);


}