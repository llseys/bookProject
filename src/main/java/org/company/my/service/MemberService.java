package org.company.my.service;

import java.util.List;
import java.util.Map;

import org.company.my.dto.Member;

public interface MemberService {

	Map<String, Object> insert(Member member) throws Exception;

	Member selectOne(String userid);
	
	List<Member> selectList();

	Map<String, Object> update(Member member);

	Map<String, Object> delete(String userid, String userpw);
	
	public String update_emailAuth(String userid);
	
}
