package com.sist.vo;

import java.util.Date;

public class QnaVO {
	private int no;
	private String userid,name,title,content,status,locking,dbday;
	private Date wrDate;
	
	public String getLocking() {
		return locking;
	}
	public void setLocking(String locking) {
		this.locking = locking;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
