<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="one" class="wrapper" style="padding:3em 0;">
		<div class="inner" style="max-width: 90em;">
			<h4 style="color:black;font-weight: bold;">통합 검색 결과</h4>
			<hr style="padding:2px;background: skyblue;">
			<div class="flex flex-3">
				<div class="col-md-8">
					<jsp:include page="${book_search_jsp }"></jsp:include>
				</div>
				<div class="col-md-4">
					<jsp:include page="${notice_search_jsp }"></jsp:include>
					<jsp:include page="${program_search_jsp }"></jsp:include>
				</div>				
			</div>
		</div>
	</section>
</body>
</html>