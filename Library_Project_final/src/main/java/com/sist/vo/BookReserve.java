package com.sist.vo;
import java.util.*;
public class BookReserve {
	private int no;
	private String isbn,userid,status,regdate_str,enddate_str;
	private Date regdate,enddate;
	private String booktitle;
	
	// admin 도서 대출 예약 내역 조회시
//	private bookInfoVO bvo=new bookInfoVO();
//	
//	
//	public bookInfoVO getBvo() {
//		return bvo;
//	}
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
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
