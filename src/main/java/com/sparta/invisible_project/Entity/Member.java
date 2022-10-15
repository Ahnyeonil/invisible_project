package com.sparta.invisible_project.Entity;


import com.sparta.invisible_project.Dto.MemberRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column
    private String username;

    @Column
    private String password;

    public Member(MemberRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
