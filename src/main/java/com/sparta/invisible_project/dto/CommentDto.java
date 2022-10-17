package com.sparta.invisible_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private String body;

    public CommentDto(String body) {
        this.body = body;
    }

}
