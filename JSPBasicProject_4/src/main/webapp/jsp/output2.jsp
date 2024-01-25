<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.bean.*"%>
    
<%
		request.setCharacterEncoding("UTF-8");
%>
<%--
		StudentBean bean=new Studentbean(): 메모리 할당
--%>
<jsp:useBean id="bean" class="com.sist.bean.StudentBean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생 정보</h1>
	학번:<%=bean.getHakbun() %><br>
	학번:<jsp:getProperty property="hakbun" name="bean"/><br>
	이름:<%=bean.getName() %><br>
	국어:<%=bean.getKor() %><br>
	영어:<%=bean.getEng() %><br>
	수학:<%=bean.getMath() %><br>
</body>
</html>