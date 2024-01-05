<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		let id=$('#log_id').val();
		if(id.trim()==="")
		{
			$('#log_id').focus();
			return;
		}
		let pwd=$('#log_pwd').val()
		if(pwd.trim()==="")
		{
			$('#log_pwd').focus()
			return;
		}
		
		$.ajax({
			type:'post',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			success:function(result)
			{
				// NOID , NOPWD , OK
				if(result==='NOID')
				{
					alert("아이디 존재하지 않습니다")
					$('#log_id').val("");
					$('#log_pwd').val("");
					$('#log_id').focus()
				}
				else if(result==='NOPWD')
				{
					alert("비밀번호가 틀립니다")
					$('#log_pwd').val("");
					$('#log_pwd').focus()
				}
				else
				{
					location.href="../main/main.do"
				}
			}
		})
	})
	$('#logoutBtn').on('click',function(){
		location.href="../member/logout.do"
	})
});
</script>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">Food & Recipe</a></h1>
    </div>
    <div class="fl_right">
    
     <c:if test="${sessionScope.id==null }">
      <ul class="inline">
        <li><i class="fa fa-user"></i><input type="text" class="input-sm" id="log_id"></li>
        <li><i class="fa fa-key"></i> <input type="password" class="input-sm" id="log_pwd"></li>
        <li><input type="button" class="btn btn-sm btn-danger" id="logBtn" value="로그인" style="height: 28px;width: 100px"></li>
      </ul>
     </c:if>
     <c:if test="${sessionScope.id!=null }">  
      <ul class="inline">
        <li><i class="fa fa-user"></i>${sessionScope.name }(${sessionScope.admin=='y'?"관리자":"일반사용자" })님 로그인중입니다</li>
        <li><input type="button" class="btn btn-sm btn-danger" id="logoutBtn" value="로그아웃" style="height: 28px;width: 100px"></li>
      </ul>
     </c:if>
    </div>
    </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
       <c:if test="${sessionScope.id==null }">
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디 찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호 찾기</a></li>
        </ul>
       </c:if>
       <c:if test="${sessionScope.id!=null }">
        <ul>
          <li><a href="pages/gallery.html">회원수정</a></li>
          <li><a href="pages/full-width.html">회원탈퇴</a></li>
        </ul>
       </c:if>
      </li>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/list.do">전체 맛집</a></li>
          <li><a href="../food/location.do">지역별 맛집 찾기</a></li>
          <c:if test="${sessionScope.id!=null }">
           <li><a href="pages/sidebar-left.html">맛집 예약</a></li>
          </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="pages/gallery.html">레시피</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
          <c:if test="${sessionScope.id!=null }">
           <li><a href="pages/sidebar-left.html">레시피 만들기</a></li>
          </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">서울여행</a>
        <ul>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/full-width.html">자연&관광</a></li>
          <li><a href="pages/sidebar-left.html">쇼핑</a></li>
          <li><a href="pages/sidebar-left.html">호텔</a></li>
          <li><a href="pages/sidebar-left.html">날씨</a></li>
        </ul>
      </li>
      <li><a href="#">스토어</a></li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="../board/list.do">자유게시판</a></li>
          <li><a href="pages/full-width.html">공지사항</a></li>
          <li><a href="../databoard/list.do">자료실</a></li>
        </ul>
      </li>
      <c:if test="${sessionScope.id!=null }">
       <c:if test="${sessionScope.admin=='n' }">
        <li><a href="#">마이페이지</a></li>
       </c:if>
       <c:if test="${sessionScope.admin=='y' }">
        <li><a href="#">관리자페이지</a></li>
       </c:if>
      </c:if>
    </ul>
    </nav>
</div>
</body>
</html>