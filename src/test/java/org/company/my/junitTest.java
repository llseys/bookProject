package org.company.my;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.company.my.dao.MemberDAO;
import org.company.my.dto.Member;
import org.company.my.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//스프링 테스트하려면 (spring-test pom.xml에 라이브러리 추가)
//객체를 생성하기 위한 어노테이션 추가
@RunWith(SpringJUnit4ClassRunner.class) //서버를 대신 실행
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"  //파일을 실행해서 객체생성
							   ,"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"}) 
public class junitTest {
	@Autowired
	private DataSource dataSource;	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BookService bookService;
	
	
	@Test
	public void dataSourceTest() {
		try {
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sqlSessionFactoryTest() {
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void sqlSessionTest() {
		System.out.println(sqlSession);
	}
	
	// 회원추가 테스트
	@Test
	public void memberInsertTest() {
		Member member = new Member();
		member.setUserid("java123");
		member.setUserpw("123");
		member.setNicname("자바킹");
		member.setUsername("강자바");
		member.setEmail("java123@naver.com");
		member.setTel("01088881111");
		member.setZipcode("12345");
		member.setAddr("aa");
		member.setAddrdetail("123123");
		member.setEmail("0");
		member.setSimplejoin("0");
		
		int cnt = memberDAO.insert(member);
		System.out.println(cnt);
	}
	
	@Test
	public void memberUpdateTest() {
		Member member = new Member();
		member.setUserid("java123");
		member.setUserpw("333");
		member.setNicname("자바12123");
		member.setUsername("강자바");
		member.setEmail("java55123@naver.com");
		member.setTel("010888851111");
		int cnt = memberDAO.update(member);
		System.out.println(cnt);
	}
	
	@Test
	public void memberDeleteTest() {
		int cnt = memberDAO.delete("java123");
		System.out.println(cnt);
	}
	
	@Test
	public void memberselectOneTest() {
		Member member = memberDAO.selectOne("java123");
		System.out.println(member);
	}
	

	

}
