package com.weekclone.marketkurlyclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {

    private int productCount;
    private int totalPrice;

    public CartRequestDto (int productCount, int totalPrice) {

        this.productCount = productCount;
        this.totalPrice = totalPrice;
    }
}