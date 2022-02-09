package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Book;
import org.company.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<Book> selectList(Page page) {
		return sqlSession.selectList("org.company.my.BookMapper.selectList", page);
	}


	@Override
	public Book selectOne(String isbn) {
		return sqlSession.selectOne("org.company.my.BookMapper.selectOne", isbn);
	}


	@Override
	public List<Book> bestSelectList(Page page) {
		return sqlSession.selectList("org.company.my.BookMapper.bestSelectList", page);
	}


	@Override
	public int selectTotcnt(Page page) {
		return sqlSession.selectOne("org.company.my.BookMapper.selectTotcnt", page);
	}


	@Override
	public List<Book> newSelectList(Page page) {
		return sqlSession.selectList("org.company.my.BookMapper.newSelectList", page);
	}


	@Override
	public int selectTotcnt_serch(Page page) {
		return sqlSession.selectOne("org.company.my.BookMapper.selectTotcnt_serch", page);
	}

}
