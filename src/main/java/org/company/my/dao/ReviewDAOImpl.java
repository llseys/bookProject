package org.company.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Page;
import org.company.my.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImpl implements ReviewDAO{
	@Autowired
	private SqlSession sqlSession;
	
	

	@Override
	public int insert(Review review) {
		return sqlSession.insert("org.company.my.ReviewMapper.insert", review);
	}



	@Override
	public List<Review> selectList(Page page) {
		return sqlSession.selectList("org.company.my.ReviewMapper.selectList", page);
	}



	@Override
	public Review selectOne(int reviewno) {
		return sqlSession.selectOne("org.company.my.ReviewMapper.selectOne", reviewno);
	}



	@Override
	public int likeUp(int reviewno) {
		return sqlSession.update("org.company.my.ReviewMapper.likeUp", reviewno);
	}



	@Override
	public int selectTotcnt(Page page) {
		return sqlSession.selectOne("org.company.my.ReviewMapper.selectTotcnt", page);
	}



	@Override
	public int delete(int reviewno) {
		return sqlSession.delete("org.company.my.ReviewMapper.delete", reviewno);
	}



	@Override
	public int update(Review review) {
		return sqlSession.update("org.company.my.ReviewMapper.update", review);
	}

}
