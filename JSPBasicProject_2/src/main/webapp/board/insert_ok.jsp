<%@page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%-- 
   JSP => 1.화면 출력 => doGet()
          2.요청값을 받아서 처리 => 종료하면 화면 이동 => doPost()
            => HTML이 필요없다 
--%>
<%
    //1. 한글 디코딩 => <jsp:useBean> => <jsp:setProperty property="*">
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String subject=request.getParameter("subject");
    String content=request.getParameter("content");
    String pwd=request.getParameter("pwd");
    
    BoardVO vo=new BoardVO();
    vo.setName(name);
    vo.setSubject(subject);
    vo.setContent(content);
    vo.setPwd(pwd);
    // public void insert(BoardVO vo)
    // 오라클연동 => BoardDAO
    BoardDAO dao=BoardDAO.newInstance();
    dao.boardInsert(vo);
    // 화면이동 => response.sendRedirect("list.jsp")
    response.sendRedirect("list.jsp");
%>