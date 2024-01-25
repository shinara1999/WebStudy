<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="../etc/user/userjoinstyle.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe']
})
$(function(){
	$('#checkBtn').click(function(){
		Shadowbox.open({
			content:'../user/idcheck.do',
			player:'iframe',
			title:'아이디 중복체크',
			width:350,
			height:200
		})
	})
	
	$('#postBtn').click(function(){
		Shadowbox.open({
			content:'../user/post.do',
			player:'iframe',
			title:'우편번호 검색',
			width:490,
			height:350
		})
	})
})
function fn_phone()
{
	let phone=$('#phone').val();
		$.ajax({
		type:'post',
		url:'../user/phonecheck_ok.do',
		data:{"phone":phone},
		success:function(result)
		{
			let count=Number(result.trim());
			if(count==0)
			{
				$('#res').html('<span style="color:black">'+phone+'는(은) 사용 가능한 전화번호입니다.</span>')		
			}
			else if(count==1)
			{
				$('#res').html('<span style="color:#F15F5F">'+phone+'는(은) 이미 사용중인 전화번호입니다.</span>')
				$('#phone').val("");
				$('#phone').focus();
			}
			else if(count==3)
			{
				$('#res').html('<span style="color:#F15F5F">전화번호는 11자리로 입력해주세요</span>')
			}
		  }
		})
}
/*유효성 검사*/
function check()
{
	if(frm.id1.value.length==0)
	{
		alert("아이디를 입력하세요.");
		frm.id1.focus();
		return false;
	}

	if(frm.join_pwd.value.length==0)
	{
		alert("비밀번호를 입력하세요.");
		frm.join_pwd.focus();
		return false;
	}
	
	if(frm.join_pwd2.value.length==0)
	{
		alert("비밀번호 확인을 입력하세요.");
		frm.join_pwd2.focus();
		return false;
	}
	
	if(frm.join_pwd.value != frm.join_pwd2.value)
	{
		alert("비밀번호가 일치하지 않습니다.");
		frm.join_pwd.select();
		return false;
	}

	if(frm.join_name.value.length==0)
	{
		alert("이름을 입력하세요.");
		frm.join_name.focus();
		return false;
	}
	
	if(frm.join_birth.value.length==0)
	{
		alert("생년월일을 입력하세요.");
		frm.join_birth.focus();
		return false;
	}	

	if(frm.phone.value.length==0)
	{
		alert("전화번호를 입력하세요.");
		frm.phone.focus();
		return false;
	}

	if(frm.join_email.value.length==0)
	{
		alert("이메일을 입력하세요.");
		frm.email1.focus();
		return false;
	}

	if(frm.post1.value.length==0)
	{
		alert("우편번호를 입력하세요.");
		frm.post1.focus();
		return false;
	}
	if(frm.hno.value.length==0)
	{
		alert("질문을 선택하세요.");
		frm.hno.focus();
		return false;
	}
	if(frm.hintA.value.length==0)
	{
		alert("질문의 답을 입력하세요.");
		frm.a.focus();
		return false;
	}
	if(frm.addr1.value.length==0)
	{
		alert("주소를 입력하세요.");
		frm.addr1.focus();
		return false;
	}

	if(frm.id1!=0 && frm.join_pwd!=0 && frm.join_name!=0 
			      && frm.join_birth!=0 && frm.phone!=0
			      && frm.post!=0 && frm.addr1!=0 && frm.hintA!=0)
	{
		alert("회원가입을 축하합니다.");
	}
}
</script>
</head>
<body>
<form id="login-form" method="post" action="../user/join_ok.do" name="frm">
  <div class="heading">회원가입</div>
  <div class="left">
    <input type="text" placeholder="아이디" name="id1" id="id1" style="float:left;width:53%;margin-top:7%;" readonly>
    <input type=button value="중복체크" id="checkBtn" style="cursor:pointer;width:16%;margin-top:7%;">
    <input type="password" placeholder="비밀번호" name="pwd" id="join_pwd">
    <input type="password" placeholder="비밀번호 확인" id="join_pwd2">
    <input type="text" placeholder="이름" name="name" id="join_name">
    <input type="email" placeholder="이메일" name="email" id="join_email">
    <input type="text" placeholder="우편번호" name="post1" id="post1" style="float:left;width:45.7%;" readonly>
    <input type=button value="우편번호검색" id="postBtn" style="cursor:pointer;width:23%;">
    <input type="text" placeholder="주소" id="addr1" name="addr1" readonly>
    <input type="text" placeholder="상세주소" name="addr2" id="addr2">
    <input type="text" placeholder="전화번호" name="phone" id="phone" style="float:left;width:53%;">
    <input type=button value="중복체크" id="phoneBtn" onclick="fn_phone()" style="cursor:pointer;width:16%;">
    <label id=res></label>
	<div class="row">
      <div class="col-half1" style="width:284px">
        <h2 style="margin-left:22%;margin-top:13%;">생년월일</h2>
          <div class="col-third">
            <input type="date" name="birthday" id="join_birth" style="margin-left:22%;width:180px;color:black">
          </div>
      </div>
      <div class="col-half1" style="width:380px">
        <h2 style="margin-top:9%;margin-left:16%;">성별</h2>
        <br>
          <input id="gender_male" type="radio" name="gender" value="남자">
          <label for="gender_male" id="male" style="cursor:pointer;color:black">남자</label>
          <input id="gender_female" type="radio" name="gender" value="여자">
          <label for="gender_female" id="female" style="cursor:pointer;color:black">여자</label>
       </div>
      </div>
    <h2 style="margin-left:14%;margin-top:6%">계정 확인 QnA</h2>
     <select name="hno" id="hno" style="cursor:pointer;width:70%;margin-left:14%">
       <option selected>질문선택</option>
       <c:forEach var="vo" items="${hList}" varStatus="s">
         <option value="${s.index+1 }">${vo }</option>
       </c:forEach>
     </select>
    <input type="text" placeholder="질문에 대한 답" id="hintA" name="hintA">
    <h6 style="color:red;margin-left:35%">※계정 확인 QnA는 수정할 수 없습니다.</h6>
      <input type="submit" value="가입" onClick="return check()" id="joinBtn" name="joinBtn" style="cursor:pointer;float:left;margin-left:14%">
      <input type="button" onClick="javascript:history.back()" value="취소" style="cursor:pointer;margin-left:30%">
    <br><br><br>
    <h2 style="margin-left:5%">이용 약관</h2>
      <input id="terms" type="checkbox">
      <label for="terms" style="cursor:pointer;margin-left:9%">실명 인증된 아이디로 가입, 위치기반서비스 이용약관, 이벤트・혜택 정보 수신 동의를 포함합니다.</label>
      <br><br>
  </div>
</form>
<!-- partial -->
</body>
</html>
