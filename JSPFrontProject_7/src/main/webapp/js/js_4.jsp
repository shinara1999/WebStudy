<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		Ajax / Vue / React ==> 서버에서 값을 읽어와서 처리
		==> 자바 / 자바스크립트 매칭 ==> Array , JSON
		Array => 배열 []
		  1) 배열에 값을 추가할 때
		     push()
		  2) 배열의 마지막 값 제거
		     pop()
		  3) 원하는 위치까지 잘라서 새로운 배열 생성
		     slice() ==> Ajax ==> 페이지 나누기
		  4) indexOf() : 데이터의 위치 확인
		  5) find() : 데이터 검색
		  6) delete : 배열을 완전 삭제
		  7) length : 저장된 갯수
		  
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	// 배열 선언
	let names=[
		{name:'신아라'},
		{name:'주찬양'},
		{name:'성준수'},
		{name:'전영중'},
		{name:'최종수'}    // 배열에 객체가 들어가는 방식
	]
	// 1. 배열 출력
	console.log(names)
	// 2. 인원수
	console.log("인원수: "+names.length)
	// 3. 인원 추가
	names.push({name:"기상호"})
	console.log(names)
	// 4. 제거 => 마지막
	names.pop()
	console.log(names)
	// 5. 전체 제거
	names.delete
	console.log(names.length)
}
</script>
</head>
<body>

</body>
</html>























