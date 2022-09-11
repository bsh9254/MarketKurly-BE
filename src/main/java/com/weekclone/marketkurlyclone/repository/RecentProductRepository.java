package com.weekclone.marketkurlyclone.repository;

import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.model.RecentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecentProductRepository extends JpaRepository<RecentProduct, Long> {
        List<RecentProduct> findAllByMember(Member member);
}