package com.packt.rspgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.rspgame.dto.RankDTO;
import com.packt.rspgame.dto.UserDTO;
import com.packt.rspgame.service.UserService;
import com.packt.rspgame.session.SessionManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.3.18:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionManager sessionManager;
	
	@PostMapping("/login")
	public String loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
		List<UserDTO> users = userService.findByUser(userDTO.getId(), userDTO.getPassWord());
		if(users.isEmpty()) {
			return "실패";
		}
		
		//세션 및 쿠키 생성	
		sessionManager.createSession(userDTO.getId(), response);
		
		return "성공";
	}
	
	//로그인
	@PostMapping("/signup")
	public String signUpUser(@RequestBody UserDTO userDTO) {
		return userService.signUpUser(userDTO);
	}
	
	
	//로그인 여부 확인
	@PostMapping("/checkcookie")
	public String checkCookie(HttpServletRequest request) {
		return sessionManager.findCookie(request.getCookies());
	}
	
	//로그아웃
	@PostMapping("/deletecookie")
	public String deleteCookie(HttpServletRequest request) {
		if(sessionManager.deleteCookie(request.getCookies())) {
			return "로그아웃";
		}else {
			return "로그아웃실패";
		}
	}

	//작성자 확인
	@PostMapping("/checkowner")
	public String checkOwner(HttpServletRequest request) {
		return sessionManager.findCookie(request.getCookies());
	}
	
	@GetMapping("/ranking")
	public List<RankDTO> getRanking() {
		return userService.getRanking();
	}
}
