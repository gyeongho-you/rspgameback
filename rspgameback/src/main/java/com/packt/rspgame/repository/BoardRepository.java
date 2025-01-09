package com.packt.rspgame.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.packt.rspgame.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b order by b.createTime desc")
    Page<Board> findAllOrderByCreateTimeDesc(Pageable pageable);
}
