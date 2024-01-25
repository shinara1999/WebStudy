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

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// <button v-on:click="">
	$('#loginBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()=="")
		{
			$('#result').text("아이디를 입력하세요")
			$('#id').focus(); // 아이디 입력하지 않으면 아이디창에 포커스 가게 한다.
			return;
		}
		else
		{
			$('#result').text("")
		}	
		
	})
	
	$('#loginBtn').click(function(){
		let pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
			$('#result').text("패스워드를 입력하세요")
			$('#pwd').focus();
			return;
		}
		else
		{
			$('#result').text("")
		}	
		
	})
})
</script>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<div class="row">
			<table class="table">
				<tr>
					<th width=20% class="text-center">ID</th>
					<td width=80%>
						<input type=text size=15 class="input-sm" id="id">
					</td>
				</tr>
				
				<tr>
					<th width=20% class="text-center">PWD</th>
					<td width=80%>
						<input type=password size=15 class="input-sm" id="pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<div style="color:red" id="result"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=button class="btn btn-sm btn-danger" id="loginBtn" value="로그인">
					</td>
				</tr>
				
			</table>
		</div>
	</div>
</body>
</html>














