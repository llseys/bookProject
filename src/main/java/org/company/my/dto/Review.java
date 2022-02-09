package org.company.my.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Review {
	private int reviewno;
	private String isbn;
	private String userid;
	private String nicname;
	private String subject;
	private String content;
	private int readhit;
	private int likehit;
	private Date regidate;
	
	private List<MultipartFile> files; //실제파일
	
	public Review() {
	}

	public Review(int reviewno, String isbn, String userid, String nicname, String subject, String content, int readhit,
			int likehit, Date regidate, List<MultipartFile> files) {
		this.reviewno = reviewno;
		this.isbn = isbn;
		this.userid = userid;
		this.nicname = nicname;
		this.subject = subject;
		this.content = content;
		this.readhit = readhit;
		this.likehit = likehit;
		this.regidate = regidate;
		this.files = files;
	}

	public int getReviewno() {
		return reviewno;
	}

	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNicname() {
		return nicname;
	}

	public void setNicname(String nicname) {
		this.nicname = nicname;
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
		return "Review [reviewno=" + reviewno + ", isbn=" + isbn + ", userid=" + userid + ", nicname=" + nicname
				+ ", subject=" + subject + ", content=" + content + ", readhit=" + readhit + ", likehit=" + likehit
				+ ", regidate=" + regidate + ", files=" + files + "]";
	}

	
}	
