package com.ezen.www.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTest {
	// DB 정보를 500개 정도 쌓아서 확인해보려고 함
	// file 연결은 root-context 를 연결해줘야 하기에 파일 경로를 연결해줘야함 (대소문자 구분 필수)
	// RunWith 는 테스트할 때 반드시 필요한 어노테이션임
	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		log.info(">>>> TEST insert in >>>>");
		
		for(int i=0; i<300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title " + i);
			bvo.setWriter("Tester");
			bvo.setContent("Test Content " + i);
			
			bdao.insert(bvo);
		}
	}
	
	
}
