package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	int countUp(int bno);

	BoardVO selectOne(int bno);

	void modify(BoardVO bvo);

	int delete(int bno);

	void down(int bno);
	
}
