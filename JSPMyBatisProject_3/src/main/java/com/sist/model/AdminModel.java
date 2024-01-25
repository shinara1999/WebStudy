package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReserveDAO;
import com.sist.vo.ReserveInfoVO;

public class AdminModel {
	  @RequestMapping("adminpage/admin_reserve.do")
	  public String mypage_reserve(HttpServletRequest request,
			  HttpServletResponse response)
	  {

		  List<ReserveInfoVO> list=
				     ReserveDAO.reserveAdminPageListData();
		  request.setAttribute("list", list);
		  request.setAttribute("admin_jsp", "../adminpage/admin_reserve.jsp");
		  request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		  return "../main/main.jsp";
	  }
	  @RequestMapping("adminpage/admin_home.do")
	  public String mypage_homee(HttpServletRequest request,
			  HttpServletResponse response)
	  {
		  
		  request.setAttribute("admin_jsp", "../adminpage/admin_home.jsp");
		  request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		  return "../main/main.jsp";
	  }
	  @RequestMapping("adminpage/admin_reserve_ok.do")
	  public String admin_reserve_ok(HttpServletRequest request, HttpServletResponse response)
	  {
		  String no=request.getParameter("no");
		  ReserveDAO.reserveAdminOk(Integer.parseInt(no));
		  return "redirect:../adminpage/admin_resereve.do";
	  }
}






