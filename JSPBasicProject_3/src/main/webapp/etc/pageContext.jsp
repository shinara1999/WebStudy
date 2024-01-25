<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
		1. 내장객체얻기
		   getRequest(), getResponse(), getOut()
		   getSession(), getPage(), getException()
		   application => getServletContext()
		   => 사용 빈도가 거의 없다 (99.99999%)
		   
		   request.getParameter()
		   pageContext.getRequest().getParameter()
		2. 웹 흐름 제어
		   include(), ***forward() => 파일마다 request를 공유한다.
		   pageContext.include() ==> X
		    => <jsp:include>
		    =>
		       1. <%@ include file=""%> : 정적
		          => file에는 반드시 file명을 설정한다.
		       2. <jsp:include page=""> : 동적
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>




















