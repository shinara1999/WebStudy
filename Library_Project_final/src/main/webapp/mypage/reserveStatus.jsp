<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('.returnBtn').click(function() {
			let isbn=$(this).attr('data-isbn')
			if(confirm('예약을 취소하시겠습니까?')===true){
				location.href='../#.do?isbn='+isbn
			}else{
				return false
			}
		})
	})
</script>
</head>
<body>
<!-- 예약현황 -->
<!-- 도서대출정보 메뉴 include -->
	<!-- <div>
		<form method="post" action="#" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<div>
					<select name="searchDate" class="input-lg" style="line-height: 0;margin-right: 10px;appearance: auto;">
						<option value="0">예약일</option>
						<option value="1">예약만기일</option>
					</select><input type="date">&nbsp;~&nbsp;<input type="date">
				</div>
				<div style="margin-top: 10px;">
					<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
						<option value="0">서명</option>
					</select>
					<input type="text" size="15" style="float: left;width: 60%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg">
					<input type="submit" class="btn btn-sm btn-info" value="검색">
				</div>
			</div>
		</div>
		</form>
	</div> -->
	<div style="width: 100%;padding: 0;">
		예약현황 <font color="red">${totalcount }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="40%" class="text-center">서명</th>
			<th width="15%" class="text-center">예약일</th>
			<th width="15%" class="text-center">예약만기일</th>
			<th width="15%" class="text-center">상태</th>
			<th width="10%" class="text-center"></th>
		</tr>
		<c:if test="${list_size eq 0 }">
			<tr>
				<td class="text-center" colspan="6">
					내역이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list_size gt 0 }">
			<c:set var="count" value="${count }"/>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td width="5%" class="text-center">${count }</td>
					<td width="40%">${vo.booktitle }</td>
					<td width="15%" class="text-center">${vo.regdate_str }</td>
					<td width="15%" class="text-center">${vo.enddate_str }</td>
					<td width="15%" class="text-center">승인대기</td>
					<td width="10%" class="text-center">
						<span class="btn btn-sm btn-warning returnBtn" data-isbn="${vo.isbn }">취소</span>
					</td>
				</tr>
				<c:set var="count" value="${count-1 }"/>
			</c:forEach>
		</c:if>
	</table>
	<div style="height: 20px;"></div>
	<div>
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${startBlockNum>1 }">
					<li><a href="../mypage/reserveStatus.do?page=${curpage gt 1 ? startBlockNum-1 : curpage }&searchDate=${searchDate }&searchType=${searchType }&search=${search }&date1=${date1 }&date2=${date2 }">&lt;</a></li>
				</c:if>
				<c:forEach var="i" begin="${startBlockNum }" end="${endBlockNum }">
					<li ${curpage eq i ? "class=active" : "" }><a href="../mypage/reserveStatus.do?page=${i }&searchDate=${searchDate }&searchType=${searchType }&search=${search }&date1=${date1 }&date2=${date2 }">${i }</a></li>
				</c:forEach>
				<c:if test="${endBlockNum<totalpage }">
					<li><a href="../mypage/reserveStatus.do?page=${curpage lt totalpage ? endBlockNum+1 : curpage }&searchDate=${searchDate }&searchType=${searchType }&search=${search }&date1=${date1 }&date2=${date2 }">&gt;</a></li>
				</c:if>
			</ul> 
		</div>
	</div>
</body>
</html>