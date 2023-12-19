package com.ezen.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

	@Inject
	private MemberService msv;

	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>> mvo >>> {}", mvo);
		int isOk = msv.signup(mvo);
		log.info(">>> signUp? >>>> {}", isOk > 0 ? "Ok":"Fail");
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, Model m, HttpServletRequest request) {
		log.info("login mvo >>>> {}", mvo);
		// mvo 객체가 db에 값과 일치하는지 체크
		MemberVO loginMvo = msv.isUser(mvo);
		
		if(loginMvo != null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo);  // session에 로그인 객체 저장
			ses.setMaxInactiveInterval(60*10);  // 로그인 유지 시간 
		} else {
			m.addAttribute("msg_login", "1");
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		// 세션 객체 삭제  =>  세션 끊기
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLogin(mvo.getId());
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_logout", "1");
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, Model m, RedirectAttributes re) {
		log.info("modify mvo >>>> {}", mvo);
		int isOk = msv.modify(mvo);
		log.info("member modify result >>>> {}", isOk > 0 ? "Ok":"Fail");
		re.addFlashAttribute("msg_modify", isOk);
		return "redirect:/member/logout";
	}
	
	@GetMapping("/remove")
	public String remove(HttpServletRequest request, @RequestParam String id, Model m) {
		log.info("remove id >>>> {}", id);
		int isOk = msv.remove(id);
		if(isOk > 0) {
			request.getSession().removeAttribute("ses");
			request.getSession().invalidate();
		}
		m.addAttribute("msg_remove", isOk);
		return "index";
	}
	
	
}
