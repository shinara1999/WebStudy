<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="one" class="wrapper" style="padding:3em 0;">
		<div class="inner">
			<h4 style="color:black;font-weight: bold;">프로그램 검색 결과</h4>
			<hr style="padding:2px;background: skyblue;">
			<div class="flex flex-3">
				<table class="table">
					<c:if test="${pList_size gt 0 }">
					<c:forEach var="vo" items="${pList }">
						<tr>
							<td>
								<a href="../program/programDetail_before.do?pno=${vo.pno }" style="text-decoration: none;">
									${vo.title }
								</a>
							</td>
						</tr>
						<tr>
							<td>
								운영일시 : ${vo.edu1_str } ~ ${vo.edu2_str }
								<font color="orange">
									(<c:if test="${vo.status==0 }">접수예정</c:if>
		          					<c:if test="${vo.status==1 }">접수중</c:if>
		          					<c:if test="${vo.status==2 }">대기접수</c:if>
		          					<c:if test="${vo.status==3 }">접수마감</c:if>
		          					<c:if test="${vo.status==4 }">종료</c:if>)
								</font>
							</td>
						</tr>
						<tr>
							<td>정원 : ${vo.applicant }/${vo.capacity }&nbsp;대기 : ${vo.waiting }/${vo.waitingCap }</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${pList_size eq 0 }">
						<tr>
							<td>검색내역이 존재하지 않습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</section>
</body>
</html>