package org.company.my.controller;

import java.util.List;
import java.util.Map;

import org.company.my.dto.Inquiry;
import org.company.my.dto.Inquiryfile;
import org.company.my.dto.Member;
import org.company.my.dto.Page;
import org.company.my.service.InquiryFileService;
import org.company.my.service.InquiryService;
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
@RequestMapping("inquiry")
public class InquiryController {
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private InquiryFileService inquiryFileService;  
	
	// 문의글리스트 이동
	@GetMapping("list")
	public String list(@ModelAttribute Page page, Model model) {
		List<Inquiry> inqList = inquiryService.selectList(page);
		model.addAttribute("inqList", inqList);
		return "inquiry/list";
	}
	
	// 문의글추가 이동
	@GetMapping("add")
	public String add() {
		return "inquiry/add";
	}
	
	// 문의글추가 이동
	@PostMapping("add")
	public String add(@ModelAttribute Inquiry inq, RedirectAttributes rattr) throws Exception {	
		Map<String, Object> result = inquiryService.insert(inq);
		String code = (String)result.get("code"); // 1성공 0실패
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		System.out.println("코드 : " + code);
		if(code.equals("1")) { //성공
			return "redirect:list";
		}else { //실패
			return "inquiry/add";
		}
	}
	
	// 문의글 상세조회
	@GetMapping("detail")
	public String detail(@RequestParam int inquiryno, Model model) {
		Map<String, Object> result = inquiryService.selectOne(inquiryno);
		Inquiry inq = (Inquiry)result.get("inq");
		String nicname= (String)result.get("nicname");
		
		model.addAttribute("nicname", nicname);
		model.addAttribute("inq", inq);

		return "inquiry/detail";
	}
	
	// 문의글수정폼으로 이동
	@GetMapping("modify")
	public String modify(@RequestParam int inquiryno, Model model) {
		Map<String, Object> result = inquiryService.selectOne(inquiryno);
		Inquiry inq = (Inquiry)result.get("inq");
		List<Inquiryfile> inqFileList = (List<Inquiryfile>)result.get("inqFileList");
		
		model.addAttribute("inq", inq);
		model.addAttribute("inqFileList", inqFileList);
		System.out.println(inqFileList);
		return "inquiry/modify";
	}
	
	// 문의글 수정하기
	@PostMapping("modify")//                                       (required =false) : 데이터 없을수도있다
	public String modify(@ModelAttribute Inquiry inq, @RequestParam(required =false) List<Integer> removeFile,
			             RedirectAttributes rattr) throws Exception {
		Map<String, Object> result = inquiryService.modify(inq, removeFile);
		String msg = (String)result.get("msg");
		
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:detail?inquiryno="+inq.getInquiryno();		
	}
	
	// 문의글삭제
	@GetMapping("remove")
	public String remove(@RequestParam int inquiryno, RedirectAttributes rattr) {
		Map<String, Object> result = inquiryService.delete(inquiryno);
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
