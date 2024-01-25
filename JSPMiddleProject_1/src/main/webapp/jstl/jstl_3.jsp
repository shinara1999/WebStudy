<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>날짜 변환(자바)</h1>
	<%
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
	%>
	<%= today %>
	<h1>날짜 변환(JSTL)</h1>
	<c:set var="today" value="<%=date %>"/>
	${today }<br>
	<fmt:formatDate value="${today }" pattern="yyyy-MM-dd hh:mm:ss"/> <%-- 저장된 시간대가 아닌 현재 시간대를 가지고 온다. --%>
	<h1>숫자 변환(자바)</h1>
	<%
		int a=12345678;
		DecimalFormat d=new DecimalFormat("##,###,###");
		// => TO_CHAR(num, '99,999,999')
		String s=d.format(a);
	%>
	<%=s %>
	<h1>숫자 변환(JSTL)</h1>
	<c:set var="a" value="<%=a %>"/>
	${a }<br>
	<fmt:formatNumber value="${a }" type="currency"/>
</body>
</html>























