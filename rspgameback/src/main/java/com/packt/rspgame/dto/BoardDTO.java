package com.packt.rspgame.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int boardId;
	private String title;
	private String content;
	private LocalDateTime createTime;
	private int views; 
	private String userId;
	private List<CommentDTO> comments;
	
	public BoardDTO(int boardid, String title, String content, LocalDateTime createTime,int views, String userId, List<CommentDTO> comments) {
		super();
		this.boardId = boardid;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.views = views;
		this.userId = userId;
		this.comments = comments;
	}
	
	public BoardDTO(String title, String content, String userId) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
	}

	public BoardDTO() {
		super();
	}
	
	
}
