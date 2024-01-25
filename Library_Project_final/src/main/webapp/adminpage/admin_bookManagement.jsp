<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#pgUpdateBtn,#pgDeleteBtn{
		height: 1em;
		line-height: 0;
		padding: 1em 1em;
		font-weight: normal;
	}
	#pgUpdateBtn{
		background-color: #5cb85c !important;
	}
	#pgDeleteBtn{
		background-color: #d58512 !important;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('.pgDeleteBtn').on('click',function(){
			let pno=$(this).attr('data-pno')
			if(confirm('프로그램을 삭제하시겠습니까?')===true){
				location.href='../admin/programDelete.do?pno='+pno
			}else{
				return false
			}
		})
	})
</script>
</head>
<body>
<!-- 좋아요 도서 조회 -->
	<%-- <div>
		<form method="post" action="#" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<div>
					<select id="searchOp" name="searchType" class="input-lg">
						<option value="0" ${searchType eq '0' ? "selected":"" }>프로그램명</option>
					</select>
					<input type="text" size="15" style="float: left;width: 60%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg"
					${search ne null ? "value="+=search:"" }>
					<input type="submit" class="btn btn-sm btn-info" value="검색">
				</div>
			</div>
		</div>
		</form>
	</div> --%>
	<div style="width: 100%;padding: 0;">
		도서대출예약 <font color="red">${totalcount }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="30%" class="text-center">서명</th>
			<th width="20%" class="text-center">등록일</th>
			<th width="20%" class="text-center">기한</th>
			<th width="15%" class="text-center">회원아이디</th>
			<th width="10%" class="text-center">비고</th>
		</tr>
		<c:if test="${list_size eq 0}">
			<tr>
				<td colspan="7" class="text-center">
					내역이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list_size gt 0 }">
		<c:set var="count" value="${count }"/>
		<c:forEach var="vo" items="${list }">
			<tr>
				<th width="5%" class="text-center">${count }</th>
				<th width="30%">${vo.booktitle }</th>
				<th width="20%" class="text-center">${vo.regdate_str }</th>
				<th width="20%" class="text-center">${vo.enddate_str }</th>
				<th width="15%" class="text-center">${vo.userid }</th>
				<th width="10%" class="text-center">
					<c:if test="${vo.status eq 'n' }">
						<form method="post" action="../admin/bookReserveAuthorize.do">
							<input type="hidden" name="no" value="${vo.no }">
							<input type="hidden" name="isbn" value="${vo.isbn }">
							<input type="hidden" name="userid" value="${vo.userid }">
							<input type="submit" value="승인">
						</form>
					</c:if>
					<c:if test="${vo.status eq 'y' }">
						<a href="#"></a>
					</c:if>
				</th>
			</tr>
			<c:set var="count" value="${count-1 }"/>
		</c:forEach>
		</c:if>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../admin/bookManagement.do?page=${startPage-1 }">&lt;</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="../admin/bookManagement.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../admin/bookManagement.do?page=${endPage+1 }">&gt;</a></li>			
			</c:if>
		</ul> 
	</div>
</body>
</html>