package com.weekclone.marketkurlyclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {

    private int product_count;
    private int total_price;

    public CartRequestDto (int productCount, int totalPrice) {

        this.product_count = productCount;
        this.total_price = totalPrice;
    }
}