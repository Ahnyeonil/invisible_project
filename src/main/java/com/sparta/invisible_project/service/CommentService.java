package com.sparta.invisible_project.service;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Comment;
import com.sparta.invisible_project.dto.CommentDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.model.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public ResponseDto<?> findComment(Long id) {

        Optional<Comment> comment = commentRepository.findById(id);

        return ResponseDto.success(comment);
    }

    // 댓글 등록
    public ResponseDto<?> createComment(CommentDto commentDto, Long boardId, Member member) {

        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment comment = new Comment(commentDto.getBody(), board, member);

        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }

    // 댓글 수정
    @Transactional
    public ResponseDto<?> updateComment(CommentDto commentDto, Long boardId, Long commentId, Member member) {

        Comment updateComment = commentRepository.findById(commentId).orElseThrow();

        if(!updateComment.getMember().getMember_id().equals(member.getMember_id())){
            return ResponseDto.fail(member.getUsername(), "댓글 작성자와 로그인한 사용자가 일치하지 않습니다. (UPDATE)");
        }

        updateComment.update(commentDto.getBody());

        return ResponseDto.success(updateComment);
    }

    // 댓글 삭제
    @Transactional
    public ResponseDto<?> deleteComment(Long boardId, Long commentId, Member member) {

        Comment deleteComment = commentRepository.findById(commentId).orElseThrow();

        if(!deleteComment.getMember().getMember_id().equals(member.getMember_id())){
            return ResponseDto.fail(member.getUsername(), "댓글 작성자와 로그인한 사용자가 일치하지 않습니다. (DELETE)");
        }

        commentRepository.deleteById(commentId);

        return ResponseDto.success(deleteComment);
    }
}
