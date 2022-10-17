package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByBoardAndMember(long boardId, String membername);

    void deleteByBoardAndMember(long boardId, String membername);
}
