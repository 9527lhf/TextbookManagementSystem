package com.lhf.library.controller;

import com.lhf.library.common.JsonResult;
import com.lhf.library.service.impl.UserServiceImpl;
import com.lhf.library.utils.IdentifyCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lhf.library.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 16:37
 */
@RestController
@RequestMapping("/users")
public class UserController extends ExceptionController {
    @Resource
    private UserServiceImpl userService;
    //验证码
    private String localIdentifyCode = null;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return json
     */
    @RequestMapping("/register")
    public JsonResult<Void> register(User user) {
        userService.register(user);
        return new JsonResult<>(200, "注册成功");
    }

    /**
     * 用户登录
     *
     * @param code     教职号
     * @param password 密码
     * @param request  request对象
     * @return json(user)
     */
    @RequestMapping("/login")
    public JsonResult<Void> login(String code, String password, String identifyCode, HttpServletRequest request) {
        if (identifyCode == null) {
            return new JsonResult<>(400, "验证码为空");
        }

        if (!identifyCode.toUpperCase().equals(localIdentifyCode)) {
            return new JsonResult<>(400, "验证码错误");
        }
        User loginUser = userService.login(code, password);
        request.getSession().setAttribute("code", loginUser.getCode());
        return new JsonResult<>(200, "登录成功", loginUser);
    }

    /**
     * 获取验证码图片
     *
     * @param response
     */
    @RequestMapping("/identifyImage")
    public void identifyImage(HttpServletResponse response) {
        //创建随机验证码
        IdentifyCodeUtils utils = new IdentifyCodeUtils();
        String identifyCode = utils.getIdentifyCode();
        //session存入验证码
        //session.setAttribute("identifyCode", identifyCode);
        localIdentifyCode = identifyCode;
        System.out.println("服务器生成的验证码：" + identifyCode);
        //根据验证码创建图片
        BufferedImage identifyImage = utils.getIdentifyImage(identifyCode);
        //回传给前端
        utils.responseIdentifyImg(identifyImage, response);
    }

    /**
     * 获取用户信息
     *
     * @param code 教职号
     * @return json(user)
     */
    //获取用户信息
    @RequestMapping("/getUserInfo")
    JsonResult<User> getUserInfo(String code) {
        User userInfo = userService.getUserInfo(code);
        return new JsonResult<>(200, userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return json(user)
     */
    @RequestMapping("/updateUserInfo")
    JsonResult<Void> updateUserInfo(User user, HttpServletRequest request) {
        user.setCode((String) request.getSession().getAttribute("code"));
        User res = userService.updateUserInfo(user);
        return new JsonResult<>(200, res);
    }

    /**
     * 修改密码
     *
     * @param newPassword 用户新密码
     * @return json
     */
    @RequestMapping("/updatePassword")
    public JsonResult<Void> updatePassword(String tempPassword, String newPassword, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        userService.updatePassword(tempPassword, newPassword, code);
        return new JsonResult<>(200, "修改密码成功");
    }

    /**
     * 账户充值
     *
     * @param password 用户密码
     * @param money    充值金额
     * @param request  请求对象
     * @return json
     */
    @RequestMapping("/recharge")
    public JsonResult<Void> recharge(String password, Double money, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        userService.rechargeMoney(password, money, code);
        return new JsonResult<>(200, "账户充值成功");
    }

    /**
     * 校验用户是否为管理员
     *
     * @param request 请求对象
     * @return json
     */
    @RequestMapping("/verifyUser")
    public JsonResult<Void> verifyUser(HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        if (!code.equals("admin")) {
            return new JsonResult<>(5002, "无管理员权限");
        }
        return new JsonResult<>(200);
    }


}