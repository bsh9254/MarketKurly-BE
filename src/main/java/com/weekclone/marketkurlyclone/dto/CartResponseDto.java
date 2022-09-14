package com.weekclone.marketkurlyclone.dto;

import com.weekclone.marketkurlyclone.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {

    private Long id;
    private String product_name;
    private String img_url;
    private int price;
    private int total_price;
    private int product_count;

    public CartResponseDto(Product product, int totalPrice, int productCount) {
        this.id = product.getId();
        this.product_name = product.getProduct_name();
        this.img_url = product.getImg_url();
        this.price = product.getPrice();
        this.total_price = totalPrice;
        this.product_count = productCount;
    }
}