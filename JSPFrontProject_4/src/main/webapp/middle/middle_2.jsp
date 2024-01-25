<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		자바스크립트의 핵심
		==============
		=> 객체모델 : 태그를 읽어오는 방법
					document.querySelector(CSS선택자) $()
					document.getElementById()
		=> 내장객체 : String, Math, Date => 함수
		=> 브라우저 내장객체 : window, document, history, location
		=========================================================
			| 라이브러리 변경 => Jquery ==> 동적 (Ajax)
		
		단일값 저장 => 변수 let 변수명=값 (값에 따라 자동으로 데이터형이 결정)
		다중값 저장
		========
		  1. 배열 [] => 자바와 다른 점 : 데이터형이 혼합 가능 ["", 10, "", 10.5, {}]
		  			 				 데이터형이 한개(let)이기 때문에 가능
		  2. 객체 {} => {키:값, 키:값...} 자바에서는 멤버변수 => JSON
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	/*
		let a=10;
	    a="aaa"
	    a=[]
	    a={}
	*/
	let arr=["신아라", 25, 'B', "개발", 3500] // Object[]
	console.log(arr)
	console.log("arr="+typeof arr)
	for(let data of arr)
	{
		document.write(data+"<br>")
	}
	document.write("<hr>")
	document.write("<h3>객체 표현법 1:(JSON)</h3>")
	// {변수:값, 변수:값...}
	let sawon={sabun:1, name:"신아라", dept:"개발부", pay:3500}
	
	document.write("사번: "+sawon.sabun+"<br>")
	document.write("이름: "+sawon.name+"<br>")
	document.write("부서: "+sawon.dept+"<br>")
	document.write("연봉: "+sawon.pay+"<br>")
	document.write("<hr>")
	
	document.write("<h3>객체 표현법 2:(JSON)</h3>")
	document.write("사번: "+sawon['sabun']+"<br>")
	document.write("이름: "+sawon['name']+"<br>")
	document.write("부서: "+sawon['dept']+"<br>")
	document.write("연봉: "+sawon['pay']+"<br>")
	document.write("<hr>")
	
	document.write("<h3>객체 표현법 3:(JSON)</h3>")
	for(let key in sawon)
	{
		document.write(key+":"+sawon[key]+"<br>")
	}
	/*
		class sawon
		{
			int sabun;
			String name, dept;
			int pay;
		}
		Sawon s=new Sawon();
		s.sabun=1
		s.name="신아라"
		s.dept="개발부"
		s.pay=3500
	*/
	// function 첨부 가능 => function 자체를 변수로 취급
}
</script>
</head>
<body>

</body>
</html>