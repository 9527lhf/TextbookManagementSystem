package com.lhf.library.mapper;

import com.lhf.library.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 16:33
 */
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     *
     * @param user 封装的用户信息
     * @return 返回受影响行数
     */
    Integer insertUser(User user);

    /**
     * 查询用户
     *
     * @param name     查询参数(姓名)
     * @param password 查询参数(密码)
     * @return 返回用户
     */
    User selectUser(String code, String password);

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return 返回用户
     */
    User selectUserById(Integer id);

    /**
     * 查询用户
     * @param code 教职号
     * @return 返回用户
     */
    User selectUserByCode(String code);

    Integer updateUserInfo(User user);

    Integer updatePassword(User user);

    Integer updateUserMoney(Double money,String code);
}