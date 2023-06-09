package com.lhf.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Textbook implements Serializable {
    private Integer id;
    private String bname;
    private String author;
    private String publisher;
    private String publishTime;
    private Integer pagination;
    private String description;
    private String img;
    private Double price;
    private String isbn;
    private Integer categoryId;
    private Integer inventory;
}