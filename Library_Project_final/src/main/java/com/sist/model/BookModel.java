package com.sist.model;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.controller.*;
public class BookModel {
	@RequestMapping("searchBook/alq.do")
	public String Search_alq(HttpServletRequest request,HttpServletResponse response) {
		
		LibraryDAO dao = LibraryDAO.newInstance();
		
		ArrayList<MajorctVO> list = dao.Major_Classification();
		
		ArrayList<MiddlectVO> sub = dao.SearchSubmenuData(0,"");
		
		request.setAttribute("list", list);
		
		request.setAttribute("sub", sub);
		
		request.setAttribute("main_jsp", "/searchBook/alq.jsp");
		
		return "/main/main.jsp";
	}
	
	@RequestMapping("searchBook/alqResult.do")
	public String Search_alqResult(HttpServletRequest request,HttpServletResponse response) {
		
		String cno = request.getParameter("cno");
		String mno = request.getParameter("mno");
		String page = request.getParameter("page");
		if(page==null) page = "1";
		int curpage = Integer.parseInt(page);
		
		LibraryDAO dao = LibraryDAO.newInstance();
		ArrayList<bookInfoVO> list = dao.BookInfoData(Integer.parseInt(cno),mno,curpage);
		
		ArrayList<MiddlectVO> CategoryData = dao.SearchSubmenuData(1, mno);
		int total = dao.BookInfoTotal(Integer.parseInt(cno),mno);
		int totalpage = (int)Math.ceil((total/12.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Cookie[] cookies = request.getCookies();
		ArrayList<bookInfoVO> cList_1 = new ArrayList<bookInfoVO>();
		int cLength = 0;
		if(cookies.length>10) cLength = cookies.length-10;
		for(int i = cookies.length-1;i>cLength;i--) {
			if(cookies[i].getName().startsWith("book_")) {
				bookInfoVO vo = new bookInfoVO();
				String data = cookies[i].getValue();
				String title = dao.SearchCookieData(data);
				vo.setIsbn(data);
				vo.setBooktitle(title);
				
				cList_1.add(vo);
				
			}
		}
		
		if(list.size()!=0) {
			request.setAttribute("list", list);
			request.setAttribute("total", total);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("curpage", curpage);
			request.setAttribute("cList_1", cList_1);
			request.setAttribute("cno", cno);
			request.setAttribute("cate", CategoryData);
			request.setAttribute("mno", mno);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			request.setAttribute("main_jsp", "/searchBook/alqResult.jsp");
		}
		else {
			request.setAttribute("cate", CategoryData);
			request.setAttribute("curpage", curpage);
			request.setAttribute("mno", mno);
			request.setAttribute("cno", cno);
			request.setAttribute("cList_1", cList_1);
			
			request.setAttribute("main_jsp", "/searchBook/alqNoData.jsp");
		}
		return "/main/main.jsp";
	}
	
	@RequestMapping("searchBook/favorLoan.do")
	public String Search_favorLoan(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String acq = request.getParameter("acq");

		if(page==null) {
			page = "1";
		}
		if(acq==null) {
			acq = "30";
		}
		int curpage = Integer.parseInt(page);
		int acqdate = Integer.parseInt(acq);
		
		LibraryDAO dao = LibraryDAO.newInstance();
		ArrayList<FavorLoanVO> list = dao.favorLoanBookData(curpage, acqdate);
		int total = dao.favorLoanTotal(acqdate);
		int totalpage = (int)Math.ceil((total)/100.0);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		request.setAttribute("acq", acq);	
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startpage", startPage);
		request.setAttribute("endpage", endPage);
		request.setAttribute("curpage", curpage);
		
		
		request.setAttribute("main_jsp", "/searchBook/favorLoan.jsp");
		
		return "/main/main.jsp";
	}
	
	@RequestMapping("searchBook/newarrival.do")
	public String Search_newarrival(HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String acq = request.getParameter("acq");

		if(page==null) {
			page = "1";
		}
		if(acq==null) {
			acq = "30";
		}
		int curpage = Integer.parseInt(page);
		int acqdate = Integer.parseInt(acq);
		
		LibraryDAO dao = LibraryDAO.newInstance();
		ArrayList<bookInfoVO> list = dao.newArrivalBookData(curpage, acqdate);
		int total = dao.newArrivalTotal(acqdate);
		int totalpage = (int)Math.ceil((total)/100.0);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		request.setAttribute("acq", acq);	
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startpage", startPage);
		request.setAttribute("endpage", endPage);
		request.setAttribute("curpage", curpage);
		
		request.setAttribute("main_jsp", "/searchBook/newarrival.jsp");
		
		return "/main/main.jsp";
	}
	@RequestMapping("searchBook/alqDetail.do")
	public String Search_Deatail(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("email");
		
		LibraryDAO dao = LibraryDAO.newInstance();
		bookInfoVO vo = dao.BookDetailSearch(isbn);
		
		String reserveBtn = dao.bookDetailbtnCheck(id, isbn);
		
		AllLikeDAO adao = AllLikeDAO.newInstance();
		int cnt = adao.bookLikeCount(isbn);
		
		int status = 0;
		if(id!=null) {
			status = adao.bookLikeCheck(isbn, id, 0);
		}
		
		request.setAttribute("status", status);//좋아요 상태
		request.setAttribute("vo", vo);
		request.setAttribute("star_count", cnt);
		request.setAttribute("reserveBtn", reserveBtn);
		
		request.setAttribute("main_jsp", "/searchBook/alqDetail.jsp");
		
		return "/main/main.jsp";
	}
	@RequestMapping("searchBook/alqDetail_before.do")
	public String Search_Detail_before(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		
		Cookie cookies = new Cookie("book_"+isbn, isbn);
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24);
		
		response.addCookie(cookies);
		
		return "redirect:../searchBook/alqDetail.do?isbn="+isbn;
	}
	
	@RequestMapping("searchBook/alqDetail_remove.do")
	public String Search_Detail_remove(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cno = request.getParameter("cno");
		String page = request.getParameter("page");
		String mno = request.getParameter("mno");
		String isbn = request.getParameter("isbn");
		
		Cookie[] cookies = request.getCookies();
		for(int i = cookies.length-1;i>0;i--) {
			if(cookies[i].getName().equals("book_"+isbn)) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				
				response.addCookie(cookies[i]);
				
				break;
			}
		}
		
		
		return "redirect:../searchBook/alqResult.do?cno="+cno+"&mno="+mno+"&page="+page;
	}
	@RequestMapping("searchBook/bookreserve.do")
	public void Search_bookreserve(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		
		LibraryDAO dao = LibraryDAO.newInstance();
		dao.bookdatareserve(id, isbn);
	}
	
	@RequestMapping("searchBook/bookreserve_cancel.do")
	public void Search_bookreserve_cancel(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		
		LibraryDAO dao = LibraryDAO.newInstance();
		dao.bookdatareserveCancel(isbn, id);
	}
	@RequestMapping("searchBook/bookreserve_ok.do")
	public void Search_bookreserve_ok(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		
		LibraryDAO dao = LibraryDAO.newInstance();
		String res = dao.bookdatareserveok(isbn, id);
		try {
			PrintWriter out = response.getWriter();
			out.write(res.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@RequestMapping("searchBook/bookreserve_return.do")
	public void Search_bookreserve_return(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		
		LibraryDAO dao = LibraryDAO.newInstance();
		dao.bookdatareservereturn(isbn,id);
	}
}
