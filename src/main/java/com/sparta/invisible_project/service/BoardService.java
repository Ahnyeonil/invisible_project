package com.sparta.invisible_project.service;


import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Comment;
import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.entity.Heart;
import com.sparta.invisible_project.model.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
import com.sparta.invisible_project.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    private final HeartRepository heartRepository;

    // 게시글 목록 조회
    public List<ResponseDto<?>> findBoards() {

        List<Board> boardList = boardRepository.findAll();

        // ??
        return Collections.singletonList(ResponseDto.success(boardList));
    }

    // 게시글 상세
    public ResponseDto<?> findBoard(Long id) {

        Optional<Board> board = boardRepository.findById(id);

        return ResponseDto.success(board);
    }

    // 게시글 등록
    public ResponseDto<?> createBoard(BoardDto boardDto, Member member) {

        Board board = new Board(boardDto, member);

        boardRepository.save(board);

        return ResponseDto.success(board);
    }

    // 게시글 수정
    @Transactional
    public ResponseDto<?> updateBoard(BoardDto boardDto, Long id, Member member) {

        Board updateBoard = boardRepository.findById(id).orElseThrow();

        if(!updateBoard.getMember().getMember_id().equals(member.getMember_id())){
            return ResponseDto.fail(member.getUsername(), "게시글 작성자와 로그인한 사용자가 일치하지 않습니다. (UPDATE)");
        }

        updateBoard.update(boardDto.getTitle(), boardDto.getContent());

        return ResponseDto.success(updateBoard);
    }

    // 게시글 삭제
    @Transactional
    public ResponseDto<?> deleteBoard(Long id, Member member) {

        Board board = boardRepository.findById(id).orElseThrow();
        List<Comment> commentList = commentRepository.findAllByBoard(board);
        List<Heart> heartList = heartRepository.findAllByBoard(board);

        if (!board.getMember().getMember_id().equals(member.getMember_id())) {
            return ResponseDto.fail(member.getUsername(), "게시글 작성자와 로그인한 사용자가 일치하지 않습니다. (DELETE)");
        }

        if(commentList.size() > 0)
            commentRepository.deleteAll(commentList);

        if(heartList.size() > 0)
            heartRepository.deleteAll(heartList);

        boardRepository.deleteById(id);
        // 단건 삭제
        /*
        for(Comment comment : commentList){
            commentRepository.delete(comment);
        }
        */

        return ResponseDto.success(board);
    }
}
