package com.sparta.invisible_project.Service;

import com.sparta.invisible_project.Dto.CommentDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Board;
import com.sparta.invisible_project.Entity.Comment;
import com.sparta.invisible_project.Repository.BoardRepository;
import com.sparta.invisible_project.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public ResponseDto<?> getComment() {
        return ResponseDto.success(commentRepository.findAll());
    }

    @Transactional
    public ResponseDto<?> createComment(Long id, CommentDto dto) {
        Board board = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("board with such Id does not exist"));
        Comment comment = new Comment(dto,board);
        return ResponseDto.success(commentRepository.save(comment));
    }
    @Transactional
    public ResponseDto<?> updateComment(Long id, CommentDto dto){
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("no comment exist with following id "+ id));
        return ResponseDto.success(comment);
        
    }
}
