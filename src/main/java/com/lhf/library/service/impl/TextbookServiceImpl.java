package com.lhf.library.service.impl;

import com.lhf.library.entity.Textbook;
import com.lhf.library.mapper.TextbookMapper;
import com.lhf.library.mapper.TextbookOrderMapper;
import com.lhf.library.service.TextbookService;
import com.lhf.library.service.ex.TextbookException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:13
 */
@Service
public class TextbookServiceImpl implements TextbookService {

    @Resource
    private TextbookMapper textbookMapper;

    @Resource
    private TextbookOrderMapper orderMapper;

    @Override
    public void updateTextbookNum(Integer id, Integer num) {
        textbookMapper.updateInventory(id, num);
    }

    @Override
    public List<Textbook> getCategoryTextbook(Integer categoryId) {
        return textbookMapper.selectTextbookByCategoryId(categoryId);
    }

    @Override
    public List<Textbook> getTextbookByParam(String param) {
        return textbookMapper.selectTextbookByParam(param);
    }

    @Override
    public void updateTextbook(Textbook textbook) {
        Integer integer = textbookMapper.updateTextbook(textbook);
        if (integer != 1) {
            throw new TextbookException("更新教材产生异常");
        }
    }

    @Override
    public void deleteBook(Integer id) {
        //删除所有关于该教材的订单
        orderMapper.deleteOrderByBId(id);
        //删除教材
        Integer res = textbookMapper.deleteBook(id);
        if (res != 1) {
            throw new TextbookException("删除教材产生异常");
        }
    }

    @Override
    public void addBook(Textbook textbook) {
        Integer res = textbookMapper.insertBook(textbook);
        if (res != 1) {
            throw new TextbookException("添加教材失败");
        }
    }

    @Override
    public List<Textbook> getAllTextbook() {
        return textbookMapper.selectAllTextbook();
    }

    @Override
    public Textbook getBookById(Integer id) {
        return textbookMapper.selectById(id);
    }
}