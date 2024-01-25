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
          <span>관리자페이지</span>
         </h3>
       </div>
       <ul class="list-group">
        <li class="list-group-item"><a href="#">회원관리</a></li>
        <li class="list-group-item"><a href="#">공지관리</a></li>
        <li class="list-group-item"><a href="../adminpage/admin_reserve.do">예약관리</a></li>
        <li class="list-group-item"><a href="#">구매관리</a></li>
        <li class="list-group-item"><a href="#">찜관리</a></li>
        <li class="list-group-item"><a href="#">묻고답하기</a></li>
       </ul>
     </div>
    </div>
    <div class="col-sm-10">
     <%-- 화면 출력 --%>
     <jsp:include page="${admin_jsp }"></jsp:include>
    </div>
  </div>
</body>
</html>