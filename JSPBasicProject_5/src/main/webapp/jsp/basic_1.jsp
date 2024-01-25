<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		JSP => Java Server Page : 서버에서 실행하는 자바 파일
		===
		==============> 교재 생략 : 자바, 데이터베이스, 제공하는 예제
		1. 지시자
		   <%@ page %>
		   		contentType : 브라우저에 전송 => 파일 형식
		   			= text/html;charset=UTF-8
		   			= text/xml;charset=UTF-8
		   			= text/plain;charset=UTF-8
		   			=====> html, xml, json 순으로 많이 쓰인다.
		   			=====> 자바 변경 => response.setContentType()
		   		**errorPage : error가 난 경우에 지정된 파일로 이동할 수 있게 만들어준다.
		   		isErrorPage : true/false => exception 객체 사용한다.
		   		**import : 외부 클래스 읽어올때 => 여러번 사용 가능하다.
		   			=> jsp에서만.. MVC에서는 사용하지 않는다.
		   		buffer : 8kb => 증가 가능 (2배 권장)
		   <%@ taglib %> => JSTL / EL
		2. 스크립트
		   <% %> : 일반 자바 (main안에 소스코딩)
		   		   => 제공하는 태그를 이용해서 사용
		   <%= %> : 데이터 출력
		   			=> ${}로 바뀔 예정..
		   			=> 가급적이면 <% %>를 다 제거.. (MVC에서)
		   JSP <===> JSP
		   		  	  |
		   		  	 DAO ===> Model 1 방식 => 재사용성이 좋지 않다.
		   		  	 		  ======= 2002~04년도 유행
		   JSP ====> Controller ==> Model ==> DAO
		   						    ===== 자바(재사용성이 좋다.)
		   						    	  ==> Model 2 방식 (현재 98% 사용)
		   ===> 당점 : Controller에 집중
		   			  ========== 분할 => Domain방식 ==> MSA
		   
		3. 내장 객체
		   request / response / session / application
		   out / pageContext
		4. JSP 액션 태그
		   <jsp:useBean> <jsp:setPorperty> <jsp:include>
		============== 기본 과정
		5. EL
		6. JSTL
		7. MVC
		============== 중급 과정
		8. XML
		9. 어노테이션
		============== 고급 과정
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>