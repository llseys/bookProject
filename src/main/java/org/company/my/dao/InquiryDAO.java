package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Inquiry;
import org.company.my.dto.Page;

public interface InquiryDAO {
	public int insert(Inquiry inq);
	public int update(Inquiry inq);
	public int delete(int inquiryno);
	public Inquiry selectOne(int inquiryno);
	public List<Inquiry> selectList(Page page);
	public int selectTotcnt(Page page);
}
