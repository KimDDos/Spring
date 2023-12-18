package com.ezen.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	// Pw를 암호화하는 메서드

	@Override
	public int signup(MemberVO mvo) {
		// 아이디가 중복되면 회원가입 실패
		// => 아이디만 주고 DB에서 일치하는 mvo 객체를 리턴
		// 일치하는 유저가 있다면 가입 실패, 없다면 가입 가능하게
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		
		if(tempMvo != null) {
			// 기존 아이디가 있는 경우
			return 0;
		} 
		
		// id가 중복되지 않는다면 회원가입 진행
		// password가 null이거나 값이 없다면 가입 불가
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		// 회원가입 진행
		// password는 암호화하여 가입
		// 암호화(encode) / matches(입력된 비번, 암호화된 비번) => true / false 로 리턴
		String pw = mvo.getPw();
		// 실제로는 아이디 중복확인을 화면에서 진행함, 종복확인 버튼을 통해서 확인후 콘트롤러로 전송
		
		String encodePw = passwordEncoder.encode(pw);  // 패스워드 암호화
		mvo.setPw(encodePw);
		
		int isOk = mdao.insert(mvo);
		
		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		// 로그인 유저 확인
		// 아이디를 주고 해당 아이디의 객체를 리턴
		MemberVO tempMvo = mdao.getUser(mvo.getId());  // 회원가입할 때 했던 메서드 호출
		
		// 해당 아이디가 없는 경우
		if(tempMvo == null) {
			return null;
		}
		// matches(원래 비번, 암호화된 비번) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		
		return null;
	}

	@Override
	public void lastLogin(String id) {
		mdao.lastLogin(id);
		
	}

	@Override
	public int modify(MemberVO mvo) {
		// 공통 : ID 스정은 못하게
		// 비밀번호도 수정할때
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return mdao.modNoPw(mvo);
		} else {
			String pw = mvo.getPw();
			String encodePw = passwordEncoder.encode(pw);  // 패스워드 암호화
			mvo.setPw(encodePw);
			return mdao.mod(mvo);
		} 
		
		// 비밀번호는 미수정할때
	}
}
