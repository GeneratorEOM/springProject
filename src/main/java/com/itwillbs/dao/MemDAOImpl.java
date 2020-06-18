package com.itwillbs.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberBean;

@Repository
public class MemDAOImpl implements MemDAO{
	
	// SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
	private SimpleJdbcTemplate template;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		template = new SimpleJdbcTemplate(dataSource);
	}
	
	String insertsql = "INSERT INTO member(id,pass,name,reg_date) VALUES(?,?,?,?)";
	
	@Override
	public void insert(MemberBean mb) {
		System.out.println("MemDaoImpl - insert()");
		template.update(insertsql, mb.getId(), mb.getPass(), mb.getName(), mb.getReg_date());

	}
	// id pass ��ġ�ϸ� ȸ������ ��� ��������
	String userChecksql = "SELECT * FROM member WHERE id=? and pass=?";
	@Override
	public MemberBean userCheck(MemberBean mb) {
		RowMapper<MemberBean> mapper = new BeanPropertyRowMapper<MemberBean>(MemberBean.class);
		return template.queryForObject(userChecksql, mapper, mb.getId(), mb.getPass());
	}
	// id �� ��ġ�ϴ� ȸ������ ��������
	String infosql = "SELECT * FROM member WHERE id=?";
	@Override
	public MemberBean info(String id) {
		RowMapper<MemberBean> mapper = new BeanPropertyRowMapper<MemberBean>(MemberBean.class);
		return template.queryForObject(infosql, mapper, id);
	}
	// id �� ��ġ�ϴ� ȸ������ ����
	String updatesql = "UPDATE member SET name = ? WHERE id = ?";
	@Override
	public void update(MemberBean mb) {
		
		template.update(updatesql, mb.getName(), mb.getId());

	}
	String deletesql = "DELETE FROM member WHERE id = ?";
	@Override
	public void delete(String id) {
	
		template.update(deletesql, id);
	}
	
	
}
