package com.itwillbs.service;

import com.itwillbs.domain.MemberBean;

public interface MemService {
	// �θ� �������̽� �߻�޼���
	public void insert(MemberBean mb);
	public MemberBean userCheck(MemberBean mb);
	public MemberBean info(String id);
	public void update(MemberBean mb);
	public void delete(String id);
}
