<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="col-sm-3">
		<%-- 부메뉴 --%>
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-leaf"></i>
						<span>마이페이지 메뉴</span>
					</h3>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">회원 수정</a></li>
					<li class="list-group-item"><a href="#">회원 탈퇴</a></li>
					<li class="list-group-item"><a href="../mypage/mypage_reserve.do">예약 정보</a></li>
					<li class="list-group-item"><a href="#">구매 정보</a></li>
					<li class="list-group-item"><a href="#">찜 정보</a></li>
					<li class="list-group-item"><a href="#">묻고답하기 정보</a></li>
				</ul>
			</div>
		</div>
		<div class="col-sm-9">
		<%-- 화면 출력 --%>
			<jsp:include page="${mypage_jsp }"></jsp:include>
		</div>
	</div>
</body>
</html>