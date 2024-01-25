<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String mode=request.getParameter("mode");
		
		if(Integer.parseInt(mode)==1)
		{
			response.sendRedirect("e.jsp");
		}
		else
		{
			pageContext.forward("e.jsp");
		}
%>