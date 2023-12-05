<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		p.139
		
		지시자
		  page : jsp 파일에 대한 정보
		  => JSP시작점
		  => 1. contentType = 실행시 변경될 파일 형식
		        => 자바로 변경 : response.setContentType()
		        => html => text/html;charset=UTF-8
		        					 =============
		        					  charset=ISO-8859-1 => default
		           xml => text/xml;charset=UTF-8
		           json => text/plain;charset=UTF-8
		        => 브라우저에 알려준다.
		        => page안에서 1번만 사용할 수 있다.
		     2. import => 외부 라이브러리 첨부
		     		      java.lang , javax.http.servlet
		     		      ============================== 생략 가능
		     		   => 사용 형식 => 2
		     		   <%@ page import="java.util.*,com.sist.dao.*"%>
		     		   <%@ page import="java.util.*"%>
		               <%@ page import="com.sist.dao.*"%> 
		     3. buffer => html을 저장하는 메모리 공간
		     		   => 8kb
		     		   => html이 출력될 때 용량이 부족해질 시에는 메모리 크기를 증가시켜야 한다.
		     		      buffer="16kb" => 출력스트림
		     4. errorPage => 에러시에 호출되는 파일
		     
		  taglib : 태그로 자바 기본 문법을 제공 => jstl/el
		  include : jsp안에 다른 jsp첨부
		  <%@ 지시자명 속성="값" 속성="값".. %>
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