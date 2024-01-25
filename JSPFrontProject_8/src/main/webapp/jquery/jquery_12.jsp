<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 450px;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.table > thead > tr').css('backgroundColor', '#CCCCFF')
	$('.table > tbody > tr:nth-child(2n)').css('backgroundColor', "rgb(255, 255, 200)")
	/*
		이벤트 처리
		$(선택자).click(function(){}) => onclick
		$(선택자).change(function(){}) => onchange
		$(선택자).keyup(function(){}) => onkeyup
		$(선택자).keydown(function(){}) => onkeydown
		$(선택자).mouseover(function(){}) 
		$(선택자).hover(function(){}) 
		
		=> 태그의 값을 읽기/설정
		   val()	==> val("")
		   text()	==> text("")
		   html()	==> html("")
		 			==> append("")
		   attr("속성")	==> attr("속성", "")
		   trim()	==> trim(병행)
	*/
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th class="text-center">번호</th>
						<th class="text-center">이름</th>
						<th class="text-center">성별</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center">1</td>
						<td class="text-center">신아라</td>
						<td class="text-center">여자</td>
					</tr>
					<tr>
						<td class="text-center">2</td>
						<td class="text-center">전영중</td>
						<td class="text-center">남자</td>
					</tr>
					<tr>
						<td class="text-center">3</td>
						<td class="text-center">성준수</td>
						<td class="text-center">남자</td>
					</tr>
					<tr>
						<td class="text-center">4</td>
						<td class="text-center">주찬양</td>
						<td class="text-center">남자</td>
					</tr>
					<tr>
						<td class="text-center">5</td>
						<td class="text-center">최종수</td>
						<td class="text-center">남자</td>
					</tr>
					<tr>
						<td class="text-center">6</td>
						<td class="text-center">기상호</td>
						<td class="text-center">남자</td>
					</tr>
					<tr>
						<td class="text-center">7</td>
						<td class="text-center">박병찬</td>
						<td class="text-center">남자</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>