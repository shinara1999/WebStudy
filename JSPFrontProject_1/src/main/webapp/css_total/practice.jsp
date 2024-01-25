<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	width: 700px;
	height: 500px;
	border: 1px solid red;
	margin: 0px auto;
}
.top{
	width: 700px;
	height: 100px;
	background-color: cyan;
}
.aside1{
	width: 150px;
	height: 300px;
	background-color: yellow;
	float: left;
}
.section{
	width: 400px;
	height: 300px;
	background-color: green;
	float: left;
}
.aside2{
	width: 150px;
	height: 300px;
	background-color: orange;
	float: left;
}
.bottom{
	width: 700px;
	height: 100px;
	background-color: gray;
	clear: both;
}
</style>
</head>
<body>
	<div class="container">
		<div class="top"></div>
		<div class="aside1"></div>
		<div class="section"></div>
		<div class="aside2"></div>
		<div class="bottom"></div>
	</div>
</body>
</html>