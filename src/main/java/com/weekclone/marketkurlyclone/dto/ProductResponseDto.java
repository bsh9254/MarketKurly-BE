package com.weekclone.marketkurlyclone.dto;


import com.weekclone.marketkurlyclone.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductResponseDto {

    private Long id;

    private String product_name;

    private int price;

    private int stock_status;

    private String img_url;

    private String detail;

    public ProductResponseDto(Product product)
    {
        this.id= product.getId();
        this.product_name= product.getProduct_name();
        this.price= product.getPrice();
        this.stock_status= product.getStock_status();
        this.img_url=product.getImg_url();
        this.detail=product.getDetail();
    }

}
