<%@page import="com.sist.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.dao.*"%>
    <%@ tablib prefix="c" uri="" %>
<%
	EmpDAO dao=EmpDAO.newInstance();
	List<EmpVO> list=dao.empListdata();
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��� ���</h1>
	<table border=1 width=800>
		<tr>
			<th>���</th>
			<th>�̸�</th>
			<th>����</th>
			<th>�Ի���</th>
			<th>�޿�</th>
		</tr>
		
</body>
</html>

















