package org.company.my.dto;

public class Inquiryfile {

	private int inquiryfileno;
	private int inquiryno;
	private String filename;
	
	public Inquiryfile() {
	}

	public Inquiryfile(int inquiryfileno, int inquiryno, String filename) {
		this.inquiryfileno = inquiryfileno;
		this.inquiryno = inquiryno;
		this.filename = filename;
	}

	public int getInquiryfileno() {
		return inquiryfileno;
	}

	public void setInquiryfileno(int inquiryfileno) {
		this.inquiryfileno = inquiryfileno;
	}

	public int getInquiryno() {
		return inquiryno;
	}

	public void setInquiryno(int inquiryno) {
		this.inquiryno = inquiryno;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Inquiryfile [inquiryfileno=" + inquiryfileno + ", inquiryno=" + inquiryno + ", filename=" + filename
				+ "]";
	}

}
