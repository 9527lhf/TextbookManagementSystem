package com.lhf.library.mapper;

import com.lhf.library.entity.TextbookOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 14:41
 */
@SpringBootTest
public class TextbookOrderMapperTest {
    @Resource
    private TextbookOrderMapper orderMapper;

    @Test
    void insertTextbook(){
        TextbookOrder order = new TextbookOrder();
        order.setBname("张三");
        order.setOrderTime("12.123");
        System.out.println(orderMapper.insertTextbook(order));
    }

    @Test
    void selectOrderBookByIdTest(){
        System.out.println(orderMapper.selectOrderBookByCode("123"));
    }
}