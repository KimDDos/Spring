package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.CommentVO;

public interface CommentService {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int remove(int cno);

	int modify(CommentVO cvo);

}
