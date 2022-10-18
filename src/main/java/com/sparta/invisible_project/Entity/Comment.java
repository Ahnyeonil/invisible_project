package com.sparta.invisible_project.Entity;

import com.sparta.invisible_project.Dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "comments")
@NoArgsConstructor
public class Comment {
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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="member_username")
    private Member member;

    public Comment(CommentDto dto, Board board){
        this.content = dto.getContent();
        this.board = board;
    }

//    public Comment(CommentDto dto) {
//        this.content = dto.getContent();
//    }

    public void updateComment(CommentDto dto){
        this.content = dto.getContent() == null ? this.content : dto.getContent();
    }

}
