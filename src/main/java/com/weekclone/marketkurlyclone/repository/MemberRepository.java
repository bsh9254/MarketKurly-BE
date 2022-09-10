package com.weekclone.marketkurlyclone.repository;


import com.weekclone.marketkurlyclone.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    Member findMemberByEmail(String email);
    boolean existsByPassword(String password);
}