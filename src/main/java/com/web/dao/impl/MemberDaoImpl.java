package com.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.web.dao.MemberDao;
import com.web.entity.Member;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> find(String name) {
		String hql = "from Member where name=?";
		return (List<Member>) hibernateTemplate.find(hql, name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> find(String nickname, String address) {
		String hql = "from Member where nickname=? and address like ?";
		return (List<Member>) hibernateTemplate.find(hql, "jade", "%beijing%");
	}

	@Override
	public List<Member> findByExample(Member member) {
		return hibernateTemplate.findByExample(member);
	}

	@Override
	public List<Member> findByExample(Member member, int offset, int rows) {
		return hibernateTemplate.findByExample(member, offset, rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findByNamedParam(String name) {
		String hql = "from Member where name=:name";
		return (List<Member>) hibernateTemplate.findByNamedParam(hql, "name",
				"admin");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findByNamedParam(String name, String passwd) {
		String hql = "from Member where name=:name and passwd=:passwd";
		String[] params = new String[] { "name", "passwd" };
		String[] values = new String[] { "admin", "123456" };
		return (List<Member>) hibernateTemplate.findByNamedParam(hql, params,
				values);
	}

}
