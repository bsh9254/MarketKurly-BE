package com.weekclone.marketkurlyclone.controller;

import com.weekclone.marketkurlyclone.dto.CartRequestDto;
import com.weekclone.marketkurlyclone.dto.CartResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private  final CartService cartService;

    // 장바구니에 담기
    @PostMapping("/auth/product/cart/{productId}")
    public ResponseDto<?> putCart(@PathVariable Long productId, @RequestBody CartRequestDto cartRequestDto, HttpServletRequest request) {
        return cartService.putCart(productId, cartRequestDto, request);
    }

    // 장바구니 조회
    @GetMapping("/auth/product/cart")
    public ResponseDto<?> showCart(HttpServletRequest request) {
        return cartService.showCart(request);
    }

    // 장바구니 삭제
    @DeleteMapping("/auth/product/cart/{productId}")
    public void deleteCart(@PathVariable Long productId, HttpServletRequest request) {
        cartService.deleteCart(productId, request);
    }

}