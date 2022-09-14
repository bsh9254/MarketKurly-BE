package com.weekclone.marketkurlyclone.repository;


import com.weekclone.marketkurlyclone.model.Cart;
import com.weekclone.marketkurlyclone.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByMemberId(String memberId);
    Cart findByMemberIdAndProductId(String id, Long productId);
}
