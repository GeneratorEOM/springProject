package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemDAO;
import com.itwillbs.dao.MemDAOImpl;
import com.itwillbs.domain.MemberBean;
@Service
public class MemServiceImpl implements MemService{
	MemDAO memDAO;
	// xml ���� set�޼��带 ���ؼ� ��ü������ �� ����
	@Inject
	public void setMemDAO(MemDAO memDAO) {
		this.memDAO = memDAO;
	}
	
	@Override
	public void insert(MemberBean mb) {
		System.out.println("MemServiceImpl - insert()");
		// 1. ��ü���� => 3���� ���� �ʿ�
//		MemDAOImpl memDAOImpl = new MemDAOImpl();
//		memDAOImpl.insert();
		// 2. �θ� = �ڽ� ��ü ���� => 1���� ���� �ʿ�
//		MemDAO memDAO = new MemDAOImpl();
//		memDAO.insert();
		// 3. �θ�=�ڽ��� ���Ǵ� �� <= ���������� xml �ڽ� ��ü���� �ʿ�� �ϴ°��� ��
		//    ���������� ��ü �����ϴ� ��� : ������������(DI)
		
		// ���Գ�¥ ó��
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		memDAO.insert(mb);
		
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		
		return memDAO.userCheck(mb);
	}

	@Override
	public MemberBean info(String id) {
		
		return memDAO.info(id);
	}

	@Override
	public void update(MemberBean mb) {
		
		memDAO.update(mb);
		
	}

	@Override
	public void delete(String id) {
		memDAO.delete(id);
		
	}
	
	
}
