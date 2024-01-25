<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let sawons=[{sabun:1, name:"신아라", dept:"개발부", job:"대리", pay:3200},
		{sabun:2, name:"주찬양", dept:"총무부", job:"사원", pay:3000},
		{sabun:3, name:"성준수", dept:"영업부", job:"대리", pay:4500},
		{sabun:4, name:"최종수", dept:"총무부", job:"팀장", pay:8000},
		{sabun:5, name:"전영중", dept:"기획부", job:"과장", pay:6700}]

// 여기서 함수를 출력해야 결과값이 나온다.
window.onload=function(){
	sawonList()
	sawonDetail(3)
	sawonInsert()
	sawonDelete()
	sawonSlice()
}
// 사원 리스트 출력
const sawonList=function(){
	console.log(sawons)
}
// 해당 사원에 대한 디테일 정보 출력
const sawonDetail=(sabun)=>{
	let sawon=sawons.find(sa=>sa.sabun==sabun)
	console.log(sawons)
}
// 새로운 사원 추가
function sawonInsert(){
	sawons.push({sabun:6, name:"기상호", dept:"영업부", job:"사원", pay:2800})
}
// 마지막 사원 삭제
const sawonDelete=()=>{
	sawons.pop()
	console.log(sawons)
}
// 해당 인덱스 번호의 사원 리스트 출력
const sawonSlice=()=>{
	let sa=sawons.slice(1, 3)
	console.log(sa)
}
</script>
</head>
<body>

</body>
</html>












