package com.ezen.www.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
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
	
	@Inject
	private FileHandler fhd;
	
	
	// 경로와 리턴의 값이 같을 경우 생략가능 => void 사용 가능
	// /board/register = /board/register.jsp
	@GetMapping("/register")
	public void register() { }
	
	
	// @RequestParam("name")String name : 파라미터 받을 때
	// required : 필수여부 (false) : 파라미터가 없어도 예외가 발생하지 않음.
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info("register bvo >>>>> {}", bvo);
		log.info("files >>>> {}", files.toString());
		// fileHandler 처리
		List<FileVO> flist = null;
		
		// 파일이 있을 경우만 fhd 호출
		if(files[0].getSize() > 0) {
			flist = fhd.uploadFiles(files);
			log.info(">>>> flist >> {}", flist);
		}
		// int isOk = bsv.register(bvo);
		// 목적지 경로
		return "redirect:/board/list";
	}
	
	// /board/list  =>  /board/list void 처리해도 상관없음.
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		log.info("list pgvo >>>> {}", pgvo);  // pageNo, qty, type, keyword
		// getparameter로 하나씩 받아올때 null이면 에러가 나지만, 객체 자체가 널이 아니기에 널 포인트는 뜨지 않음!
		// 만약에 pgvo에 타입이 키워드가 널인 상태에서 pgvo의 get타입을 뺄 때 null이면 null에러가 나옴!
		// 리턴 타입은 목적지 경로에 대한 타입 (destPage가 리턴이라고 생각)
		// Model 객체  =>  setAttribute 역할을 하는 객체
		m.addAttribute("list", bsv.getList(pgvo));
		
		// ph 객체 다시 생성 (pgvo, totalCount)
		int totalCount = bsv.getTotalCount(pgvo);
		log.info("totalCount >>> {}", totalCount);
		m.addAttribute("ph", new PagingHandler(pgvo, totalCount));
		m.addAttribute("totalCount", totalCount);
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
	public String modify(BoardVO bvo, Model m) {
		log.info("modify >>>>> {}", bvo);
		bsv.modify(bvo);
		bsv.countDown(bvo.getBno());
		m.addAttribute("bno",bvo.getBno());
		return "redirect:/board/detail";   // bno가 필요함
	}
	
	@GetMapping("/delete")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info("bno >>>> {}", bno);
		int isOk = bsv.remove(bno);
		// 페이지가 새로고침 될 때 남아있을 필요가 없는 데이터
		// 리다이렉트 될 때 데이터를 보내는 객체( RedirectAttribute )
		// 한번만 일회성으로 데이터를 보낼때 사용
		re.addFlashAttribute("isDel", isOk);
		return "redirect:/board/list";
	}
	
}
