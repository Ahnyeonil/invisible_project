package com.sparta.invisible_project.Controller;

import com.sparta.invisible_project.Dto.CommentDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Service.CommentService;
import com.sparta.invisible_project.Service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseDto<?> getComment(){
        return commentService.getComment();
    }

    @PostMapping("/comment/{id}")
    public ResponseDto<?> createComment(@PathVariable Long id, @RequestBody CommentDto dto, HttpServletResponse response){
        System.out.println(response.getHeader("Access_Token"));

        System.out.println(dto);
        System.out.println(id);

        return  commentService.createComment(id,dto);
    }

    @PutMapping("/comment/update/{id}")
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentDto dto){
        return commentService.updateComment(id,dto);
    }






}
