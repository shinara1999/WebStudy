<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.row{
		margin: 0 auto;
		width: 100%;
	}
	.rdays:hover {
		cursor: pointer;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		let dd=''
		$('.rdays').click(function() {
			let year=$(this).attr('data-year')
			let month=$(this).attr('data-month')
			let day=$(this).text()
			let rday=year+'-'+month+'-'+day
			$(this).toggleClass('success')
			dd+=','+rday
			console.log(dd)
			
			$('#dd').val(dd)
			/* $.ajax({
				type: 'POST',
				url: '../reserve/food_time.do',
				data: {"day":day},
				success:function(result){
					$('#times').html(result)
				}
			}) */
		})
	})
</script>
</head>
<body>
	<input type="hidden" name="dd" id="dd">
	<table class="table">
		<caption class="text-center">
			<h3>${year }년도 ${month }월</h3>
		</caption>
		<tr class="danger">
			<c:forEach var="strWeek" items="${strWeek }">
				<td class="text-center">${strWeek }</td>
			</c:forEach>
			<c:set var="week" value="${week }"/>
			<c:forEach var="i" begin="1" end="${lastday }">
				<c:if test="${i==1 }">
					<tr>
						<c:forEach begin="1" end="${week }">
							<td class="text-center">&nbsp;</td>
						</c:forEach>
				</c:if>
				<c:if test="${rday[i]==1 }">
					<td class="text-center rdays" data-year="${year }" data-month="${month }">${i }</td>
				</c:if>
				<c:if test="${rday[i]==0 }">
					<td class="text-center" style="color: #C6C6C6">${i }</td>
				</c:if>
				<%-- <td class="text-center">${i }</td> --%>
				<c:set var="week" value="${week+1 }"/>
				<c:if test="${week>6 }">
					<c:set var="week" value="0"/>
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>