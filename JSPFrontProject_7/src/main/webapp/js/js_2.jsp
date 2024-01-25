<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		형변환 : Number() , parseInt()
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 450px;
}
</style>
<script type="text/javascript">
window.onload=function(){
	let btn=document.getElementById("calcBtn") // 태그 정보를 읽어온다.
	btn.addEventListener('click', function(){
		let kor=document.getElementById("kor")
		
		// 유효성 검사 (반드시 입력을 해야한다.)
		if(kor.value.trim()==="kor")
		{
			kor.focus()
			return;
		}
		if(eng.value.trim()==="eng")
		{
			eng.focus()
			return;
		}
		if(math.value.trim()==="math")
		{
			math.focus()
			return;
		}
		
		// 문자열결합이므로 계산 시 형변환 필요
		let total=Number(kor.value)+Number(eng.value)+parseInt(math.value);
		document.querySelector('#total').value=total;
		
		// 평균 계산
		let avg=total/3
		document.querySelector("#avg").value=Math.round(avg, 2); // 반올림
	})
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">성적 계산</h1>
			<table class="table">
				<tr>
					<td class="text-center" width="30%">국어</td>
					<td width="70%">
						<input type="text" id=kor size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<td class="text-center" width="30%">영어</td>
					<td width="70%">
						<input type="text" id=eng size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<td class="text-center" width="30%">수학</td>
					<td width="70%">
						<input type="text" id=math size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<td class="text-center" width="30%">총점</td>
					<td width="70%">
						<input type="text" id=total size=15 class="input-sm" readonly> <%-- readonly : 비활성화 --%>
					</td>
				</tr>
				
				<tr>
					<td class="text-center" width="30%">평균</td>
					<td width="70%">
						<input type="text" id=avg size=15 class="input-sm" readonly>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="text-center">
						<input type=button value="계산" class="btn btn-sm btn-primary" id="calcBtn">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>














