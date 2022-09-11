package com.weekclone.marketkurlyclone.controller;

import com.weekclone.marketkurlyclone.dto.CategoryRequestDto;
import com.weekclone.marketkurlyclone.dto.CategoryResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cms/post/category", method = RequestMethod.POST)
    public ResponseDto<?> createCategory(@RequestBody CategoryRequestDto requestDto,
                                         HttpServletRequest request) {
        return categoryService.createCategory(requestDto, request);
    }

    @RequestMapping(value = "/cms/post/category", method = RequestMethod.GET)
    public ResponseDto<?> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @RequestMapping(value = "/cms/post/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseDto<?> updateCategory(@RequestBody CategoryRequestDto requestDto,
                                         @PathVariable Long categoryId, HttpServletRequest request) {
        return categoryService.updateCategory(requestDto, categoryId, request);
    }

    @RequestMapping(value = "/cms/post/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteCategory(@PathVariable Long categoryId, HttpServletRequest request) {
        return categoryService.deleteCategory(categoryId, request);
    }






}





