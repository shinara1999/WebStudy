<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
	margin: 0px auto;
	/*width: 700px;*/
}
</style>
</head>
<body>
	<div class="wrapper row3">
  		<main class="container clear"> 
  		<h2 class="sectiontitle">글쓰기</h2>
  			<div class="row row1" style="height: 500px">
  			<form method="post" action="../board/insert_ok.do">
  				<table class="table">
  					<tr>
  						<th width=15% class="text-right">이름</th>
  						<td width=85%>
  							<input type=text name=name size=15 class="input-sm" required>
  						</td>
  					</tr>
  					
  					<tr>
  						<th width=15% class="text-right">제목</th>
  						<td width=85%>
  							<input type=text name=subject size=55 class="input-sm" required>
  						</td>
  					</tr>
  					
  					<tr>
  						<th width=15% class="text-right">내용</th>
  						<td width=85%>
  							<textarea rows="10" cols="57" required name=content></textarea>
  						</td>
  					</tr>
  					
  					<tr>
  						<th width=15% class="text-right">비밀번호</th>
  						<td width=85%>
  							<input type=password name=pwd size=10 class="input-sm" required>
  						</td>
  					</tr>
  					
  					<tr>
  						<td clospan="1" class="text-center inline">
  							<input type="submit" value="글쓰기" class="btn-primary btn-sm">
  							<input type="button" value="취소" class="btn-primary btn-sm" onclick="javascript:history.back()">
  						</td>
  					</tr>
  				</table>
  				</form>
  			</div>
  		</main>
  	</div>
</body>
</html>