package com.sparta.invisible_project.service;

import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Heart;
import com.sparta.invisible_project.model.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.HeartRepository;
import com.sparta.invisible_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    public void heart(String membername, long boardId) {

        Member member = memberRepository.findByUsername(membername).orElse(null);
        Board board = boardRepository.findById(boardId).orElse(null);

        if(heartRepository.existsByBoardAndMember(boardId, membername)){
            heartRepository.deleteByBoardAndMember(boardId, membername);
        } else {
            Heart heart = new Heart(member, board);
            heartRepository.save(heart);
        }
    }
}
