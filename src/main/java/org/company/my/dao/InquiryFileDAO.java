package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Inquiry;
import org.company.my.dto.Inquiryfile;

public interface InquiryFileDAO {
	public int insert(Inquiryfile inqFile);

	public List<Inquiryfile> selectList(int inquiryno);

	public int delete(int inquiryfileno);

}
