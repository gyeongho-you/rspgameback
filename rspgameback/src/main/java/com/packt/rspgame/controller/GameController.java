package com.packt.rspgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.rspgame.service.GameService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.3.18:3000"})
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;

	@GetMapping("/wait")
	public String waitGame(HttpServletRequest req) {
		return gameService.checkMatchGame(req.getParameter("userId"));
	}
	
	@GetMapping("/matching")
	public String onChangeSelect(HttpServletRequest req) {
		return gameService.onChangeSelect(req.getParameter("userId"),req.getParameter("select"));
	}
	
	@GetMapping("/result")
	public String checkResult(HttpServletRequest req) {
		return gameService.checkResult(req.getParameter("userId"));
	}
	
	@GetMapping("/cancel")
	public void cancelGame(HttpServletRequest req) {
		gameService.cancelGame(req.getParameter("userId"));
	}
	
	
}
