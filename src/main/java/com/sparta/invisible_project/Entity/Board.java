package com.sparta.invisible_project.Entity;


import com.sparta.invisible_project.Dto.BoardDto;
import com.sparta.invisible_project.Dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;
    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private  List<Comment> comments;

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
