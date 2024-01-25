<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		setInterval()	==> 내가 원하는 시간대에 계속 수행하게 만든다. 
		setTimeout()	==> 초가 지나면 다른 일을 할 수 있게 만든다.
		
		
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let index=1;
window.onload=function(){
	setInterval('show()', 500);
}
function show(){
	index++;
	if(index>7)
		index=1;
	let img=document.querySelector('img')
	img.src='../image/m'+index+'.jpg';
	
}
</script>
</head>
<body>
  <center>
	<img src="../image/m1.jpg" width="600" height="700">
  </center>
</body>
</html>



















