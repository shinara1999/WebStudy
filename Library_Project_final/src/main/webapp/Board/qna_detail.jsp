<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table tbody tr:nth-child(2n+1){
		background-color: white;
	}
	.table > tbody > tr > td,.table > tbody > tr > th{
		border-top: none;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('#qnaCommentBtn').click(function() {
			$('#qnaCommentBtn').hide()
			$('#qnaComment').show()
		})
		$('#commentCancleBtn').click(function() {
			$('#qnaCommentBtn').show()
			$('#qnaComment').hide()
		})
		
		$('.rDelete').click(function() {
			let rno=$(this).attr('data-rno')
			if(confirm('댓글을 삭제하시겠습니까?')===true){
				$('#r_del_'+rno).submit()
			}else{
				return false
			}
		})
		
		let bCheck=false
		$('.rUpdate').click(function() {
			let rno=$(this).attr('data-rno')
			let cont=$(this).attr('data-cont')
			
			if(!bCheck){
				bCheck=true
				$(this).text('취소')
				$('#r_upd_'+rno).show()
				$('#r_upd_'+rno+' textarea').text(cont)
			}else{
				bCheck=false
				$(this).text('수정')
				$('#r_upd_'+rno).hide()
			}
		})
	})
	function deleteBtn(no) {
		if(confirm('글을 삭제하시겠습니까?')){
			location.href='../Board/qna_delete.do?no='+no
		}else{
			return false
		}			
	}
	function deleteCommentBtn(qcvono,no) {
		if(confirm('글을 삭제하시겠습니까?')){
			location.href='../Board/qna_comment_delete.do?commentno='+qcvono+'&no='+no
		}else{
			return false
		}			
	}
</script>
</head>
<body>
	<div>
		<h2>묻고답하기</h2>
		<hr>
	</div>
	<table class="table" style="border-collapse: unset;">
		<tr style="background-color: rgba(144, 144, 144, 0.075);">
			<th width="80%" rowspan="2" style="font-size: 18px;">${vo.title }</th>
			<td width="20%" class="text-right">${vo.dbday }</td>
		</tr>
		<tr style="border-bottom: 1px solid #dbdbdb;background-color: rgba(144, 144, 144, 0.075);">
			<td class="text-right">${fn:substring(vo.name,0,1)+='**' }</td>
		</tr>
		<tr>
			<td colspan="2">
				<%-- <textarea rows="10" cols="" readonly style="background-color: white;resize: none;border: none;outline: none;">${vo.content }</textarea> --%>
				<pre style="background-color: white;border: none;white-space: pre-wrap;">${vo.content }</pre>
			</td>
		</tr>
		<c:if test="${sessionScope.admin eq 'n' || sessionScope.admin eq null }">
			<tr>
				<td colspan="2" class="text-right">
					<c:if test="${vo.userid eq sessionScope.email }">
						<a href="../Board/qna_update.do?no=${vo.no }" class="btn btn-xs btn-info">수정</a>
						<span onclick="deleteBtn(${vo.no})" class="btn btn-xs btn-warning">삭제</span>
					</c:if>
					<input type="button" value="목록" class="btn btn-xs btn-success" onclick="javascript:history.back()">
				</td>
			</tr>
		</c:if>
	</table>
	<div style="height: 20px;"></div>
	<%-- <c:if test="${vo.status eq 'n' }">
		<c:if test="${sessionScope.admin eq 'y' }">
		<div class="text-center">
			<input type="button" class="btn btn-lg" value="답글작성" id="qnaCommentBtn">
		</div>
		<div id="qnaComment" style="display: none;">
			<form method="post" action="../Board/qna_comment_ok.do">
			<input type="hidden" name="sqno" value="${vo.no }">
			<table class="table">
				<tr>
					<th width="10%">제목</th>
					<td width="90%">
						<input type="text" name="title" class="input-sm" required>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="10" cols="" name="content" required></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="저장" class="btn btn-sm btn-info" data-sqno="${vo.no }" id="commentBtn">
						<input type="button" value="취소" class="btn btn-sm btn-info" id="commentCancleBtn">
					</td>
				</tr>
			</table>
			</form>
		</div>
		</c:if>
	</c:if> --%>
	<c:if test="${vo.status eq 'y' }">
		<table class="table" style="border-collapse: unset;">
			<tr style="background-color: rgba(144, 144, 144, 0.075);">
				<th width="80%" rowspan="2" style="font-size: 18px;">${qcvo.title }</th>
				<td width="20%" class="text-right">${qcvo.dbday }</td>
			</tr>
			<tr style="border-bottom: 1px solid #dbdbdb;background-color: rgba(144, 144, 144, 0.075);">
				<td class="text-right">관리자</td>
			</tr>
			<tr>
				<td colspan="2">
					<%-- <textarea rows="10" cols="" readonly style="background-color: white;resize: none;border: none;outline: none;">${vo.content }</textarea> --%>
					<pre style="background-color: white;border: none;white-space: pre-wrap;">${qcvo.content }</pre>
				</td>
			</tr>
			<%-- <c:if test="${sessionScope.admin eq 'y' }">
				<tr>
					<td colspan="2" class="text-right">
						<a href="#" class="btn btn-xs btn-info">수정</a>
						<span onclick="deleteCommentBtn(${qcvo.no },${vo.no })" class="btn btn-xs btn-warning">삭제</span>
						<input type="button" value="목록" class="btn btn-xs btn-success" onclick="javascript:history.back()">
					</td>
				</tr>
			</c:if> --%>
		</table>
	</c:if>
	<!-- 댓글 영역 -->
	<table class="table">
		<tr>
			<td>
				<table class="table" style="width: 960px;">
					<c:forEach var="rvo" items="${rList }">
						<tr>
							<td class="text-left" width="20%">${rvo.userid }(${rvo.dbday })</td>
							<td class="text-left" width="65%">${rvo.r_content }</td>
							<td class="text-right" width="15%">
								<c:if test="${rvo.userid eq sessionScope.email }">
									<span class="btn btn-xs btn-success inline rUpdate" data-rno="${rvo.rno }" data-cont="${rvo.r_content }" style="float: left;">수정</span>
									<form method="post" action="../reply/delete.do" id="r_del_${rvo.rno }" style="float: left;">
										<input type="hidden" name="rno" value="${rvo.rno }">
										<input type="hidden" name="cno" value="${rvo.cno }">
										<input type="hidden" name="typeno" value="1">
										<span class="btn btn-xs btn-danger rDelete" data-rno="${rvo.rno }">삭제</span>
									</form>
								</c:if>
							</td>
						</tr>
						<tr id="r_upd_${rvo.rno }" style="display: none;">
							<td colspan="3">
								<form method="post" action="../reply/update.do">
									<input type="hidden" name="typeno" value="1">
									<input type="hidden" name="cno" value="${vo.no }">
									<input type="hidden" name="rno" value="${rvo.rno }">
									<textarea rows="2" cols="70" name="r_content" style="float: left;width: 855px;resize: none;background-color: white;"></textarea>
									<input type="submit" value="댓글수정" style="float: left;width: 80px;height: 80px;text-align: center;margin-left: 5px;padding: 0;" class="btn-primary">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<c:if test="${sessionScope.email ne null }">
			<tr>
				<td>
					<form method="post" action="../reply/insert.do">
						<input type="hidden" name="typeno" value="1">
						<input type="hidden" name="cno" value="${vo.no }">
						<textarea rows="2" cols="70" name="r_content" style="float: left;width: 875px;resize: none;background-color: white;"></textarea>
						<input type="submit" value="댓글쓰기" style="float: left;width: 80px;height: 80px;text-align: center;margin-left: 5px;padding: 0;" class="btn-primary">
					</form>
				</td>
			</tr>
		</c:if>
	</table>
	<!-- ------ -->
</body>
</html>