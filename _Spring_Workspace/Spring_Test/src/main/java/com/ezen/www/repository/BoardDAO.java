package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	int countUp(int bno);

	BoardVO selectOne(int bno);

	int modify(BoardVO bvo);

	int delete(int bno);

	void down(int bno);

	int getTotalCount(PagingVO pgvo);

	int selectBno();

	int updateCount();

	int updateCommentCount();
	
}
