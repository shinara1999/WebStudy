<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.table>tbody>tr>td{
		background:white !important;
		border-top:1px solid white !important;
	}
	ul.pagination>li {
	    padding-left: 0;
	}
	
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
					<h6>검색어<font style="color: #ff5a46;">[카테고리분류:${cate.cate }]</font></h6>
				</c:forEach>
				<h6>총 <format:formatNumber value="${total }" type="number"/>건 중 <format:formatNumber value="${total }" type="number"/>건 출력</h6>
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
			<c:forEach var="vo" items="${list }" >
				<table class="table">
					<tr>
						<td width="15%" align="center" rowspan="8">
							<c:set var="data" value="../images/defaultImage.jpg"/>
					        <a href="../searchBook/alqDetail_before.do?isbn=${vo.isbn }"><img src="${vo.image==null?data:vo.image }" style="width:100%;height:100%;"></a>
						</td>
						<td width="85%" align="left">
							<span id="title" style="font-weight: bold;">
								<a href="../searchBook/alqDetail_before.do?isbn=${vo.isbn }" style="color:black;text-decoration: none;">${vo.booktitle }</a>
							</span>
						</td>
					</tr>
					<tr>
						<td width="85%">
							<span id="author" style="font-size:11px;">
								${vo.bookauthor }
							</span>
						</td>
					</tr>
					<tr>
						<td width="85%">
							<span id="publisher" style="font-size:11px;">
								${vo.bookpublisher }
							</span>
						</td>
					</tr>
					<tr>
						<td width="85%">
							<span id="bookcallnum" style="font-size:11px;">
								${vo.bookcallnum }
							</span>
						</td>
					</tr>
					<tr>
						<td width="85%">
							<span id="Bookdate" style="font-size:11px;">
								${vo.bookdate }
							</span>
						</td>
					</tr>
					<tr>
						<td width="85%">
							<table>
								<thead>	
									<tr>
										<th style="text-align:center !important;">자료실/서가</th>
										<th style="text-align:center !important;">청구기호</th>
										<th style="text-align:center !important;">등록번호</th>
										<th style="text-align:center !important;">도서상태</th>
									</tr>
								</thead>
								<tbody>
									<tr style="background:white !important;">
										<td style="text-align:center !important;">${vo.booklocation }</td>
										<td style="text-align:center !important;"><span id="bookcallnum">${vo.bookcallnum }</span></td>
										<td style="text-align:center !important;">${vo.bookaccessionno }</td>
										<td style="text-align:center !important;">
											<c:if test="${vo.brvo.status==null }"><font style="color:skyblue;">대출가능</font></c:if>
											<c:if test="${vo.brvo.status=='y' }"><font style="color:red;">대출중</font></c:if>
											<c:if test="${vo.brvo.status=='n' }"><font style="color:#74A16F;">예약진행중</font></c:if>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		</div>
		</section>
	</div>
	<div class="col-md-2">
		<div style="margin-top: 50px;"></div>
		<h3>도서 검색 목록</h3>
		<hr>
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
			<c:if test="${startPage>1 }">
				<li><a href="../searchBook/alqResult.do?page=${startPage-1}&cno=${cno}&mno=${mno}">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${curpage==i?"class=active":"" }><a href="../searchBook/alqResult.do?page=${i}&cno=${cno}&mno=${mno}">${i }</a></li>
			</c:forEach>
			<c:if test="${endPage<totalpage }">
					<li><a href="../searchBook/alqResult.do?page=${endPage+1}&cno=${cno}&mno=${mno}">&gt;</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>