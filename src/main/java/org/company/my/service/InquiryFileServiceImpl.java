package org.company.my.service;

import java.io.File;
import java.io.IOException;

import org.company.my.dto.Inquiryfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InquiryFileServiceImpl implements InquiryFileService{

	// root-context.xml에 빈생성
	@Autowired
	private String saveDir;
	
	
	// 공통모듈
	@Override
	public String fileUpload(MultipartFile file) throws Exception{
		// 파일을 폴더에 저장하고 파일명을 리턴
		// 업로드파일이름 : 시스템의시간+실제파일이름으로 셋팅
		String originFilename = file.getOriginalFilename();
		
		// 파일이 없을경우
		if (originFilename.equals("")) return "";
		
		// 파일이름 셋팅
		String filename =System.currentTimeMillis() + "_" + originFilename;
		// 업로드경로, 파일명
		File f = new File(saveDir, filename);
		file.transferTo(f); //업로드경로에 파일 저장
		return filename;
	}


	
}
