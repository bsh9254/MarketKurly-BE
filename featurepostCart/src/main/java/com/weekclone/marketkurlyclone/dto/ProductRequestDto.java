package com.weekclone.marketkurlyclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private Long category_id;

    private String product_name;

    private String seller_name;

    private int price;

    private int stock_status;

    private String img_url;

    private String detail;
}