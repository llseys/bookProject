package org.company.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insert(Reply reply) {
		return sqlSession.insert("org.company.my.ReplyMapper.insert", reply);
	}
	
	// 글순서 +1
	@Override
	public int updateRestep(Reply reply) {
		return sqlSession.update("org.company.my.ReplyMapper.updateRestep", reply);
	}

	@Override
	public List<Reply> selectList(int reviewno) {
		return sqlSession.selectList("org.company.my.ReplyMapper.selectList", reviewno);
	}

	@Override
	public int delete(int replyno) {
		return sqlSession.delete("org.company.my.ReplyMapper.delete", replyno);
	}

	@Override
	public int update(Reply reply) {
		return sqlSession.update("org.company.my.ReplyMapper.update", reply);
	}
}
