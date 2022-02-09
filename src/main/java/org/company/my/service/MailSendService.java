package org.company.my.service;

public interface MailSendService {
	// 이메일 전송하고 인증번호를 반환
	String sendAuthMail(String email, String nicname) throws Exception;
	String getAuthCode();

}
