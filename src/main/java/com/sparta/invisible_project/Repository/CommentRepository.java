package com.sparta.invisible_project.Repository;

import com.sparta.invisible_project.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
