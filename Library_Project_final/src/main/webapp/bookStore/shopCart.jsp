<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/storestyle.css">
<style type="text/css">
.row {
		margin: 0px auto;
	}
	
.shopping-cart {
	padding: 30px;
}
	
form {
	display: inline;
}
</style>
</head>
<body>
	<div class="container">
	<div class="row">
		<div class="shopping-cart">
			<h3 style="margin-bottom: 10px;">장바구니</h3>
			<table class="table">
				<tr class="success">
					<th width=20%></th>
					<th width=40% class="text-center">상품명</th>
					<th width=10% class="text-center">가격</th>
					<th width=10% class="text-center">수량</th>
					<th width=10% class="text-center">Total</th>
					<th width=10%></th>
				</tr>
				
				<!--<c:forEach var="vo" items="${list }">
				
				<tr>
					<td width=20% class="text-center">
						<img src="${vo.image }" style="width: 80px; height: 80px;">
					</td>
					<td width=40% style="vertical-align: middle;">${vo.booktitle }</td>
					<td width=10% class="text-center" style="vertical-align: middle;">${vo.price }</td>
					<td width=10% class="text-center" style="vertical-align: middle;">${vo.b_count }</td>
					<td width=10% class="text-center" style="vertical-align: middle;">${vo.sumprice }</td>
					<td width=10% class="text-center" style="vertical-align: middle;">
						<form method="post" action="../cart/cartOrder.do">
							<input type=submit class="btn btn-sm btn-default" value="구매" id="buyBtn">
							<input type=hidden name="stno" value="${vo.stno }">
							<input type=hidden name="sumprice" value="${vo.sumprice }" id="totalprice">
							<input type=hidden name="b_count" value="${vo.b_count }" id="b_count">
						</form>
					</td>
				</tr>
				
				</c:forEach>-->
			</table>
			
		</div>
		
		<div style="height: 80px;"></div>
	</div>
</div>
</body>
</html>