package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Member member) {
		return sqlSession.insert("org.company.my.MemberMapper.insert", member);
	}

	@Override
	public int update(Member member) {
		return sqlSession.update("org.company.my.MemberMapper.update", member);
	}

	@Override
	public int delete(String userid) {
		return sqlSession.delete("org.company.my.MemberMapper.delete", userid);
	}

	@Override
	public Member selectOne(String userid) {
		return sqlSession.selectOne("org.company.my.MemberMapper.selectOne", userid);
	}

	@Override
	public List<Member> selectList() {
		return sqlSession.selectList("org.company.my.MemberMapper.selectList");
	}

	@Override
	public int update_emailAuth(String userid) {
		return sqlSession.update("org.company.my.MemberMapper.update_emailAuth", userid);
	}
	

	
	
	
	
	
	
}
