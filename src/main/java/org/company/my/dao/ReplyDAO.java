package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Reply;

public interface ReplyDAO {
	int insert(Reply reply);
	int updateRestep(Reply reply);
	List<Reply> selectList(int reviewno);
	int delete(int replyno);
	int update(Reply reply);
}
