package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int readCount(int bno);

	void modify(BoardVO bvo);

	int remove(int bno);

	void countDown(int bno);
}
