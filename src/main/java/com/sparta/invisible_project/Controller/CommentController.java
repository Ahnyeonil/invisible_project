package com.sparta.invisible_project.Controller;

import com.sparta.invisible_project.Dto.CommentDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public ResponseDto<?> createComment(@PathVariable Long id, @RequestBody CommentDto dto){
        System.out.println(dto);
        System.out.println(id);

        return  commentService.createComment(id,dto);
    }


    @GetMapping("/comment")
    public ResponseDto<?> getComment(){
        return commentService.getComment();
    }


}
