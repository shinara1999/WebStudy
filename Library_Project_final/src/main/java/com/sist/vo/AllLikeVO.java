package com.sist.vo;

public class AllLikeVO {
	private int ino,typeno;
	private String userid,no;
	// 좋아요 도서 조회시 JOIN
	private bookInfoVO bivo=new bookInfoVO();
	
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public bookInfoVO getBivo() {
		return bivo;
	}
}
