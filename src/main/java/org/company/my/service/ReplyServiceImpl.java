package org.company.my.service;

import org.company.my.dto.Reply;

import java.util.List;

import org.company.my.dao.MemberDAO;
import org.company.my.dao.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO replyDAO;

	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional
	@Override
	public int insert(Reply reply) {
		//1) 글순서와 글레벨 +1셋팅 
		//  원본의댓글 기본 (0,0)이어서 자동 1,1된다
		
		reply.setRestep(reply.getRestep()+1);
		reply.setRelevel(reply.getRelevel()+1);
		
		//1) 내 밑에있는 모든 글순서 +1
		replyDAO.updateRestep(reply);

		return replyDAO.insert(reply);
	}

	@Override
	public List<Reply> selectList(int bnum) {
		return replyDAO.selectList(bnum);
	}

	@Override
	public int delete(int replyno) {
		return replyDAO.delete(replyno);
	}

	@Override
	public int update(Reply reply) {
		return replyDAO.update(reply);
	}
	
}
