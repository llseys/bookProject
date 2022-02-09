package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Reviewfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewFileDAOImpl implements ReviewFileDAO{
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public int insert(Reviewfile reviewFile) {
		return sqlSession.insert("org.company.my.ReviewFileMapper.insert", reviewFile);
	}


	@Override
	public List<Reviewfile> selectList(int reviewno) {
		return sqlSession.selectList("org.company.my.ReviewFileMapper.selectList", reviewno);
	}


	@Override
	public int delete(int reviewfileno) {
		return sqlSession.delete("org.company.my.ReviewFileMapper.delete", reviewfileno);
	}

}
