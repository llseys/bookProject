package org.company.my.service;

import java.util.List;
import java.util.Map;

import org.company.my.dto.Inquiry;
import org.company.my.dto.Page;

public interface InquiryService {

	List<Inquiry> selectList(Page page);

	Map<String, Object> insert(Inquiry inq)throws Exception;

	Map<String, Object> selectOne(int inquiryno);

	Map<String, Object> modify(Inquiry inq, List<Integer> removeFile)throws Exception;

	Map<String, Object> delete(int inquiryno);



}
