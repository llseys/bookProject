package org.company.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.company.my.dao.InquiryDAO;
import org.company.my.dao.InquiryFileDAO;
import org.company.my.dao.MemberDAO;
import org.company.my.dto.Inquiry;
import org.company.my.dto.Inquiryfile;
import org.company.my.dto.Member;
import org.company.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InquiryServiceImpl implements InquiryService{
	@Autowired
	private InquiryDAO inquiryDAO; 
	@Autowired
	private InquiryFileService inquiryFileService; 
	@Autowired
	private InquiryFileDAO inquiryFileDAO;
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public List<Inquiry> selectList(Page page) {
	// page 처리 값 구하기
		
		int curPage = page.getCurPage(); //현재페이지
		int perPage = page.getPerPage(); //한페이지당 게시물수
		int perBlock = page.getPerBlock(); // 화면 페이지 수
		
		// 1) 전체게시물수
		int totcnt = inquiryDAO.selectTotcnt(page);
		
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
	
		return inquiryDAO.selectList(page);
	}

	
	
	//문의글추가
	@Transactional
	@Override
	public Map<String, Object> insert(Inquiry inq) throws Exception {
		// Inquiry에 insert 저장
		inquiryDAO.insert(inq);
		
		// 
		Inquiryfile inqFile = new Inquiryfile();
		inqFile.setInquiryno(inq.getInquiryno());
		
		List<MultipartFile> files = inq.getFiles();
		for(MultipartFile file : files) {
			String filename = inquiryFileService.fileUpload(file);
			if(!filename.equals("")) {
				inqFile.setFilename(filename);
				inquiryFileDAO.insert(inqFile);
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", "1");
		result.put("msg", "게시글이 등록되었습니다.");
		
		return result;
	}
	
	// 문의글한건조회 
	// 리턴값 : 1)inquiry한건 2)member의 nicname 3)inquiryfile 구하기
	@Transactional
	@Override
	public Map<String, Object> selectOne(int inquiryno) {
		//1)inquiry한건 구하기
		Inquiry inq = inquiryDAO.selectOne(inquiryno);
		//2)member의 nicname구하기
		// inq의 userid 빼내서 member의 pk로 selectOne후 nicname만 추출
		Member member = memberDAO.selectOne(inq.getUserid());
		String nicname = member.getNicname();
		//3) inquiryfile selectList(inquiryno) 구하기
		List<Inquiryfile> inqFileList = inquiryFileDAO.selectList(inquiryno);
		
		Map<String, Object> result = new HashMap<>();
		result.put("inq", inq);
		result.put("nicname", nicname);
		result.put("inqFileList", inqFileList);
		return result;
	}
	
	@Override
	@Transactional
	public Map<String, Object> modify(Inquiry inq, List<Integer> removeFile) throws Exception {

		//1) inquiry 수정
		inquiryDAO.update(inq);
		
		//2) inquiryfile 파일삭제
		if(removeFile!=null) {
			for(int inquiryfileno : removeFile) {
				inquiryFileDAO.delete(inquiryfileno);
			}
		}
		//3) inquiryfile 파일 다시추가
		
		List<MultipartFile> files = inq.getFiles();
		
		if(files!=null) { // ==> 수정폼에서 파일선택 input을 마이너스아이콘 클릭으로 다 없앤뒤 수정할경우
			Inquiryfile inqFile = new Inquiryfile();
			inqFile.setInquiryno(inq.getInquiryno());
				
			for(MultipartFile file : files) {
				String filename = inquiryFileService.fileUpload(file);
				if(!filename.equals("")) {
					inqFile.setFilename(filename);
					inquiryFileDAO.insert(inqFile);
				}
			}	
		}
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "수정이 완료되었습니다");
		
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> delete(int inquiryno) {
		
		// 무결성제약 
		// inquiryfile 글 먼저 삭제후 
		// inquiry 글 삭제
		inquiryFileDAO.delete(inquiryno);
		
		
		Map<String, Object> result = new HashMap<>();
		int cnt = inquiryDAO.delete(inquiryno);
		if(cnt>0) {
			result.put("msg", "게시글이 삭제되었습니다.");
			return result;
		}else {
			result.put("msg", "삭제실패");
			return result;
		}	
	}



	
	

}
