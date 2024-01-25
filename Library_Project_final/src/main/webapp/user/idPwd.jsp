<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Login to Everdwell</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'><link rel="stylesheet" href="../etc/user/idpwdstyle.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	  $('#fi').click(function(){
		  let name=$('#find_nid').val();
		  if(name.trim()==="")
		  {
			  $('#find_nid').focus();
			  $('#find_nid').val("");
			  return;
		  }
		  
		  let email=$('#find_eid').val();
		  if(email.trim()==="")
		  {
			  $('#find_eid').focus();
			  $('#find_eid').val("");
			  return;
		  }
		  $.ajax({
			  type:'post',
			  url:'../user/findid_ok.do',
			  data:{"find_nid":name,"find_eid":email},
			  success:function(result)
			  {
				  let res=result.trim()
				  if(res==='FINDNO')
				  {
					  alert("이름이나 이메일을 다시 입력해주세요.")
				  }
				  else
				  {
					  
					  $('#res1').html('<span style="color:blue">아이디: '+res+'</span>')	
				  }
			  }
		  })
	  })
	  
	  $('#fp').click(function(){
		  let userID=$('#find_ipwd').val();
		  if(userID.trim()==="")
		  {
			  $('#find_ipwd').focus();
			  return;
		  }
		  
		  let hno=$('#find_hno').val();
		  if(hno.trim()==="0")
		  {
			  $('#find_hno').select();   //focus 이동하면서 입력한게 블록으로 선택됨
			  return;
		  }
		  
		  let hintA=$('#find_hintA').val();
		  if(hintA.trim()==="0")
		  {
			  $('#find_hintA').focus();
			  return;
		  }
		  
		  $.ajax({
			  type:'post',
			  url:'../user/findpwd_ok.do',
			  data:{"find_ipwd":userID,"find_hno":hno,"find_hintA":hintA},
			  success:function(result)
			  {
				  let res=result.trim()
				  if(res==='FINDNO')
				  {
					  alert("아이디나 질문을 다시 입력해주세요.")
				  }
				  else
				  {
					  $('#res2').html('<span style="color:blue">비밀번호: '+res+'</span>')
					  $('.pwdall').show();				  }
			  }
		  })
	  })
});
</script>
</head>
<body>
<!-- partial:index.partial.html -->
<form action="#" id="login-form">
  <div class="heading">아이디/비밀번호 찾기</div>
  <div class="left">
    <label for="find_nid" style="margin-top: 5%;">이름</label>
    <input type="text" name="find_nid" id="find_nid" required>
    <label for="find_eid" style="margin-top: 10%;">이메일</label>
    <input type="text" name="find_eid" id="find_eid" required>
    <input type="button" value="아이디 찾기" id="fi">
    <br>
    <label id=res1></label>
   </div>

  <div class="right">
    <label for="find_ipwd" style="margin-top: 5%;">아이디</label>
    <input type="text" id="find_ipwd" name="find_ipwd" required>
    <label>계정 확인 질문</label>
    <select name="find_hno" id="find_hno">
	<option>질문선택</option>
       <c:forEach var="vo" items="${hList}" varStatus="s">
         <option value="${s.index+1 }">${vo }</option>
       </c:forEach>
     </select>
    <label>질문에 대한 답</label>
    <input type="text" name="find_hintA" id="find_hintA" required>
    <input type=button value="비밀번호 찾기" id="fp">
    <br>
    <label id=res2></label>
  </div>
  
</form>
<!-- partial -->
  <script  src="../etc/user/script.js"></script>

</body>
</html>
