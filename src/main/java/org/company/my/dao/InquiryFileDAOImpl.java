package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Inquiry;
import org.company.my.dto.Inquiryfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryFileDAOImpl implements InquiryFileDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Inquiryfile inqFile) {
		return sqlSession.insert("org.company.my.InquiryFileMapper.insert", inqFile);
	}

	@Override
	public List<Inquiryfile> selectList(int inquiryno) {
		return sqlSession.selectList("org.company.my.InquiryFileMapper.selectList", inquiryno);
	}

	@Override
	public int delete(int inquiryfileno) {
		return sqlSession.insert("org.company.my.InquiryFileMapper.delete", inquiryfileno);
	}








	
	
	
	
	
	
}
