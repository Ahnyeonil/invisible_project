package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.service.BoardService;
import lombok.RequiredArgsConstructor;
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
        public ResponseDto<?> findBoard(@RequestParam long id) {
        return boardService.findBoard(id);
    }

    @PostMapping("/auth/boards/create")
    public ResponseDto<?> createBoard(@RequestBody BoardDto boardDto) {
        return boardService.createBoard(boardDto);
    }

    @PutMapping("/auth/boards/update")
    public ResponseDto<?> updateBoard(@RequestBody BoardDto boardDto, long id) {
        return boardService.updateBoard(boardDto, id);
    }

    @DeleteMapping("/auth/boards/delete")
    public ResponseDto<?> deleteBoard(long id) {
        return boardService.deleteBoard(id);
    }
}
