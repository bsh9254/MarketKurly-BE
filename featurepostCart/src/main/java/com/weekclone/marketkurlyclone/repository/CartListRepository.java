package com.weekclone.marketkurlyclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CartListRepository extends JpaRepository<CartList, Long> {

    List<CartList> findByMemberId(Long MemberId);
    Optional<CartList> findByProductIdAndMemberId(Long productId, Long MemberId);
}