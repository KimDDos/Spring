package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int readCount(int bno);

	void modify(BoardVO bvo);

	int remove(int bno);

	void countDown(int bno);

	int getTotalCount();
}
