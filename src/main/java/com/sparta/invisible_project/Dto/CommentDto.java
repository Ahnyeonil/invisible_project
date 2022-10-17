package com.sparta.invisible_project.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private String content;
    public CommentDto(String content){
        this.content = content;
    }



}
