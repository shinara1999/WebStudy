package com.sist.vo;

import java.util.Date;

public class QnaCommentVO {
	private int no,sqno;
	private String title,content,dbday;
	private Date wrDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSqno() {
		return sqno;
	}
	public void setSqno(int sqno) {
		this.sqno = sqno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getWrDate() {
		return wrDate;
	}
	public void setWrDate(Date wrDate) {
		this.wrDate = wrDate;
	}
}
