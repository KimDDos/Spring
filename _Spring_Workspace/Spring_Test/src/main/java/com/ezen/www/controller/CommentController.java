package com.ezen.www.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	// 비동기 컨트롤러는 @RestController 를 많이 씀, 그냥 Controller를 써도 쓸수 있음
	
	@Inject
	private CommentService csv;
	
	@PostMapping(value="", consumes = "application/json",
				 produces = {MediaType.TEXT_PLAIN_VALUE})
	public void post(CommentVO cmt) {
		log.info("cmt >>>> {}", cmt);
	}
	
}
