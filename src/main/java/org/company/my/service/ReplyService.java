package org.company.my.service;

import java.util.List;

import org.company.my.dto.Reply;

public interface ReplyService {
	int insert(Reply reply);

	List<Reply> selectList(int reviewno);
	
	int delete(int replyno);
	
	int update(Reply reply);
}
