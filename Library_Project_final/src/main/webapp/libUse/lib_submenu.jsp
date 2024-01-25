<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>lib_submenu</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/sideBar.css">
</head>
<body>
<div style="height: 50px;"></div>
	<div class="col-md-1"></div>
	<div class="col-md-2">
		<div class="panel">
			<div class="panel-heading">
				<h3 class="panel-title text-center">
					<p>도서관 이용</p>
				</h3>
			</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="../libUse/libTimeInfo.do">도서관 이용 시간</a></li>
				<li class="list-group-item"><a href="../libUse/libReserveInfo.do">대출.반납.연장.예약</a></li>
				<li class="list-group-item"><a href="#">자리 예약</a></li>
				<li class="list-group-item"><a href="../libUse/libWayInfo.do">찾아오시는 길</a></li>
			</ul>
		</div>
    </div>
    <div class="col-md-8">
		<jsp:include page="${libUse_jsp }"></jsp:include>
	</div>
	<div class="col-md-1"></div>
	<div style="clear: both;"></div>
</body>
</html>