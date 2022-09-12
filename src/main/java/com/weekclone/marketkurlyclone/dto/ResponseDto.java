package com.weekclone.marketkurlyclone.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

  public boolean is_success;
  public T data;

  public static <T> ResponseDto<T> is_Success(T data)
  {
    return new ResponseDto<T>(true,data);
  }

}