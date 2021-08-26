package com.engure.spider.demo.bean;

import com.engure.spider.demo.Book0;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book1 {
    private List<Book0> bookList;
    private String domain;
}
