<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%
	SawonVO vo=new SawonVO();
	vo.setName("신아라");
	vo.setDept("개발부");
	// ${} => 우선 request, session에 담아줘야 한다.
	request.setAttribute("vo", vo); // JSP로 데이터를 추가해서 전송
	session.setAttribute("vo", vo);
	// request에 들어가는 것은 제한이 없다. 
	SawonVO svo=(SawonVO)request.getAttribute("vo");
%>
<%-- 
		화면 출력 = 태그형 프로그램 제작 (자바 최소화)
		<%= %> => ${} , <c:out value=""/>
				 === $는 Jquery 라이브러리
		let a=${name}
		
		${출력물} => 자바의 변수가 아니다.
		 ====== request , session값이 들어간다.
		request.setAttribute("name", "신아라")
		
		<%= request.getAttribute("name") %>
		${name}
		=================================== 이거 2개는 같은 표현이다.
		
		=> getParameter() => ?... GET/POST
		   setAttribute() => request에 기존에 있는 데이터 외에 다른 데이터를 추가해서 전송할 때 사용
		   
		=> ${name} : 새로 추가된 데이터를 읽어서 출력한다.
		
		=> param : getParameter("name")
		           ${param.name}		==> 2개 같은 표현
		
		1) 연산자
		   산술연산자 : (+,-,*,/(=div),%(=mod))
		             +  : 순수하게 산술만 처리 (문자열 결합X)
		             += : 문자열 결합
		                  null값은 0으로 인식
		                  "5"+1 = 5를 자동으로 int형으로 형변환 시킨다.
		                  "a"+1 = 오류 발생
		             /  : 정수/정수=실수
		   비교연산자 : if(조건문) => 숫자 / 문자 / 날짜
		     		 <c:if test="${vo.getId()==sessionScope.id}">
		     		 == (eq) ${1==1} ${1 eq 1}
		     		 != (ne) ${1!=1} ${1 ne 1}
		     		 <  (lt)
		     		 >  (gt)
		     		 <= (le)
		     		 >= (ge)
		   논리연산자 : 조건문
		   			 && (and) => 범위 안에 포함
		   			 || (or)  => 범위 밖에 있는 경우
		   			 !  (not) => 부정 연산자
		   삼항연산자 : 페이지 ${curpage>1?curpage-1:curpage}
		   				   ${curpage<totalpage?curpage+1:curpage}
		   =============================================================
		   ${requestScope.name} => request.getAttribute("name");
		                  ==== 키
		   ${sessionScope.id} => session.getAttribute("id");
		   				  == 키
		   => request , session 저장시에는 Map형식으로 저장한다.
		   	  ================= 키, 값
		   예)
		   		session.setAttribute("admin", "1");
		   							  ===== 키 == 값
		   		 => session.getAttribute("admin");
		   								  ===== 값을 가져올 때는 키를 이용한다.
		   		 => ${sessionScope.admin} (같은 표현)
		   		 
		   	 	request.setAttribute("id", "shin");
		   	 			============ getParameter로 받을 수 없다.
		   	 	 => request.getAttribute("id");
		   	 	 => ${requestScope.id}
		   	 	 => ${id} => requestScope는 생략이 가능하다.
		   	 	 
		   	 	?id=admin&pwd=1234
		   	 	 => request.getParameter("id")
		   	 	    request.getParameter("pwd")
		   	 	 => ${param.id}
		   	 	    ${param.pwd}
		   	 	    
		   	   bean => vo
		   	=> class Sawon
		   	   {
		   	   		private int sabun;
		   	   		private String name;
		   	   		
		   	   		=> getter/setter
		   	   		   getSabun() , setSabun()
		   	   		   getName(), setName()
		   	   }
		   	   
		   	   Sawon vo=new Sawon();
		   	   vo.setSabun(1); ===> getSabun()
		   	   vo.setName("신아라"); ===> getName()
		   	   
		   	   request.setAttribute("vo", vo); => 해당 JSP로 요청값을 전송한다.
		   	   => Sawon vo=(Sawon)request.getAttribute("vo");
		   	                =================================
		   	                ${vo.getName()} => ${vo.name} 둘 중 아무거나 써도 상관없다.
		   	      vo.getName() / vo.getSabun()
		   	      
		  Model => 자바
		  ===== DAO/VO/...자바로 코딩하는 모든 파일
		  	    ====== 한개로 만들 수 있다.
		  	 
		  request > session > application 순으로 우선순위이다.
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : <%= svo.getName() %><br>
부서 : <%= svo.getDept() %><br>
<h1>EL : request</h1>
이름 : ${vo.name }<br>
부서 : ${vo.dept }<br>
<h1>EL : sessionScope</h1>
이름 : ${sessionScope.vo.name }<br>
부서 : ${sessionScope.vo.dept }<br>
</body>
</html>


















