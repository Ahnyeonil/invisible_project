package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.dto.CommentDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.security.MemberDetails;
import com.sparta.invisible_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseDto<?> findComment(@PathVariable Long id) {
        return commentService.findComment(id);
    }

    @PostMapping("/auth/{board-id}/comments/create")
    public ResponseDto<?> createComment(@RequestBody CommentDto commentDto,
                                        @PathVariable("board-id") Long boardId,
                                        @AuthenticationPrincipal MemberDetails memberDetails) {

        return commentService.createComment(commentDto, boardId, memberDetails.getMember());
    }

    @PutMapping("/auth/{board-id}/comments/update/{comment-id}")
    public ResponseDto<?> updateComment(@RequestBody CommentDto commentDto,
                                        @PathVariable("board-id") Long boardId,
                                        @PathVariable("comment-id") Long commentId,
                                        @AuthenticationPrincipal MemberDetails memberDetails) {

        return commentService.updateComment(commentDto, boardId, commentId, memberDetails.getMember());
    }

    @DeleteMapping("/auth/{board-id}/comments/delete/{comment-id}")
    public ResponseDto<?> deleteComment(@PathVariable("board-id") Long boardId,
                                        @PathVariable("comment-id") Long commentId,
                                        @AuthenticationPrincipal MemberDetails memberDetails) {

        return commentService.deleteComment(boardId, commentId, memberDetails.getMember());
    }
}
