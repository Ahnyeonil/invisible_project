package com.sparta.invisible_project.Service;

import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;


    public ResponseDto<?> getComment() {
        return ResponseDto.success(commentRepository.findAll());
    }
}
