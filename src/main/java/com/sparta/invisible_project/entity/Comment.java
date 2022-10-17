package com.sparta.invisible_project.entity;

import com.sparta.invisible_project.model.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    @NotBlank
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_name")
    private Members members;

    //Board 에서 붙일지 comment 에서 붙일지는 향후 결정. 단방향/양방향
    //거기에 맞춰 constructor 사용 예정.
    public Comment(String body, Board board, Members memebrs){
        this.body = body;
        this.board = board;
        this.members = memebrs;
    }

    public void update(String body) {
        this.body = body;
    }

}