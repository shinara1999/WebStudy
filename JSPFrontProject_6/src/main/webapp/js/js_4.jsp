<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let s1="Hello JavaScript"
	document.write("s1="+s1+"<br>");
	let s2=s1.substring(0, 5); // 시작인덱스, 끝나는 인덱스
	document.write("s2="+s2+"<br>");
	let s3=s1.substr(6, 4); // 시작 인덱스부터 몇개를 자를 것인가? => 6번부터 4글자 자른다.
	document.write("s3="+s3+"<br>");
}
</script>
</head>
<body>

</body>
</html>