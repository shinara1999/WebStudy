<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 한글 => 디코딩을 해야한다. => 모든 jsp마다 내장객체를 다 가지고 있다.
	// 2. 화면 변경 => request가 초기화
	// 3. forward
	try{
		request.setCharacterEncoding("UTF-8"); // 이거 안하면 한글 깨짐
		// 내장 객체
	}catch(Exception e){}
	// 2. 전송된 값을 받는다.
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	// radio는 이름이 동일해야 한개만 선택 가능하게 만들 수 있다.
	String tel1=request.getParameter("tel1");
	String tel2=request.getParameter("tel2");
	String tel=tel1+")"+tel2;
	String content=request.getParameter("content");
	String[] hobby=request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--
		JSP주석은 사라진다
		HTML주석은 남아있다
		HTML+java를 동시에 주석 설정
		
		=> 내장객체 ==> Spring / Spring-Boot
		   ***request : JSP마다 request를 가지고 있다.
		      		    단점은 화면 변경/새로고침 => request는 초기화
		      		    => HttpServletRequest의 객체명
		      		    => jsp페이지로 데이터를 전송시에 데이터 전체를 묶어서 보내준다.
		      		    						   =============== request
		      		    => request는 Map형식으로 되어있다.
		      		       (키, 값) => 키는 중복이 불가능하다.
		      		       <input type=text name="aaa">
		      		       					========== 키, 입력값
		      		       					getParameter("aaa")
		      		       <input type=text name="aaa">
		      		       <input type=text name="aaa">
		      		       
		      		       => ?name=aaa => getParameter("name")
		      		           ==== ===
		      		            키   값 => ?page=1
		      		            		  ? page= 1
		      		            		  ====== ===
		      		       => a.jsp
		      		          => null
		      		          if(a==null)
		      		       => a.jsp?name=
		      		          => " "
		      		          if(a.equals(""))
		   ***response
		   ***session
		   application
		   ===========
		   pageContext => RequestDispacher => include, forward
		    <jsp:include>
		   out
		   =========== 다운로드
--%>
<body>
	1. 사용자의 IP:<%= request.getRemoteAddr() %> <br>
	2. 서버 정보:<%= request.getServerName() %> <br>
	3. 서버 프로토콜:<%= request.getProtocol() %> <br>
	4. 전송방식:<%= request.getMethod() %> <br>
	5. 포트번호:<%= request.getServerPort() %> <br>
	6. 요청 URL:<%= request.getRequestURL() %> <br>
	7. 요청 URI:<%= request.getRequestURI() %> <br>
	8. ContextPath:<%= request.getContextPath() %>:루트 폴더 <br>
	9. 전송 값<br>
	   이름:<%=name %><br>
	   성별:<%=sex %><br>
	   전화:<%=tel %><br>
	   소개:<%=content %><br>
	   취미:<%
	   			try{
	   				out.write("<ul>");
	   				for(String h:hobby)
	   				{
	   					out.write("<li>"+h+"</li>");
	   				}
	   				out.write("</ul>");
	   			}catch(Exception e)
	   			{
	   				out.write("취미가 없습니다.");
	   			}
	   	%>
</body>
</html>



















