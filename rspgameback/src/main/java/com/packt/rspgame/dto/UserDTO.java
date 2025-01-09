package com.packt.rspgame.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	
	private String id;
	private String passWord;
	private String nickName;
	private int money;
	private int gameCnt;
	private int win;
	private int lose;
	private int winStreak;
	
	public UserDTO(String id, String passWord, String nickName, int money, int gameCnt, int win, int lose,
			int winStreak) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.nickName = nickName;
		this.money = money;
		this.gameCnt = gameCnt;
		this.win = win;
		this.lose = lose;
		this.winStreak = winStreak;
	}
	

}
