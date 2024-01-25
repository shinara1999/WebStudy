<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    <%@ page import="com.oreilly.servlet.*" %>
    <%@ page import="com.oreilly.servlet.multipart.*" %>
    <%@ page import="java.io.*" %>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"></jsp:useBean>
<jsp:useBean id="bean" class="com.sist.dao.DataBoardBean"/>
<%
	request.setCharacterEncoding("UTF-8");
	
	// 1. 파일업로드 지정
	String path="c:\\download";
	String enctype="UTF-8"; // 한글파일명
	int max=1024*1024*100; // 100 mb
	MultipartRequest mr=new MultipartRequest(request, path, max, enctype,
			new DefaultFileRenamePolicy());
	
	// a.jpg => a1.jpg => a2.jpg
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	// mr.getOriginalFileName("upload") : 사용자 보낸 파일명
	// mr.getFilesystemName("upload") : 실제 저장된 파일명
	String filename=mr.getFilesystemName("upload");
	
	bean.setName(name);
	bean.setSubject(subject);
	bean.setPwd(pwd);
	bean.setContent(content);
	
	if(filename==null) // 업로드가 안된 상태
	{
		bean.setFilename("");
		bean.setFilesize(0);
	}
	else
	{
		bean.setFilename(filename);
		File file=new File(path+"\\"+filename);
		bean.setFilesize((int)file.length());
	}
	
	dao.boardInsert(bean);
	
	response.sendRedirect("list.jsp");
%>
















