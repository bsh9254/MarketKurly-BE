package com.weekclone.marketkurlyclone.controller;


import com.weekclone.marketkurlyclone.dto.ProductRequestDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productservice;

    @GetMapping("/product")
    public ResponseDto<?> getAllProduct()
    {
        return productservice.getAllProduct();
    }

    @GetMapping("/auth/product/{id}")
    public ResponseDto<?> getProduct(@PathVariable Long id, HttpServletRequest request)
    {
        return productservice.getProduct(id,request);
    }

    @GetMapping("/auth/product/recentProduct")
    public ResponseDto<?> getRecentProduct(HttpServletRequest request)
    {
        return productservice.getRecentProduct(request);
    }
    @PostMapping("/cms/product")
    public ResponseDto<?> createProduct(@RequestBody ProductRequestDto requestDto,
                                        HttpServletRequest request) {
        return productservice.createProduct(requestDto, request);
    }

    @PatchMapping("/cms/product/{productId}")
    public ResponseDto<?> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto requestDto,
                                        HttpServletRequest request) {
        return productservice.updateProduct(productId, requestDto, request);
    }

    @DeleteMapping("/cms/product/{productId}")
    public ResponseDto<?> deleteProduct(@PathVariable Long productId, HttpServletRequest request) {
        return productservice.deleteProduct(productId, request);
    }



}
