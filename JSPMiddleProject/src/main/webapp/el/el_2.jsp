<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%-- 
		p.581
		
		기본객체 (내장객체)
		=> ***requestScope / ***sessionScope / applicationScope
		= param / pageContext
		EL은 자바변수를 출력하는 것이 아니다.
		== request, session에 저장된 데이터 출력
--%>
<jsp:useBean id="model" class="com.sist.dao.StudentModel"/>
<%
	StudentVO vo=model.studentDetailData();
	request.setAttribute("vo", vo);
	//String name="신아라";
	//request.setAttribute("name", "성준수");
	//session.setAttribute("name1", "전영중");
	// param => getParameter("name")
	// param.key => param.name
	// applicationScope => realpath
	/*
		${} => 출력물 (request, session에 담겨 출력을 요청시에만 사용)
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
학번:${vo.hakbun }<br>
이름:${vo.getName() }<br>
국어:${vo.getKor() }<br>
영어:${vo.getEng() }<br>
수학:${vo.getMath() }<br>
<%-- 이름은 ${requestScope.name } 입니다..<br>
이름은 ${sessionScope.name1 } 입니다..
<br>
이름은 <%=request.getAttribute("name") %> 입니다.. --%>
</body>
</html>