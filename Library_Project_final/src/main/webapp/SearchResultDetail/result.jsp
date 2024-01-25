<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<section id="one" class="wrapper" style="padding:3em 0;">
		<div class="inner">
			<h4 style="color:black;font-weight: bold;">${type } 검색 결과</h4>
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
	<div class="text-center" style="clear:both;">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../search/searchDetail.do?page=${startPage-1}&search=${search}&type-data=${type1}">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${curpage==i?"class=active":"" }><a href="../search/searchDetail.do?page=${i}&search=${search}&type-data=${type1}">${i }</a></li>
			</c:forEach>
			<c:if test="${endPage<totalpage }">
					<li><a href="../search/searchDetail.do?page=${endPage+1}&search=${search}&type-data=${type1}">&gt;</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>