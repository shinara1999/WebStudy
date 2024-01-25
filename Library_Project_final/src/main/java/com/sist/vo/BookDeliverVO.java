package com.sist.vo;

import java.util.*;

public class BookDeliverVO {
	private int orderNum, stno, b_count, sumprice, price, buy_ok, check_ok, fixedprice, saleprice;
	private String userid, booktitle, image, isbn;
	private Date orderDate;
	
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getFixedprice() {
		return fixedprice;
	}
	public void setFixedprice(int fixedprice) {
		this.fixedprice = fixedprice;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBuy_ok() {
		return buy_ok;
	}
	public void setBuy_ok(int buy_ok) {
		this.buy_ok = buy_ok;
	}
	public int getCheck_ok() {
		return check_ok;
	}
	public void setCheck_ok(int check_ok) {
		this.check_ok = check_ok;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getStno() {
		return stno;
	}
	public void setStno(int stno) {
		this.stno = stno;
	}
	public int getB_count() {
		return b_count;
	}
	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	public int getSumprice() {
		return sumprice;
	}
	public void setSumprice(int sumprice) {
		this.sumprice = sumprice;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
