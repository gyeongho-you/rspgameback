package com.packt.rspgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.rspgame.dto.BoardDTO;
import com.packt.rspgame.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.3.18:3000"})
@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/getboards")
	public List<BoardDTO> getBoards(HttpServletRequest req){
		return boardService.getBoards(Integer.parseInt(req.getParameter("start"))
				,req.getParameter("size") != null ? Integer.parseInt(req.getParameter("size")) : 20);
	}
	
	@GetMapping("/getboard")
	public BoardDTO getbBoard(HttpServletRequest req) {
		return boardService.findById(Integer.parseInt(req.getParameter("boardId")));
	}
	
	@GetMapping("/addview")
	public void addBoardView(HttpServletRequest req) {
		boardService.addBoardView(Integer.parseInt(req.getParameter("boardId")));
	}
	
	
	@GetMapping("/getsize")
	public Long getSize() {
		return boardService.getsize();
	}
	
	@PostMapping("/putboard")
	public String putBoard (@RequestBody BoardDTO boardDTO) {
		if(boardDTO.getUserId() == null || boardDTO.getUserId().equals("")) {
			return "재로그인";
		}else{
			boardService.save(boardDTO);
			return "저장완료";
		}
	}
	
	@PostMapping("/remove")
	public void removeBoard (@RequestBody BoardDTO boardDTO) {
		boardService.remove(boardDTO);
	}
}
