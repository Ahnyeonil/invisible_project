package com.sparta.invisible_project.Controller;

import com.sparta.invisible_project.Dto.BoardDto;
import com.sparta.invisible_project.Dto.LoginResponseDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Member;
import com.sparta.invisible_project.Service.BoardService;
import com.sparta.invisible_project.Service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/boards")
    public ResponseDto<?> createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(boardDto.getContent());
        System.out.println(userDetails.getMember());
        return boardService.createBoard(boardDto, userDetails.getMember());
    }

}
