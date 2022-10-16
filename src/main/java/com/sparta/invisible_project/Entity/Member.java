package com.sparta.invisible_project.Entity;


import com.sparta.invisible_project.Dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

    @Column
    private String password;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Board> boards;

    public Member(MemberRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
