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
	function plsLogin() {
		if(confirm('로그인이 필요한 서비스입니다')===true){
			location.href='../user/login.do'
		}else{
			return false
		}
	}
	function pgApplication(){
		let pno=document.querySelector('#pgApplBtn').getAttribute('data-pno')
		if(confirm('프로그램을 신청하시겠습니까?')===true){
			location.href='../program/programApplication.do?pno='+pno
		}else{
			return false;
		}
	}
	function pgCancel(){
		let pno=document.querySelector('#pgCancelBtn').getAttribute('data-pno')
		if(confirm('신청을 취소하시겠습니까?')===true){
			location.href='../program/programCancel.do?pno='+pno
		}else{
			return false;
		}
	}
</script>
</head>
<body>
	<div class="col-md-9">
		<table class="table">
			<tr>
				<th width="100%" colspan="4" style="font-size: 20px;">${vo.title }</th>
			</tr>
			<tr>
				<th width="10%">운영일시</th>
				<td width="90%" colspan="3" class="text-left">
					[
					<c:if test="${vo.status==0 }">접수예정</c:if>
   					<c:if test="${vo.status==1 }">접수중</c:if>
   					<c:if test="${vo.status==2 }">대기접수</c:if>
   					<c:if test="${vo.status==3 }">접수마감</c:if>
   					<c:if test="${vo.status==4 }">종료</c:if>
   					]&nbsp;
					${vo.edu1_str }&nbsp;~&nbsp;${vo.edu2_str }
				</td>
			</tr>
			<tr>
				<th width="10%">대상</th>
				<td width="40%" class="text-left">
					${vo.target1 }
					<c:if test="${vo.target2 ne null }">
						| ${vo.target2 }
					</c:if>
				</td>
				<th width="10%">장소</th>
				<td width="40%" class="text-left">${vo.place }</td>
			</tr>
			<tr>
				<th width="10%">접수기간</th>
				<td width="90%" colspan="3" class="text-left">${vo.accept1_str }&nbsp;~&nbsp;${vo.accept2_str }</td>
			</tr>
			<tr>
				<th width="10%">신청현황</th>
				<td colspan="3" width="90%" class="text-left">
					정원 : ${vo.applicant }/${vo.capacity }&nbsp;대기 : ${vo.waiting }/${vo.waitingCap }&nbsp;
					<c:if test="${vo.status==1 || vo.status==2 }">
						<c:if test="${isAppl==0 }">
							<input type="button" value="신청하기" class="btn btn-sm btn-success" id="pgApplBtn"
							onclick="${sessionScope.email eq null ? 'plsLogin()' : 'pgApplication()'}" data-pno="${vo.pno }">						
						</c:if>
						<c:if test="${isAppl>0 }">
							<input type="button" value="신청취소" class="btn btn-sm btn-success" id="pgCancelBtn"
							onclick="${sessionScope.email eq null ? 'plsLogin()' : 'pgCancel()'}" data-pno="${vo.pno }">
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="4">
					<c:choose>
						<c:when test="${fn:startsWith(vo.poster,'https') }">
							<img src="${vo.poster }" style="max-width: 855px;max-height: 1200px;">
						</c:when>
						<c:otherwise>
							<img src="https://www.junggulib.or.kr/attachfile/editor/${vo.poster }" style="max-width: 855px;max-height: 1200px;">	
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<c:if test="${vo.content ne null }">
				<tr>
					<td width="100%" colspan="4">
						<textarea rows="10" cols="" readonly>${vo.content }</textarea>
					</td>
				</tr>			
			</c:if>
			<tr>
				<td width="100%" colspan="4" class="text-center">
					<input type="button" value="목록" class="btn btn-lg" onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>