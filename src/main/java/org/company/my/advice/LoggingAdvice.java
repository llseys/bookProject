package org.company.my.advice;

import java.util.Arrays;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	
	@Before("execution(* org.company.my.controller.*.*(..))" )
	public void beforeLog(JoinPoint jp) {
 		System.out.println(jp.getSignature().toShortString() +" / 매개변수 : " + Arrays.toString(jp.getArgs()));
	}
	
	@AfterReturning(pointcut="execution(* org.company.my.service.*.*(..))", returning="rObj")
	public void afterLog(JoinPoint jp, Object rObj) {
		System.out.println("실행 클래스+메서드 : " + jp.getSignature().toShortString());
		if(rObj!=null)
			System.out.println("리턴값 : " + rObj.toString());
		else
			System.out.println("리턴값 없다(null)");
	}
	
	
}
