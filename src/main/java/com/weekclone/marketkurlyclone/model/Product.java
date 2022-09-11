package com.weekclone.marketkurlyclone.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;


    private Long category_id;

    private String member_uuid;

    private String product_name;

    private Long price;

    private Long stock_status;

    private String product_img;

    private String detail;

}
