<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#box').hover(function(){ // if
		$('#box').addClass('hover') // <div class="hover">
	},function(){ // else
		$('#box').removeClass('hover') // <div>
		// 메뉴 => active
	})
})
</script>
<style type="text/css">
#box{
	width: 100px;
	height: 100px;
	background-color: pink
}
#box.hover{
	background-color: skyblue;
	border-radius: 50px;
}
</style>
</head>
<body>
	<div id="box"></div>
</body>
</html>