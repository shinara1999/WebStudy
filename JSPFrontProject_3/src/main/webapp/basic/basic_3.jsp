<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		이항연산자
		=> 산술연산자 (+,-,*,/,%)
		+ : 산술 , 문자열 결합
		/ : 0으로 나눌 수 없다. 정수/정수=실수
						    ============
		% : 왼쪽부호가 남는다.
		    -5 	% -2 => -1
		    5 	% -2 => +1
		    -5 	%  2 => -1
		    5 	%  2 => +1
		"10"+"20" => 1020
		"10"-"20" => 오류 , -10
		"10"*"20" => 200
		"10"/"20" => 0.5
		=> +외의 다른 산술연산 시 자동으로 parseInt()
--%>
<%
	String a="aaa";
	request.setAttribute("a", a);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	document.write("10"+"20"+"<br>")
	document.write(("10"-"20")+"<br>")
	document.write(("10"*"20")+"<br>")
	document.write(("10"/"20")+"<br>")
	document.write(("10"%"20")+"<br>")
	
	// 주의점
	/*
		undefined => 변수의 초기화가 안된 상태, 변수 선언이 없는 경우
					 ====================
					  자동 초기화가 없다.
		NaN => 연산처리가 안된 상태
		Infinity => 0으로 나눌 수 없다.
	*/
	let m='A';	// char(X) , 싱글따옴표여도 문자열("")이다.
	let n=10;
	document.write("m+n="+(m+n)+"<br>") // +는 상관없다.
	document.write("m-n="+(m-n)+"<br>") // -는 NaN 오류발생
	document.write("m/n="+(m/n)+"<br>") // /는 NaN 오류발생
	let k;
	document.write("k="+k+"<br>") // undefined
	document.write("o="+o+"<br>") // 아예 출력 안됨
	
	let a=${a}
	document.write("a="+a+"<br>") // 출력 안됨
}
</script>
</head>
<body>

</body>
</html>


















