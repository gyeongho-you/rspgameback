	package com.packt.rspgame.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
	private int commentId;
	private String userId;
	private int boardId;
	private String content;
	private LocalDateTime createTime;
	
	

	public CommentDTO() {
		super();
	}


	public CommentDTO(String userId, int boardId, String content) {
		super();
		this.userId = userId;
		this.boardId = boardId;
		this.content = content;
	}
	
	public CommentDTO(String userId, int commentId, String content, LocalDateTime createTime) {
		super();
		this.userId = userId;
		this.commentId = commentId;
		this.content = content;
		this.createTime = createTime;
	}
	
	
}
