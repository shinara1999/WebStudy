<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		- 단일조건문
		if
		{
			조건이 true일 때 처리 문장
		}
		else
		{
			조건이 false일 때 처리 문장
		}
		
		- 다중조건문
		if(조건문)
		{
			조건이 true일 때 처리 문장
		}
		else if(조건문)
		{
			조건이 true일 때 수행 ==> 종료
			조건이 false일 때는 아래있는 if조건문으로 이동
		}
		else if(조건문)
		{
			조건이 true일 때 수행 ==> 종료
			조건이 false일 때는 아래있는 if조건문으로 이동
		}
		...
		else
		{
			해당 조건이 없는 경우에 수행하는 문장
		}
		==> 사용처 : 사용자 입력창에서 많이 사용 (글쓰기, 회원가입, 로그인, 우편번호)
		
		- 선택문 : switch
				  switch(정수)
				  {
				  	case 값:
				  		처리문장
				  		break;
				  	...
				  	...
				  	default: 처리문장
				  }
		- 반복문
			do~while
			while : 무한 스크롤
			***for
		
		- 반복제어문
			break / continue
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 350px;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript">
function login()
{
		// 1. id읽기 (입력한 id)
		let id=document.querySelector("#id");
		// $('#id')
		if(id.value==="")
		{
			// 입력이 안된 경우
			let div=document.querySelector(".id")
			div.innerHTML="<font color=red>아이디를 입력하세요.</font>"
			id.focus();
			return;
		}
		else
		{
			// 입력이 된 경우
			let div=document.querySelector(".id")
			div.innerHTML=""; // 공백을 가져온다.
		}
		
		// 2. 비밀번호 읽기
		let pwd=document.querySelector("#pwd");
		// $('#pwd')
		if(pwd.value==="")
		{
			// 입력이 안된 경우
			let div=document.querySelector(".pwd")
			div.innerHTML="<font color=red>비밀번호를 입력하세요.</font>"
			pwd.focus();
			return;
		}
		else
		{
			// 입력이 된 경우
			let div=document.querySelector(".pwd")
			div.innerHTML=""; // 공백을 가져온다.
		}
}
</script>
</head>
<body>
	<div class="container">
	  <h1>Login</h1>
		<div class="row">
			<table class="table">
				<tr>
					<th width="20%" class="text-center">ID</th>
					<td width="80%">
					<input type="text" id="id" size=25 class="input-sm">
						<div class="id"></div>
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-center">PWD</th>
					<td width="80%">
					<input type="password" id="pwd" size=25 class="input-sm">
						<div class="pwd"></div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="text-center">
					<input type="button" value="로그인" class="btn btn-sm btn-primary" onclick="login()">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
					















