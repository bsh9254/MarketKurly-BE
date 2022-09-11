package com.weekclone.marketkurlyclone.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecentProduct {


    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    @Id
    private Long id;

    //@OneToMany(mappedBy = "post",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)

    @JoinColumn(name = "product_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)///이거 정확히 알기
    private Product product;

    @JoinColumn(name="member_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
