package com.packt.rspgame.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	private String content;
	private LocalDateTime createTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User comment_user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
}
