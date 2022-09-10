package com.weekclone.marketkurlyclone.repository;

import com.weekclone.marketkurlyclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}