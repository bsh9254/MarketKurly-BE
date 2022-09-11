package com.weekclone.marketkurlyclone.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {


    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    @Id
    private Long id;

    @Column(nullable = false)
    private String category_name;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock_status;

    @Column(nullable = false)
    private String img_url;

    @Column(nullable = false)
    private String detail;



}
