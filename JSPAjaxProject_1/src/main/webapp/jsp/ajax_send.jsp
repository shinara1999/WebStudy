<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		Ajax
		Async (비동기적인)
		javascript
		And
		XML => JSON
		
		1. 요청을 처리할 파일을 연결 => open
		2. 요청 데이터 전송 => send
		3. 처리할 함수를 자동호출하게 만든다. => callback
		
		Jquery
		$.ajax({
			type: GET/POST
			url: 요청 처리되는 JSP/Model 호출
			data:{} => 요청할 데이터가 없는 경우에는 생략할 수 있다.
			login.jsp?id=aaa&pwd=1234
			=>{"id":"aaa", "pwd":"1234"}
			success:function(res)
			{
				res=> 실행 결과를 읽어온다.
			}
		});
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.btn').click(function(){
		let no=$(this).attr("value")
		//alert(no)
		$.ajax({
			type:'get',
		    url:'ajax_recv.jsp',
			data:{"no":no}, // ?no=1
			success:function(html)
			{
				$('img').attr("src", html)
			}
		})			
	})
})
</script>
</head>
<body>
	<input type="button" value="1" class="btn">
	<input type="button" value="2" class="btn">
	<input type="button" value="3" class="btn">
	<input type="button" value="4" class="btn">
	<input type="button" value="5" class="btn">
	<input type="button" value="6" class="btn">
	<input type="button" value="7" class="btn">
	<center>
		<img src="../image/m1.jpg" width="300" height="350">
	</center>
</body>
</html>



















