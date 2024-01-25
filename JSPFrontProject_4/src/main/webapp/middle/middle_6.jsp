<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		p.316
		
		함수 : 코드의 집합
		      기능처리 (사용자가 요청=>행위, 브라우저에서만 실행 가능하다.)
		      ======
		      버튼 클릭 / 마우스 오버 / 변경 / 키보드
		      => 호출시에 이벤트가 발생한다.
		         onclick / onmouseover(hover)
		         onmouseout - 지도 / onchange - select box / onkeydown / onkeyup
		
		함수 생성 방법
		  익명의 함수 (함수에 이름이 없는 것) : function(){} => callback (시스템에 의해 자동 호출)
		  								 map(function(){})
		  선언적 함수 :
		    = function 함수명(){} ==> 함수명()			=> 기본
		    = let func=function(){} => 이벤트 등록		=> 변형
		    = let func=()=>{} => 이벤트 등록
		
		함수 유형 : 리턴형을 기술하지 않는다. => 리턴은 가능
				  리턴형 메소드명(매개변수, 매개변수...)
				  function func_name(매개변수....)
				  {
				  	return "a"
				  }
				  
				  let a=func_name()
		function 함수명(매개변수...)
		===========================================
			리턴형		매개변수	=> 리턴형은 let, sm
			  O			   O
			  O			   X
			  X  		   O
			  X			   X
			 ==================
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	document.write("<h3>리턴형, 매개변수 설정</h3>")
	let name=hello("신아라");
	document.write(name);
	document.write("<hr>");
	
	document.write("<h3>리턴형만 존재하는 함수</h3>");
	let msg=hello2();
	document.write(msg+"<br>");
	document.write("<hr>");
	
	hello3('신아라')
	hello4()
}
//function hello(name)
//let hello=function(name)
let hello=(name)=>
{
	return name+"님 환영합니다!";
}
//function hello2()
//let hello2=function()
let hello2=()=>
{
	return "Hello JavaScript Function"
}
//function hello3(name)
//let hello3=function(name)
let hello3=(name)=>
{
	// void => 직접처리
	document.write("<h3>매개변수</h3>");
	document.write(name+"<br>")
	document.write("<hr>");
}
//function hello4()
//let hello4=function()
let hello4=(name)=>
{
	document.write("<h3>매개변수와 리턴형이 없는 함수</h3>");
	document.write("받은 데이터가 없다..<br>")
	document.write("<hr>");
}
</script>
</head>
<body>

</body>
</html>