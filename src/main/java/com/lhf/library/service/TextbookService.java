package com.lhf.library.service;

import com.lhf.library.entity.Textbook;

import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:12
 */
public interface TextbookService {

    List<Textbook> getAllTextbook();

    Textbook getBookById(Integer id);

    void updateTextbookNum(Integer id,Integer num);

    List<Textbook> getCategoryTextbook(Integer categoryId);
    List<Textbook> getTextbookByParam(String param);

    void updateTextbook(Textbook textbook);

    void deleteBook(Integer id  );

    void addBook(Textbook textbook);
}