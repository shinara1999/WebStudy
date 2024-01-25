<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
		내장객체
		1) 브라우저 내장 객체
		   window	: 브라우저 창 제어
		   			  => open
		   			  => close
		   document	: 화면 (HTML의 태그) => body
		   			  => querySelector()
		   			  => write
		   history	: 기록 정보
		   			  => back() , go() => 이전화면 이동
		   location	: 화면 이동 정보
		   			  => href
		   
		2) 내장 객체
		   = Number()
		   = parseInt()
		   = String() ======> 형변환
		   ***웹의 단점 : 모든 데이터가 문자열로 되어있다.
		   String / Date / Array
		   ===================== 특별한 경우가 아니면 자바에서 처리된다.
		   = 서버에서 데이터 읽기 => ajax / axios
		   							   ====== vue / react
		   String => 유효성 검사 (오라클 => Primary key, NOT NULL)
		   ======
		    1. length => 입력된 문자의 갯수 => 비밀번호
		    2. charAt(number)
		    		 ======== 문자열 번호 (0)
		    		  Hello
		    		  01234 ==> 'o' : charAt(4)
		    3. indexOf() => 해당 문자열 찾기
		       indexOf('찾는 문자열', fromIndex)
		       => Number (return) -1은 존재하지 않는 경우
		    4. lastIndexOf('찾는 문자열', fromIndex)
		       =================================== 서제스트 (자동완성기)
		        => 자음으로 찾기 => PROCEDURE로 만들 예정 (함수)
		    5. replace => 변경
		       replace('변경될 문자열', '변경할 문자열')
		    6. *** split(string, 구분문자) => []
		    7. substring() => 문자를 자를때 사용
		       substring(number, number)
		       			시작문자번호 끝나는문자번호(-)
		    8. substr(number, number)
		             시작문자번호  갯수
		    9. *** trim() => 좌우 공백 제거
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
		1. 변수 (let(지역변수, 전역변수)), const(상수=자바에서 데이터를 받는 부분)
		2. 연산자 => (+, 비교연산자, 대입연산자(=, +=))
		3. 제어문
		   if ==> if~else
		   일반 for for(let i=0;i<=10;i++)
			       for(let data of 배열)
			       ======================
			    	map, forEach
			    	===
			        배열.map(function(읽을 데이터){})
			        => 배열.map((읽을 데이터)=>{})
			        => map대신 forEach
		4. 함수
		   = 내장함수 : 형변환, JSON변환
		    		  Number(), parseInt()
		    		  String()
		    		  JSON.parse()
		    		  오라클 => 자바 => 자바스크립트 (List, VO 인식 불가)
		    		  		  List ==> [](배열)
		    		  		  VO   ==> {}(객체)
		    		  		  ================ JSON이 아니고 String
		    		  Vue <==> Spring
		    		  React <==> Spring-Boot
		   = 사용자 정의 함수
		     => 선언적 함수
		        function func_name(매개변수...)
		        {
		    	 
		        }
		     => 익명의 함수 => 함수 안에 함수 선언 => 이벤트 등록
		        let func_name=function(){}
		        let func_name=()=>{}
		     => 주의점 : 매개변수는 데이터형을 사용하지 않는다.
		        	   리턴형도 선언하지 않는다.
		        	   (name, addr)
		     = 객체 모델 선택
		       자바스크립트에서 태그를 객체로, 속성을 멤버변수로 취급
		       => document.querySelector(CSS선택자)
		          ================================ $(CSS선택자)
		       => document.getElementById(id명)
		       => 태그를 제어하는 프로그램
		       	  =================== DOM 프로그램
		       => createElement => 태그를 만들어주는 함수
		          createTextNode => 태그와 태그 사이에 값을 설정
		          <a>()</a>
		       => appendChild()
		       => 태그를 읽어서 값을 설정
		          <td> </td>
		          	  | =====> innerHTML / textContent
		          <embed src="|"> => 동영상
		          			setAttribute => getAttribute
		          class="" => active => addClass , removeClass
		          
		     = 이벤트
		       일반 방식
		         => function aaa(){}
		            <button onclick="aaa()"></button>
		       고전적 방식
		            let button=document.querySelector("")
		            button.onclick=function(){}
		       리스너 이용 방식
		       		let button=document.querySelector()
		       		button.addEventListener('click', function(){})
		       		=> Jquery
		       		   $('button').click(function(){})
		       		   $('button').on('click', function(){})
 */
</script>
</head>
<body>

</body>
</html>






















