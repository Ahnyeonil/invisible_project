package com.sparta.invisible_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

    // 우선 다 갖다 넣을수있는 Dto.
    private T data;
    private Boolean success;
    private Error error;

    public static <T> ResponseDto<T> success(T data){
        return new ResponseDto<>(data,true,null);
    }

}