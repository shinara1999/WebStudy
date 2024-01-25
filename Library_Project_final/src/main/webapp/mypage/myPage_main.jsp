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
					<p>나만의 도서관</p>
				</h3>
			</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="../mypage/mypage_main.do">기본정보</a></li>
				<li class="list-group-item"><a href="../mypage/bookloaninfo_main.do">도서대출정보</a></li>
				<li class="list-group-item"><a href="../mypage/proAppInq.do">나의 신청</a></li>
				<li class="list-group-item"><a href="../mypage/mypage_cart.do">장바구니</a></li>
				<li class="list-group-item"><a href="../mypage/bookPurchaseList.do">구매 내역</a></li>
				<li class="list-group-item"><a href="../user/userupdate.do">회원정보 수정</a></li>
			</ul>
		</div>
    </div>
    <div class="col-md-8">
		<jsp:include page="${mypage_jsp }"></jsp:include>
		<jsp:include page="${user_jsp }"></jsp:include>
	</div>
	<div class="col-md-1"></div>
	<div style="clear: both;"></div>
</body>
</html>