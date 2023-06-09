package com.lhf.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private String password;
    private String salt;
    private String code;
    private Integer sex;
    private String birthday;
    private String classes;
    private String academy;
    private Integer role;
    private Integer status;
    private Double money;
}