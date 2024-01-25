<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>묻고답하기</h2>
		<hr>
	</div>
	<form method="post" action="../Board/qna_insert_ok.do">
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
			<th width="10%">게시판설정</th>
			<td width="90%">
				<input type="radio" name="locking" id="public" value="n">
				<label for="public">공개</label>
				<input type="radio" name="locking" id="private" value="y" checked>			
				<label for="private">비공개</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="orange">※ 게시물은 비공개가 기본이며, 공개를 원하는 경우 공개에 체크해주시기 바랍니다.</font>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<input type="submit" value="저장" class="btn btn-sm btn-info">
				<input type="button" value="취소" class="btn btn-sm btn-info" onclick="javascript:history.back()">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>