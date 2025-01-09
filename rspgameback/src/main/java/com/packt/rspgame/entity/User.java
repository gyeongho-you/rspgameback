package com.packt.rspgame.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {
	
	@Id
	private String id;
	
	private String passWord;
	private String nickName;	
	private int money;
	private int gameCnt;
	private int win;
	private int lose;
	private int winStreak;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="board_user")
	private List<Board> boards;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="comment_user")
	private List<Comment> comments;

	public User(String id, String passWord, String nickName) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.nickName = nickName;
	}

	public User() {
		super();
	}
	
	
}
