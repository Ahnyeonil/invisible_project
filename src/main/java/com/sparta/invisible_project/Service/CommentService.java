package com.sparta.invisible_project.service;

import com.sparta.invisible_project.Entity.Board;
import com.sparta.invisible_project.Entity.Comment;
import com.sparta.invisible_project.dto.CommentDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.model.Members;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    // 댓글 목록 조회
    public List<ResponseDto<?>> findComments() {

        List<Comment> comment = commentRepository.findAll();

        return Collections.singletonList(ResponseDto.success(comment));
    }

    // 댓글 상세 조회
    public ResponseDto<?> findComment(long id) {

        Optional<Comment> comment = commentRepository.findById(id);

        return ResponseDto.success(comment);
    }

    // 댓글 등록
    public ResponseDto<?> createComment(CommentDto commentDto, long boardId) {

        Members members = new Members();
        Board board = boardRepository.findById(boardId).orElse(null);
        Comment comment = new Comment(commentDto.getBody(), board, members);

        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }

    // 댓글 수정
    public ResponseDto<?> updateComment(CommentDto commentDto, long boardId, long commentId) {

        // 인증 필요
        Comment updateComment = commentRepository.findById(commentId).orElse(null);
        updateComment.update(commentDto.getBody());

        return ResponseDto.success(updateComment);
    }

    // 댓글 삭제
    public ResponseDto<?> deleteComment(long boardId, long commentId) {

        // 인증 필요
        Comment deleteComment = commentRepository.findById(commentId).orElse(null);
        commentRepository.deleteById(commentId);

        return ResponseDto.success(deleteComment);
    }
}
