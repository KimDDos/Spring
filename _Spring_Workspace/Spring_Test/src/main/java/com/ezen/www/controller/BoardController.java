package com.ezen.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;


// 컨트롤러는 어노테이션으로 붙여줘야 스프링이 인식함
@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	// 아래 어노테이션은 무엇? 라이브러리 확인 필요
	// Inject와 Autowired 는 동일한 기능임, 세부적으로는 다름
	@Inject
	private BoardService bsv;
	
	
	// 경로와 리턴의 값이 같을 경우 생략가능 => void 사용 가능
	// /board/register = /board/register.jsp
	@GetMapping("/register")
	public void register() { }
	
	
	// @RequestParam("name")String name : 파라미터 받을 때 
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info("register bvo >>>>> {}", bvo);
		int isOk = bsv.register(bvo); 
		// 목적지 경로
		return "redirect:/board/list";
	}
	
	// /board/list  =>  /board/list void 처리해도 상관없음.
	@GetMapping("/list")
	public String list(Model m) {
		// 리턴 타입은 목적지 경로에 대한 타입 (destPage가 리턴이라고 생각)
		// Model 객체  =>  setAttribute 역할을 하는 객체
		m.addAttribute("list", bsv.getList());
		return "/board/list";
	
	}
	
	// 두개를 맵핑하려면 중 괄호를 씌여줘야함!
	@GetMapping({"/detail" , "/modify"})
	public void detail(HttpServletRequest request, Model m, @RequestParam("bno") int bno) {
		log.info(">>>>> bno {} ", bno);
		String path = request.getServletPath();
		log.info(">>>>> path {} ", path);
		
		if(path.equals("/board/detail")) {
			int isOk = bsv.readCount(bno);
			log.info("readCount Up >>>> {}", isOk > 0 ? "Ok":"Fail");
		}
		
		m.addAttribute("bvo", bsv.getDetail(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info("modify >>>>> {}", bvo);
		
		
		return "redirect:/board/detail";   // bno가 필요함
	}
	
}
