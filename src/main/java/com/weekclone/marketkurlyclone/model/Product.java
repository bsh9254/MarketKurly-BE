package com.weekclone.marketkurlyclone.model;


import com.weekclone.marketkurlyclone.dto.ProductRequestDto;
import com.weekclone.marketkurlyclone.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {


    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    @Id
    private Long id;

    @JoinColumn(name="category_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    ////
    @JoinColumn(name="member_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

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

    public void update(Category category, ProductRequestDto requestDto) {
        this.category = category;
        this.product_name = requestDto.getProduct_name();
        this.price = requestDto.getPrice();
        this.stock_status = requestDto.getStock_status();
        this.img_url = requestDto.getImg_url();
        this.detail = requestDto.getDetail();
    }

}
