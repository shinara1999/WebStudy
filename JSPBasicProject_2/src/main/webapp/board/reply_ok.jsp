<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    //1. 한글 디코딩 => <jsp:useBean> => <jsp:setProperty property="*">
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String subject=request.getParameter("subject");
    String content=request.getParameter("content");
    String pwd=request.getParameter("pwd");
    // hidden 
    String pno=request.getParameter("pno");
    String curpage=request.getParameter("page");
    /*
                      no gi gs gt root depth
          AAAAAA       1 1  0  0   0    0 => 1
            =>BBBBB    2 1  1  1   1    0 => 1
             =>CCCCC   3 1  2  2   2    0
    */
    BoardVO vo=new BoardVO();
    vo.setName(name);
    vo.setSubject(subject);
    vo.setContent(content);
    vo.setPwd(pwd);
    // public void insert(BoardVO vo)
    // 오라클연동 => BoardDAO
    BoardDAO dao=BoardDAO.newInstance();
    dao.boardReplyInsert(Integer.parseInt(pno), vo); 
    // 화면이동 => response.sendRedirect("list.jsp")
    response.sendRedirect("list.jsp?page="+curpage);
    
%>


