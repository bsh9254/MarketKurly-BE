package com.weekclone.marketkurlyclone.service;

import com.weekclone.marketkurlyclone.dto.CategoryRequestDto;
import com.weekclone.marketkurlyclone.dto.CategoryResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.model.Category;
import com.weekclone.marketkurlyclone.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public ResponseDto<?> createCategory(CategoryRequestDto requestDto, HttpServletRequest request) {

        Category category = Category.builder()
                .category_name(requestDto.getCategory_name())
                .build();

        categoryRepository.save(category);

        return ResponseDto.success(requestDto.getCategory_name() + " 카테고리가 생성 되었습니다.");

    }

    public ResponseDto<?> getAllCategory() {

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDtoList.add(CategoryResponseDto.builder()
                            .category_name(category.getCategory_name())
                            .build());
        }

        return ResponseDto.success(categoryResponseDtoList);
    }

    public ResponseDto<?> updateCategory(CategoryRequestDto requestDto, Long categoryId, HttpServletRequest request) {

     Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        Category category = optionalCategory.get();
        category.update(requestDto.getCategory_name());

        return ResponseDto.success(requestDto.getCategory_name() + " 로 카테고리가 수정 되었습니다.");
    }

    public ResponseDto<?> deleteCategory(Long categoryId, HttpServletRequest request) {

      Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        Category category = optionalCategory.get();
        categoryRepository.delete(category);

        return ResponseDto.success("카테고리가 삭제 되었습니다.");

    }

}
