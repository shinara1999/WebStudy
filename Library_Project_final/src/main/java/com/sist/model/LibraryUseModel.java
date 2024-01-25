package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

public class LibraryUseModel {
	@RequestMapping("libUse/libTimeInfo.do")
	public String libUse_libTimeInfo(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("libUse_jsp", "../libUse/libTimeInfo.jsp");
		request.setAttribute("main_jsp", "../libUse/lib_submenu.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("libUse/libReserveInfo.do")
	public String libUse_libReserveInfo(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("libUse_jsp", "../libUse/libReserveInfo.jsp");
		request.setAttribute("main_jsp", "../libUse/lib_submenu.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("libUse/libWayInfo.do")
	public String libUse_libWayInfo(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("libUse_jsp", "../libUse/libWayInfo.jsp");
		request.setAttribute("main_jsp", "../libUse/lib_submenu.jsp");
		return "../main/main.jsp";
	}
}
