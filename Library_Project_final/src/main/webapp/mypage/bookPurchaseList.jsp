<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div style="width: 100%;padding: 0; margin-top: 20px">
      도서구매내역 <font color="red">${totalcount }</font>건
      <hr style="margin-top: 10px;">
   </div>
   <div class="row">
      <table class="table">
         <tr>
            <th width="10%" class="text-center">주문번호</th>
            <th width="15%" class="text-center">이미지</th>
            <th width="40%" class="text-center">도서명</th>
            <th width="10%" class="text-center">가격</th>
            <th width="10%" class="text-center">전체금액</th>
            <th width="15%" class="text-center">주문일자</th>
          </tr>
          <c:forEach var="vo" items="${list }">
             <tr>
                <!-- 도서명 또는 이미지 클릭 시 전체 내역 출력 -->
                <td class="text-center">${vo.orderNum }</td>
                <td class="text-center"><a href="../searchBook/alqDetail_before.do?isbn=${vo.isbn }"><img src="${vo.image }" style="width: 30px;height: 45px"></a></td>
                <td><a href="../searchBook/alqDetail_before.do?isbn=${vo.isbn }">${vo.booktitle }</a>&nbsp;&nbsp;<font color="red"></font></td>
                <td class="text-center">${vo.saleprice }&nbsp;원</td>
                <td class="text-center"><font color="red">${vo.sumprice }</font>&nbsp;원</td>
                <td class="text-center">${vo.orderDate }</td>
                
             </tr>
          </c:forEach>
      </table>
   </div>
    <nav class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../mypage/bookPurchaseList.do?page=${startPage-1 }">&lt; 이전</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="../mypage/bookPurchaseList.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../mypage/bookPurchaseList.do?page=${endPage+1 }">다음 &gt;</a></li>			
			</c:if>
		</ul> 
	</nav>	
</body>
</html>