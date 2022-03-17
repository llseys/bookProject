package org.company.my.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.company.my.dto.Member;
import org.company.my.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	//회원가입폼으로 이동
	@GetMapping(value="join")
	public String join() {
		return "member/join";
	}
	
	//테스트폼으로 이동(임시 상세조회이동용)
	@GetMapping(value="list")
	public String list(Model model) {
		List<Member> mlist = memberService.selectList(); //회원전체 보이기
		model.addAttribute("mlist", mlist);
		return "list";
	}
	
	//회원가입하기 
	@PostMapping(value="join")
	public String join(@ModelAttribute Member member,HttpSession session, RedirectAttributes rattr) throws Exception {
		Map<String, Object> result = memberService.insert(member);
		String code = (String)result.get("code"); // 1성공 0실패
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		System.out.println("코드 : " + code);
		if(code.equals("1")) { //성공
			//이메일 인증번호(만든거)를 세션에 넣기 
			session.setAttribute("authCode", result.get("authCode"));
			// 이메일인증(링크클릭)되면 'emailauth' 컬럼을 변경해야하기에 세션에 이메일을 올린다
			// 근데 로그인전인데 로그인할때도 세션에 이메일을 올리는데 명이 겹친다 그래서 authemail로 바꿨다
			session.setAttribute("authemail", member.getUserid()); 
			
			return "redirect:/login";
		}else { 
			return "redirect:/member/join";
		}
	}
	
	//주소팝업창 호출
	// jusoPopup.jsp 이동시는 get방식
	// 주소를 검색 후 callback시에는 post방식
	@RequestMapping("jusoPopup")
	public String jusoPopup() {
		return "member/jusoPopup";
	}
	

	// 이메일에서 인증링크 클릭시 처리 (callback주소)
	@GetMapping("joinConfirm")
	public String joinConfirm(@RequestParam String authCode, HttpSession session, RedirectAttributes attr) {
		String authCodeS = (String)session.getAttribute("authCode");
		String msg = "이메일 인증실패";
		if(authCode.equals(authCodeS)) { //이메일 인증이 됐다면
			String userid = (String)session.getAttribute("authemail");
			msg = memberService.update_emailAuth(userid); //
		}
		attr.addFlashAttribute("msg", msg);

		return "redirect:/login";
	}
	
	
	//수정폼으로 이동
	@GetMapping(value="modify")
	public String modify(@RequestParam String userid, Model model) {
		Member member = memberService.selectOne(userid);
		model.addAttribute("member", member);
		return "member/modify";
	}
	
	//수정하기
	@PostMapping(value="modify")
	public String modify(@ModelAttribute Member member, Model model, RedirectAttributes rattr) {
		Map<String, Object> result = memberService.update(member);
		String code = (String)result.get("code"); // 1성공 0실패
		String msg = (String)result.get("msg");

		if(code.equals("1")) { //성공
			rattr.addFlashAttribute("msg", msg);
			return "redirect:list";
		}else { //실패
			model.addAttribute("msg", msg);
			return "member/modify";
		}
	}
	
	// 삭제하기
	@GetMapping(value="remove")
	public String remove(@RequestParam  String userid, String userpw, Model model, RedirectAttributes rattr) {
		Map<String, Object> result = memberService.delete(userid, userpw);
		String code = (String)result.get("code"); // 1성공 0실패
		String msg = (String)result.get("msg");

		if(code.equals("1")) { //성공
			rattr.addFlashAttribute("msg", msg);
			return "redirect:list";
		}else { //실패
			rattr.addFlashAttribute("msg", msg);
			return "redirect:modify?userid="+userid;
		}
	}

	
	
	
	
	
	
	
}
