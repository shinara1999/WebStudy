package com.sist.vo;
import java.util.*;
public class BookReserveCountVO {
	private int bno,brno;//고유번호 , 책 예약 참조 번호
	private String isbn;//isbn
	private Date regdate;//대여날짜
	// 마이페이지 대출이력 출력에 필요
	private String userid,regdate_str,enddate_str;
	private String booktitle;
	private Date enddate;
	
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getRegdate_str() {
		return regdate_str;
	}
	public void setRegdate_str(String regdate_str) {
		this.regdate_str = regdate_str;
	}
	public String getEnddate_str() {
		return enddate_str;
	}
	public void setEnddate_str(String enddate_str) {
		this.enddate_str = enddate_str;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getBrno() {
		return brno;
	}
	public void setBrno(int brno) {
		this.brno = brno;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
