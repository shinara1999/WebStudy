package com.sist.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.LibraryDAO;
import com.sist.dao.MyPageDAO;
import com.sist.dao.ProgramDAO;
import com.sist.vo.AllLikeVO;
import com.sist.vo.BookCartVO;
import com.sist.vo.BookDeliverVO;
import com.sist.vo.BookReserve;
import com.sist.vo.BookReserveCountVO;
import com.sist.vo.ProgramApplicationVO;
import com.sist.vo.UserVO;

public class MypageModel {
	@RequestMapping("mypage/mypage_main.do")
	public String mypage_main(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("email");
		MyPageDAO dao=MyPageDAO.newInstance();
		UserVO vo=dao.userBasicInfo(id);
		int likeBookCnt=dao.likeBookTotalCount(id);
		int programApplCnt=dao.myProgramApplTotalCount_OK(id);
		int loanStatusTotalCnt=dao.loanStatusTotalCnt(id);
		int reserveStatusTotalCnt=dao.reserveStatusTotalCnt(id);
		request.setAttribute("vo", vo);
		request.setAttribute("likeBookCnt", likeBookCnt);
		request.setAttribute("programApplCnt", programApplCnt);
		request.setAttribute("loanStatusTotalCnt", loanStatusTotalCnt);
		request.setAttribute("reserveStatusTotalCnt", reserveStatusTotalCnt);
		request.setAttribute("mypage_jsp", "../mypage/basicinfo.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
	// 책 대출 현황
	@RequestMapping("mypage/bookloaninfo_main.do")
	public String mypage_loaninfo_main(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		MyPageDAO dao=MyPageDAO.newInstance();
		List<BookReserve> list=dao.loanStatusList(curpage, userid);
		int list_size=list.size();
		int totalcount=dao.loanStatusTotalCnt(userid);
		int totalpage=(int)(Math.ceil(totalcount/(double)dao.getROW()));
		int count=totalcount-((curpage*dao.getROW())-dao.getROW());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("loaninfo_select_jsp", "../mypage/loanStatus.jsp");
		request.setAttribute("mypage_jsp", "../mypage/bookLoanInfo_main.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
	// 책 대출 반납
	@RequestMapping("mypage/bookLoanReturn.do")
	public String mypage_bookLoanReturn(HttpServletRequest request,HttpServletResponse response) {
		String isbn=request.getParameter("isbn");
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		LibraryDAO dao=LibraryDAO.newInstance();
		dao.bookdatareservereturn(isbn, userid);
		return "redirect:../mypage/bookloaninfo_main.do";
	}
	// 책 예약 현황
	@RequestMapping("mypage/reserveStatus.do")
	public String mypage_reserve_status(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		MyPageDAO dao=MyPageDAO.newInstance();
		List<BookReserve> list=dao.reserveStatusList(curpage, userid);
		int list_size=list.size();
		int totalcount=dao.reserveStatusTotalCnt(userid);
		int totalpage=(int)(Math.ceil(totalcount/(double)dao.getROW()));
		int count=totalcount-((curpage*dao.getROW())-dao.getROW());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("loaninfo_select_jsp", "../mypage/reserveStatus.jsp");
		request.setAttribute("mypage_jsp", "../mypage/bookLoanInfo_main.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
	// 책 대출 이력
	@RequestMapping("mypage/loanHistory.do")
	public String mypage_loan_history(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		MyPageDAO dao=MyPageDAO.newInstance();
		List<BookReserveCountVO> list=dao.loanHistoryList(curpage, userid);
		int list_size=list.size();
		int totalcount=dao.loanHistoryTotalCnt(userid);
		int totalpage=(int)(Math.ceil(totalcount/(double)dao.getROW()));
		int count=totalcount-((curpage*dao.getROW())-dao.getROW());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("loaninfo_select_jsp", "../mypage/loanHistory.jsp");
		request.setAttribute("mypage_jsp", "../mypage/bookLoanInfo_main.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
//	@RequestMapping("mypage/reserveHistory.do")
//	public String mypage_reserve_history(HttpServletRequest request,HttpServletResponse response) {
//		request.setAttribute("loaninfo_select_jsp", "../mypage/reserveHistory.jsp");
//		request.setAttribute("mypage_jsp", "../mypage/bookLoanInfo_main.jsp");
//		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
//		return "../main/main.jsp";
//	}
	// 마이페이지 프로그램 신청 조회
	@RequestMapping("mypage/proAppInq.do")
	public String mypage_myApp_main(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("email");
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		//------------------------------------- 검색
		String search=request.getParameter("search");
		String searchType=request.getParameter("searchType");
		SimpleDateFormat eduFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date edu1=null;
		Date edu2=null;
		MyPageDAO dao=MyPageDAO.newInstance();
		List<ProgramApplicationVO> list=null;
		int totalcount=0;
		if(request.getParameter("edu1")!=null && request.getParameter("edu2")!=null) {
			try {
				edu1=eduFormat.parse(request.getParameter("edu1"));
				edu2=eduFormat.parse(request.getParameter("edu2"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list=dao.myProgramApplList(curpage, id,search,Integer.parseInt(searchType),eduFormat.format(edu1),eduFormat.format(edu2));
			totalcount=dao.myProgramApplTotalCount(id,search,Integer.parseInt(searchType),eduFormat.format(edu1),eduFormat.format(edu2));
		}else {
			list=dao.myProgramApplList(curpage, id);
			totalcount=dao.myProgramApplTotalCount(id);
		}
		
		int count=0;
		int list_size=list.size();
		int totalpage=(int)(Math.ceil(totalcount/(double)dao.getROW()));
		count=totalcount-((curpage*dao.getROW())-dao.getROW());
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("search", search);
		request.setAttribute("searchType", searchType);
		request.setAttribute("app_select_jsp", "../mypage/proAppInq.jsp");
		request.setAttribute("mypage_jsp", "../mypage/myApp_main.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
	// 마이페이지 프로그램 신청 취소
	@RequestMapping("mypage/programCancel.do")
	public String program_cancel(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		String pno=request.getParameter("pno");
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		
		ProgramApplicationVO vo=new ProgramApplicationVO();
		vo.setNo(Integer.parseInt(no));
		vo.setPno(Integer.parseInt(pno));
		vo.setUserid(userid);
		
		ProgramDAO dao=ProgramDAO.newInstance();
		dao.programCancel(vo);
		
		return "redirect:../mypage/proAppInq.do";
	}
	// 마이페이지 좋아요 도서 조회
	@RequestMapping("mypage/likeBookInq.do")
	public String mypage_likeBook_Inq(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("email");
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		//------------------------------------- 검색
		String search=request.getParameter("search");
		System.out.println(search);
		String searchType=request.getParameter("searchType");
		if(searchType==null) searchType="0";
		//-------------------------------------
		MyPageDAO dao=MyPageDAO.newInstance();
		List<AllLikeVO> list=null;
		int count=0;
		if(search==null) {
			list=dao.likeBookList(curpage,id);
			count=dao.likeBookTotalCount(id);
		}else {
			list=dao.likeBookList(curpage,id,search,Integer.parseInt(searchType));
			count=dao.likeBookTotalCount(id,search,Integer.parseInt(searchType));
		}
		int list_size=list.size();
		int totalpage=(int)(Math.ceil(count/(double)dao.getROW()));
		count=count-((curpage*dao.getROW())-dao.getROW());
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("search", search);
		request.setAttribute("searchType", searchType);
		request.setAttribute("app_select_jsp", "../mypage/likeBookInq.jsp");
		request.setAttribute("mypage_jsp", "../mypage/myApp_main.jsp");
		request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
		return "../main/main.jsp";
	}
	// 마이페이지 좋아요 도서 취소
	@RequestMapping("mypage/likeBookDelete.do")
	public String mypage_likeBook_delete(HttpServletRequest request,HttpServletResponse response) {
		String ino=request.getParameter("ino");
		MyPageDAO dao=MyPageDAO.newInstance();
		dao.likeBookDelete(Integer.parseInt(ino));
		return "redirect:../mypage/likeBookInq.do";
	}
	
		// 도서구매내역
		@RequestMapping("mypage/bookPurchaseList.do")
		public String mypage_bookPurchaseList(HttpServletRequest request, HttpServletResponse response)
		{
			String page=request.getParameter("page");
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			
			MyPageDAO dao=MyPageDAO.newInstance();
			HttpSession session=request.getSession();
			String userid=(String)session.getAttribute("email"); // 무슨의미일까
			List<BookDeliverVO> list=dao.userPurchaseList(curpage, userid);
			//int totalpage=dao.userPurchaseTotalpage();
			
			int count=dao.buyListTotalCount(userid);
			int totalcount=count;
			int totalpage=(int)(Math.ceil(count/(double)dao.getROW()));
		    count=count-((curpage*dao.getROW())-dao.getROW());
			
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			request.setAttribute("totalcount", totalcount);
			request.setAttribute("count", count);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("list", list);
		
			request.setAttribute("mypage_jsp", "../mypage/bookPurchaseList.jsp");
			request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
			return "../main/main.jsp";
		}
	
		// 도서결제내역 디테일
		@RequestMapping("mypage/bookPurchaseDetail.do")
		public String mypage_bookPurchaseDetail(HttpServletRequest request, HttpServletResponse response)
		{
			MyPageDAO dao=MyPageDAO.newInstance();
			HttpSession session=request.getSession();
			String userid=(String)session.getAttribute("email");
			//String orderNum=request.getParameter("orderNum");
			
			BookDeliverVO vo=dao.bookBuyDetail(userid);
			request.setAttribute("vo", vo);
		
			request.setAttribute("mypage_jsp", "../mypage/bookPurchaseDetail.jsp");
			request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
			return "../main/main.jsp";
		}
		
		// 장바구니내역
		@RequestMapping("mypage/mypage_cart.do")
		public String mypage_cart(HttpServletRequest request, HttpServletResponse response)
		{
			String page=request.getParameter("page");
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			
			
			
			String stno=request.getParameter("stno");
			MyPageDAO dao=MyPageDAO.newInstance();
			HttpSession session=request.getSession();
			String userid=(String)session.getAttribute("email"); // 무슨의미일까
			List<BookCartVO> list=dao.userCartList(curpage, userid);
			//int totalpage=dao.userCartTotalpage();
			
			int count=dao.cartTotalCount(userid);
			int totalcount=count;
			int totalpage=(int)(Math.ceil(count/(double)dao.getROW()));
		    count=count-((curpage*dao.getROW())-dao.getROW());
			
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			request.setAttribute("totalcount", totalcount);
			request.setAttribute("count", count);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("list", list);
			
			request.setAttribute("mypage_jsp", "../mypage/mypage_cart.jsp");
			request.setAttribute("main_jsp", "../mypage/myPage_main.jsp");
			return "../main/main.jsp";
		}
		
		// 장바구니내역 삭제
		@RequestMapping("mypage/mypage_cartDelete.do")
		public String mypage_cartDelete(HttpServletRequest request, HttpServletResponse response)
		{
			String stno=request.getParameter("stno");
			MyPageDAO dao=MyPageDAO.newInstance();
			dao.cartDelete(Integer.parseInt(stno));
			return "redirect:../mypage/mypage_cart.do";
		}
}
