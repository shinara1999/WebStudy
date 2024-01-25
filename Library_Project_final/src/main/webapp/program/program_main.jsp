<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/searchForm.css">
<link rel="stylesheet" href="../assets/css/sideBar.css">
<style type="text/css">
	hr{
		border: 0;
		height: 2px;
		background-color: skyblue;
	}
</style>
</head>
<body>
	<div style="height: 50px;"></div>
	<div class="col-md-2">
		<div class="panel">
			<div class="panel-heading">
				<h3 class="panel-title text-center">
					<p>신청 및 소식</p>
				</h3>
			</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="../Board/notice.do?typeno=1">공지사항</a></li>
				<li class="list-group-item"><a href="../Board/notice.do?typeno=2">보도자료</a></li>
				<li class="list-group-item"><a href="../Board/qna.do">묻고답하기</a></li>
				<li class="list-group-item"><a href="../Board/calendar.do">일정 및 행사</a></li>
				<li class="list-group-item"><a href="../program/programList.do">프로그램 신청</a></li>
			</ul>
		</div>
    </div>
	<div class="col-md-10">
		<jsp:include page="${program_jsp }"></jsp:include>
	</div>
	<div style="clear: both;"></div>
</body>
</html>