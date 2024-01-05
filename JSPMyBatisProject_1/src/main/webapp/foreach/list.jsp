<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<String> list=EmpDAO.empNameData();
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 900px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>사원이름</h1>
			<div class="row">
			 <form method=post action="find.jsp">
				<table class="table">
					<tr>
						<td>
							<c:forEach var="name" items="${list }">
								<input type="checkbox" name="names" value="${name }">${name }
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="text-center">
							<button class="btn-sm btn-danger">전송</button>
						</td>
					</tr>
				</table>
			  </form>
			</div>
	</div>
</body>
</html>