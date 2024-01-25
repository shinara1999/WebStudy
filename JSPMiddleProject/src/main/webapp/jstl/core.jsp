<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
		prefix="c" => <c:
		prefix="core" => <core:
		=> 본인이 정하는 것이다. (주로 c 사용)
		
		JSTL Java Standard Tag Library => 자바의 제어문 => 태그로 만들어져 있다.
		====
		 core : 변수 설정 , 제어문 , URL (화면 이동)
		 	<c:set> : 변수 설정
		 	<c:set var="today" value="2023-12-11"/>
		 	=> request.setAttribute("today", "");
		 	=> ${today}
		 	<c:redirect url="a.jsp">
		 	=> response.sendRedirect("a.jsp");
		 	<c:if> if => 다중조건문 / else문장이 없다.
		 	<c:forEach> for
		 	<c:choose> switch
		 	<c:forTokens>
		 	
		 format / xml / sql / fn 
		 ======				  == 
		 날짜변환, 숫자변환		  String메소드 호출
--%>
<%
	List<String> list=new ArrayList<String>();
	list.add("신아라");
	list.add("전영중");
	list.add("성준수");
	list.add("주찬양");
	list.add("최종수");
%>
<c:set var="list" value="<%=list %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Java : for</h1>
	<%
		for(int i=1;i<=10;i++)
		{
	%>
			<%=i %>&nbsp;
	<%
		}
	%>
	<h1>JSTL : for</h1>
	<c:forEach var="i" begin="1" end="10" step="1"> <%-- end는 숫자 포함 / step="1"은 생략 가능 / step="-1" 사용 불가 --%>
		${i }&nbsp;
	</c:forEach>
	<h1>for-each</h1>
	<ul>
	<%
		for(String name:list)
		{
	%>
		<li><%=name %></li>
	<%
		}
	%>
	</ul>
	<h1>JSTL</h1>
	<ul>
		<%-- var은 자동 지정 변수 --%>
		<c:forEach var="name" items="${list }">
			<li>${name }</li>
		</c:forEach>
	</ul>
</body>
</html>












