package com.sparta.invisible_project.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.invisible_project.Dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Component
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column
    private String username;
    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;
    @Column
    @JsonIgnore
    private String password;
//
    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();
//
//    public Member(String username, String password, List<Board> boards) {
//        this.username = username;
//        this.password = password;
//        this.boards = boards;
//    }

    public Member(MemberRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();

    }
}
