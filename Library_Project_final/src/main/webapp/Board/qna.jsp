<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%-- 신청 및 소식 => 공지사항 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#notice .curTr:hover {
		background-color: #EBF7FF;
	}
</style>
<script type="text/javascript">
	function plsLogin() {
		alert('로그인이 필요한 서비스입니다')
		location.href='../user/login.do'
	}
	function denyAccess() {
		alert('타인의 비공개글은 열람할 수 없습니다')
		void(0)
	}
</script>
</head>
<body>
	<!-- <div class="board"> -->
	<div>
		<div>
			<h2>묻고답하기</h2>
			<hr>
		</div>
		<form method="post" action="../Board/qna.do" id="searchForm">
		<div>
			<div style="padding: 15px;">
				<!-- <div>
					작성일&nbsp;:&nbsp;<input type="date">&nbsp;~&nbsp;<input type="date">
				</div> -->
				<div>
					<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
						<option value="0">제목</option>
						<option value="1">내용</option>
					</select>
					<input type="text" size="15" style="float: left;width: 60%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg">
					<input type="submit" class="btn btn-sm btn-info" value="검색">
				</div>
			</div>
		</div>
		</form>
	</div>
	<div style="width: 100%;padding: 0;">
		총 <font color="red">${totalcnt }</font>건,${curpage }/${totalpage }페이지
		<hr style="margin-top: 10px;">
	</div>
	<table class="table" id="notice">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="65%" class="text-center">제목</th>
			<th width="10%" class="text-center">작성자</th>
			<th width="15%" class="text-center">작성일</th>
			<th width="5%" class="text-center">답변</th>
		</tr>
		<c:set var="count" value="${count }"/>
		<c:forEach var="vo" items="${list }">
			<tr class="curTr">
				<td width="5%" class="text-center">${count }</td>
				<td width="65%">
					<c:if test="${vo.locking eq 'y' }">
						<c:if test="${sessionScope.admin eq 'n' || sessionScope.admin eq null }">
							<a ${vo.userid ne sessionScope.email ? 'onclick="denyAccess()"' : 'href="../Board/qna_detail.do?no=' += vo.no += '&status='+=vo.status+='"' }  style="text-decoration: none;color: #333;cursor: pointer;">${vo.title }</a>						
						</c:if>
						<c:if test="${sessionScope.admin eq 'y' }">
							<a href="../Board/qna_detail.do?no=${vo.no }&status=${vo.status }"  style="text-decoration: none;color: #333;cursor: pointer;">${vo.title }</a>						
						</c:if>
						&nbsp;
						<img src="../images/ico_lock.png" width="12px" height="16px">
					</c:if>
					<c:if test="${vo.locking eq 'n' }">
						<a href="../Board/qna_detail.do?no=${vo.no }"  style="text-decoration: none;color: #333;cursor: pointer;">${vo.title }</a>
					</c:if>
				</td>
				<td width="10%" class="text-center">${fn:substring(vo.name,0,1)+='**' }</td>
				<td width="15%" class="text-center">${vo.dbday }</td>
				<td width="5%" class="text-center">
					${vo.status eq 'n' ? "대기" : "완료" }
				</td>
			</tr>
			<c:set var="count" value="${count-1 }"/>
		</c:forEach>
		<tr>
			<td colspan="5" class="text-center" style="position: relative;">
				<ul class="pagination" style="margin: 0;">
					<c:if test="${startPage>1 }">
						<li><a href="../Board/qna.do?page=${startPage-1 }">&lt;</a></li>					
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<li ${i==curpage?"class=active":"" }><a href="../Board/qna.do?page=${i }">${i }</a></li>					
					</c:forEach>
					<c:if test="${endPage<totalpage }">
						<li><a href="../Board/qna.do?page=${endPage+1 }">&gt;</a></li>					
					</c:if>
				</ul>
				<a ${sessionScope.email==null?"onclick=plsLogin()":"href=../Board/qna_insert.do" } class="btn btn-sm btn-primary" style="position: absolute;right: 0;">글쓰기</a>
			</td>
		</tr>
	</table>
	<!-- </div> -->
</body>
</html>