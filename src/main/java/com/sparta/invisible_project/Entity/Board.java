package com.sparta.invisible_project.Entity;


import com.sparta.invisible_project.Dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String title;

    public Board (BoardDto boardDto){
        this.content = boardDto.getContent();
        this.title = boardDto.getTitle();
    }

}
