package org.company.my.dao;

import java.util.List;

import org.company.my.dto.Book;
import org.company.my.dto.Page;

public interface BookDAO {

	List<Book> selectList(Page page);

	Book selectOne(String isbn);

	List<Book> bestSelectList(Page page);

	int selectTotcnt(Page page);

	List<Book> newSelectList(Page page);

	int selectTotcnt_serch(Page page);

}
