package com.packt.rspgame.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int BoardId;
		
	private String title;
	private String content;
	private LocalDateTime createTime;
	private int views;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User board_user;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="board")
	private List<Comment> comments;
}
