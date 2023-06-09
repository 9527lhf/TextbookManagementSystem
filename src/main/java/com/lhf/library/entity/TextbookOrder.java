package com.lhf.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @Author lihuifeng
 * @Create 2023/5/31 8:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TextbookOrder {
    private Integer id;
    private Integer bid;
    private String bname;
    private String bimg;
    private String code;
    private String name;
    private String orderTime;
    private Integer num;
    private Double price;
    private Integer status;

}