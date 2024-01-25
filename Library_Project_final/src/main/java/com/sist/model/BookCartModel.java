package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

public class BookCartModel {
	@RequestMapping("bookStore/shopCart.do")
	public String shopCart(HttpServletRequest request, HttpServletResponse response) 
	{
		
		// 장바구니 리스트
		String b_count=request.getParameter("b_count");
		String userid = request.getParameter("userid");
		
		BookDeliverVO vo=new BookDeliverVO();
		
		BookStoreCartDAO dao = BookStoreCartDAO.newInstance();
		List<BookDeliverVO> list = dao.cartListData(userid);
		
		request.setAttribute("list", list);
		request.setAttribute("bCheck", true);
		request.setAttribute("main_jsp", "../bookStore/shopCart.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("bookStore/cartInsert.do")
	public String shopCartInsert(HttpServletRequest request, HttpServletResponse response) {
		
		String stno = request.getParameter("stno");
		String sumprice = request.getParameter("price");
		String b_count = request.getParameter("b_count");
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		BookDeliverVO vo = new BookDeliverVO();
		vo.setStno(Integer.parseInt(stno));
		vo.setSumprice(Integer.parseInt(sumprice));
		vo.setB_count(Integer.parseInt(b_count));
		vo.setUserid(userid);
		
		BookStoreCartDAO dao = BookStoreCartDAO.newInstance();
		dao.CartInsert(vo);
		
		return "redirect:../bookStore/bookPurchase.do?stno="+stno;
	}
//	
//	@RequestMapping("bookStore/cartOrder.do")
//	public String shopCartOrder(HttpServletRequest request, HttpServletResponse response) {
//		
//		// 구매 상품 정보
//		HttpSession session = request.getSession();
//		String userid = (String)session.getAttribute("userid");
//		String stno = request.getParameter("stno");
//		String sumprice = request.getParameter("price");
//		String b_count = request.getParameter("b_count");
//		
//		BookDeliverVO vo = new BookDeliverVO();
//		vo.setStno(Integer.parseInt(stno));
//		vo.setSumprice(Integer.parseInt(sumprice));
//		vo.setB_count(Integer.parseInt(b_count));
//		vo.setUserid(userid);
//		
//		BookStoreCartDAO dao = BookStoreCartDAO.newInstance();
//		dao.CartInsert(vo);
//		
//		List<BookDeliverVO> list = dao.cartListData(userid);
//		request.setAttribute("list", list);
//		
//		// 주문자 정보
//		String name = (String)session.getAttribute("name");
//		String post = (String)session.getAttribute("post");
//		String addr1 = (String)session.getAttribute("addr1");
//		String phone = (String)session.getAttribute("phone");
//		
//		request.setAttribute("name", name);
//		request.setAttribute("post", post);
//		request.setAttribute("addr1", addr1);
//		request.setAttribute("phone", phone);
//		
//		request.setAttribute("bCheck", true);
//		request.setAttribute("main_jsp", "../bookStore/shopOrder.jsp");
//		return "../main/main.jsp";
//	}
//	
//	@RequestMapping("bookStore/orderComplete.do")
//	public String shopOrderComplete(HttpServletRequest request, HttpServletResponse response) {
//		
//		String orderNum = request.getParameter("orderNum");
//		BookStoreCartDAO dao = BookStoreCartDAO.newInstance();
//		dao.cartBuy(Integer.parseInt(orderNum));
//		
//		request.setAttribute("bCheck", true);
//		request.setAttribute("main_jsp", "../bookStore/shopOrderComplete.jsp");
//		return "../main/main.jsp";
//	}
}
