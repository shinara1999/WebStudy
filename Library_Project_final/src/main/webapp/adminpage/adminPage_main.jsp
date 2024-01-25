<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	ul li{
		list-style: none;
	}
</style>
</head>
<body>
<!-- 마이페이지 메인 -->
<!-- 메뉴바 include 부분 -->
<!-- 각 메뉴에 대한 jsp include 부분 -->
<div style="height: 50px;"></div>
	<div class="col-md-1"></div>
	<div class="col-md-2">
		<div class="panel">
			<div class="panel-heading">
				<h3 class="panel-title text-center">
					<p>관리자페이지</p>
				</h3>
			</div>
			<ul class="list-group">
				<!-- <li class="list-group-item"><a href="../admin/main.do">서비스</a></li> -->
				<li class="list-group-item"><a href="../admin/bookManagement.do">도서대출 관리</a></li>
				<li class="list-group-item"><a href="../admin/programList.do">프로그램 관리</a></li>
				<li class="list-group-item"><a href="../admin/noticeList.do?typeno=0">공지사항 관리</a></li>
				<li class="list-group-item"><a href="../admin/noticeList.do?typeno=1">보도자료 관리</a></li>
				<li class="list-group-item"><a href="../admin/qnaList.do">묻고답하기 관리</a></li>
			</ul>
		</div>
    </div>
    <div class="col-md-9">
		<jsp:include page="${admin_jsp }"></jsp:include>
	</div>
	<!-- <div class="col-md-1"></div> -->
	<div style="clear: both;"></div>
</body>
</html>