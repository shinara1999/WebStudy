<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	width: 900px;
	height: 700px;
	border: 1px solid black;
	margin: 0px auto;
}
.header{
	width:  900px;
	height: 100px;
	background-color: pink;
}
.nav{
	width:  900px;
	height: 150px;
	background-color: lightyellow;
}
.aside{
	width:  200px;
	height: 350px;
	background-color: violet;
	float: left;
}
.section{
	width:  700px;
	height: 350px;
	background-color: skyblue;
	float: left;
}
.footer{
	width:  900px;
	height: 100px;
	background-color: lightgreen;
	clear: both;
}
.search{
	width:  700px;
	margin: 0px auto;
}
#keyword{
	width: 300px;
	height: 50px;
	margin-top: 20px;
	margin-left: 170px;
}
#btn{
	width: 50px;
	height: 50px;
}
ul{
	list-style: none;
	background-color: lightgray;
}
ul li{
	padding: 15px;
	float: left;
	font-weight: bold;
	color: green;
}
ul li:first-child {
	border-radius: 10px 0px 0px 10px;
}
ul li:last-child {
	border-radius: 0px 10px 10px 0px;
}
ul li:nth-child(odd) {
	background-color: paleorange;
}
ul li:nth-child(even) {
	background-color: palered;
}
ul li:hover {
	cursor: pointer;
	color: white;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<div class="search">
				<input type=text id="keyword">
				<input type=button id="btn" value="검색">
			</div>
		</div>
		<div class="nav">
			<ul>
				<li>홈</li>
				<li>맛집</li>
				<li>레시피</li>
				<li>서울여행</li>
				<li>제주여행</li>
				<li>추천</li>
				<li>커뮤니티</li>
				<li>마이페이지</li>
			</ul>
		</div>
		<div class="aside"></div>
		<div class="section"></div>
		<div class="footer"></div>
	</div>
</body>
</html>

















