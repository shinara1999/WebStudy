package com.sist.vo;

import java.util.*;
public class bookInfoVO {
	private String isbn,booktitle,bookauthor,bookpublisher,bookdtype,bookperson,booksign,bookdate,bookaccessionno,bookcallnum,booklocation;
	private int bino,fixedprice,saleprice;
	private String bookinfo,contents,authorinfo,image;
	private Date acquisition;
	private int count;
	private BookReserve brvo = new BookReserve();
	
	public BookReserve getBrvo() {
		return brvo;
	}
	public void setBrvo(BookReserve brvo) {
		this.brvo = brvo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookpublisher() {
		return bookpublisher;
	}
	public void setBookpublisher(String bookpublisher) {
		this.bookpublisher = bookpublisher;
	}
	public String getBookdtype() {
		return bookdtype;
	}
	public void setBookdtype(String bookdtype) {
		this.bookdtype = bookdtype;
	}
	public String getBookperson() {
		return bookperson;
	}
	public void setBookperson(String bookperson) {
		this.bookperson = bookperson;
	}
	public String getBooksign() {
		return booksign;
	}
	public void setBooksign(String booksign) {
		this.booksign = booksign;
	}
	public String getBookdate() {
		return bookdate;
	}
	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}
	public String getBookaccessionno() {
		return bookaccessionno;
	}
	public void setBookaccessionno(String bookaccessionno) {
		this.bookaccessionno = bookaccessionno;
	}
	public String getBookcallnum() {
		return bookcallnum;
	}
	public void setBookcallnum(String bookcallnum) {
		this.bookcallnum = bookcallnum;
	}
	public String getBooklocation() {
		return booklocation;
	}
	public void setBooklocation(String booklocation) {
		this.booklocation = booklocation;
	}
	public int getBino() {
		return bino;
	}
	public void setBino(int bino) {
		this.bino = bino;
	}
	public int getFixedprice() {
		return fixedprice;
	}
	public void setFixedprice(int fixedprice) {
		this.fixedprice = fixedprice;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public String getBookinfo() {
		return bookinfo;
	}
	public void setBookinfo(String bookinfo) {
		this.bookinfo = bookinfo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAuthorinfo() {
		return authorinfo;
	}
	public void setAuthorinfo(String authorinfo) {
		this.authorinfo = authorinfo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getAcquisition() {
		return acquisition;
	}
	public void setAcquisition(Date acquisition) {
		this.acquisition = acquisition;
	}
	
}
