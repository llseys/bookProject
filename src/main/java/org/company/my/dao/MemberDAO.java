package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Member;

public interface MemberDAO {
	public int insert(Member member);
	public int update(Member member);
	public int delete(String userid);
	public Member selectOne(String userid);
	public List<Member> selectList();
	public int update_emailAuth(String userid);
}
