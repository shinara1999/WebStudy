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
	<div>
		<form method="post" action="../admin/programList.do" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<div>
					<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
						<option value="0" ${searchType eq '0' ? "selected":"" }>프로그램명</option>
						<option value="1" ${searchType eq '1' ? "selected":"" }>장소</option>
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
		프로그램 <font color="red">${totalcount }</font>건
		<a href="../admin/programInsert.do" class="btn btn-sm btn-primary" style="margin-left: 20px;">프로그램 등록</a>
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="25%" class="text-center">프로그램명</th>
			<th width="10%" class="text-center">등록일</th>
			<th width="10%" class="text-center">운영기간</th>
			<th width="15%" class="text-center">접수기간</th>
			<th width="10%" class="text-center">신청현황</th>
			<th width="10%" class="text-center">상태</th>
			<th width="15%" class="text-center">비고</th>
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
				<th width="25%">
					<a href="../program/programDetail.do?pno=${vo.pno }" style="text-decoration: none;color: #333;">${vo.title }</a>
				</th>
				<th width="10%" class="text-center">${vo.regdate_str }</th>
				<th width="10%" class="text-center">${vo.edu1_str }&nbsp;~<br>${vo.edu2_str }</th>
				<th width="15%" class="text-center">${vo.accept1_str }&nbsp;~<br>${vo.accept2_str }</th>
				<th width="10%">정원 : ${vo.applicant }/${vo.capacity }<br>대기 : ${vo.waiting }/${vo.waitingCap }</th>
				<th width="10%" class="text-center">
					<c:if test="${vo.status==0 }">접수예정</c:if>
   					<c:if test="${vo.status==1 }">접수중</c:if>
   					<c:if test="${vo.status==2 }">대기접수</c:if>
   					<c:if test="${vo.status==3 }">접수마감</c:if>
   					<c:if test="${vo.status==4 }">종료</c:if>
				</th>
				<th width="15%" class="text-center">
					<a href="../admin/programUpdate.do?pno=${vo.pno }" class="btn btn-sm btn-success">수정</a>
					<input type="button" value="삭제" class="btn btn-sm btn-warning pgDeleteBtn" data-pno="${vo.pno }">
				</th>
			</tr>
			<c:set var="count" value="${count-1 }"/>
		</c:forEach>
		</c:if>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../admin/programList.do?page=${startPage-1 }">&lt;</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="../admin/programList.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../admin/programList.do?page=${endPage+1 }">&gt;</a></li>			
			</c:if>
		</ul> 
	</div>
</body>
</html>