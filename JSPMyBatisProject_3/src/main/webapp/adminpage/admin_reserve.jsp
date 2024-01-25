<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h3 class="text-center">맛집 예약 목록</h3>
 <table class="table">
   <tr>
    <th class="text-center">번호</th>
    <th class="text-center"></th>
    <th class="text-center">업체명</th>
    <th class="text-center">예약일</th>
    <th class="text-center">시간</th>
    <th class="text-center">인원</th>
    <th class="text-center"></th>
   </tr>
   <c:forEach var="vo" items="${list }">
   <tr>
    <td class="text-center">${vo.no }</td>
    <td class="text-center">${vo.id }</td>
    <td class="text-center">
     <img src="https://www.menupan.com${vo.fvo.poster }" style="width: 30px;height: 30px">
    </td>
    <td>${vo.fvo.name }</td>
    <td class="text-center">${vo.day }</td>
    <td class="text-center">${vo.time }</td>
    <td class="text-center">${vo.inwon }</td>
    <td class="text-center">
      <c:if test="${vo.ok==1 }">
        <span class="btn btn-sm btn-default">승인완료</span>
      </c:if>
      <c:if test="${vo.ok==0 }">
        <a href="../adminpage/admin_reserve_ok.do?no=${vo.no }" class="btn btn-sm btn-danger">승인대기</a>
      </c:if>
    </td>
   </tr>
   </c:forEach>
 </table>
</body>
</html>