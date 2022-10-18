package com.sparta.invisible_project.Service;

import com.sparta.invisible_project.Dto.BoardDto;
import com.sparta.invisible_project.Dto.LoginResponseDto;
import com.sparta.invisible_project.Dto.ResponseDto;
import com.sparta.invisible_project.Entity.Board;
import com.sparta.invisible_project.Entity.Member;
import com.sparta.invisible_project.Repository.BoardRepository;
import com.sparta.invisible_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.sparta.invisible_project.Dto.ResponseDto.success;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

//    private final UserDetailsImpl userDetails;
    @Transactional
    public ResponseDto<?> createBoard(BoardDto boardDto, Member member) {
//        System.out.println(userDetails.getMember()+" in service");
        Board board = new Board(boardDto, member);
        return success(boardRepository.save(board));
    }

    public ResponseDto<?> getBoard() {
        return success(boardRepository.findAll());
    }
    @Transactional
    public ResponseDto<?> deleteBoard(Long id) {
        System.out.println("BoardService.deleteBoard");
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not present"));
        boardRepository.delete(board);
        return success(board.getId());
    }

    @Transactional
    public ResponseDto<?> updateBoard(BoardDto dto ,Long id){
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Id not exist"));
        board.boardUpdate(dto);

        return success(board);

    }

    public List<?> getMyBoard(Long userId)  {

        Member member = memberRepository.findById(userId).orElseThrow();
        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println(boardRepository.findAllByMember(member));
        return boardRepository.findAllByMember(member);

    }
}
