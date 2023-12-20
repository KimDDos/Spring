package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	// service와 연결은 사용자 본인이 implements 를 해줘야함, 안 하면 각자 독립적인 클래스임
	
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("register service impl check!");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		return bdao.selectOne(bno);
	}

	@Override
	public int readCount(int bno) {
		return bdao.countUp(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		bdao.modify(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.delete(bno);
	}

	@Override
	public void countDown(int bno) {
		bdao.down(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	
}
