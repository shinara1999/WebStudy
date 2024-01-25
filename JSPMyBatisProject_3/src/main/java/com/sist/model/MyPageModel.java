package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class MyPageModel {
  @RequestMapping("mypage/mypage_reserve.do")
  public String mypage_reserve(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  List<ReserveInfoVO> list=
			     ReserveDAO.reserveMyPageListData(id);
	  request.setAttribute("list", list);
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/mypage_home.do")
  public String mypage_homee(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  
	  request.setAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/mypage_cart.do")
  public String mypage_cart(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  Map map=new HashMap();
	  map.put("id", id);
	  List<CartVO> list=GoodsDAO.mypageGoodsCartData(map);
	  request.setAttribute("list", list);
	  
	  request.setAttribute("mypage_jsp", "../mypage/mypage_cart.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("mypage/mypage_buy.do")
  public String mypage_buy(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  Map map=new HashMap();
	  map.put("id", id);
	  List<CartVO> list=GoodsDAO.mypageGoodsBuyData(map);
	  request.setAttribute("list", list);
	  
	  request.setAttribute("mypage_jsp", "../mypage/mypage_buy.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  
}







