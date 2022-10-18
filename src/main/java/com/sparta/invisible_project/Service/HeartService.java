package com.sparta.invisible_project.Service;

import com.sparta.invisible_project.Dto.HeartDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Heart;
import com.sparta.invisible_project.Entity.Member;
import com.sparta.invisible_project.Repository.BoardRepository;
import com.sparta.invisible_project.Repository.HeartRepository;
import com.sparta.invisible_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    public ResponseDto<?> heart( HeartDto dto,  Long boardId,UserDetailsImpl userDetails){
        dto.setBoard_id(boardId);
        System.out.println("boardId = " + boardId);
        dto.setMember_id(userDetails.getMember().getMember_id());
        System.out.println("userDetails = " + userDetails.getMember().getMember_id());
        Heart heart = new Heart(dto);
        heartRepository.save(heart);
        return ResponseDto.success(heartRepository.save(heart));
    }

}
