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
  <div class="container">
   <div class="row">
     <div class="text-center">
      <a href="../goods/goods_list.do?type=1" class="btn btn-lg btn-danger">전체상품</a>
      <a href="../goods/goods_list.do?type=2" class="btn btn-lg btn-success">베스트상품</a>
      <a href="../goods/goods_list.do?type=3" class="btn btn-lg btn-info">특가상품</a>
      <a href="../goods/goods_list.do?type=4" class="btn btn-lg btn-warning">신상품</a>
     </div>
   </div>
   <div style="height: 20px"></div>
   <div class="row">
   	<c:forEach var="vo" items="${list }">
   		<div class="col-md-3">
		    <div class="thumbnail">
		      <a href="../goods/goods_detail.do?no=${vo.no }&type=${type}">
		        <img src="${vo.goods_poster }" style="width:100%" title="${vo.goods_name }">
		        <div class="caption">
		          <p>${vo.goods_price }</p>
		        </div>
		      </a>
		    </div>
		  </div>
   	</c:forEach>
   </div>
    <div style="height: 20px"></div>
   <div class="row">
     <div class="text-center">
       <ul class="pagination">
          <c:if test="${startPage>1 }">
		    <li><a href="../goods/goods_list.do?page=${startPage-1 }&type=${type}">&lt;</a></li>
		  </c:if>
		  <c:forEach var="i" begin="${startPage }" end="${endPage }">
		    <li ${curpage==i?"class=active":""}><a href="../goods/goods_list.do?page=${i }&type=${type}">${i}</a></li>
		  </c:forEach>
		  <c:if test="${endPage<totalpage }">
		   <li><a href="../goods/goods_list.do?page=${endPage+1 }&type=${type}">&gt;</a></li>
		  </c:if>
		</ul>
     </div>
   </div>
  </div>

</body>
</html>