package com.weekclone.marketkurlyclone.service;

import antlr.Token;
import com.weekclone.marketkurlyclone.dto.ProductResponseDto;
import com.weekclone.marketkurlyclone.dto.RecentProductResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.jwt.TokenProvider;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.model.Product;
import com.weekclone.marketkurlyclone.model.RecentProduct;
import com.weekclone.marketkurlyclone.repository.ProductRepository;
import com.weekclone.marketkurlyclone.repository.RecentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RecentProductRepository recentProductRepository;

    private final TokenProvider tokenProvider;
    public ResponseDto<?> getAllProduct()
    {
        List<Product> products=productRepository.findAll();
        List<ProductResponseDto> productResponseDtos= new ArrayList<>();

        for(Product product :products)
        {
            productResponseDtos.add(
                    new ProductResponseDto(product)
            );

        }

        return ResponseDto.is_Success(productResponseDtos);
    }

    public ResponseDto<?> getProduct(Long productId, HttpServletRequest request)
    {

        Product product=findPresentProduct(productId);
        Member member=validateMember(request);
        RecentProduct recentProduct=RecentProduct.builder()
                .product(product)
                .member(member)
                .build();
        ProductResponseDto productResponseDto=new ProductResponseDto(product);
        recentProductRepository.save(recentProduct);

        return ResponseDto.is_Success(productResponseDto);
    }
    public ResponseDto<?> getRecentProduct(HttpServletRequest request)
    {
        Member member=validateMember(request);
        List<RecentProduct> recentProducts=recentProductRepository.findAllByMember(member);
        List<RecentProductResponseDto> recentProductResponseDtos= new ArrayList<>();
        int index=0;
        int size=recentProducts.size(); //현재 recentproduct 데이터의 개수

        ////////queue로 구현 예정
        if(size>4)
        {
            index=size-5;
        }
        else{
            index=0;
        }

        for(int i=index;i<size;i++)
        {
            recentProductResponseDtos.add(RecentProductResponseDto.builder()
                            .id(recentProducts.get(i).getProduct().getId())
                            .product_name(recentProducts.get(i).getProduct().getProduct_name())
                            .img_url(recentProducts.get(i).getProduct().getImg_url())
                            .build());
        }
        ///데이터베이스 정리는 logout 할 때
        return ResponseDto.is_Success(recentProductResponseDtos);
    }



    public Product findPresentProduct(Long productId)
    {
        Optional<Product> optionalproduct= productRepository.findById(productId);

        return optionalproduct.orElse(null);
    }


    ///////구현

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

}
