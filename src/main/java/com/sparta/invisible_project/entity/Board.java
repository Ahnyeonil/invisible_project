package com.sparta.invisible_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    public Board (BoardDto boardDto, Member member){
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}