package org.company.my.dto;

import java.sql.Date;

public class Book {
	private String isbn;
	private String title;
	private String description;
	private String category;
	private String author;
	private String publisher;
	private String pubdate;
	private String smallfileurl;
	private String bigfileurl;
	private double reviewrank;
	private String bookKind="s";
	
	
	public Book() {
	}


	public Book(String isbn, String title, String description, String category, String author, String publisher,
			String pubdate, String smallfileurl, String bigfileurl, double reviewrank, String bookKind) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.category = category;
		this.author = author;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.smallfileurl = smallfileurl;
		this.bigfileurl = bigfileurl;
		this.reviewrank = reviewrank;
		this.bookKind = bookKind;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getPubdate() {
		return pubdate;
	}


	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}


	public String getSmallfileurl() {
		return smallfileurl;
	}


	public void setSmallfileurl(String smallfileurl) {
		this.smallfileurl = smallfileurl;
	}


	public String getBigfileurl() {
		return bigfileurl;
	}


	public void setBigfileurl(String bigfileurl) {
		this.bigfileurl = bigfileurl;
	}


	public double getReviewrank() {
		return reviewrank;
	}


	public void setReviewrank(double reviewrank) {
		this.reviewrank = reviewrank;
	}


	public String getBookKind() {
		return bookKind;
	}


	public void setBookKind(String bookKind) {
		this.bookKind = bookKind;
	}


	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", author=" + author + ", publisher=" + publisher + ", pubdate=" + pubdate + ", smallfileurl="
				+ smallfileurl + ", bigfileurl=" + bigfileurl + ", reviewrank=" + reviewrank + ", bookKind=" + bookKind
				+ "]";
	}

	

}
