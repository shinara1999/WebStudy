<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#sendBtn').click(function(){
		sendRequest('recv.jsp', null, recvCallback, 'POST')
	})
})
function recvCallback()
{
	if(httpRequest.readyState==4)
	{
		/*
			0 : 준비
			1 : open한 파일에 연결중
			2 : 파일 연결 완료
			3 : 요청데이터 전송중
			4 : 요청데이터 전송완료
		*/
		if(httpRequest.status==200)
		{
			$('#recv').html(httpRequest.responseText)
			// responseText => 일반문자열 / HTML
			// 
		}
	}
}
</script>
</head>
<body>
	<input type="button" value="전송" id="sendBtn">
	<div id="recv">
	
	</div>
</body>
</html>












