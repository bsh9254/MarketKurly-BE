package com.weekclone.marketkurlyclone.service;

import antlr.Token;
import com.weekclone.marketkurlyclone.dto.ProductRequestDto;
import com.weekclone.marketkurlyclone.dto.ProductResponseDto;
import com.weekclone.marketkurlyclone.dto.RecentProductResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.jwt.TokenProvider;
import com.weekclone.marketkurlyclone.model.*;
import com.weekclone.marketkurlyclone.repository.ImgfileRepository;
import com.weekclone.marketkurlyclone.repository.ProductRepository;
import com.weekclone.marketkurlyclone.repository.RecentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RecentProductRepository recentProductRepository;
    private final CategoryService categoryService;
    private final FileUploadService fileUploadService;
    private final ImgfileRepository imgfileRepository;

    private final MemberService memberService;
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
        Member member=memberService.getMemberfromContext();

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
        Member member=memberService.getMemberfromContext();

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
     /////////////////////다시

    @Transactional
    public ResponseDto<?> createProduct(@RequestBody ProductRequestDto requestDto, HttpServletRequest request) {
        Category category=categoryService.findPresentCategory(requestDto.getCategory_id());
        Member member=memberService.getMemberfromContext();
        Product product = Product.builder()
                .category(category)
                .member(member)
                .product_name(requestDto.getProduct_name())
                .price(requestDto.getPrice())
                .stock_status(requestDto.getStock_status())
                .img_url(requestDto.getImg_url())
                .detail(requestDto.getDetail())
                .build();

        productRepository.save(product);

        return ResponseDto.is_Success(requestDto.getProduct_name() + " 상품이 등록 되었습니다.");

    }
    @Transactional
    public ResponseDto<?> addImg(@RequestPart MultipartFile multipartFile,HttpServletRequest request)
    {
        Imgfile imgfile=Imgfile.builder()
                .path(fileUploadService.uploadImage(multipartFile))
                .build();

        imgfileRepository.save(imgfile);


        return ResponseDto.is_Success(imgfile.getPath());
    }



    @Transactional
    public ResponseDto<?> updateProduct(@PathVariable Long productId,
                                        @RequestBody ProductRequestDto requestDto, HttpServletRequest request) {

        Product product=findPresentProduct(productId);
        Category category=categoryService.findPresentCategory(requestDto.getCategory_id());

        product.update(category,requestDto);



        return ResponseDto.is_Success(requestDto.getProduct_name() + " 로 상품이 수정 되었습니다.");

    }

    @Transactional
    public ResponseDto<?> deleteProduct(@PathVariable Long productId, HttpServletRequest request) {

        Product product = findPresentProduct(productId);
        productRepository.delete(product);

        return ResponseDto.is_Success("상품이 삭제 되었습니다.");

    }
    public Product findPresentProduct(Long productId)
    {
        Optional<Product> optionalproduct= productRepository.findById(productId);

        return optionalproduct.orElse(null);
    }


}
