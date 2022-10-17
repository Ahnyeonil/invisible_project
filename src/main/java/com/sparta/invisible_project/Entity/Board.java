package com.sparta.invisible_project.Entity;

import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.model.Members;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;

    public Board (BoardDto boardDto, Members member){
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}