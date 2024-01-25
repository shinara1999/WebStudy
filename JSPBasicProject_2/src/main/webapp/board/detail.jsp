<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    /*
        1. 회원가입 , 회원 수정 
           예매 , 글쓰기 .... 입력창/처리창 => jsp두개 
           => SELECT
    */
    //1. 요청 데이터 받기 
    //?no= &page=
    //request.setCharacterEncoding() => 디코딩 
    //한글 => 알파벳 / 숫자 (1,2byte)
    String no=request.getParameter("no");
    String curpage=request.getParameter("page");
    //2. 오라클에서 데이터를 가지고 온다 => DAO 
    BoardDAO dao=BoardDAO.newInstance();
    BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
    //3. 화면 출력 
    
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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0; // 전역변수 
$(function(){
	// window.onload=function(){}
	$('#delBtn').click(function(){
		if(i==0)
		{
			$('#del').show();
			$('#delBtn').text("취소");
			$('#pwd').focus();
			//document.getElementById("pwd");
			i=1;
		}
		else 
		{
			$('#del').hide();
			$('#delBtn').text("삭제");
			i=0;
		}
		
	});
});
</script>
</head>
<body>
  <center>
    <img id="main" src="https://i.namu.wiki/i/JNKvslt1j4AioYDmWSiZdcJBzwWD2-XibS8JYqMiQcSpF5GSUzYHVdVBsSOPElngPI5CfiAUf0oM3bXXzFr30w.webp" width="200" height="200">
    <table class="table_content" width=800>
     <tr>
       <th width=20%>번호</th>
       <td width=30% class="text-center"><%=vo.getNo() %></td>
       <th width=20%>작성일</th>
       <td width=30% class="text-center"><%=vo.getDbday() %></td>
     </tr>
     <tr>
       <th width=20%>이름</th>
       <td width=30% class="text-center"><%=vo.getName() %></td>
       <th width=20%>조회수</th>
       <td width=30% class="text-center"><%=vo.getHit() %></td>
     </tr>
     <tr>
       <th width=20%>제목</th>
       <td colspan=3><%=vo.getSubject() %></td>
     </tr>
     <tr>
       <td colspan="4" class="text-left" valign="top" height="200"><pre style="white-space: pre-wrap;"><%=vo.getContent() %></pre></td>
     </tr>
     <tr>
       <td colspan="4" class="text-right">
        <a href="reply.jsp?no=<%=no%>&page=<%=curpage%>">답변</a>&nbsp;
        <a href="update.jsp?no=<%=no%>&page=<%=curpage%>">수정</a>&nbsp;
        <span id="delBtn" style="cursor:pointer">삭제</span>&nbsp;
        <a href="list.jsp?page=<%=curpage%>">목록</a>
       </td>
     </tr>
     <tr style="display:none" id="del">
       <td colspan="4" class="text-right">
       <form method="post" action="delete.jsp">
        <input type="hidden" name=no value="<%=no%>">
        <input type="hidden" name=page value="<%=curpage%>">
        비밀번호:<input type="password" name=pwd size=15 id="pwd" required>
        <button>삭제</button>
       </form>
       </td>
     </tr>
    </table>
   </center>
</body>
</html>