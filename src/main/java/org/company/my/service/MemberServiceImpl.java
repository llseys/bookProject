package org.company.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.company.my.dao.MemberDAO;
import org.company.my.dto.Member;
import org.company.my.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired // 비번 암호화
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired // 메일인증
	private MailSendService mailSendService;
	
	// 회원추가
	@Override
	public Map<String, Object> insert(Member member) throws Exception {
		Map<String, Object> result = new HashMap<>();

		//  비밀번호 암호화
		String cryptPassword = bCryptPasswordEncoder.encode(member.getUserpw()); //패스워드 암호화
		member.setUserpw(cryptPassword);
		
		//  이메일전송
		String authCode = mailSendService.sendAuthMail(member.getEmail(), member.getUsername());
			
		int cnt = memberDAO.insert(member);
		if(cnt==1) {
			result.put("authCode", authCode);
			result.put("code", "1");
			result.put("msg", "가입완료 되었습니다");
			return result;
		}else{
			result.put("code", "0");
			result.put("msg", "가입에 실패했습니다");
			return result;
		}
	}
	
	// 회원 한건조회
	@Override
	public Member selectOne(String userid) {
		return memberDAO.selectOne(userid);
	}
	
	// 회원 전체조회
	@Override
	public List<Member> selectList() {
		return memberDAO.selectList();
	}
	
	// 회원 한건수정
	@Override
	public Map<String, Object> update(Member member) {
		Map<String, Object> result = new HashMap<>();
		// code: 1 msg:한건수정 성공
		// code: 0 msg: 한건수정 실패
		int cnt = memberDAO.update(member);
		if(cnt==1) {
			result.put("code", "1");
			result.put("msg", "수정완료 되었습니다");
			return result;
		}else{
			result.put("code", "0");
			result.put("msg", "수정에 실패했습니다");
			return result;
		}
	}

	// 회원삭제
	@Override
	public Map<String, Object> delete(String userid, String userpw) {
		Map<String, Object> result = new HashMap<>();
		
		Member dbMember = memberDAO.selectOne(userid); //실제 db
		String dbpw =dbMember.getUserpw(); // 실제 db 비밀번호
		
		if(userpw.equals(dbpw)) { //db비밀번호와 입력받은비밀번호 같은경우
			memberDAO.delete(userid);
			result.put("code", "1");
			result.put("msg", "삭제 완료되었습니다.");
			return result;
		}else {
			result.put("code", "0");
			result.put("msg", "비밀번호가 틀렸습니다.");
			return result;
		}
	}

	@Override
	public String update_emailAuth(String userid) {
		String msg="";
		if(memberDAO.update_emailAuth(userid)>0) {
			msg="이메일인증 성공";
			return msg;
			
		}else {
			msg="이메일인증 실패";
			return msg;
		}
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
