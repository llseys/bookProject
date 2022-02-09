package org.company.my.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceImpl implements MailSendService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	//인증번호 생성 메서드
	@Override
	public String getAuthCode() {
		StringBuffer authCode = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<6;i++) {
			authCode.append(random.nextInt(10)); //0~9사이의 랜덤정수 
		}
		return authCode.toString(); //authCode 의 주소값
	}

	// 인증번호 이메일 전송
	@Override
	public String sendAuthMail(String email, String nicname) throws Exception {	// 매개변수 : 보낼이메일, 아이디
		// 6자리 인증번호 생성
		String authCode = getAuthCode();
		System.out.println(authCode);
		
		//보낼 이메일 내용
		StringBuffer content = new StringBuffer();
		content.append(nicname+"님 반갑습니다 링크를 클릭하여 인증완료를 해주세요. <br>");
		
		//로컬
//		content.append("<a href='http://localhost:8081/my/member/joinConfirm?authCode="+authCode+"'>이메일인증확인</a>");
		//실서버 (경로 공인ip)
		content.append("<a href='http://115.85.182.210:8080/my/member/joinConfirm?authCode="+authCode+"'>이메일인증확인</a>");
		
		
		//보낼메일 객체 생성
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("회원가입 이메일 인증","utf-8"); //제목세팅
		message.setText(content.toString(), "utf-8", "html"); //내용세팅
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
		//메일보내기
		javaMailSender.send(message);
		
		return authCode;
	}

}
