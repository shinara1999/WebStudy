<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	@media screen and (max-width:1190px){
		#aside{
			visibility: hidden;
		}
	}
</style>
<link rel="stylesheet" href="../assets/css/sideBar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="col-md-2" id="aside">
		<div style="margin-top: 50px;">
			<div style="margin:0px auto;">
				<c:forEach var="cate" items="${cate }">
					<h6>${cate.cate }</h6>
				</c:forEach>
				<h6>총 ${total }건 중 ${total }건 출력</h6>
			</div>
			<div class="panel">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<p>자료검색</p>
					</h3>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="../searchBook/alq.do">자료탐색</a></li>
					<li class="list-group-item"><a href="../searchBook/newarrival.do">새로 들어온 책</a></li>
					<li class="list-group-item"><a href="../searchBook/favorLoan.do">대출이 많은 책</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-8">
	<section id="one" class="wrapper" style="padding:3em 0;"><div class="inner">
	 	<h4 style="color:black">자료탐색</h4>
		<hr style="padding:2px;background: skyblue;">
		<div class="flex flex-3">
			<h6>내역이 존재하지 않습니다.</h6>
		</div>
		</div>
		</section>
	</div>
	<div class="col-md-2">
		<div style="margin-top: 50px;"></div>
		<h6>Cookies</h6>
		<c:forEach var="c" items="${cList_1 }">
			<div style="display: flex;">
				<p style="width:170px; text-overflow: ellipsis;white-space: nowrap;overflow: hidden;"><a href="../searchBook/alqDetail.do?isbn=${c.isbn }" style="color:black;text-decoration: none;">${c.booktitle }</a></p>
				<form method="post" action="../searchBook/alqDetail_remove.do">
					<input type=hidden name="page" value="${curpage }">
					<input type=hidden name="mno" value="${mno}">
					<input type=hidden name="cno" value="${cno }">
					<input type=hidden name="isbn" value="${c.isbn }">
					<input type=submit value="X" style="background-color:white !important;color:black !important;padding:0px !important;line-height:0px !important;height:13px !important;padding-left: 5px !important;">
				</form>
			</div>
		</c:forEach>
	</div>
	<div class="text-center" style="clear:both;">
		<ul class="pagination">
			<li></li>
		</ul>
	</div>
</body>
</html>