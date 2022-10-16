package com.sparta.invisible_project.Entity;

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

    //Board 에서 붙일지 comment 에서 붙일지는 향후 결정. 단방향/양방향
    //거기에 맞춰 constructor 사용 예정.

}
