<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('.cancleBtn').click(function() {
			if(confirm('좋아요를 취소하시겠습니까?')===true){
				let ino=$(this).attr('data-ino')
				location.href='../mypage/likeBookDelete.do?ino='+ino
			}else{
				return false
			}
		})
	})
</script>
</head>
<body>
<!-- 좋아요 도서 조회 -->
	<div>
		<form method="post" action="../mypage/likeBookInq.do" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<div>
					<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
						<option value="0" ${searchType eq '0' ? "selected":"" }>서명</option>
						<!-- <option value="">소장도서관</option> -->
						<option value="1" ${searchType eq '1' ? "selected":"" }>저자</option>
						<option value="2" ${searchType eq '2' ? "selected":"" }>출판사</option>
					</select>
					<input type="text" size="15" style="float: left;width: 60%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg"
					${search ne null ? "value="+=search:"" }>
					<input type="submit" class="btn btn-sm btn-info" value="검색">
				</div>
			</div>
		</div>
		</form>
	</div>
	<div style="width: 100%;padding: 0;">
		좋아요 <font color="red">${totalcount }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="10%" class="text-center">등록번호</th>
			<th width="50%" class="text-center">서명</th>
			<!-- <th width="15%" class="text-center">소장도서관</th> -->
			<th width="15%" class="text-center">저자</th>
			<th width="15%" class="text-center">출판사</th>
			<th width="5%" class="text-center">취소</th>
		</tr>
		<c:if test="${list_size eq 0}">
			<tr>
				<td colspan="6" class="text-center">
					내역이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list_size gt 0 }">
		<c:set var="count" value="${count }"/>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td width="5%" class="text-center">${count }</td>
				<td width="10%" class="text-center">${vo.bivo.bookaccessionno }</td>
				<td width="50%" style="white-space: nowrap;text-overflow: ellipsis;">${vo.bivo.booktitle }</td>
				<td width="15%" class="text-center">${vo.bivo.bookperson }</td>
				<td width="15%" class="text-center">${vo.bivo.bookpublisher }</td>
				<td width="5%" class="text-center">
					<span class="btn btn-xs btn-warning cancleBtn" data-ino="${vo.ino }">취소</span>
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