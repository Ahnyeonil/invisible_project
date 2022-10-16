package com.sparta.invisible_project.Service;

import com.sparta.invisible_project.Dto.BoardDto;
import com.sparta.invisible_project.Dto.LoginResponseDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Board;
import com.sparta.invisible_project.Entity.Member;
import com.sparta.invisible_project.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

//    private final UserDetailsImpl userDetails;

    public ResponseDto<?> createBoard(BoardDto boardDto, Member member){
//        System.out.println(userDetails.getMember()+" in service");
        Board board = new Board(boardDto, member);
        return  ResponseDto.success(boardRepository.save(board));
    }

}
