package com.packt.rspgame.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.packt.rspgame.entity.User;
import com.packt.rspgame.repository.UserRepository;

@Service
public class GameService {
	
	Queue<String> waiting = new LinkedList<>(); // 매칭 대기
	HashMap<String,String[]> matching = new HashMap<>(); // 게임중
	HashSet<String> endGame = new HashSet<>();
	HashSet<String> cancelGame = new HashSet<>();
	
	@Autowired
	UserRepository userRepository;
	
	@Async // 비동기 실행
	public String checkMatchGame(String userId) { 
		if(cancelGame.contains(userId)) {
			waiting.remove(userId);
			cancelGame.remove(userId);
			return "취소됨";
		}
		
		if(matching.containsKey(userId)) {
			return matching.get(userId)[0];
		}
		
		if(!waiting.contains(userId)) {
			waiting.add(userId);
		}
		
		if(waiting.size() >= 2) { // 대기인원이 2명 이상이면 맨 앞 두명 매칭
			
			String user1 = waiting.poll();
			String user2 = waiting.poll();
			
			matching.put(user1,new String[]{user2,"","0"});
			matching.put(user2,new String[]{user1,"","0"});
		}
		
		return "대기중";
	}
	
	@Async // 비동기 실행
	public void cancelGame(String userId) {
		cancelGame.add(userId);
	}
	
	@Async // 비동기 실행
	public String onChangeSelect(String userId,String select) {
		String[] beforeSelect = matching.get(userId);
		beforeSelect[1] = select;
		beforeSelect[2] += "0";
		
		matching.put(userId,beforeSelect);
		if(beforeSelect[2].length() > 5) { // 5초 이상이면 게임 종료
			return "선택종료";
		}
		
		return "변경완료"; 
	}
	
	@Async // 비동기 실행
	public String checkResult(String userId) {
		String me = matching.get(userId)[1]; // 내가 낸 값
		String you = matching.get(matching.get(userId)[0])[1]; // 상대가 낸 값
		
		String player1 = userId; // 1 플레이어
		String player2 = matching.get(userId)[0]; // 2 플레이어
		
		if(endGame.contains(player1) || endGame.contains(player2)) {
			//게임종료 후 둘 다 결과 나옴
			//System.out.println("삭제");
			
			matching.remove(player1);
			matching.remove(player2);
			endGame.remove(player1);
			endGame.remove(player2);
		}else {
			//System.out.println("종료게임 저장");
			
			endGame.add(player1);
			endGame.add(player2);
		}
		
		
		String result = "";
		
		if(me.equals(you)) {
			result = "무승부";
		}else if(me.equals("가위")) {
			if(you.equals("바위")) {
				result = "패배";
			}else {
				result = "승리";
			}
		}else if(me.equals("바위")) {
			if(you.equals("보")) {
				result = "패배";
			}else {
				result = "승리";
			}
		}else {
			if(you.equals("가위")) {
				result = "패배";
			}else {
				result = "승리";
			}
		}
		
		//데이터베이스 데이터 저장
		User user = userRepository.getReferenceById(userId);
		
		user.setGameCnt(user.getGameCnt()+1);
		
		if(result.equals("무승부")) {
			
		}else if(result.equals("승리")) {
			user.setWin(user.getWin()+1);
			user.setWinStreak(user.getWinStreak()+1);
		}else {
			user.setLose(user.getLose()+1);
			user.setWinStreak(0);
		}
		
		userRepository.save(user);
		
		return result;

	}
}
