package com.sparta.invisible_project.Repository;

import com.sparta.invisible_project.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
