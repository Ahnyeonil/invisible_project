package com.sparta.invisible_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDto {
    private String title;
    private String content;

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}