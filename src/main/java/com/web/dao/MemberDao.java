package com.web.dao;

import java.util.List;

import com.web.entity.Member;

public interface MemberDao {
	List<Member> find(String name);
	
	List<Member> find(String nickname, String address);
	
	List<Member> findByExample(Member member);
	
	List<Member> findByExample(Member member, int offset, int rows);
	
	List<Member> findByNamedParam(String name);
	
	List<Member> findByNamedParam(String name, String passwd);

}
