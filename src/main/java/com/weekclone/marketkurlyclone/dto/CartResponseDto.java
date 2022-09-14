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
    private int totalPrice;
    private int productCount;

    public CartResponseDto(Product product, int totalPrice, int productCount) {
        this.id = product.getId();
        this.product_name = product.getProduct_name();
        this.img_url = product.getImg_url();
        this.price = product.getPrice();
        this.totalPrice = totalPrice;
        this.productCount = productCount;
    }
}