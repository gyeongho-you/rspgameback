package com.packt.rspgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.rspgame.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
	