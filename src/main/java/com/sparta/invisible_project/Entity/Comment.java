package com.sparta.invisible_project.Entity;

import com.sparta.invisible_project.Dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    //add member info in here

    public Comment(CommentDto dto, Board board){
        this.content = dto.getContent();
        this.board = board;
    }

    public void updateComment(CommentDto dto){
        this.content = dto.getContent() == null ? this.content : dto.getContent();
    }

}
