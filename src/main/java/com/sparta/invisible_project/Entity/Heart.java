package com.sparta.invisible_project.Entity;

import com.sparta.invisible_project.model.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean red_heart;

    @ManyToOne
    @Column(name = "members_name")
    private Members members;

    @ManyToOne
    @Column(name = "id")
    private Board board;

    public Heart(boolean red_heart, Members members, Board board) {
        this.red_heart = red_heart;
        this.members = members;
        this.board = board;
    }
}
