package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO getDetail(int bno);

	int readCount(int bno);

	int modify(BoardDTO bdto);

	int remove(int bno);

	void countDown(int bno);

	int getTotalCount(PagingVO pgvo);

	int deleteFile(String uuid);

	int updateCount();
}
