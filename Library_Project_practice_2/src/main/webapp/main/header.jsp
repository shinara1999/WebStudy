<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<header id="header">
				<div class="inner">
					<a href="index.html" class="logo">
						<img src="../images/lib_logo.png" alt="로고 이미지">
					</a>
					<nav id="nav"><a href="../user/login.jsp">로그인</a>
						<a href="../user/userjoin.jsp">회원가입</a>
						<a href="../user/mypage.jsp">마이페이지</a>
					</nav><a href="#navPanel" class="navPanelToggle"><span class="fa fa-bars"></span></a>
				</div>
			</header>
			<!-- Banner -->
			<section id="banner">
			<h1>서 울 도 서 관</h1>
				<p>서울도서관에 오신 것을 환영합니다.</p>
				<!-- searchBar include -->
				<jsp:include page="../etc/searchBar/searchBar.jsp"></jsp:include>
			</section>
			<!-- menuBar include -->
			<jsp:include page="../etc/menuBar/menuBar.jsp"></jsp:include>
</body>
</html>