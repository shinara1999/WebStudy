<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 450px;
}
h1{
	text-align: center;
}
.chatrow{
	width: 450px;
	height: 500px;
	margin: 0px auto;
}
#chatArea{
	height: 250px;
	overflow-y: auto; 
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#sendBtn').on('click', function(){
		let data=$('#sendMsg').val()
		if(data.trim()=="")
		{
			$('#sendMsg').focus();
			return;
		}
		$('#recvMsg').append(data+"<br>")
		// 스크롤바 맨 마지막에 내려가게 하기
		let ch=$('#chatArea').height();
		let m=$('#recvMsg').height()-ch;
		$('#chatArea').scrollTop(m)
		$('#sendMsg').val("")
		$('#sendMsg').focus()
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode==13)
		{
			let data=$('#sendMsg').val()
			if(data.trim()=="")
			{
				$('#sendMsg').focus();
				return;
			}
			$('#recvMsg').append(data+"<br>")
			// 스크롤바 맨 마지막에 내려가게 하기
			let ch=$('#chatArea').height();
			let m=$('#recvMsg').height()-ch;
			$('#chatArea').scrollTop(m)
			$('#sendMsg').val("")
			$('#sendMsg').focus()
		}
	})
})
</script>
</head>
<body>
	<div class="container">
	<h1>실시간 채팅</h1>
		<div class="row chatrow">
			<table class="table">
				<tr>
					<td>
						<div id="chatArea">
							<div id="recvMsg"><%-- 채팅문자열 위치 --%></div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type=text id="sendMsg" size=55 class="input-sm">
						<input type=button id="sendBtn" value="전송" class="btn btn-sm btn-primary">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>











