package org.company.my.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ReviewFileService {
	public String fileUpload(MultipartFile file)throws Exception;
}
