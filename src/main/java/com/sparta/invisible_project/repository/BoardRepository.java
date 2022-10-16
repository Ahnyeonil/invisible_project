package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
