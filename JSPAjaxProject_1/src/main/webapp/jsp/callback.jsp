<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 		배열.map(function(m){})
 */
function aaa()
{
	alert("Hello Callback")
}
function bbb(callback)
{
	callback();	
}
window.onload=function(){
	bbb(aaa)
}
</script>
</head>
<body>

</body>
</html>