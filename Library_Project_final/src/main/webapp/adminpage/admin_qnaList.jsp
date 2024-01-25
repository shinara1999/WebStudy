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
		let bCheck=false
		$('.show_qna').click(function() {
			let no=$(this).attr('data-no')
			let status=$(this).attr('data-status')
			
			if(bCheck){
				bCheck=false
				$(this).text('>')
				$('.qna_content_'+no).hide()
				$('.qna_title_'+no).hide()
				$('.qna_comment_'+no).hide()
			}else{
				bCheck=true
				$(this).text('<')
				$('.qna_content_'+no).show()
				$('.qna_title_'+no).show()
				$('.qna_comment_'+no).show()
				
				if(status!=='n'){
					$.ajax({
						type: 'POST',
						url: '../Board/qna_select_comment.do',
						data: {"sqno":no},
						success:function(result){
							let res=JSON.parse(result)
							$('#upd_comment_no_'+no).val(res.no)
							$('#title_'+no).text(res.title)
							$('#comment_'+no).text(res.content)
						}
					})
				}
			}
		})
		
		let bCheck2=false
		$('.show_update').click(function() {
			let no=$(this).attr('data-no')
			let title=$('#title_'+no).text()
			let comment=$('#comment_'+no).text()
			
			console.log(title)
			console.log(comment)
			
			if(bCheck2){
				bCheck2=false
				$(this).val('수정')
				$('.updateBtn_'+no).hide()
				$('#title_'+no).html(title)
				$('#comment_'+no).html(comment)
			}else{
				/* bCheck2=true
				$(this).val('취소')
				$('.updateBtn_'+no).show()
				$('#title_'+no).html('<textarea rows="" cols="" class="t_${vo.no }" name="title" style="height: 45px;" value="'+title+'">'+title+'</textarea>')
				$('#comment_'+no).html('<textarea rows="" cols="" class="c_${vo.no }" name="content" value="'+comment+'">'+comment+'</textarea>') */
				
			}
			/* <textarea rows="" cols="" class="t_${vo.no }" name="title" style="height: 45px;"></textarea> */
			/* <textarea rows="" cols="" class="c_${vo.no }" name="content"></textarea> */
		})
	})
</script>
</head>
<body>
	<div style="width: 100%;padding: 0;">
		묻고답하기 <font color="red">${totalcount }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<table class="table">
		<tr>
			<th width="5%" class="text-center">번호</th>
			<th width="40%" class="text-center">제목</th>
			<th width="20%" class="text-center">작성자</th>
			<th width="20%" class="text-center">작성일</th>
			<th width="10%" class="text-center">상태</th>
			<th width="5%" class="text-center"></th>
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
				<td width="5%" class="text-center">${count }</td>
				<td width="40%">${vo.title }</td>
				<td width="20%" class="text-center">${vo.userid }</td>
				<td width="20%" class="text-center">${vo.dbday }</td>
				<td width="10%" class="text-center">
					<c:if test="${vo.status eq 'y' }"><font color="#1DDB16">답변완료</font></c:if>
					<c:if test="${vo.status eq 'n' }"><font color="#A81919">답변대기</font></c:if>
				</td>
				<td width="5%" class="text-center"><span class="show_qna" data-no="${vo.no }" data-status="${vo.status }" style="cursor: pointer;">&gt;</span></td>
			</tr>
			<tr style="display: none;" class="qna_content_${vo.no }">
				<th class="text-center">내용</th>
				<td colspan="3">${vo.content }</td>
				<td colspan="2"></td>
			</tr>
			<c:if test="${vo.status eq 'n' }">
			<form method="post" action="../Board/qna_comment_ok.do">
			<input type="hidden" name="sqno" value="${vo.no }">
			<tr style="display: none;" class="qna_title_${vo.no }">
				<th class="text-center">제목</th>
				<td colspan="3">
					<textarea rows="" cols="" class="t_${vo.no }" name="title" style="height: 45px;"></textarea>
				</td>
				<td colspan="2"></td>
			</tr>
			<tr style="display: none;" class="qna_comment_${vo.no }">
				<td></td>
				<td colspan="3">
					<textarea rows="" cols="" class="c_${vo.no }" name="content"></textarea>
				</td>
				<td class="text-center">
					<input type="submit" value="답변등록">
				</td>
			</tr>
			</form>
			</c:if>
			<form method="post" action="../Board/qna_comment_update.do">
			<input type="hidden" name="no" id="upd_comment_no_${vo.no }">
			<c:if test="${vo.status eq 'y' }">
			<tr style="display: none;" class="qna_title_${vo.no }">
				<th class="text-center">답변</th>
				<td colspan="3" id="title_${vo.no }">
					<!-- 답변 제목 -->
				</td>
				<td colspan="2"></td>
			</tr>
			<tr style="display: none;" class="qna_comment_${vo.no }">
				<td></td>
				<td colspan="3" id="comment_${vo.no }">
					<!-- 답변 내용 -->
				</td>
				<td class="text-center">
					<%-- <input type="button" value="수정" data-no="${vo.no }" class="show_update" style="width: 98px;"> --%>
					<%-- <input type="submit" value="답변수정" style="display: none;" class="updateBtn_${vo.no }"> --%>
				</td>
			</tr>
			</c:if>
			</form>
			<c:set var="count" value="${count-1 }"/>
		</c:forEach>
		</c:if>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../admin/qnaList.do?page=${startPage-1 }">&lt;</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="../admin/qnaList.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../admin/qnaList.do?page=${endPage+1 }">&gt;</a></li>			
			</c:if>
		</ul> 
	</div>
</body>
</html>