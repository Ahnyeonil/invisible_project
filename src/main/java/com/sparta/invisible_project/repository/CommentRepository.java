package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard(Board board);

    Long countCommentByBoard(Board board);

}
