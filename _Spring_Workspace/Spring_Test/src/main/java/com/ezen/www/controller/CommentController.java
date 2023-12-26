package com.ezen.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// ResponseEntity 객체 사용 : body 내용 + http status 상태
	// 본인의 바디에 실어야 하는 타입형태로 제네릭스를 작성함
	// ResponseEntity<String> / ResponseEntity<List<>>
	// @RequestBody : body 값 추출
	// consumes : 가져오는 데이터의 형태
	// produces : 보내는 데이터 형식 / 나가는 데이터 타입 : MediaType
	// json : application/json / text: text_plain => json으로 나가냐 text로 나가냐 이 차이임
	// (value="", consumes = "application/json", produces =
	// {MediaType.TEXT_PLAIN_VALUE})
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		log.info("cvo >>>> {}", cvo);
		int isOk = csv.post(cvo);

		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// HttpStatus.OK : 200을 띄움
	// INTERNAL_SERVER_ERROR : 서버에러 500을 띄움

	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno) {
		log.info(">>>> bno >>>>> {}", bno);

		List<CommentVO> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}

	
	 @DeleteMapping(value="/{cnoVal}", produces = MediaType.TEXT_PLAIN_VALUE)
	 public ResponseEntity<String> remove(@PathVariable("cnoVal") int cno){
	 log.info(">>>> cno >>>> {} ", cno);
	 
	 int isOk = csv.remove(cno); 
	 return isOk > 0 ? new ResponseEntity<String>("1",
	 HttpStatus.OK) : new ResponseEntity<String>("0",
	 HttpStatus.INTERNAL_SERVER_ERROR); }
	 
	/*
	 * @PutMapping(value="/{cnoVal}", consumes = "application/json", produces =
	 * MediaType.TEXT_PLAIN_VALUE) public ResponseEntity<String> modify(@RequestBody
	 * CommentVO cvo){ log.info(">>> cvo >>> {}", cvo); int isOk = csv.modify(cvo);
	 * return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new
	 * ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 들어오는 데이터의 타입이 변경되지 않는다면 consumes을 안 적어줘도 됨 ㅎ 
	 */
	 
	 @PutMapping("/modify")
	 public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
		 log.info(">>> cvo >>> {}", cvo);
		 int isOk = csv.modify(cvo);
		 return isOk > 0 ? new ResponseEntity<String>("1",
				 HttpStatus.OK) : new ResponseEntity<String>("0",
				 HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
}
