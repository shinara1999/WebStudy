<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/sideBar.css">
<link rel="stylesheet" href="../assets/css/searchForm.css">
</head>
<body>
  <div style="height: 50px;"></div>
  <div class="col-md-1"></div>
  <div class="col-md-2">
	<div class="panel">
		<div class="panel-heading">
			<h3 class="panel-title text-center">
	 		 <p>나만의 도서관</p>
			</h3>
		</div>
		<ul class="list-group">
			<li class="list-group-item"><a href="../user/userupdate.do">회원정보 수정</a></li>
			<li class="list-group-item"><a href="../user/userdelete.do">회원탈퇴</a></li>
		</ul>
	</div>
  </div>
  <jsp:include page="${user_jsp }"></jsp:include>
  <div style="clear: both;"></div>
</body>
</html>