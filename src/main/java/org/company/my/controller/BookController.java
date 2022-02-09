package org.company.my.controller;

import java.sql.Array;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.company.my.dto.Book;
import org.company.my.dto.Page;
import org.company.my.dto.Review;
import org.company.my.service.BookService;
import org.company.my.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping("serch")
	public String serch(@ModelAttribute Page page, Model model) throws Exception {
		page.setBookKind("s");
		List<Book> blist = bookService.serch(page);	
		model.addAttribute("blist", blist);
		model.addAttribute("bname", page.getBname());
		return "book/bookList";
	}
	
	@GetMapping("bookInfo")
	public String bookInfo(@ModelAttribute Page page, Model model) {
		String isbn = page.getIsbn();
		Book book = bookService.selectOne(isbn);
		List<Review> rlist =reviewService.selectList(page);
		model.addAttribute("book", book);
		model.addAttribute("rlist", rlist);
		return "book/bookInfo";
	}
	
	@GetMapping("reviewAdd")
	public String reviewAdd(@RequestParam String isbn, Model model){
		model.addAttribute("isbn", isbn);
		return "book/reviewAdd";
	}
	
	@PostMapping("reviewAdd")
	public String reviewAdd(@ModelAttribute Review review, RedirectAttributes rattr) throws Exception{
		Map<String, Object> result = reviewService.insert(review);
		String msg = (String)result.get("msg");
		String isbn = review.getIsbn();
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/book/bookInfo?isbn="+isbn;
	}
	
	@GetMapping("reviewInfo")
	public String reviewInfo(@RequestParam int reviewno, Model model){
		Map<String, Object> result = reviewService.selectOne(reviewno);
		Review review = (Review)result.get("review");
		String nicname = (String)result.get("nicname");
		String isbn = review.getIsbn();
		Book book = bookService.selectOne(isbn);
		
		model.addAttribute("review", review);
		model.addAttribute("nicname", nicname);
		model.addAttribute("book", book);
		return "/book/reviewInfo";
	} 
	
	// 게시물 수정폼이동
		@GetMapping("modify")
		public String modify(@RequestParam int reviewno, Model model) {
			Map<String, Object> result = reviewService.selectOne(reviewno);
			model.addAttribute("result", result);
			return "/book/reviewModify";
		}
		
		
		// 게시물수정
		@PostMapping("modify")                                      // (required =false): 데이터가 없을수도있다
		public String modify(@ModelAttribute Review review, @RequestParam(required =false) List<Integer> removeFile
				            , HttpServletRequest request, RedirectAttributes rattr) throws Exception {
			Map<String, Object> result = reviewService.update(review, removeFile);
			String msg = (String)result.get("msg");
			return "redirect:/book/reviewInfo?reviewno="+review.getReviewno();
		}
	
		
		@Transactional	
		// 게시물 삭제
		@GetMapping("remove")
		public String remove(@RequestParam int reviewno, Model model, RedirectAttributes rattr) {
			// 삭제후 도서상세폼으로 이동시 필요한 isbn 구하기
			Map<String, Object> result_select = reviewService.selectOne(reviewno);
			Review review = (Review)result_select.get("review");
			String isbn = review.getIsbn();
			// 게시물삭제
			Map<String, Object> result_del = reviewService.delete(reviewno);
			String msg = (String)result_del.get("msg");
			rattr.addFlashAttribute("msg", msg);
			return "redirect:/book/bookInfo?isbn="+isbn;
		}
	
	
	
	
	@ResponseBody
	@PutMapping(value="/likeUp/{reviewno}", produces = "application/text; charset=utf-8")
	public String likeUp(@PathVariable("reviewno") int reviewno){
		//좋아요 +1
		reviewService.likeUp(reviewno);
		//좋아요후 조회
		Map<String, Object> result = reviewService.selectOne(reviewno);
		Review review = (Review)result.get("review");
		int likehit = review.getLikehit();
		return String.valueOf(likehit);
	}

	
	//베스트셀러
	@GetMapping("bestbook")
	public String bestbook(@ModelAttribute Page page, Model model) throws Exception{
		page.setBookKind("b");
		List<Book> bestList = bookService.bestbookList(page);	
		model.addAttribute("bestList", bestList);
		return "book/bestBookList";
	}
	
	
	//신간도서 클릭
	@GetMapping("newbook")
	public String newbook(@ModelAttribute Page page, Model model) throws Exception{
		page.setBookKind("n");
		List<Book> newList = bookService.newbookList(page);	
		model.addAttribute("newList", newList);
		return "book/newBookList";
	}
	
	
	
}
