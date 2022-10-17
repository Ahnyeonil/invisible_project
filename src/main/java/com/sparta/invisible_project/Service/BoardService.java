package com.sparta.invisible_project.service;


import com.sparta.invisible_project.Entity.Board;
import com.sparta.invisible_project.Entity.Comment;
import com.sparta.invisible_project.dto.BoardDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.model.Members;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
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

    // 게시글 목록 조회
    public List<ResponseDto<?>> findBoards() {

        List<Board> boardList = boardRepository.findAll();

        // ??
        return Collections.singletonList(ResponseDto.success(boardList));
    }

    // 게시글 상세
    public ResponseDto<?> findBoard(long id) {

        Optional<Board> board = boardRepository.findById(id);

        return ResponseDto.success(board);
    }

    // 게시글 등록
    public ResponseDto<?> createBoard(BoardDto boardDto) {

        Members members = new Members();
        Board board = new Board(boardDto, members);

        boardRepository.save(board);

        return ResponseDto.success(board);
    }

    // 게시글 수정
    @Transactional
    public ResponseDto<?> updateBoard(BoardDto boardDto, long id) {

        Board updateBoard = boardRepository.findById(id).orElseThrow();

        updateBoard.update(boardDto.getTitle(), boardDto.getContent());

        return ResponseDto.success(updateBoard);
    }

    // 게시글 삭제
    public ResponseDto<?> deleteBoard(long id) {

        Board board = boardRepository.findById(id).orElse(null);
        Comment comment = commentRepository.findAllByBoard(board);

        boardRepository.deleteById(id);
        commentRepository.delete(comment);

        return ResponseDto.success(board);
    }
}
