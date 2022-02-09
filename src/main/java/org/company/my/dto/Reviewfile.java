package org.company.my.dto;

public class Reviewfile {
	private int reviewfileno;
	private int reviewno;
	private String filename;
	
	public Reviewfile() {
	}

	public Reviewfile(int reviewfileno, int reviewno, String filename) {
		this.reviewfileno = reviewfileno;
		this.reviewno = reviewno;
		this.filename = filename;
	}

	public int getReviewfileno() {
		return reviewfileno;
	}

	public void setReviewfileno(int reviewfileno) {
		this.reviewfileno = reviewfileno;
	}

	public int getReviewno() {
		return reviewno;
	}

	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Reviewfile [reviewfileno=" + reviewfileno + ", reviewno=" + reviewno + ", filename=" + filename + "]";
	}


	
	
}
