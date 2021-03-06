package com.itwillbs.dao;

import com.itwillbs.domain.MemberBean;

public interface MemDAO {
	// 부모 인터페이스 추상메서드
	public void insert(MemberBean mb);
	public MemberBean userCheck(MemberBean mb);
	public MemberBean info(String id);
	public void update(MemberBean mb);
	public void delete(String id);
}
