package com.sist.vo;

import java.util.Date;

public class ProgramApplicationVO {
	private int no,pno,status,waitingNo;
	private String userid,dbday,msg;
	private Date regdate;
	private ProgramVO pvo=new ProgramVO();
	
	public ProgramVO getPvo() {
		return pvo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getWaitingNo() {
		return waitingNo;
	}
	public void setWaitingNo(int waitingNo) {
		this.waitingNo = waitingNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
