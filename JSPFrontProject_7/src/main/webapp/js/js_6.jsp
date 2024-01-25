<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		Date : 날짜와 시간을 관리하는 내장 객체
		=> 선언 방식
		   let today=new Date()
		   => getYear ==> getFullYear() : 연도 읽기
		   => getMonth() => 0부터 시작
		   => getDate() => 날
		   => getDat() => 요일 => 0=일...
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let today=new Date();
	let year=today.getFullYear();
	let month=today.getMonth()+1;
	let date=today.getDate();
	let day=today.getDay();
	let week='';
	switch(day)
	{
	case 0: week='일'; break;
	case 1: week='월'; break;
	case 2: week='화'; break;
	case 3: week='수'; break;
	case 4: week='목'; break;
	case 5: week='금'; break;
	case 6: week='토'; break;
	}
	document.write("오늘은"+year+"년 "+month+"월 "+date+"일 "+week+"요일 입니다.")
}
</script>
</head>
<body>

</body>
</html>