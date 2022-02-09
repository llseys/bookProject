package org.company.my.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.NoHandlerFoundException;

// 컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스

@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class) //예외발생하면 클래스실행
	public String common(Exception e) {
		e.printStackTrace();
//		System.out.println(e.toString());
		return "error/errorCommon"; //사용자에게 보여줄 화면
	}
	
	
	// NoHandlerFoundException 예외발생시 실행될 메서드 //
	@ExceptionHandler(NoHandlerFoundException.class) 
	public String commonNofound(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getRequestURL()+"이 잘못되었습니다");
		return "error/error404";
	}
	
	
	
	
	
	
	
}
