package com.packt.rspgame.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.packt.rspgame.dto.BoardDTO;
import com.packt.rspgame.dto.CommentDTO;
import com.packt.rspgame.entity.Board;
import com.packt.rspgame.entity.User;
import com.packt.rspgame.repository.BoardRepository;
import com.packt.rspgame.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<BoardDTO> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Board> boardPage = boardRepository.findAllOrderByCreateTimeDesc(pageable);	
        return boardPage.getContent().stream().map((board) -> {
        	List<CommentDTO> comments = board.getComments() != null ? board.getComments().stream().map((comment) ->(new CommentDTO(
    				comment.getComment_user().getId(),comment.getCommentId()
    				,comment.getContent(),comment.getCreateTime()))).collect(Collectors.toList()) : new ArrayList<>();
        	
    		return new BoardDTO( board.getBoardId(),board.getTitle(),board.getContent()
    			,board.getCreateTime(),board.getViews(),board.getBoard_user().getId()
    			,comments);
        }).collect(Collectors.toList());
	}
	
	public void addBoardView(int id) {
		Optional<Board> opBoard = boardRepository.findById(id);
		
		if(opBoard != null) {
			Board board = opBoard.get();
			board.setViews(board.getViews()+1);
			boardRepository.save(board);
		}
	}
	
	public BoardDTO findById(int id) {
		Optional<Board> opBoard = boardRepository.findById(id);
		if(opBoard != null) {
			Board board = opBoard.get();
			
			List<CommentDTO> comments = board.getComments() != null ? board.getComments().stream().map((comment) ->(new CommentDTO(
    				comment.getComment_user().getId(),comment.getCommentId()
    				,comment.getContent(),comment.getCreateTime()))).collect(Collectors.toList()) : new ArrayList<>();
			
			return new BoardDTO(
				board.getBoardId(),board.getTitle(),board.getContent()
				,board.getCreateTime(),board.getViews(),board.getBoard_user().getId(),comments);
		}
		return null;
	}

	public Long getsize() {
		return boardRepository.count();
	}
	
	public void save(BoardDTO boardDTO) {
		Optional<User> user = userRepository.findById(boardDTO.getUserId());
		
		Board board = new Board();
		if(boardDTO.getBoardId() == 0) {
			board.setContent(boardDTO.getContent());
			board.setTitle(boardDTO.getTitle());
			board.setBoard_user(user.get());
			board.setCreateTime(LocalDateTime.now());

		}else {
			board = boardRepository.getReferenceById(boardDTO.getBoardId());
			board.setContent(boardDTO.getContent());
			board.setTitle(boardDTO.getTitle());
		}
		
		
		boardRepository.save(board);
	}
	
	public void remove(BoardDTO boardDTO) {
		boardRepository.deleteById(boardDTO.getBoardId());
	}
}
