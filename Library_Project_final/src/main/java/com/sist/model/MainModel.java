package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.dao.LibraryDAO;
import com.sist.dao.ProgramDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.FavorLoanVO;
import com.sist.vo.NoticeVO;
import com.sist.vo.ProgramVO;
import com.sist.vo.bookInfoVO;

public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request,HttpServletResponse response) {
		// 슬라이드바 프로그램 목록 리스트
		ProgramDAO dao=ProgramDAO.newInstance();
		List<ProgramVO> programList=dao.programSlideData();
		// 슬라이드바 새로 들어온 책 목록 리스트
		LibraryDAO ldao = LibraryDAO.newInstance();
		int newArrivalCnt = ldao.newArrivalTotal(30);
		ArrayList<bookInfoVO> newArrivalList = null;
		if(newArrivalCnt!=0) {
			newArrivalList = ldao.newArrivalBookData(1, 30);
			request.setAttribute("newArrival", newArrivalList);
		}
		
		int favorLoancnt = ldao.favorLoanTotal(30);
		ArrayList<FavorLoanVO> favorLoanList = null;
		if(favorLoancnt!=0) {
			favorLoanList = ldao.favorLoanBookData(1, 30);
			request.setAttribute("favorLoan", favorLoanList);
		}
		// 메인화면 Board 요약 출력 리스트
		BoardDAO bdao=BoardDAO.newInstance();
		List<NoticeVO> noticeMainData=bdao.noticeMainData();
		
		request.setAttribute("programList", programList);
		request.setAttribute("noticeMainData", noticeMainData);
		request.setAttribute("newArrivalCnt", newArrivalCnt);
		request.setAttribute("favorLoancnt", favorLoancnt);
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
