<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%--
		1. 데이터 읽기
		   1) import 설정
		      <%@ import="java.util.*,com.sist.dao.*"%>
		      
		      <%@ page import="java.util.*"%>
		      <%@ page import="com.sist.dao.*"%>
		      
		2. html의 해당 위치에 <% %> => for
		   => 데이터 출력은 <%= %>
		   => 중심이 View (화면 출력)
--%>
<%
	EmpDAO dao=EmpDAO.newInstance();
	List<EmpVO> list=dao.empListdata();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../css/table.css">
	<style type="text/css">
		.container{
			margin-top: 50px;
			width: 100%;
			margin: 0px auto;
		}
		h1{
			text-align: center;
		}
		.row, .table_content{
			width: 800px;
			margin: 0px auto;
		}
	</style>
</head>
<body>
  <div class="container">
   <div class="row">
	<h1>사원 목록</h1>
	<table class="table_content" width=800>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직위</th>
			<th>입사일</th>
			<th>급여</th>
		</tr>
		<%--
			<% 자바 소스 %> : 메소드 안에 들어가는 자바소스
			<%= 출력물 %> : out.println(여기에 들어가는 문장)
			
			= 자바 / HTML을 구분
			= 모든 소스는 _jspService()에 첨부 => 톰캣이 자동 처리
			  톰캣 : WAS
			  형상관리 : GIT
		--%>
		<%
			for(EmpVO vo:list)
			{
		%>
			<tr class="dataTr">
				<td class="text-center"><%= vo.getEmpno() %></td>
				<td class="text-center"><%= vo.getEname() %></td>
				<td class="text-center"><%= vo.getJob() %></td>
				<td class="text-center"><%= vo.getHiredate().toString() %></td>
				<td class="text-center"><%= vo.getSal() %></td>
			</tr>
		<%
			}
		%>
	</table>
   </div>
  </div>
</body>
</html>



















