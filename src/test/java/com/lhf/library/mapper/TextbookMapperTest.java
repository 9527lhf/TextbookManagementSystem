package com.lhf.library.mapper;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:19
 */
@SpringBootTest
public class TextbookMapperTest {

    @Resource
    private DataSource dataSource;
    @Resource
    private TextbookMapper textbookMapper;

    @Test
    void selectById(){
        System.out.println(textbookMapper.selectById(1));
    }

    @Test
    void updateInventoryTest(){
        textbookMapper.updateInventory(3,790);
    }
    @Test
    void test1() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
