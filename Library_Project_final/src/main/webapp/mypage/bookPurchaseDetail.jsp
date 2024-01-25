<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width: 100%;padding: 0; margin-top: 20px">
      도서구매내역(상세) <font color="red">1</font>개&nbsp;&nbsp;&nbsp;
      <font color="orange">(주문번호:${vo.orderNum })</font>
      <hr style="margin-top: 10px;">
   </div>
   <div class="row">
   	<table class="table">
         <tr>
            <th width="15%" class="text-center">이미지</th>
            <th width="40%" class="text-center">도서명</th>
            <th width="15%" class="text-center">가격</th>
            <th width="15%" class="text-center">주문일자</th>
          </tr>
          
             <tr>
                <td class="text-center"><img src="${vo.image }" style="width: 30px;height: 45px"></td>
                <td>${vo.booktitle }</td>
                <td class="text-center">${vo.saleprice }&nbsp;원</td>
                <td class="text-center">${vo.orderDate }</td>
             </tr>
     </table>     
   </div>
</body>
</html>