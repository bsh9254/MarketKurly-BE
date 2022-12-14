package com.weekclone.marketkurlyclone.service;

import com.weekclone.marketkurlyclone.dto.CartRequestDto;
import com.weekclone.marketkurlyclone.dto.CartResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.model.Cart;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.model.Product;
import com.weekclone.marketkurlyclone.repository.CartRepository;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import com.weekclone.marketkurlyclone.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final MemberService memberService;
    // 장바구니에 담기
    @Transactional
    public ResponseDto<?> putCart(Long productId, CartRequestDto cartRequestDto, HttpServletRequest request) {
        Member member = memberService.getMemberfromContext();
        // 장바구니 수량
        int productCount = cartRequestDto.getProduct_count();
        // 장바구니 가격
        int totalPrice = cartRequestDto.getTotal_price();
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("제품이 존재하지 않습니다.")
        );
        // 장바구니 수량 유효성검사
        if(productCount == 0) {
            throw new IllegalArgumentException("장바구니에 담을 수량을 정해주세요");
        }
        Cart cart = new Cart(member, product, productCount, totalPrice);
        cartRepository.save(cart);

        return ResponseDto.is_Success("장바구니에 추가되었습니다.");
    }
    // 카트 목록
    public ResponseDto<?> showCart(HttpServletRequest request) {
        Member member = memberService.getMemberfromContext();


        List<CartResponseDto> cartResponseDtoList = new ArrayList<>();
        List<Cart> cartList = cartRepository.findAllByMember(member);
        for(Cart eachCart : cartList) {
            CartResponseDto cartResponseDto = new CartResponseDto(eachCart.getProduct(), eachCart.getTotal_price(), eachCart.getProduct_count());
            cartResponseDtoList.add(cartResponseDto);
        }
        return ResponseDto.is_Success(cartResponseDtoList);
    }
    // 장바구니 삭제
    @Transactional
    public void deleteCart(Long productId, HttpServletRequest request) {
        Member member = memberService.getMemberfromContext();
        Product product=productService.findPresentProduct(productId);
        Cart cart = cartRepository.findByMemberAndProduct(member, product);
        if(cart == null) {
            throw new IllegalArgumentException("장바구니에서 상품을 찾을 수 없습니다.");
        }
        cartRepository.delete(cart);
    }
}