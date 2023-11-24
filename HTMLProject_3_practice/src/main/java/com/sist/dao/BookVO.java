package com.sist.dao;

import java.util.*;

//isbn, booktitle, bookperson, bookdate, image

public class BookVO {
	private String isbn;
	private String image;
	private String booktitle, bookperson;
	private Date bookdate;
	
	
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
	public String getBookperson() {
		return bookperson;
	}
	public void setBookperson(String bookperson) {
		this.bookperson = bookperson;
	}
	public Date getBookdate() {
		return bookdate;
	}
	public void setBookdate(Date bookdate) {
		this.bookdate = bookdate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
