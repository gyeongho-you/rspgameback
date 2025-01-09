package com.packt.rspgame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.rspgame.dto.RankDTO;
import com.packt.rspgame.dto.UserDTO;
import com.packt.rspgame.entity.User;
import com.packt.rspgame.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//변환 후 반환
	public List<UserDTO> findByUser(String id, String passWord) {
		List<User> users = userRepository.findByUser(id, passWord);
				
		return users.stream()
				.map(user -> new UserDTO(user.getId()
						,user.getPassWord(),user.getNickName(),user.getMoney()
						,user.getGameCnt(),user.getWin(),user.getLose(),user.getWinStreak()))
				.collect(Collectors.toList());
	}
	
	public String signUpUser(UserDTO userDTO) {
		if(findByUser(userDTO.getId(), userDTO.getPassWord()).isEmpty()) {
			if(userRepository.findByUserNickName(userDTO.getNickName()).isEmpty()) {
				userRepository.save(new User(userDTO.getId(),userDTO.getPassWord(),userDTO.getNickName()));
				return "생성완료";
			}
			return "닉네임중복";
		}
		
		return "아이디중복";

	}
	
	public List<RankDTO> getRanking() {
		List<RankDTO> ranks = new ArrayList<>();
		List<User> users = userRepository.findByUserWinStreakDesc();
		int cnt = 0;
		if(!users.isEmpty()) {
			for(User user: users) {
				if(cnt > 10) {
					break;
				}
				ranks.add(new RankDTO(user.getId(),user.getWinStreak()));
				cnt++;
			}
		}
		return ranks;
	}

}
