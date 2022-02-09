package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Reviewfile;

public interface ReviewFileDAO {
	public int insert(Reviewfile reviewFile);

	public List<Reviewfile> selectList(int reviewno);

	public int delete(int reviewfileno);
}
