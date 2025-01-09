package com.packt.rspgame.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankDTO {
	private String userId;
	private int winStreak;
	
	public RankDTO(String userId, int winStreak) {
		super();
		this.userId = userId;
		this.winStreak = winStreak;
	}
	
	public RankDTO() {
		super();
	}
}
