package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
