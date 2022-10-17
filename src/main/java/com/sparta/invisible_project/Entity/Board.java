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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Board (BoardDto boardDto, Member member){
        this.content = boardDto.getContent();
        this.title = boardDto.getTitle();
        this.member = member;
    }
    public void boardUpdate(BoardDto dto){
        this.content = dto.getContent() == null ? this.content : dto.getContent();
        this.title = dto.getTitle() == null ? this.title : dto.getTitle();
    }



}
