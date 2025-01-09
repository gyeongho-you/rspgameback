package com.packt.rspgame.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.rspgame.dto.CommentDTO;
import com.packt.rspgame.entity.Comment;
import com.packt.rspgame.repository.BoardRepository;
import com.packt.rspgame.repository.CommentRepository;
import com.packt.rspgame.repository.UserRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void addComment (CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setBoard(boardRepository.getReferenceById(commentDTO.getBoardId()));
		comment.setComment_user(userRepository.getReferenceById(commentDTO.getUserId()));
		comment.setContent(commentDTO.getContent());
		comment.setCreateTime(LocalDateTime.now());
		
		commentRepository.save(comment);
	}
	
	public void removeComment(int commentId) {
		commentRepository.deleteById(commentId);
	}
	
	
}
