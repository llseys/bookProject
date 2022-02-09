package org.company.my.dao;

import java.util.List;
import java.util.Map;

import org.company.my.dto.Page;
import org.company.my.dto.Review;

public interface ReviewDAO {
	public int insert(Review review);

	public List<Review> selectList(Page page);

	public Review selectOne(int reviewno);

	public int likeUp(int reviewno);

	public int selectTotcnt(Page page);

	public int delete(int reviewno);

	public int update(Review review);
}
