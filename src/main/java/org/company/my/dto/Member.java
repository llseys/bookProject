package org.company.my.dto;

import java.sql.Date;

public class Member {
	private String userid;
	private String userpw;
	private String nicname;
	private String username;
	private String email;
	private String tel;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String emailauth="0";
	private String simplejoin="0";
	private Date regidate;
	
	public Member() {
	}

	public Member(String userid, String userpw, String nicname, String username, String email, String tel,
			String zipcode, String addr, String addrdetail, String emailauth, String simplejoin, Date regidate) {
		this.userid = userid;
		this.userpw = userpw;
		this.nicname = nicname;
		this.username = username;
		this.email = email;
		this.tel = tel;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.emailauth = emailauth;
		this.simplejoin = simplejoin;
		this.regidate = regidate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getNicname() {
		return nicname;
	}

	public void setNicname(String nicname) {
		this.nicname = nicname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getEmailauth() {
		return emailauth;
	}

	public void setEmailauth(String emailauth) {
		this.emailauth = emailauth;
	}

	public String getSimplejoin() {
		return simplejoin;
	}

	public void setSimplejoin(String simplejoin) {
		this.simplejoin = simplejoin;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpw=" + userpw + ", nicname=" + nicname + ", username=" + username
				+ ", email=" + email + ", tel=" + tel + ", zipcode=" + zipcode + ", addr=" + addr + ", addrdetail="
				+ addrdetail + ", emailauth=" + emailauth + ", simplejoin=" + simplejoin + ", regidate=" + regidate
				+ "]";
	}

	

}
