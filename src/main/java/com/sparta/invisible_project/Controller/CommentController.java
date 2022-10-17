package com.sparta.invisible_project.Controller;

import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;


    @GetMapping()
    public ResponseDto<?> getComment(){
        return commentService.getComment();
    }


}
