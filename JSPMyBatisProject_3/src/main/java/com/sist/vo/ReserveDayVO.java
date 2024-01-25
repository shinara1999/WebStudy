package com.sist.vo;
/*
 *    RNO                                       NOT NULL NUMBER
 RTIME                                     NOT NULL VARCHAR2(150)
 */
public class ReserveDayVO {
    private int rno;
    private String rtime;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
   
}
