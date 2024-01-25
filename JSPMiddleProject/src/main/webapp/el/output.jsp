<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
			param.name => request.getParameter("name") 과 같다.
			empty => 값이 없는 경우 => true
				     값이 있는 경우 => false
				     => List
				     => 검색 결과가 없다. / 장바구니가 비워져 있다.
	--%>
	<h1>${empty param.name }</h1>
</body>
</html>