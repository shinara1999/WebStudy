<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Login to Everdwell</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'><link rel="stylesheet" href="../etc/user/Before.css">
</head>
<body>
<!-- partial:index.partial.html -->
<form action="#" id="login-form">
  <div class="heading">회원정보 수정</div>
    <label for="email" style="margin-top:8%">아이디</label>
    <input type="text" name="email" id="id">
    <label for="password">비밀번호</label>
    <input type="text" name="password" id="up_pwd">
    <input type="button" onclick="location.href='../user/mUpdate.do'" value="수정" id="s1" style="cursor:pointer;">
    <input type="button" onclick="javascript:history.back()" value="취소" id="s2" style="cursor:pointer;">
</form>
<jsp:include page="${mUpdate_jsp }"></jsp:include>
</body>
</html>
