package org.company.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.company.my.dto.Reply;
import org.company.my.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController 
@RequestMapping("reply")
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyservice;
	
	@ResponseBody
	@PostMapping("/")
	public String replyAdd(@RequestBody org.company.my.dto.Reply reply, HttpServletRequest request) {
		replyservice.insert(reply);
		return "ok";
	}
	
	@ResponseBody
	@GetMapping("/list/{reviewno}")
	public List<Reply> replyList(@PathVariable("reviewno") int reviewno) {
		List<Reply> rlist = replyservice.selectList(reviewno);
		return rlist;
	}
	
	//댓글수정
	@PutMapping("/")
	public String replyModify(@RequestBody Reply reply) {
		replyservice.update(reply);
		return "modi ok";
	}
	
	//댓글삭제
	@DeleteMapping("/{replyno}")
	public String replyRemove(@PathVariable("replyno") int replyno) {
		replyservice.delete(replyno);
		return "remove ok";
	}
	

	
	
	
	
}
