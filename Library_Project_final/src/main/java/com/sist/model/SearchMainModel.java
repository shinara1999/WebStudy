package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.dao.LibraryDAO;
import com.sist.dao.ProgramDAO;
import com.sist.vo.NoticeVO;
import com.sist.vo.ProgramVO;
import com.sist.vo.bookInfoVO;

public class SearchMainModel {
	@RequestMapping("search/searchDetail.do")
	public String SearchResultDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String search = request.getParameter("search");
		String type = request.getParameter("type-data");
		if(type.equals("post")) {
			String page = request.getParameter("page");
			
			if(page==null) page ="1";
			int curpage = Integer.parseInt(page);
			LibraryDAO dao = LibraryDAO.newInstance();
			
			ArrayList<bookInfoVO> list = dao.SearchBookData(search, curpage);
			int total = dao.SearchBookTotal(search);
			
			int totalpage = (int)Math.ceil((total/12.0));
			
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("type", "소장자료");
			request.setAttribute("type1", type);
			request.setAttribute("search", search);
			request.setAttribute("main_jsp", "../SearchResultDetail/result.jsp");
		}
		else {
			String page = request.getParameter("page");
			
			if(page==null) page ="1";
			int curpage = Integer.parseInt(page);
			LibraryDAO dao = LibraryDAO.newInstance();
			
			ArrayList<bookInfoVO> list = dao.SearchBookData(search, curpage);
			int total = dao.SearchBookTotal(search);
			
			int totalpage = (int)Math.ceil((total/12.0));
			
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			/* 프로그램 통합검색 */
			ProgramDAO pdao=ProgramDAO.newInstance();
			List<ProgramVO> pList=pdao.programFindAllData(search);
			int pList_size=pList.size();
			/* 공지사항 및 보도자료 통합검색 */
			BoardDAO bdao=BoardDAO.newInstance();
			List<NoticeVO> nList=bdao.noticeFindAllData(search);
			int nList_size=nList.size();
			
			request.setAttribute("list", list);
			request.setAttribute("pList", pList);
			request.setAttribute("nList", nList);
			request.setAttribute("pList_size", pList_size);
			request.setAttribute("nList_size", nList_size);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("type", "소장자료");
			request.setAttribute("type1", type);
			request.setAttribute("search", search);
			request.setAttribute("main_jsp", "../SearchResultDetail/result.jsp");
			// 프로그램 통합검색 화면
			request.setAttribute("program_search_jsp", "../SearchResultDetail/program_search.jsp");
			// 공지사항 통합검색 화면
			request.setAttribute("notice_search_jsp", "../SearchResultDetail/notice_search.jsp");
			// 소장자료 검색 화면
			request.setAttribute("book_search_jsp", "../SearchResultDetail/result.jsp");
			request.setAttribute("main_jsp", "../SearchResultDetail/result_all.jsp");
		}
		
		return "/main/main.jsp";
	}
}
