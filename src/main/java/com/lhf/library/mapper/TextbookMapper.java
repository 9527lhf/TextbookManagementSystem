package com.lhf.library.mapper;

import com.lhf.library.entity.Textbook;
import com.lhf.library.entity.TextbookOrder;

import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:15
 */
public interface TextbookMapper {

    Textbook selectById(Integer id);
    List<Textbook> selectAllTextbook();

    void updateInventory(Integer id,Integer num);

    List<Textbook> selectTextbookByCategoryId(Integer categoryId);

    List<Textbook> selectTextbookByParam(String param);

    Integer updateTextbook(Textbook textbook);

    Integer deleteBook(Integer id);

    Integer insertBook(Textbook textbook);
}