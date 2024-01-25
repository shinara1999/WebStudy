<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  
		1. CSS
		   => 자바스크립트 적용 (DOM Script)
		      => 태그에서 이벤트 발생
		   => Selector
		      = 기본
		        => 태그명
		           태그명 {
		           		속성:값
		           }
		           * $('태그명').click(function(){})		=> JQuery
		             document.querySelector("태그명");	=> 일반 자바스크립트
		             new Vue({
		                el:'태그명'
		             })
		           * 많이 사용되지 않는 이유 => 태그가 많다.
		        =============== 구분자 ===============
		        => ID명 => 중복이 없는 구분자 (button)
		           #ID {
		              속성:값
		           }
		           * $('#ID명').change(function(){처리}) => 변경되었을 때 처리된다. (많이 사용하지는 않음)
		             document.querySelector("#ID명");
		        => Class명 => 중복이 되는 상태 (td, tr)
		           .class명{
		              속성:값
		           }
		           * $('.class명').keyup(function(){}) 
		             document.querySelector(".class명");
		        =======================================
		  속성 선택자 : input / img / a
		  ========
		  태그명[속성=값]  ==> equals 
		  태그명[속성^=값] ==> startsWith => http/https
		  태그명[속성*=값] ==> contains => 크롤링
		  img [src*=/recipe/]
		  태그명[속성$=값] ==> endsWith => 이미지만 가지고 오고 싶은 경우
		  				==> 후기게시판
		  img[src$='jpg|png|gif']
		  => $('img[src$="jpg|png|gif"]')
		  
		  *** HTML => 계층구조 (트리형태)
		  					  ====== DOM
		  			  <div>
		  			  	<h1></h1>
		  			  	<span>
		  			  		<h1></h1>
		  			  	</span>
		  			  </div>
		  			  
		  	  후손선택자
		  	  		div
		  	  		 |
		  	  	----------
		  	  	|		 |
		  	   h1	    span ==> 자손 div > h1, div > span (꺽쇄 사용)
		  	   			 |
		  	   			 h1  ==> 후손 div h1 (공백 사용)
		  	  자손선택자
		  	  인접 => div > h1+span (바로 옆에있는 태그만 가져올 때)
		  	      => div > h1~span (이 밑의 태그를 전부 가져올 때)
		 ===========================================================
		 반응선택자 : ID명, class명, 태그명:hover (클론찍고 호버를 줘야함)
		 						 =========== mouseover
		 구조선택자 : 	nth-child(수열)
		 		   	nth-child(2n)	==> n=0 => 0 2 4...
		 		   	nth-child(2n+1)	==> n=0 => 1 3 5...
		 문자선택자 : ::after , ::before
		 ===========================================================
		 
	p.192 => 위치속성
	      ----------------------------------
	       static	: 태그에 맞추어서 배치
	      ----------------------------------
	       relative	: 상대 태그 좌표 이동
	      ----------------------------------
	       absolute	: 절대 좌표
	      ----------------------------------
	       fixed	: 고정 => 상세보기
	      ----------------------------------
	       => 위치 지정
	          top / left / right / bottom
	       => float (left|right) => 옆으로 붙이는 경우 => 레이아웃
	       => 해제 : clear: both
	       => z-index : 레이어 (순위가 높을수록 위로 올라온다.)
--%>
<%-- 
		
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
	width: 200px;
	height: 250px;
	margin-right: 30px;
	float: left;
}
</style>
</head>
<body>
	<h1>신차원! 짱구는 못말려 더 무비 초능력 대결전 ~날아라 수제김밥~ </h1>
	<img src="https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87888/87888_320.jpg">
	<pre>
최초의 3D CG! 제작 기간 7년
최고의 웃음과 감동! 최강의 스케일
옷까지 갈아입은 볼록 짱구 등장!

어느 날, 우주에서 날아온 검은 빛과 하얀 빛이 떡잎마을을 향해 떨어진다.
평소처럼 저녁밥을 손꼽아 기다리던 짱구는 하얀 빛에 정통으로 맞게 되고
그러자 온몸에 넘치는 신비한 힘!
힘에 몸을 맡긴 채 엉덩이에 의식을 집중하자 장난감들이 붕붕 떠오른다.

"엉덩이... 엉덩이가 뜨거워...!?
뭔지 몰라도 엄청난 힘을 손에 넣은 것 같아."

한편, 검은 빛을 통해 초능력을 손에 넣은 또 다른 남자는
이 세상의 파멸을 바라며 폭주하기 시작하는데,
위기에 처한 세상을 구하기 위한 유일한 희망이 바로 짱구...!?

올 겨울, 짱구의 엉덩이가 뜨겁게 타오른다!
	</pre>
</body>
</html>

















