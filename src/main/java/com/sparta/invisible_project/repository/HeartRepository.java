package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Heart;
import com.sparta.invisible_project.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByBoardAndMember(Board boardId, Member member);

    void deleteByBoardAndMember(Board boardId, Member member);

    List<Heart> findAllByBoard(Board board);

    Long countHeartByBoard(Board board);

}
