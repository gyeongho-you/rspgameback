package com.packt.rspgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.rspgame.dto.CommentDTO;
import com.packt.rspgame.service.CommentService;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.3.18:3000"})
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("/add")
	public void addComment(@RequestBody CommentDTO commentDTO) {
		commentService.addComment(commentDTO);
	}
	
	@PostMapping("/remove")
	public void removeCommnet(@RequestBody CommentDTO commentDTO) {
		commentService.removeComment(commentDTO.getCommentId());
	}
}
