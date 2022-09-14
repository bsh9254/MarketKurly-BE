package com.weekclone.marketkurlyclone.model;


import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Timestamped{


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;          // 구매자
    @ManyToOne  //OneToMany
    @JoinColumn(name = "product_id", nullable = false)       // 제품
    private Product product;
    @Column(nullable = false)       // 제품 개수
    private int product_count;
    @Column(nullable = false)       // 제품 가격
    private int total_price;
        //    @Column(nullable = false)       // 장바구니 체크여부
//    private boolean checked;
    public Cart(Member member, Product product, int totalPrice, int productCount) {
        this.member = member;
        this.product = product;
        this.total_price = totalPrice;
        this.product_count = productCount;
//        this.checked = true;
    }
}