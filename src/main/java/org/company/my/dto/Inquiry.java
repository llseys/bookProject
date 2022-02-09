package org.company.my.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Inquiry {
	private int inquiryno;
	private String userid;
	private String subject;
	private String content;
	private int readhit;
	private int likehit;
	private Date regidate;
	
	private List<MultipartFile> files; //실제파일
	
	public Inquiry() {
	}

	public Inquiry(int inquiryno, String userid, String subject, String content, int readhit, int likehit,
			Date regidate, List<MultipartFile> files) {
		this.inquiryno = inquiryno;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.readhit = readhit;
		this.likehit = likehit;
		this.regidate = regidate;
		this.files = files;
	}

	public int getInquiryno() {
		return inquiryno;
	}

	public void setInquiryno(int inquiryno) {
		this.inquiryno = inquiryno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	public int getLikehit() {
		return likehit;
	}

	public void setLikehit(int likehit) {
		this.likehit = likehit;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Inquiry [inquiryno=" + inquiryno + ", userid=" + userid + ", subject=" + subject + ", content="
				+ content + ", readhit=" + readhit + ", likehit=" + likehit + ", regidate=" + regidate + ", files="
				+ files + "]";
	}

	




	
	
}
