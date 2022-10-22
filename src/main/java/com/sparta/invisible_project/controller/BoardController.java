package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.dto.response.BoardResponseDto;
import com.sparta.invisible_project.security.MemberDetails;
import com.sparta.invisible_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    //return type 상의후 결정.

    @GetMapping("/boards")
    public List<ResponseDto<?>> findBoards() {
        return boardService.findBoards();
    }

    @GetMapping("/boards/detail/{id}")
        public BoardResponseDto findBoard(@PathVariable Long id) {
        return boardService.findBoard(id);
    }

    @PostMapping("/auth/boards/create")
    public ResponseDto<?> createBoard(@RequestBody BoardDto boardDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        return boardService.createBoard(boardDto, memberDetails.getMember());
    }

    @PutMapping("/auth/boards/update/{id}")
    public ResponseDto<?> updateBoard(@RequestBody BoardDto boardDto,  @PathVariable Long id, @AuthenticationPrincipal MemberDetails memberDetails) {
        return boardService.updateBoard(boardDto, id, memberDetails.getMember());
    }

    @DeleteMapping("/auth/boards/delete/{id}")
    public ResponseDto<?> deleteBoard( @PathVariable Long id, @AuthenticationPrincipal MemberDetails memberDetails) {
        return boardService.deleteBoard(id, memberDetails.getMember());
    }
}
