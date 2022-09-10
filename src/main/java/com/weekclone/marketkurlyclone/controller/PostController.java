package com.weekclone.marketkurlyclone.controller;


import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final ProductService productservice;

    @GetMapping("")
    public ResponseDto<?> getAllProduct()
    {
        return productservice.getAllProduct();
    }

    @GetMapping("{productId}")
    public ResponseDto<?> getProduct(@PathVariable Long productId, HttpServletRequest request)
    {
        return productservice.getProduct(productId,request);
    }

    @GetMapping("")
    public ResponseDto<?> getRecentProduct(HttpServletRequest request)
    {
        return productservice.getRecentProduct(request);
    }


}
