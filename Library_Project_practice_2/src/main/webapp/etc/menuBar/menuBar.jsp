<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/menubar.css">
</head>
<body>
<div class="container">
		<div style="position:relative;z-index:1;margin:0;">
			<ul class="flexnav" style="padding-left: 25%">
				<li>
					<a href="main.jsp">자료검색</a>
					<ul>
						<li><a href="../libUse/libReserveInfo">자료탐색</a></li>
						<li><a href="../libUse/libTimeInfo">새로 들어온 책</a></li>
						<li><a href="../libUse/seatReservation">대출이 많은 책</a></li>
					</ul>
				</li>
				<li>
					<a href="main.jsp">도서관이용</a>
					<ul>
						<li><a href="../libUse/libTimeInfo.do">도서관 이용 시간</a></li>
						<li><a href="../libUse/libTimeInfo">대출.반납.연장.예약</a></li>
						<li><a href="../libUse/seatReservation">자리 예약</a></li>
						<li><a href="../libUse/libWayInfo">찾아오시는 길</a></li>
					</ul>
				</li>
				<li>
					<a href="main.jsp">신청 및 소식</a>
					<ul>
						<li><a href="../libUse/libReserveInfo">공지사항</a></li>
						<li><a href="../libUse/libTimeInfo">보도자료</a></li>
						<li><a href="../libUse/seatReservation">일정 및 행사</a></li>
						<li><a href="../libUse/seatReservation">프로그램 신청</a></li>
					</ul>
				</li>
				<li>
					<a href="main.jsp">우리동네 도서관</a>
					<ul>
						<li><a href="../libUse/libReserveInfo">도서관 찾기</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>