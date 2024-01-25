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
	/* .mypage .row{
		margin: 0 auto;
		width: 950px;
	} */
	#basicinfo_div th,td{
		text-align: left;
	}
	#basicinfo_div #name{
		font-size: 20px;
		font-weight: bold;
		color: rgb(67,116,217);
	}
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
<!-- 기본정보 -->
			<div id="basicinfo_div">
				<table class="table">
					<tr class="info">
						<td colspan="3">
							<span id="name">${vo.name }</span>님,반갑습니다.
						</td>
					</tr>
					<tr>
						<td width="15%" rowspan="4">
							<img src="../images/user-profile.png" width="130px;" height="130px;">
						</td>
						<td width="10%">아이디</td>
						<td width="75%">${vo.userID }</td>
					</tr>
					<tr>
						<td width="10%">회원가입일</td>
						<td width="75%">${vo.signdate_str }</td>
					</tr>
					<tr>
						<td width="10%">휴대폰번호</td>
						<td width="75%">${vo.phone }</td>
					</tr>
					<tr>
						<td width="10%">이메일주소</td>
						<td width="75%">${vo.email }</td>
					</tr>
				</table>
			</div>
			<div class="row" id="service_div">
				<h3>서비스 이용 현황</h3>
				<ul>
					<li>
						<a href="../mypage/bookloaninfo_main.do">
							<strong class="tit">대출중인<br>도서수</strong>
							<span class="cnt">${loanStatusTotalCnt }</span>
						</a>
					</li>
					<li>
						<a href="../mypage/reserveStatus.do">
							<strong class="tit">예약신청한<br>도서수</strong>
							<span class="cnt">${reserveStatusTotalCnt }</span>
						</a>
					</li>
					<li>
						<a href="../mypage/likeBookInq.do">
							<strong class="tit">좋아요<br>도서수</strong>
							<span class="cnt">${likeBookCnt }</span>
						</a>
					</li>
					<li>
						<a href="../mypage/proAppInq.do">
							<strong class="tit">신청중인<br>문화행사수</strong>
							<span class="cnt">${programApplCnt }</span>
						</a>
					</li>
				</ul>
			</div>
</body>
</html>