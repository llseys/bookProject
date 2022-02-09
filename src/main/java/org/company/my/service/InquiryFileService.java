package org.company.my.service;

import org.company.my.dto.Inquiryfile;
import org.springframework.web.multipart.MultipartFile;

public interface InquiryFileService {
	public String fileUpload(MultipartFile file)throws Exception;

}
