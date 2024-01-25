<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		1. for
		for(초기값;조건식;증가식)
		{
			반복수행문장
		}
		
		["신아라", "전영중", "주찬양"]
		["여자", "남자", "남자"]
		[25, 19, 18]
		2. forEach구문
		   for(let data of 배열) => for(데이터형 변수:배열, 컬렉션)
		   {
		   		반복수행문장 ==> 배열의 실제 값을 읽어온다.
		   }
		   ==>
		   for(let data in 배열)
		   {
		   		반복수행문장 ==> 배열의 인덱스 번호를 읽어온다.
		   		=> 오라클 (JOIN) WHERE no=no
		   }
		 3. map => 반복문 (가장 많이 사용되는 반복문) => vue, react
		    사용법
		    배열명.map(function(데이터)){
		    	반복 출력물
		    }
		    
		 4. forEach
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	const names=["신아라", "전영중", "주찬양", "성준수", "최종수"] // 상수 배열
	const sexs=["여자", "남자", "남자", "남자", "남자"];
	
	// 일반 for
	document.write("<h3>일반 for</h3>");
	// length => 배열의 데이터 갯수
	// 자바스크립트의 배열 => 인덱스 번호는 0번부터 시작
	for(let i=0;i<=names.length;i++)
	{
		document.write(names[i]+"&nbsp;")
	}
	document.write("<hr>")
	
	// for of
	document.write("<h3>for of</h3>");
	// for문을 돌렸을 때 for(String name:names) 에서 값을 가지고 온다.
	for(let name of names)
	{
		document.write(names+"&nbsp;")
	}
	document.write("<hr>")
	
	// for in
	document.write("<h3>for in</h3>");
	for(let index in names)
	{
		document.write(names[index]+"("+sexs[index]+")<br>")
	}
	document.write("<hr>")
	
	// map
	// 함수형으로 변경 => map / forEach
	// 일반 for , map , forEach
	document.write("<h3>map 사용법 1</h3>");
	// 형식 => ajax
	names.map(function(name){
		document.write(name+"&nbsp;")
	})
	document.write("<hr>")
	
	document.write("<h3>map 사용법 2</h3>");
	names.map((name)=>{	// 화살표함수
		document.write(name+"&nbsp;")
	})
	document.write("<hr>")
	
	document.write("<h3>forEach 사용법 1</h3>");
	// 형식 => ajax
	names.forEach(function(name){
		document.write(name+"&nbsp;")
	})
	document.write("<hr>")
	
	document.write("<h3>forEach 사용법 2</h3>");
	names.forEach((name)=>{	// 화살표함수
		document.write(name+"&nbsp;")
	})
	document.write("<hr>")
}
</script>
</head>
<body>

</body>
</html>










