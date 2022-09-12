package com.weekclone.marketkurlyclone.repository;

import com.weekclone.marketkurlyclone.model.Category;
import com.weekclone.marketkurlyclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
}