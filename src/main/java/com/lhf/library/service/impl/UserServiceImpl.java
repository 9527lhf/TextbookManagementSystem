package com.lhf.library.service.impl;

import com.lhf.library.entity.User;
import com.lhf.library.mapper.UserMapper;
import com.lhf.library.service.UserService;
import com.lhf.library.service.ex.UserException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 17:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        //1.判断该用户名是否已被注册
        User userByCode = userMapper.selectUserByCode(user.getCode());
        if (userByCode != null) {
            throw new UserException("职工号被占用,注册失败");
        }
        //2.加密密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(user.getPassword(), salt);
        //3.插入数据库
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setRole(0);
        user.setStatus(1);
        //账户金额赠送10元
        user.setMoney(10.0);
        //默认为user
        user.setName("user");
        Integer res = userMapper.insertUser(user);
        if (res != 1) {
            throw new UserException("产生未知异常，注册失败");
        }
    }

    @Override
    public User login(String code, String password) {
        User userByCode = userMapper.selectUserByCode(code);
        if (userByCode == null) {
            throw new UserException("该教职号不存在");
        }
        if (userByCode.getStatus() == 0) {
            throw new UserException("被锁用户被锁定,登录失败");
        }
        String codePassword = userByCode.getPassword();
        String md5Password = getMD5Password(password, userByCode.getSalt());
        if (!codePassword.equals(md5Password)) {
            throw new UserException("密码错误");
        }
        //数据脱敏
        userByCode.setId(null);
        userByCode.setSalt(null);
        userByCode.setPassword(null);
        userByCode.setRole(null);
        userByCode.setStatus(null);
        return userByCode;
    }

    @Override
    public User getUserInfo(String code) {
        User userByCode = userMapper.selectUserByCode(code);
        if (userByCode == null || userByCode.getStatus() == 0) {
            throw new UserException("用户状态异常");
        }
        //数据脱敏
        userByCode.setPassword(null);
        userByCode.setSalt(null);
        return userByCode;
    }

    @Override
    public User updateUserInfo(User user) {
        //获取数据库中的user对象
        System.out.println(user);
        User userByCode = userMapper.selectUserByCode(user.getCode());
        System.out.println(userByCode);
        userByCode.setAcademy(user.getAcademy());
        userByCode.setBirthday(user.getBirthday());
        userByCode.setName(user.getName());
        userByCode.setClasses(user.getClasses());
        userByCode.setSex(user.getSex());
        Integer integer = userMapper.updateUserInfo(userByCode);
        if (integer != 1) {
            throw new UserException("产生未知异常");
        }
        userByCode.setPassword(null);
        userByCode.setSalt(null);
        userByCode.setRole(null);
        userByCode.setStatus(null);
        userByCode.setRole(null);
        return user;
    }

    @Override
    public void updatePassword(String tempPassword, String newPassword, String code) {
        User userByCode = userMapper.selectUserByCode(code);
        //获取数据库中的原密码
        String oldPassword = userByCode.getPassword();
        //获取数据库中的盐值
        String salt = userByCode.getSalt();
        String md5Password = getMD5Password(tempPassword, salt);
        if (!md5Password.equals(oldPassword)) {
            throw new UserException("原密码错误，修改失败");
        }
        if (getMD5Password(newPassword, salt).equals(oldPassword)) {
            throw new UserException("新密码与原密码相同,修改失败");
        }
        //生成新密码
        String newSalt = UUID.randomUUID().toString().toUpperCase();
        String newMd5Password = getMD5Password(newPassword, newSalt);
        userByCode.setPassword(newMd5Password);
        userByCode.setSalt(newSalt);
        Integer integer = userMapper.updatePassword(userByCode);
        if (integer != 1) {
            throw new UserException("产生未知异常");
        }
    }

    @Override
    public void rechargeMoney(String tempPassword, Double money, String code) {
        User userByCode = userMapper.selectUserByCode(code);
        if (userByCode.getStatus() != 1) {
            throw new UserException("用户状态被锁定,充值失败");
        }
        //获取数据库中的原密码
        String oldPassword = userByCode.getPassword();
        //获取数据库中的盐值
        String salt = userByCode.getSalt();
        String md5Password = getMD5Password(tempPassword, salt);
        if (!md5Password.equals(oldPassword)) {
            throw new UserException("密码错误，充值失败");
        }
        Integer res = userMapper.updateUserMoney(money+userByCode.getMoney(), code);
        if (res != 1) {
            throw new UserException("充值失败");
        }
    }

    //md5加密密码
    private String getMD5Password(String oldUsername, String salt) {
        for (int i = 0; i < 3; i++) {
            oldUsername = DigestUtils.md5DigestAsHex((salt + oldUsername + salt).getBytes()).toUpperCase();
        }
        return oldUsername;
    }

}