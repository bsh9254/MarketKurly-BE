package com.weekclone.marketkurlyclone.service;

import com.weekclone.marketkurlyclone.dto.CategoryRequestDto;
import com.weekclone.marketkurlyclone.dto.CategoryResponseDto;
import com.weekclone.marketkurlyclone.dto.ProductResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.model.Category;
import com.weekclone.marketkurlyclone.model.Product;
import com.weekclone.marketkurlyclone.repository.CategoryRepository;
import com.weekclone.marketkurlyclone.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ResponseDto<?> createCategory(CategoryRequestDto requestDto, HttpServletRequest request) {

        Category category = Category.builder()
                .category_name(requestDto.getCategory_name())
                .build();

        categoryRepository.save(category);

        return ResponseDto.is_Success(requestDto.getCategory_name() + " 카테고리가 생성 되었습니다.");

    }

    public ResponseDto<?> getAllCategory() {

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDtoList.add(CategoryResponseDto.builder()
                            .category_name(category.getCategory_name())
                            .build());
        }

        return ResponseDto.is_Success(categoryResponseDtoList);
    }

    @Transactional
    public ResponseDto<?> updateCategory(CategoryRequestDto requestDto, Long categoryId, HttpServletRequest request) {
        Category category=findPresentCategory(categoryId);
        category.update(requestDto);
        return ResponseDto.is_Success(requestDto.getCategory_name() + " 로 카테고리가 수정 되었습니다.");
    }

    @Transactional
    public ResponseDto<?> deleteCategory(Long categoryId, HttpServletRequest request) {

        Category category=findPresentCategory(categoryId);
        categoryRepository.delete(category);

        return ResponseDto.is_Success("카테고리가 삭제 되었습니다.");

    }

    public ResponseDto<?> sortByCatetory(Long categoryId)
    {
        Category category=findPresentCategory(categoryId);

        List<Product> products=productRepository.findAllByCategory(category);
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for(Product product:products)
        {
            productResponseDtos.add(new ProductResponseDto(product));

        }

        return ResponseDto.is_Success(productResponseDtos);
    }

    public Category findPresentCategory(Long categoryId)
    {
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);

        return optionalCategory.orElse(null);
    }

}
