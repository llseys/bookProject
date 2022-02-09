package org.company.my.service;

import java.util.List;
import java.util.Map;

import org.company.my.dto.Page;
import org.company.my.dto.Review;

public interface ReviewService {

	Map<String, Object> insert(Review review) throws Exception;

	List<Review> selectList(Page page);

	Map<String, Object> selectOne(int reviewno);

	int likeUp(int reviewno);

	Map<String, Object> update(Review review, List<Integer> removeFile) throws Exception;

	Map<String, Object> delete(int reviewno);

}
