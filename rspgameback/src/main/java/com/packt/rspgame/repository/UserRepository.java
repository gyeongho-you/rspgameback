package com.packt.rspgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.packt.rspgame.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("select u from User u where u.id = ?1 AND u.passWord = ?2")
	public List<User> findByUser(String id, String passWord);
	
	@Query("select u from User u where u.nickName = ?1")
	public List<User> findByUserNickName(String nickName);
	
	@Query("select u from User u order by u.winStreak desc")
	public List<User> findByUserWinStreakDesc();
}
