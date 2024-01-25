<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		비교연산자
		=== (==) => 같다
		!== (!=) => 같지않다
		<		 => 작다
		>		 => 크다
		<=		 => 작거나 같다
		>=		 => 크거나 같다
		====================> 숫자만 아니라 문자열 비교시에도 처리 가능
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a=10
	let b=5
	console.log("a===b =>"+(a===b))
	console.log("a!==b =>"+(a!==b))
	console.log("a<b =>"+(a<b))
	console.log("a>b =>"+(a>b))
	console.log("a<=b =>"+(a<=b))
	console.log("a>=b =>"+(a>=b))
	
	let c="hello"
	let d="JavaScript"
	console.log("c===d =>"+(c===d))
	console.log("c!==d =>"+(c!==d))
	console.log("c<d =>"+(c<d))
	console.log("c>d =>"+(c>d))
	console.log("c<=d =>"+(c<=d))
	console.log("c>=d =>"+(c>=d))
}
</script>
</head>
<body>

</body>
</html>