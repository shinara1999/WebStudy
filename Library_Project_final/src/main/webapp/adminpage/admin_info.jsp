<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../mypage/libUse.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	#service_div h3{
		float: none;
		padding: 0;
		text-align: left;
	}
	#service_div ul{
		float: none;
		width: 100%;
		height: 90px;
		padding: 0;
	}
	#service_div ul li{
		float: left;
		width: 25%;
		height: 100%;
	}
	#service_div ul li a{
		background-color: #F6F6F6;
		display: block;
		width: 100%;
		height: 100%;
		text-decoration: none;
		position: relative;
	}
	#service_div .tit{
		position: absolute;
		top: 25px;
		left: 10px;
		text-align: left;
	}
	#service_div .cnt{
		position: absolute;
		top: 25px;
		right: 10px;
		text-align: left;
		font-size: 30px;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="row" id="service_div">
		<h3>서비스</h3>
		<ul>
			<li>
				<a href="../admin/bookManagement.do">
					<strong class="tit">도서대출 <br>관리</strong>
					<span class="cnt">0</span>
				</a>
			</li>
			<li>
				<a href="../admin/programList.do">
					<strong class="tit">프로그램<br>관리</strong>
					<span class="cnt">0</span>
				</a>
			</li>
			<li>
				<a href="../mypage/likeBookInq.do">
					<strong class="tit">묻고답하기<br>관리</strong>
					<span class="cnt">0</span>
				</a>
			</li>
			<!-- <li>
				<a href="#">
					<strong class="tit">신청중인<br>문화행사수</strong>
					<span class="cnt">0</span>
				</a>
			</li> -->
		</ul>
	</div>
</body>
</html>