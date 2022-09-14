package com.weekclone.marketkurlyclone.dto;


import com.weekclone.marketkurlyclone.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private Long id;

    private String category_name;
    private String product_name;

    private int price;

    private int stock_status;

    private String imgUrl;

    private String detail;

    public ProductResponseDto(Product product)
    {
        this.id= product.getId();
        this.category_name=product.getCategory().getCategoryName();
        this.product_name= product.getProduct_name();
        this.price= product.getPrice();
        this.stock_status= product.getStock_status();
        this.imgUrl=product.getImg_url();
        this.detail=product.getDetail();
    }

}
