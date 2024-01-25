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
	
	.table tr th{
		text-align:center;
	}
</style>
<link rel="stylesheet" href="../assets/css/sideBar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$('#acq_data').change(function(){
			let dt = $(this).val();
			location.href="../searchBook/favorLoan.do?page=1&acq="+dt;
		})
	})
</script>
</head>
<body>
	<div class="col-md-2" id="aside">
		<div style="margin-top: 50px;">
			<div style="margin:0px auto;">
				<h6>${cate }</h6>	
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
	 	<h4 style="color:black">대출이 많은 책</h4>
		<hr style="padding:2px;background: skyblue;">
		<div class="flex flex-3">
			<div class="text-right" style="width:100%;margin-bottom: 5px;">
				<select id="acq_data" style="width:10%;display:unset !important;">
					<option value="30" <c:if test="${acq == '30'}">selected="selected"</c:if>>30일</option>
					<option value="60" <c:if test="${acq == '60'}">selected="selected"</c:if>>60일</option>
					<option value="90" <c:if test="${acq == '90'}">selected="selected"</c:if>>90일</option>
					<option value="120" <c:if test="${acq == '120'}">selected="selected"</c:if>>120일</option>
				</select>
			</div>
			<table class="table">
				<tr>
					<th width=5% id="book_no">No.</th>
					<th width=45% id="book_title">제목</th>
					<th width=15% id="book_author">저자</th>
					<th width=15% id="book_publisher">발행처</th>
					<th width=10% id="book_date">발행년도</th>
					<th width=10% id="book_rDate">대출횟수</th>
				</tr>
				<c:forEach var="list" items="${list }">
					<tr>
						<td  width=5% style="text-align: center;">${list.num }</td>
						<td  width=45%><a href="../searchBook/alqDetail.do?isbn=${list.isbn }" style="color:black"><p style="width:400px; text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" title="${list.booktitle }">${list.booktitle }</p></a></td>
						<td  width=15% style="text-align: center;">${list.bookauthor }</td>
						<td  width=15% style="text-align: center;">${list.bookpublisher }</td>
						<td  width=10% style="text-align: center;">${list.bookdate }</td>
						<td  width=10% style="text-align: center;">${list.cnt }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>
		</section>
	</div>
	<div class="col-md-2">
	</div>
	<div class="text-center" style="clear:both;">
		<ul class="pagination">
			<c:if test="${startpage>1 }">
				<li><a href="../searchBook/favorLoan.do?page=${startpage-1}&acq=${acq}">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startpage }" end="${endpage }">
				<li ${curpage==i?"class=active":"" }><a href="../searchBook/favorLoan.do?page=${i }&acq=${acq}">${i }</a></li>
			</c:forEach>
			<c:if test="${endpage<totalpage }">
					<li><a href="../searchBook/favorLoan.do?page=${endpage+1}&acq=${acq}">&gt;</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>