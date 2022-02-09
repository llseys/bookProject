package org.company.my.service;

import java.util.Map;

public interface NaverLoginService {
	// 네이버 로그인 apiUrl 만들기
	Map<String, String> getApiUrl()throws Exception;
	
//	// 토큰을얻기
//	String getToken(String code, String state)throws Exception;
//	// 개인정보얻기
//	Map<String, String> getUserInfo(String token)throws Exception;
		
	// 토큰을얻고 + 개인정보 얻기
	Map<String, String> getTokenUserInfo(String code, String state)throws Exception;
	
	// db에 회원저장
	Map<String, Object> insert(String email);
}
