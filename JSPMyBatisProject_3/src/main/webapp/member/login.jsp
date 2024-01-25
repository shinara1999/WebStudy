<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.row {
    margin: 0px auto;
    width:300px; 
}
h1{
    text-align: center
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').on('click',function(){
		let id=$('#id').val()
		if(id.trim()==="")
		{
			$('#id').focus()
			return
		}
		let pwd=$('#pwd').val()
		if(pwd.trim()==="")
		{
			$('#pwd').focus()
			return
		}
		
		// 전송후에 데이터 결과값을 받는다 
		$.ajax({
			type:'post',
			url:'../member/login_ok.do',
			data:{"id":id,"pwd":pwd},
			success:function(res)
			{
				// res ==> NOID , NOPWD , OK
				if(res==='NOID')
				{
					alert("아이디가 존재하지 않습니다\n다시 입력하세요")
					$('#id').val("")
					$('#pwd').val("")
					$('#id').focus()
				}
				else if(res==='NOPWD')
				{
					alert("비밀번호가 틀립니다\n다시 입력하세요")
					$('#pwd').val("")
					$('#pwd').focus()
				}
				else 
				{
					location.href="../main/main.do"
				}
			},
			error:function(e)
			{
				console.log(e)
			}
		})
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
         <input type=text id=id size=20 class="input-sm">
        </td>
       </tr>
       <tr>
        <th width=20% class="text-center">PWD</th>
        <td width=80%>
         <input type=password id=pwd size=20 class="input-sm">
        </td>
       </tr>
       <tr>
         <td class="text-center" colspan="2">
           <input type=button value="로그인"
             class="btn-sm btn-primary" id="logBtn">
         </td>
       </tr>
     </table>
   </div>
  </div>
</body>
</html>