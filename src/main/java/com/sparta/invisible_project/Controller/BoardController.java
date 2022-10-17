package com.sparta.invisible_project.Controller;

import com.sparta.invisible_project.Dto.BoardDto;
import com.sparta.invisible_project.Dto.LoginResponseDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Member;
import com.sparta.invisible_project.Service.BoardService;
import com.sparta.invisible_project.Service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/boards/post")
    public ResponseDto<?> createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(boardDto.getContent());
        System.out.println(userDetails.getMember());
        return boardService.createBoard(boardDto, userDetails.getMember());
    }

    @GetMapping("/boards")
    public ResponseDto<?> getBoard(){
        return boardService.getBoard();
    }

    @DeleteMapping("/boards/delete/{id}")
    public ResponseDto<?> deleteBoard(@PathVariable Long id){
        return boardService.deleteBoard(id);
    }
    @PutMapping("/boards/update/{id}")
    public ResponseDto<?> updateBoard(@PathVariable Long id,@RequestBody BoardDto dto){
        return boardService.updateBoard(dto, id);
    }

}
