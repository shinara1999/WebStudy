<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="col-sm-2">
     <%-- 뷰메뉴 --%>
     <div class="panel panel-success">
       <div class="panel-heading">
         <h3 class="panel-title">
          <i class="glyphicon glyphicon-leaf"></i>
          <span>마이페이지</span>
         </h3>
       </div>
       <ul class="list-group">
        <li class="list-group-item"><a href="#">회원수정</a></li>
        <li class="list-group-item"><a href="#">회원탈퇴</a></li>
        <li class="list-group-item"><a href="../mypage/mypage_reserve.do">예약정보</a></li>
        <li class="list-group-item"><a href="../mypage/mypage_cart.do">장바구니정보</a></li>
        <li class="list-group-item"><a href="../mypage/mypage_buy.do">구매정보</a></li>
        <li class="list-group-item"><a href="#">찜정보</a></li>
        <li class="list-group-item"><a href="#">묻고답하기</a></li>
       </ul>
     </div>
    </div>
    <div class="col-sm-10">
     <%-- 화면 출력 --%>
     <jsp:include page="${mypage_jsp }"></jsp:include>
    </div>
  </div>
</body>
</html>