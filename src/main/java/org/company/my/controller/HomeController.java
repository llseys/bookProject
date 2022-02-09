package org.company.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.company.my.service.LoginService;
import org.company.my.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private NaverLoginService naverLoginService;
	
	
	//홈화면이동
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	// 로그인 폼
		@GetMapping("login")
		public void login(HttpSession session, Model model) throws Exception {
			//네이버 apiUrl호출
			Map<String, String> result = naverLoginService.getApiUrl();
			String apiURL = result.get("apiURL");
			String state = result.get("state");
			
			session.setAttribute("state", state); //인증값을 세션에 저장
			session.setMaxInactiveInterval(60*60*5);

			model.addAttribute("apiURL", apiURL); //apiUrl은 뷰로 	
		}
		
		// 로그인
		@PostMapping("login")
		public String login(@RequestParam String userid, @RequestParam String userpw,
							RedirectAttributes rattr, HttpSession session) {
			
			Map<String, Object> result = loginService.login(userid, userpw);
			// code) 0 : 로그인 성공
			// code) 1 : 이메일 미존재(없는아이디)
			// code) 2 : 비밀번호 불일치
			// code0 3 : 이메일 미인증
			
			int code = (int)result.get("code");
			String msg = (String)result.get("msg");
			rattr.addFlashAttribute("msg", msg);
			
			if(code==0) { //로그인성공 이동
				session.setAttribute("userid", userid); //세션생성
				session.setMaxInactiveInterval(60*60*5); //5시간 유지
				return "redirect:/";
				
			}else { //로그인실패 이동
				return "redirect:login";
			}
			
		}
		
		// 콜백주소 : naver_callback
		@RequestMapping("naver_callback")
		public String naver_callback(@RequestParam String code, String state, HttpSession session, RedirectAttributes rattr) throws Exception {
			//네이버의 개인정보 가져오기(email)
			Map<String, String> result = naverLoginService.getTokenUserInfo(code, state);
			String userid = result.get("email");
			//db에 회원을 등록
			Map<String, Object> resultInsert = naverLoginService.insert(userid);
			int rcode = (int)resultInsert.get("code");
			String msg = (String)resultInsert.get("msg");
			
			//일반가입 회원인 경우
			if(rcode > 1) { 
				rattr.addFlashAttribute("msg", msg);
				return "redirect:/login";
			}
			
			// 이미네이버가입회원이거나 , 네이버가입완료시
			// 로그인성공후 세션생성 권한부여
			session.setAttribute("userid", userid); //세션에 이메일넣기
			session.setMaxInactiveInterval(60*60*5);
			
			return "redirect:/";
		}
		
		// 로그아웃
		@GetMapping("logout")
		public String logout(HttpSession session) {
			session.invalidate(); //세션삭제
			return "redirect:/";
		}
	
	//맵보기
	@GetMapping("map")
	public String map() {
		return "map";
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------
	
	@GetMapping(value="images/demo/author-2.jpg")
	public String img1() {
		return "home";
	}
	@GetMapping(value="images/demo/author-4.jpg")
	public String img2() {
		return "home";
	}	@GetMapping(value="images/demo/portfolio-2.jpg")
	public String img3() {
		return "home";
	}	@GetMapping(value="images/demo/author-6.jpg")
	public String img4() {
		return "home";
	}	@GetMapping(value="images/demo/portfolio-1.jpg")
	public String img5() {
		return "home";
	}	@GetMapping(value="images/demo/portfolio-4.jpg")
	public String img6() {
		return "home";
	}	@GetMapping(value="images/demo/portfolio-3.jpg")
	public String img7() {
		return "home";
	}	@GetMapping(value="images/demo/portfolio-5.jpg")
	public String img8() {
		return "home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
