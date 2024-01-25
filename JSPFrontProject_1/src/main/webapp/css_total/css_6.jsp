<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a{
	width: 100px;
	height: 100px;
	position: absolute;
}
.a:nth-child(1){
	background-color: pink;
	left: 10px;
	top: 10px;
	z-index: 1;
}
.a:nth-child(2){
	background-color: blue;
	left: 50px;
	top: 50px;
	z-index: 5;
}
.a:nth-child(3){
	background-color: green;
	left: 90px;
	top: 90px;
	z-index: 3;
}
</style>
</head>
<body>
	<div class="a"></div>
	<div class="a"></div>
	<div class="a"></div>
</body>
</html>