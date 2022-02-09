package org.company.my.dto;

//검색+페이징처리

public class Page {

	private int curPage=1; //현재페이지
	private int perPage=10; //한페이지당 게시물 수
	private int perBlock=10; //한화면당 페이지 수
	
	private int totPage; //전체 페이지 수
	private int startNum; //시작번호
	private int endNum; // 끝번호
	private int startPage; //시작 페이지
	private int endPage; //끝 페이지
	
	
	
	private String isbn;
	private String bookKind; //책종류==> s:기본책 / b:베스트셀러 /n:신간도서
	private String bname;

	public Page() {
	}

	public Page(String isbn, String bookKind, String bname, int curPage, int perPage, int perBlock, int totPage,
			int startNum, int endNum, int startPage, int endPage) {
		this.isbn = isbn;
		this.bookKind = bookKind;
		this.bname = bname;
		this.curPage = curPage;
		this.perPage = perPage;
		this.perBlock = perBlock;
		this.totPage = totPage;
		this.startNum = startNum;
		this.endNum = endNum;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookKind() {
		return bookKind;
	}

	public void setBookKind(String bookKind) {
		this.bookKind = bookKind;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getPerBlock() {
		return perBlock;
	}

	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "Page [isbn=" + isbn + ", bookKind=" + bookKind + ", bname=" + bname + ", curPage=" + curPage
				+ ", perPage=" + perPage + ", perBlock=" + perBlock + ", totPage=" + totPage + ", startNum=" + startNum
				+ ", endNum=" + endNum + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

}
