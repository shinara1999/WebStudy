<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
		JSTL
		  -- Tag Library
		  <% %> 를 tag형으로 제작
		   = 1. 변수 선언 => int a=10; => <c:set var="a" value="10"/>
		     2. 제어문 =>
		         for(int i=1;i<=10;i++)
		         => <c:forEach var="i" begin="1" end="10" step="1">
		         for(SawonVO vo:list)
		         => <c:forEach var="vo" items="list" varStatus="in">
		         									 ========= index
		         						=> 번호 출력 / 다른 List 출력
		         <c:forTokens var="" value="" delim="">
		         => StringTokenizer st=new StringTokenizer(value, delim)
		            while(st.hasMoreTokens())
		            {
		            }	
		         => <c:if> 조건문
		            <c:if test="${}">
		            	  ======= 조건문이 들어가는 위치
		            => if(test)
		            => 단점 : else문장이 없다.
		            		 ============= 사용자 정의 (회사)
		         => 다중 조건문 => XML로 제작
		         		XML
		         		===
		         		 1. 태그나 속성 => 대문자 구분
		         		 2. 속성값 => ""
		         		 3. 계층구조 => 여는태그 / 닫는태그 일치
		         		    => <jsp:include>
		            <c:choose>
		              <c:when test="조건문">출력</c:when>
		              <c:when test="조건문">출력</c:when>	
		              <c:when test="조건문">출력</c:when>	
		              <c:otherwise>default</c:otherwise>
		            </c:choose>	  
		     3. 화면 이동
		     	<c:redirect url=""/>
		     	=> response.sendRedirect(url)
		     4. 화면 출력
		        <%= %>
		        <c:out value=""> => JQuery
		        ${} 는 JQuery에서 함수로 사용되기 때문에 사용 불가, c:out을 사용해야 한다.
		        	import가 동일하게 있으면 오류 발생
		     ==================================================================== core
		     5. 날짜 변환 / 숫자 변환
		     	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd")
		     	<fmt:formatDate value="" pattern="yyyy-MM-dd">
		     	=> 오라클에서 TO_CHAR를 사용하는 것이 더 간편할 수도..
		     	   TO_CHAR(regdate, 'YYYY-MM-DD')
		     	DecimalFormat d=new DecimalFormat("###,###"); => 1000자리 앞 ,
		     	<fmt:formatNumber value="" type="currency">   
		     ==================================================================== format
		     6. 메소드 호출
		     ==================================================================== functions
		     
--%>
<%
	// JSTL => 출력은 EL로 (%를 쓰지 않는다.)
	List<String> nList=new ArrayList<String>();
	nList.add("신아라");
	nList.add("전영중");
	nList.add("성준수");
	nList.add("주찬양");
	nList.add("최종수");
	
	List<String> sList=new ArrayList<String>();
	sList.add("여자");
	sList.add("남자");
	sList.add("남자");
	sList.add("남자");
	sList.add("남자");
	
	request.setAttribute("nList", nList);
%>
<c:set var="sList" value="<%=sList %>"/>
<%-- c:set은 일반 변수가 아니라 request.setAttribute(var, value) --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름</h1>
	<ul>
		<%-- for(String name:nList) --%>
		<c:forEach var="name" items="${nList }">
			<li>${name }</li>
		</c:forEach>
	</ul>
	<h1>성별</h1>
	<ul>
		<c:forEach var="sex" items="${sList }">
			<li>${sex }</li>
		</c:forEach>
	</ul>
	<h1>이름(성별)</h1>
	<ul>
		<c:forEach var="name" items="${nList }" varStatus="s">
			<li>${s.index+1 }.${name }(${sList[s.index] })</li>
		</c:forEach>
	</ul>
</body>
</html>













