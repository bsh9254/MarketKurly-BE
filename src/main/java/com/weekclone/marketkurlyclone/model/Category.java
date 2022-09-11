package com.weekclone.marketkurlyclone.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String category_name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> products;

    public void update(String category_name) {
        this.category_name = category_name;
    }





}
