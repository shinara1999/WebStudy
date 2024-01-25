<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String no=request.getParameter("no");
    String curpage=request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/table.css">
<style type="text/css">
img#main{
   margin-top: 5px;
}
a{
   color: black;
   text-decoration: none;
}
a:hover {
	color: green;
	text-decoration: underline;
}
</style>
</head>
<body>
  <center>
    <img id="main" src="https://i.namu.wiki/i/JNKvslt1j4AioYDmWSiZdcJBzwWD2-XibS8JYqMiQcSpF5GSUzYHVdVBsSOPElngPI5CfiAUf0oM3bXXzFr30w.webp" width="200" height="200">
    <form method=post action="reply_ok.jsp">
    <table class="table_content" width=800>
      <tr>
        <th width=20%>이름</th>
        <td width=80%>
         <input type=text name=name size=15 required>
         <input type="hidden" name=pno value="<%=no%>">
         <input type="hidden" name=page value="<%=curpage%>">
        </td>
      </tr>
      <tr>
        <th width=20%>제목</th>
        <td width=80%>
         <input type=text name=subject size=45 required>
        </td>
      </tr>
      <tr>
        <th width=20%>내용</th>
        <td width=80%>
         <textarea rows="10" cols="50" name=content required></textarea>
        </td>
      </tr>
      <tr>
        <th width=20%>비밀번호</th>
        <td width=80%>
         <input type=password name=pwd size=10 required>
        </td>
      </tr>
      <tr>
       <td colspan="2" class="text-center">
         <button>답변</button>
         <input type=button value="취소"
          onclick="javascript:history.back()">
       </td>
      </tr>
    </table>
    </form>
</body>
</html>