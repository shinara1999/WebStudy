package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.vo.*;
import com.sist.dao.*;
import java.util.*;

import com.sist.controller.RequestMapping;
import com.sist.dao.GoodsDAO;
/*
 		DAO => 오라클 연결
 		Model => 요청을 받아서 처리 => 결과값을 request에 모아두는 것
 		Controller => Model에서 모아둔 request를 JSP로 전송
 */
public class StoreModel {
	@RequestMapping("store/all.do")
	public String store_all(HttpServletRequest request, HttpServletResponse response)
	{
		// all.jsp로 데이터베이스 결과값 전송하기 (request 사용)
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		// DB 연동
		GoodsDAO dao=GoodsDAO.newInstance();
		List<GoodsVO> list=dao.goodsAllListData(curpage);
		int totalpage=dao.goodsAllTotalpage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		
		// => 쿠키데이터 전송 (detail_before에서 만든 것)
		Cookie[] cookies=request.getCookies();
		List<GoodsVO> cList=new ArrayList<GoodsVO>();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("goods_")) // getName() : 페이지 실행 후 개발자도구 => Application => Cookies => Name
				{
					String no=cookies[i].getValue(); // getValue() : 페이지 실행 후 개발자도구 => Application => Cookies => Value
					GoodsVO vo=dao.goodsCookieData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cList", cList);
		request.setAttribute("count", cList.size()); // all.jsp에서 ${} 안에는 .size()를 보낼 수 없기 때문에 count 변수 하나를 더 준다.
		
		// 요청 => .do
		// include => .jsp
		
		request.setAttribute("store_jsp", "../store/all.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("store/best.do")
	public String store_best(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("store_jsp", "../store/best.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("store/special.do")
	public String store_special(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("store_jsp", "../store/special.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("store/new.do")
	public String store_new(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("store_jsp", "../store/new.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	
	// 쿠키메소드는 따로 만들어야 한다. (동시 처리 불가)
	@RequestMapping("store/detail_before.do")
	public String store_detail_before(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		Cookie cookie=new Cookie("goods_"+no, no);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24); // 하루동안 저장
		// 브라우저로 전송
		response.addCookie(cookie);
		return "redirect:../store/detail.do?no="+no;
	}
	
	@RequestMapping("store/detail.do")
	public String store_detail(HttpServletRequest request, HttpServletResponse response)
	{
		// 사용자가 보내주는 데이터 => no로 받는다.
		String no=request.getParameter("no");
		//${} => request.getAttribute("store_jsp") 달러 안에 request.getAttribute가 생략되어 있다.
		GoodsDAO dao=GoodsDAO.newInstance();
		GoodsVO vo=dao.goodsAllDetailData(Integer.parseInt(no));
		String price=vo.getGoods_price();
		// 30,000원 => 30000으로 바꿔줘야 한다.
		price=price.replaceAll("[^0-9]", ""); // [^0-9] : 숫자를 제외(^)하고 다 공백으로 바꿔라.
		vo.setPrice(price);
		request.setAttribute("vo", vo);
		
		request.setAttribute("store_jsp", "../store/detail.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	
	
}






