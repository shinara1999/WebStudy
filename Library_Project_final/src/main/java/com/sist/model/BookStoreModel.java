package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.AllReplyDAO;
import com.sist.dao.BookStoreDAO;
import com.sist.vo.AllReplyVO;
import com.sist.vo.BookStoreVO;

public class BookStoreModel {
	@RequestMapping("bookStore/bookPurchase.do")
	public String bookStore_bookPurchase(HttpServletRequest request, HttpServletResponse response)
	{
		String isbn=request.getParameter("isbn");
		BookStoreDAO dao=BookStoreDAO.newInstance();
		BookStoreVO vo=dao.bookStoreAllDetailData(isbn);
		AllReplyDAO rdao = AllReplyDAO.newInstance();
		List<AllReplyVO> rvo = rdao.replyReviewListData(0, isbn);
		request.setAttribute("vo", vo);
		request.setAttribute("rvo", rvo);
		
		request.setAttribute("main_jsp", "../bookStore/bookPurchase.jsp");
		return "../main/main.jsp";
	}
	
	// 도서 결제
	@RequestMapping("bookStore/bookPurchase_ok.do")
	public void bookPurchase_ok(HttpServletRequest request, HttpServletResponse response)
	{
		// data: {"userid":userid, "isbn":isbn, sumprice:total}
		String userid=request.getParameter("userid");
		String isbn=request.getParameter("isbn");
		String sumpriceStr=request.getParameter("sumprice");
		
		// sumprice를 정수로 변환
		int sumprice=0;
		try {
		    sumprice=Integer.parseInt(sumpriceStr);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
		BookStoreDAO dao=BookStoreDAO.newInstance();
		dao.bookPurchase_ok(userid, isbn, sumprice);
		
	}
	
	// 도서 카트 인서트
		@RequestMapping("bookStore/cart_insert.do")
		public void cart_insert(HttpServletRequest request, HttpServletResponse response)
		{
			
			String userid=request.getParameter("userid");
			String isbn=request.getParameter("isbn");
			String sumpriceStr=request.getParameter("sumprice");
			if(sumpriceStr==null)
				sumpriceStr="0";
			
			int sumprice=Integer.parseInt(sumpriceStr);;
			
			BookStoreDAO dao=BookStoreDAO.newInstance();
			dao.cart_insert(userid, isbn, sumprice);
			
		}
		
		// 카트 도서 결제
		@RequestMapping("bookStore/cartbookPurchase_ok.do")
		public void cartbookPurchase_ok(HttpServletRequest request, HttpServletResponse response)
		{
			// data: {"userid":userid, "isbn":isbn, sumprice:total}
			String userid=request.getParameter("userid");
			String isbn=request.getParameter("isbn");
			String sumpriceStr=request.getParameter("sumprice");
			String stno=request.getParameter("stno");
			
			// sumprice를 정수로 변환
			int sumprice=0;
			try {
			    sumprice=Integer.parseInt(sumpriceStr);
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
			
			BookStoreDAO dao=BookStoreDAO.newInstance();
			dao.cartbookPurchase_ok(userid, isbn, sumprice, Integer.parseInt(stno));
			
		}
}















