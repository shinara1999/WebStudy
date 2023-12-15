package com.sist.vo;

import java.util.Date;

public class ProgramVO {
	private int pno,capacity; // 고유번호,모집정원
	// 포스터,제목,대상,장소,이용료,전화
	private String poster,title,target,place,fee,tel;
	// 교육기간,접수시작일,접수마감일,등록일
	private Date edu,accept1,accept2,regdate;
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getEdu() {
		return edu;
	}
	public void setEdu(Date edu) {
		this.edu = edu;
	}
	public Date getAccept1() {
		return accept1;
	}
	public void setAccept1(Date accept1) {
		this.accept1 = accept1;
	}
	public Date getAccept2() {
		return accept2;
	}
	public void setAccept2(Date accept2) {
		this.accept2 = accept2;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
