package org.company.my.service;

import java.util.HashMap;
import java.util.Map;

import org.company.my.dao.MemberDAO;
import org.company.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private MemberDAO memberDAO;
	@Autowired // 비번 암호화
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Map<String, Object> login(String userid, String userpw) {
		Map<String, Object> result = new HashMap<>();
		// code, msg
		// code) 0 : 로그인 성공
		// code) 1 : 이메일 미존재
		// code) 2 : 비밀번호 불일치
		// code) 3 : 이메일 미인증   
		// code) 4 : 간편가입 
		
		//회원조회
		Member member = memberDAO.selectOne(userid);
		
		System.out.println("userid : " + userid + " 끝");
		
		System.out.println("멤버 : " + member);

		// 1)이메일 체크
		if(member==null) {
			result.put("code", 1);
			result.put("msg", "아이디 미존재");
			return result;
		}
		
		// 2)간편가입 체크
		if(!member.getSimplejoin().equals("0")) { //일반가입이 아닌사람
			if(member.getSimplejoin().equals("1")) { //네이버 간편가입자
				result.put("code", 4);
				result.put("msg", "네이버 간편가입자 입니다");
				return result;
			}else {
				return result;
			}
		}

		// 3)비밀번호 체크
		// 평문과 암호문을 비교해서 일치여부 반환
		// 매개변수 (입력받은pw , 조회한member의pw)
 		boolean match = bCryptPasswordEncoder.matches(userpw, member.getUserpw()); 
		if(!match) {
			result.put("code", 2);
			result.put("msg", "비밀번호 불일치");
			return result;
		}

		// 4)이메일 인증체크
		if(member.getEmailauth().equals("0")) {
			result.put("code", 3);
			result.put("msg", "이메일 미인증");
			return result;
		}

		//성공
		result.put("code", 0);
		result.put("msg", "로그인성공");
		return result;
			
	}
}
