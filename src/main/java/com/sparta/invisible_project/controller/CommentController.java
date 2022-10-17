package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.dto.CommentDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    //return type 상의후 결정.

    @GetMapping("/comments")
    public List<ResponseDto<?>> findComments() {
        return commentService.findComments();
    }

    @GetMapping("/comments/detail/{id}")
    public ResponseDto<?> findComment(@RequestParam long id) {
        return commentService.findComment(id);
    }

    @PostMapping("/auth/{board-id}/comments/create")
    public ResponseDto<?> createComment(@RequestBody CommentDto commentDto, @PathVariable("board-id") long boardId) {
        return commentService.createComment(commentDto, boardId);
    }

    @PutMapping("/auth/{board-id}/comments/update/{comment-id}")
    public ResponseDto<?> updateComment(@RequestBody CommentDto commentDto, @PathVariable("board-id") long boardId, @PathVariable("comment-id") long commentId) {
        return commentService.updateComment(commentDto, boardId, commentId);
    }

    @DeleteMapping("/auth/{board-id}/comments/delete/{comment-id}")
    public ResponseDto<?> deleteComment(@PathVariable("board-id") long boardId, @PathVariable("comment-id") long commentId) {
        return commentService.deleteComment(boardId, commentId);
    }
}
