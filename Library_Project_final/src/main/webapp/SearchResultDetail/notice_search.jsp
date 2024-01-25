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
			<h4 style="color:black;font-weight: bold;">공지사항 및 보도자료 검색 결과</h4>
			<hr style="padding:2px;background: skyblue;">
			<div class="flex flex-3">
				<table class="table">
					<c:if test="${nList_size gt 0 }">
					<c:forEach var="vo" items="${nList }">
						<tr>
							<td>
								<c:if test="${vo.typeno eq 0 }">[공지사항]</c:if>
								<c:if test="${vo.typeno eq 1 }">[보도자료]</c:if>
								<a href="../Board/notice_detail.do?no=${vo.no }&typeno=${vo.typeno }" style="text-decoration: none;">
									${vo.title }
								</a>
							</td>
						</tr>
						<tr>
							<td>${vo.dbday }</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${nList_size eq 0 }">
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