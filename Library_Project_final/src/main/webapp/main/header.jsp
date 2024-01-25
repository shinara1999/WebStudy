<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style type="text/css">
	header#header{
		position: sticky;
		top: 0;
		background-color: black;
		height: 50px;
		line-height: 0;
	}
	header#header .logo img{
		width: 70px;
		height: 35px;
		margin-top: 7px;
	}
	header#header form.search-form{
		margin: 0;
		height: 35px;
		transform: translate(-50%, 20%);
	}
	header#header form.search-form input[type="search"]{
		width: 240px; 
	}
	div#menubar{
		position: sticky;
		top: 50px;
		z-index: 999;
	}
</style>
<script type="text/javascript">
	function plsLogin() {
		alert('로그인이 필요한 서비스입니다')
		location.href='../user/login.do'
	}
</script>
</head>
<body>
			<header id="header">
				<div class="inner">
					<a href="../main/main.do" class="logo">
						<img src="../images/lib_logo2.png" alt="로고 이미지">
					</a>
					<!-- searchBar include -->
					<jsp:include page="../etc/searchBar/searchBar.jsp"></jsp:include>
					<nav id="nav">
					<c:if test="${sessionScope.email==null }">
						<a href="../user/login.do">로그인</a>
						<a href="../user/userjoin.do">회원가입</a>	
					</c:if>	
						
					<c:if test="${sessionScope.email!=null }">
					  <a href="../user/logout.do">로그아웃</a>
					
					<c:if test="${sessionScope.admin eq 'n' || sessionScope.admin eq null }">
						<a ${sessionScope.email==null?"onclick=plsLogin()":"href=../mypage/mypage_main.do" } style="cursor: pointer;">마이페이지</a>					
					</c:if>
					<c:if test="${sessionScope.admin eq 'y' }">
						<a href="../admin/bookManagement.do">관리자페이지</a>
					</c:if>
					</c:if>
					</nav>
					<a href="#navPanel" class="navPanelToggle"><span class="fa fa-bars"></span></a>
				</div>
			</header>
			<!-- menuBar include -->
			<jsp:include page="../etc/menuBar/menuBar.jsp"></jsp:include>
			<!-- Banner -->
			<section id="banner" ${main_jsp!="../main/home.jsp"?"style=padding:1em 0 1em 0;":""}>
			<h1>서 울 도 서 관</h1>
				<p ${main_jsp!="../main/home.jsp"?"style=margin:0;":""}>서울도서관에 오신 것을 환영합니다.</p>
			</section>
</body>
</html>