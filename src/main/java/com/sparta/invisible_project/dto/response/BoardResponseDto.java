package com.sparta.invisible_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;

    private Long commentCount;

    private Long heartCount;

    public BoardResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}