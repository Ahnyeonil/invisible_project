package com.sparta.invisible_project.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private String msg;
    private int statusCode;

    public ResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
