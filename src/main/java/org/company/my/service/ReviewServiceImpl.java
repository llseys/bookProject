package org.company.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.company.my.dao.MemberDAO;
import org.company.my.dao.ReviewDAO;
import org.company.my.dao.ReviewFileDAO;
import org.company.my.dto.Inquiryfile;
import org.company.my.dto.Member;
import org.company.my.dto.Page;
import org.company.my.dto.Review;
import org.company.my.dto.Reviewfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO reviewDAO;

	@Autowired
	private ReviewFileDAO reviewFileDAO;
	@Autowired
	private MemberDAO memberDAO;
	

	
	@Autowired
	private ReviewFileService reviewFileService;
	@Override
	public Map<String, Object> insert(Review review) throws Exception {
		reviewDAO.insert(review);
		
		Reviewfile reviewFile = new Reviewfile();
		reviewFile.setReviewno(review.getReviewno());
		
		List<MultipartFile> files = review.getFiles();
		for(MultipartFile file : files) {
			String filename = reviewFileService.fileUpload(file);
			if(!filename.equals("")) {
				reviewFile.setFilename(filename);
				reviewFileDAO.insert(reviewFile);
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("msg", "리뷰등록완료");
		
		return result;	
	}

	@Override
	public List<Review> selectList(Page page) {
		// page 처리 값 구하기
		
		int curPage = page.getCurPage(); //현재페이지
		int perPage = page.getPerPage(); //한페이지당 게시물수
		int perBlock = page.getPerBlock(); // 화면 페이지 수
		
		// 1) 전체게시물수
		int totcnt = reviewDAO.selectTotcnt(page);
		
		// 2) 전체페이지수
		int totPage = totcnt / perPage; //전체게시물 / 한페이지당 게시물수
		
		if(totcnt % perPage !=0){ //나머지 있는경우
			totPage = totPage+1;
		}
		
		page.setTotPage(totPage);
		
		// 3) 시작번호
		int startNum = (curPage -1)*perPage+1; // 오라클을 1번부터 시작
//		int startNum = (curPage -1)*perPage; //mysql은 0번부터시작해야함
		page.setStartNum(startNum);
		// 4) 끝번호
		int endNum = startNum + perPage -1; //시작번호 + 한페이지당 게시물 수 -1
		page.setEndNum(endNum);
		// 5) 시작페이지
		int startPage = curPage -((curPage-1)%perBlock);  //현재페이지 - ((현재페이지-1)%한 화면당 페이지수)
		page.setStartPage(startPage);
		// 6) 끝페이지
		int endPage = startPage + perBlock -1; // 시작페이지 + 한 화면당 페이지 수 -1
		if(endPage>totPage) endPage = totPage; // 맨끝 페이지는 전체페이지수보다 클수없다
		page.setEndPage(endPage);
	
		return reviewDAO.selectList(page);
	}

	@Transactional
	@Override
	public Map<String, Object> selectOne(int reviewno) {
		// 리턴값 : 1)review한건 2)member의 nicname 3)inquiryfile 구하기
		//1)
		Review review = reviewDAO.selectOne(reviewno);
		//2)
		System.out.println(reviewno);
		System.out.println(review);
		String userid = review.getUserid();
		Member member = memberDAO.selectOne(userid);
		String nicname = member.getNicname();
		//3)	
		List<Reviewfile> rfList = reviewFileDAO.selectList(reviewno);

		Map<String, Object> result = new HashMap<>();
		result.put("review", review);
		result.put("nicname", nicname);
		result.put("rfList", rfList);
		
		return result;
	}

	@Override
	public int likeUp(int reviewno) {
		return reviewDAO.likeUp(reviewno);
	}

	@Override
	public Map<String, Object> update(Review review, List<Integer> removeFile) throws Exception {
		// 1)게시물 수정
		reviewDAO.update(review);

		// 2)게시물 파일삭제
		if(removeFile!=null) {
			for(int reviewfileno : removeFile) {
				reviewFileDAO.delete(reviewfileno);
			}	
		}

		// 3)게시물 파일추가
		List<MultipartFile> files = review.getFiles();
		
		if(files!=null) {      // ==> 수정폼에서 파일선택 input을 마이너스아이콘 클릭으로 다 없앤뒤 수정할경우
			Reviewfile reviewFile = new Reviewfile();
			reviewFile.setReviewno(review.getReviewno());

			for(MultipartFile file : files) {
				String filename = reviewFileService.fileUpload(file);
				if(!filename.equals("")) {  // 파일이름이 공백이 아닐때 저장
					reviewFile.setFilename(filename);
					reviewFileDAO.insert(reviewFile);
				}
			}	
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "수정완료");
		
		return result;
	}

	@Override
	public Map<String, Object> delete(int reviewno) {
		
		Map<String, Object> result = new HashMap<>();
		
		if(reviewDAO.delete(reviewno)>0) {
			result.put("msg", "삭제가 완료되었습니다.");
		}	
		return result;
	}

}
