package com.lhf.library.controller;

import com.lhf.library.common.JsonResult;
import com.lhf.library.entity.Textbook;
import com.lhf.library.service.impl.TextbookServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:32
 */
@RestController
@RequestMapping("/textbook")
public class TextbookController extends ExceptionController {
    @Resource
    private TextbookServiceImpl textbookService;

    /**
     * 获取所有教材
     *
     * @return json(list)
     */
    @RequestMapping("/getAllBooks")
    public JsonResult<List<Textbook>> getAllBooks() {
        List<Textbook> textbookList = textbookService.getAllTextbook();
        return new JsonResult<>(200, textbookList);
    }

    /**
     * 通过id获取教材
     *
     * @param id
     * @return json(textbook)
     */
    @RequestMapping("/getBookById")
    public JsonResult<Void> getBookById(Integer id) {
        return new JsonResult<>(200, textbookService.getBookById(id));
    }


    /**
     * 根据categoryId进行分类查询
     *
     * @param categoryId
     * @return json(list)
     */
    @RequestMapping("/category")
    public JsonResult<Void> category(Integer categoryId) {
        List<Textbook> res = textbookService.getCategoryTextbook(categoryId);
        return new JsonResult<>(200, res);
    }

    /**
     * 查询教材
     *
     * @param param 关键字
     * @return json(textbook)
     */
    @RequestMapping("/querybook")
    public JsonResult<Void> querybook(String param) {
        List<Textbook> res = textbookService.getTextbookByParam(param);
        return new JsonResult<>(200, res);
    }

    /**
     * 更新教材信息
     *
     * @param textbook 教材
     * @return json
     */
    @RequestMapping("/updatebook")
    public JsonResult<Void> updatebook(Textbook textbook) {
        textbookService.updateTextbook(textbook);
        return new JsonResult<>(200, "更新教材信息成功");
    }

    /**
     * 删除教材
     *
     * @param id
     * @return json
     */
    @RequestMapping("/deleteBook")
    public JsonResult<Void> deleteBook(Integer id, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        textbookService.deleteBook(id);
        return new JsonResult<>(200, "删除教材成功");
    }

    /**
     * 添加教材
     *
     * @param textbook 教材
     * @return json
     */
    @RequestMapping("/addBook")
    public JsonResult<Void> addBook(Textbook textbook) {
        System.out.println(textbook);
        textbookService.addBook(textbook);
        return new JsonResult<>(200, "添加教材成功");
    }
}
