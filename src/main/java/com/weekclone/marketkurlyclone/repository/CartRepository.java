package com.weekclone.marketkurlyclone.repository;


import com.weekclone.marketkurlyclone.model.Cart;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByMember(Member member);
    Cart findByMemberAndProduct(Member member, Product product);
}
