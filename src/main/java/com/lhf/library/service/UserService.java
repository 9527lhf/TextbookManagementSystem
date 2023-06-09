package com.lhf.library.service;

import com.lhf.library.entity.User;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 17:00
 */
public interface UserService{
    void register(User user);

    User login(String code, String password);

    User getUserInfo(String code);

    User updateUserInfo(User user);

    void updatePassword(String tempPassword,String newPassword,String code);

    void rechargeMoney(String password,Double money,String code);
}