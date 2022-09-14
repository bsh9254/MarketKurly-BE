package com.weekclone.marketkurlyclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecentProductResponseDto {



    private Long id;
    private String product_name;
    private String img_url;



}