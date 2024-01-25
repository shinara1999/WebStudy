<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Login to Everdwell</title>
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="../etc/user/userdelete.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#delBtn').on('click',function(){
		let userID=$('#delBtn').attr("data-id");
		let pwd=$('#delete_pwd').val()
		if(pwd.trim()==="")
		{
			$('#delete_pwd').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../user/delete_ok.do',
			data:{"userID":userID,"pwd":pwd},
			success:function(result)
			{
				if(result==="yes")
				{
					var returnValue=confirm("탈퇴 하시겠습니까?");
					if(returnValue===true)
					{
						alert("탈퇴 되었습니다.")
						setTimeout("location.href='../main/main.do'",500);
					}
				}
				else
				{
					alert("비밀번호가 틀립니다.")
					$('#delete_pwd').val("")
					$('#delete_pwd').focus();
				}
			}
		})
	})
	
	window.addEventListener("keyup",(e)=>{
		if(e.keyCode===13)
		{
			let userID=$('#delBtn').attr("data-id");
			let pwd=$('#delete_pwd').val()
			if(pwd.trim()==="")
			{
				$('#delete_pwd').focus();
				return;
			}
			$.ajax({
				type:'post',
				url:'../user/delete_ok.do',
				data:{"userID":userID,"pwd":pwd},
				success:function(result)
				{
					if(result==="yes")
					{
						alert("탈퇴 되었습니다.");
						setTimeout("location.href='../main/main.do'",1000);
					}
					else
					{
						alert("비밀번호가 틀립니다.")
						$('#delete_pwd').val("")
						$('#delete_pwd').focus();
					}
				}
			})
		}
	})
});
</script>
</head>
<body>
<!-- partial:index.partial.html -->
<form id="delete_form">
  <div class="heading">회원 탈퇴</div>
    <label for="delete_pwd">비밀번호</label>
    <input type="password" name="delete_pwd" id="delete_pwd">
    <input type="button" value="탈퇴" id="delBtn" data-id="${sessionScope.email }" style="cursor:pointer;">
    <input type="button" onclick="location.href='../mypage/mypage_main.do'" value="취소" id="delnoBtn" style="cursor:pointer;">
</form>
</body>
</html>