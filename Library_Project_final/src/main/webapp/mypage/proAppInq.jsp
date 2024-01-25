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
		$('.pgCancelBtn').click(function() {
			let no=$(this).attr('data-no')
			let pno=$(this).attr('data-pno')
			if(confirm('프로그램 신청을 취소하시겠습니까?')===true){
				location.href='../mypage/programCancel.do?no='+no+'&pno='+pno
			}else{
				return false
			}
		})
	})
</script>
</head>

<body>
<!-- 프로그램 신청 조회 -->
	<div>
		<form method="post" action="../mypage/proAppInq.do" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<div>
					강좌기간&nbsp;:&nbsp;<input type="date" name="edu1">&nbsp;~&nbsp;<input type="date" name="edu2">
				</div>
				<div style="margin-top: 10px;">
					<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
						<option value="0">강좌명</option>
						<option value="1">장소</option>
					</select>
					<input type="text" size="15" style="float: left;width: 60%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg">
					<input type="submit" class="btn btn-sm btn-info" value="검색">
				</div>
			</div>
		</div>
		</form>
	</div>
	<div style="width: 100%;padding: 0;">
		프로그램신청 <font color="red">${totalcount }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="35%" class="text-center">강좌명</th>
			<th width="25%" class="text-center">장소</th>
			<th width="20%" class="text-center">강좌기간</th>
			<th width="10%" class="text-center">상태</th>
			<th width="5%" class="text-center">취소</th>
		</tr>
		<c:if test="${list_size eq 0}">
			<tr>
				<td class="text-center" colspan="6">
					내역이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list_size gt 0}">
		<c:set var="count" value="${count }"/>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td width="5%" class="text-center">${count }</td>
				<td width="35%" style="white-space: nowrap;text-overflow: ellipsis;">
					${vo.pvo.title }
				</td>
				<td width="25%" class="text-center">${vo.pvo.place }</td>
				<td width="20%" class="text-center">${vo.pvo.edu1_str } ~<br>${vo.pvo.edu2_str }</td>
				<td width="10%" class="text-center">
					<c:if test="${vo.status==0 }">신청취소</c:if>
					<c:if test="${vo.status==1 }">신청중<br>${vo.waitingNo gt 0?"대기:"+=vo.waitingNo:"" }</c:if>
					<c:if test="${vo.status==2 }">종료</c:if>
				</td>
				<td width="5%" class="text-center">
					<c:if test="${vo.status==1 }">
						<input type="button" value="취소" class="pgCancelBtn" data-no="${vo.no }" data-pno=${vo.pno } style="border-radius: 10px;font-weight: normal;height: 1.5em;line-height: 0;">
					</c:if>
				</td>
			</tr>
			<c:set var="count" value="${count-1 }"/>
		</c:forEach>
		</c:if>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../mypage/likeBookInq.do?page=${startPage-1 }">&lt;</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="../mypage/likeBookInq.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../mypage/likeBookInq.do?page=${endPage+1 }">&gt;</a></li>			
			</c:if>
		</ul> 
	</div>
</body>
</html>