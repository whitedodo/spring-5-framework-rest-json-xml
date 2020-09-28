/*
 * 
 * 	Subject: REST Spring framework 5.4
 * 	생성일자(Create Date): 2020-09-28
 * 	파일명(Filename): BoardRestController.java
 *  저자(Author): Dodo(rabbit.white at daum dot net)
 *  설명(Description):
 * 
 *  1. 게시판 구조 설계(REST기반), Dodo(도도), 2020-09-28
 * 
 */

package com.example.restexample2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restexample2.model.Board;

@RestController
@RequestMapping("/v1/api")
public class BoardRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	private static int num = 1;
	private List<Object> tmpBoard = new ArrayList<Object>(); 
	
	//@Autowired
    //private BoardService boardService;

    // 조회 = GET
    // (전체 게시물)
	@GetMapping("board")
    public List<Object> listBoard(HttpServletRequest request, @ModelAttribute Board board) throws Exception {
    	
    	logger.info("게시판 목록");
    	logger.info("----------------------------------");
    	
    	Board createNode = new Board();
    	createNode.setId(num);
    	createNode.setName("홍길동");
    	createNode.setMemo("메모");
    	createNode.setSubject("주소지");
    	
    	// tmpBoard.put(num, createNode);
    	// num = num + 1;
    	
    	return tmpBoard;
    	//return this.boardService.selectBoardList(request, board);
    }

    // 조회 = GET
    // (특정 세부 게시물)
    @GetMapping("board/{boardIdx}")
    public Board detailBoard(HttpServletRequest request, 
    										@PathVariable(name="boardIdx", required=true) int boardIdx)
    												throws Exception {

    	logger.info("게시판 조회");
    	logger.info("----------------------------------");
    	logger.info("게시판 특정 게시물 번호" + boardIdx);
    	
    	//return this.boardService.selectBoard(request, boardIdx);
    	return (Board) tmpBoard.get(boardIdx);
    }
    
    // 등록 = POST
    @PostMapping("board/new")
    public void insertBoard(HttpServletRequest request, @RequestBody Board board) throws Exception {
    	
    	logger.info("게시판 삽입");
    	
    	board.setId(num);

    	tmpBoard.add(board);
    	System.out.println(num);
    	num = num + 1;
        //this.boardService.insertBoard(request, board);
    }

    // 수정 = PUT, PATCH (전송 방식)
    // /member/{id} + body (json데이터 등)
    @PutMapping("board/{boardIdx}")
    @PatchMapping("board/{boardidx}")
    public void updateBoard(HttpServletRequest request, 
    										@PathVariable(name="boardIdx", required=true) int boardIdx, 
    										@RequestBody Board board) throws Exception {
    	
    	logger.info("게시판 수정");
    	
    	if ( !tmpBoard.isEmpty() ) {
    	
    		board.setId(boardIdx);						// 고유키 그대로 유지할 것
    		tmpBoard.set(boardIdx - 1, board);
    	}
    	//board.setBoardIdx(boardIdx);
        //this.boardService.updateBoard(request, board);
    	
    }
    
    // 삭제 = DELETE(전송 방식)
    @DeleteMapping("board/{boardIdx}")
    public void deleteBoard(HttpServletRequest request,
    									@PathVariable(name="boardIdx", 
    									required=true) int boardIdx) throws Exception {
    	
    	logger.info("게시판 삭제");
    	
    	try {    	
    		tmpBoard.remove(boardIdx);
    	}
    	catch(Exception e) {
        	logger.info("Null값");
    		e.getStackTrace();
    	}
    	
    	//this.boardService.deleteBoard(request, boardIdx);
    }

}
