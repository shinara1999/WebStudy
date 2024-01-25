package com.sist.model;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.*;
import com.sist.dao.LibrarySearchDAO;
import com.sist.vo.OtherLibBookVO;
import com.sist.vo.OtherLibVO;
public class OtherLibModel {
	@RequestMapping("otherLib/obs.do")
	public String searchOtherLib(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String page = request.getParameter("page");
		String name = request.getParameter("libname");
		String book = request.getParameter("libbook");
		if(page==null)
			page = "1";
		if(name==null)
			name = "";
		if(book==null)
			book = "";
		
		int curpage = Integer.parseInt(page);
		
		LibrarySearchDAO dao = LibrarySearchDAO.newInstance();
		
		//default 선언
		ArrayList<OtherLibVO> list = dao.OtherLibSearch(curpage);
		int total = dao.OtherLibTotal(0,"","");
		String apikey = dao.apiFind("kakao");
		//name , book 별 list 선언
		
		if(name!="" && book == "") {
			list = dao.OtherLibSearchLib(curpage, name);
			total = dao.OtherLibTotal(1, name,"");
		}
		else if(name=="" && book != "") {
			list = dao.OtherLibSearchBook(curpage, book);
			total = dao.OtherLibTotal(2, "",book);
		}
		else if(name!="" && book != "") {
			list = dao.OtherLibSearchBook_Lib(curpage, name, book);
			total = dao.OtherLibTotal(3, name, book);
		}
		int totalpage = (int)Math.ceil(total/4.0);
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		request.setAttribute("name", name);
		request.setAttribute("api", apikey);
		request.setAttribute("book", book);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		// 도서관명,주소
		request.setAttribute("mapinfo_jsp", "/otherLib/kakaomap.jsp");
		request.setAttribute("main_jsp", "/otherLib/obs.jsp");
		
		return "/main/main.jsp";
	}
	@RequestMapping("otherLib/bookindex.do")
	public void Searchbookindex(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String booktitle = request.getParameter("booktitle");
		
		LibrarySearchDAO dao = LibrarySearchDAO.newInstance();
		ArrayList<OtherLibBookVO> list = dao.OtherLibSearchBook_shadow(name,booktitle);
		
		JSONArray jarr = new JSONArray();
		for(OtherLibBookVO vo:list) {
			JSONObject sobj = new JSONObject();
			sobj.put("cnt",vo.getCnt());
			sobj.put("isbn", vo.getIsbn());
			sobj.put("booktitle", vo.getBooktitle());
			jarr.add(sobj);
		}
		//전송 => ajax
		try {
			response.setContentType("application/x-wwww-form-urlencoded; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write(jarr.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
