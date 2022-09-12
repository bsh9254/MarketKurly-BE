package com.weekclone.marketkurlyclone.repository;


import com.weekclone.marketkurlyclone.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByMemberId(String memberId);
    Member findMemberByEmail(String email);
    boolean existsByPassword(String password);

}