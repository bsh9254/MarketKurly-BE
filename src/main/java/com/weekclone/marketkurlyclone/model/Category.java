package com.weekclone.marketkurlyclone.model;

import com.weekclone.marketkurlyclone.dto.CategoryRequestDto;
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
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> products;

    public void update(CategoryRequestDto requestDto) {
        this.categoryName = requestDto.getCategory_name();
    }





}
