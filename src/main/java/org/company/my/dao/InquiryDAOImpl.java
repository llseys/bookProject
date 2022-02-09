package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Inquiry;
import org.company.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryDAOImpl implements InquiryDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(Inquiry inq) {
		return sqlSession.insert("org.company.my.InquiryMapper.insert", inq);
	}

	@Override
	public int update(Inquiry inq) {
		return sqlSession.update("org.company.my.InquiryMapper.update", inq);
	}

	@Override
	public int delete(int inquiryno) {
		return sqlSession.delete("org.company.my.InquiryMapper.delete", inquiryno);
	}

	@Override
	public Inquiry selectOne(int inquiryno) {
		return sqlSession.selectOne("org.company.my.InquiryMapper.selectOne", inquiryno);
	}

	@Override
	public List<Inquiry> selectList(Page page) {
		return sqlSession.selectList("org.company.my.InquiryMapper.selectList", page);
	}

	@Override
	public int selectTotcnt(Page page) {
		return sqlSession.selectOne("org.company.my.InquiryMapper.selectTotcnt", page);
	}

}
