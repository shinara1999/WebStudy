<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input[type="button"]{
		height: 1em;
		line-height: 0;
		padding: 1em 1em;
		font-weight: normal;
	}
	input[type="checkbox"]{
		display: inline;
		float: none;
	}
	input[type="checkbox"] + label{
		padding-left: 2em;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="col-md-10">
	<h2>${typeno==0?"공지사항 수정":"보도자료 수정" }</h2>
	<hr>
	<div style="height: 20px;"></div>
	<form method="post" action="../admin/noticeUpdate_ok.do" enctype="multipart/form-data">
	<input type="hidden" name="no" value="${vo.no }">
	<input type="hidden" name="typeno" value="${vo.typeno }">
	<table class="table">
		<tr>
			<th width="10%">상시여부</th>
			<td width="90%">
				<input type="radio" name="status" id="public" value="y" ${vo.status eq 'y' ? "checked":"" }>
				<label for="public">상시</label>
				<input type="radio" name="status" id="private" value="n" ${vo.status eq 'n' ? "checked":"" }>			
				<label for="private">비상시</label>
			</td>
		</tr>
		<tr>
			<th width="10%">제목</th>
			<td width="90%">
				<input type="text" name="title" size="75" required class="input-sm" value="${vo.title }">
			</td>
		</tr>
		<tr>
			<th width="10%">파일</th>
			<td width="90%">
				<input type="file" name="file">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea rows="10" cols="" name="content">${vo.content }</textarea>
			</td>
		</tr>
	</table>
	<div style="height: 20px;"></div>
	<div class="text-center">
		<input type="submit" value="수정" class="btn btn-lg btn-success">
		<input type="button" value="취소" class="btn btn-lg btn-danger">
	</div>
	</div>
	</form>
</body>
</html>