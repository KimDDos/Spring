package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	int countUp(int bno);

	BoardVO selectOne(int bno);

	void modify(BoardVO bvo);

	int delete(int bno);

	void down(int bno);

	int getTotalCount(PagingVO pgvo);
	
}
