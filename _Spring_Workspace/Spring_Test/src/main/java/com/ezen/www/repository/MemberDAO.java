package com.ezen.www.repository;

import com.ezen.www.domain.MemberVO;

public interface MemberDAO {

	MemberVO getUser(String id);

	int insert(MemberVO mvo);

	void lastLogin(String id);

	int mod(MemberVO mvo);

	int modNoPw(MemberVO mvo);

}
