package org.company.my.dto;

import java.sql.Date;

public class Reply {

	private int replyno;
	private int reviewno;
	private String nicname;
	private String content;
	private int restep;
	private int relevel;
	private Date regidate;
	
	public Reply() {
	}

	public Reply(int replyno, int reviewno, String nicname, String content, int restep, int relevel, Date regidate) {
		this.replyno = replyno;
		this.reviewno = reviewno;
		this.nicname = nicname;
		this.content = content;
		this.restep = restep;
		this.relevel = relevel;
		this.regidate = regidate;
	}

	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}

	public int getReviewno() {
		return reviewno;
	}

	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}

	public String getNicname() {
		return nicname;
	}

	public void setNicname(String nicname) {
		this.nicname = nicname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRestep() {
		return restep;
	}

	public void setRestep(int restep) {
		this.restep = restep;
	}

	public int getRelevel() {
		return relevel;
	}

	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "Reply [replyno=" + replyno + ", reviewno=" + reviewno + ", nicname=" + nicname + ", content=" + content
				+ ", restep=" + restep + ", relevel=" + relevel + ", regidate=" + regidate + "]";
	}


	
}
