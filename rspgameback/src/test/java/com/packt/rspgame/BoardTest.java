package com.packt.rspgame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.packt.rspgame.entity.Board;
import com.packt.rspgame.repository.BoardRepository;

@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void addBoards() {
		
		for(int i = 0; i<30; i++) {
			Board b1 = new Board();
			b1.setBoard_user(null);
			b1.setContent("테스트1");
			b1.setCreateTime(LocalDateTime.now());
			b1.setTitle("테스트1");
			
			boardRepository.save(b1);
		}
	}
}
