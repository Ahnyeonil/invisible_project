package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart/{member-id}/{board-id}")
    public void clickHeart(@PathVariable("member-id") String membername, @PathVariable("board-id") long boardId) {
        heartService.heart(membername, boardId);
    }
}
