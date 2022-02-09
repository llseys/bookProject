package org.company.my.service;

import java.util.List;

import org.company.my.dto.Book;
import org.company.my.dto.Page;

public interface BookService {
	public List<Book> serch(Page page)throws Exception;
	public List<Book> selectList(Page page);
	public Book selectOne(String isbn);
	public List<Book> bestbookList(Page page)throws Exception;
	public List<Book> newbookList(Page page) throws Exception;
}
