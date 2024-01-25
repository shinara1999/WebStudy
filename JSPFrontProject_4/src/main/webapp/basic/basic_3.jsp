<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 다중 조건문
window.onload=function(){
	let kor=90
	let eng=80
	let math=89
	
	let total=kor+eng+math
	let avg=total/3.0
	
	// ;은 생략 가능
	
	document.write("국어:"+kor+"<br>")
	document.write("영어:"+eng+"<br>")
	document.write("수학:"+math+"<br>")
	document.write("총점:"+total+"<br>")
	document.write("평균:"+Math.round(avg)+"<br>")
	
	// 다중 조건문 => switchcase로 사용하는 것이 더 편리하다.
	/*let score='A'
	if(avg>=90)
		score='A'
	else if(avg>= 80)
		score='B'*/
	switch(Math.round(avg/10))
	{
	case 10:
	case 9:
		score='A';
		break;
	case 8:
		score='B';
		break;
	}
	document.write("학점:"+score+"<br>")
}
</script>
</head>
<body>

</body>
</html>