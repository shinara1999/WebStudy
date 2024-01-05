<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="wrapper row3">
  		<main class="container clear"> 
  		<h2 class="sectiontitle">상세보기</h2>
  		<table class="table">
     <tr>
       <td width=40% class="text-center" rowspan="8">
        <img style="width:100%" id="poster" src="${vo.poster }">
       </td>
       <td colspan="2">
        <h3 id="name">${vo.name }&nbsp;<span style="color: orange">${vo.score }</span></h3>
       </td>
     </tr>
     <tr>
       <th width=15% class="text-left">업종</th>
       <td width=45% id="type">${vo.type }</td>
     </tr>
     <tr>
       <th width=15% class="text-left">전화</th>
       <td width=45% id="phone">${vo.phone }</td>
     </tr>
     <tr>
       <th width=15% class="text-left">주소</th>
       <td width=45% id="address">${vo.address }</td>
     </tr>
     <tr>
       <th width=15% class="text-left">테마</th>
       <td width=45% id="theme">
       	<ul>
       		<c:forTokens items="${vo.theme }" delims="," var="t">
       			<li>${t }</li>
       		</c:forTokens>
       	</ul>
       </td>
     </tr>
     <tr>
       <th width=15% class="text-left">영업시간</th>
       <td width=45% id="time">${vo.time }</td>
     </tr>
     <tr>
       <th width=15% class="text-left">가격대</th>
       <td width=45% id="price">${vo.price }</td>
     </tr>
     <tr>
       <th width=15% class="text-left">좌석</th>
       <td width=45% id="seat">${vo.seat }</td>
     </tr>
     <tr>
       <td colspan="3" id="content">${vo.content }</td>
     </tr>
     <tr>
     	<td colspan="3" class="text-right">
     		<a href="#" class="btn btn-xs btn-danger">좋아요(0)</a>
     		<a href="#" class="btn btn-xs btn-success">찜하기</a>
     		<a href="#" class="btn btn-xs btn-info">예약하기</a>
     		<a href="javascript:history.back()" class="btn btn-xs btn-warning">목록</a>
     	</td>
     </tr>
    </table>
  		</main>
  	</div>
</body>
</html>