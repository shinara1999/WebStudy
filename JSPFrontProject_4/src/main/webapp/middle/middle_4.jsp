<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		배열 => let 변수명=[]
		객체 => let 변수명={} ==> ~VO
		객체배열 => let 변수명[{}, {}, {}, {}...] ==> List
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 800px;
}
</style>
<script type="text/javascript">
window.onload=function(){
	const sawons=[
		{sabun:1, name:"신아라", dept:"개발부", loc:"여수", job:"대리"},
		{sabun:2, name:"전영중", dept:"영업부", loc:"서울", job:"과장"},
		{sabun:3, name:"성준수", dept:"영업부", loc:"부산", job:"팀장"},
		{sabun:4, name:"주찬양", dept:"기획부", loc:"서울", job:"팀장"},
		{sabun:5, name:"최종수", dept:"기획부", loc:"서울", job:"사원"}
	]
	let html='';
	/*
	for(let sa of sawons)
	{
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	}*/
	/*
	sawons.map(function(sa){
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	})*/
	/*
	sawons.map((sa)=>{
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	})*/
	/*
	sawons.forEach(function(sa){
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	})*/
	sawons.forEach((sa)=>{
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	})
	document.querySelector('tbody').innerHTML=html;
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<thead>
					<tr class="success">
						<th class="text-center">사번</th>
						<th class="text-center">이름</th>
						<th class="text-center">부서</th>
						<th class="text-center">근무지</th>
						<th class="text-center">직위</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
				










