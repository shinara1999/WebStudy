<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략 가능
IMP.init("imp76004233");  // 예: imp00000000 (내 식별코드 쓰기)
let booktitle = '';
let sumprice='';
function requestPay() {
   console.log('clicked');
   console.log(booktitle);
   console.log(sumprice);
  // IMP.request_pay(param, callback) 결제창 호출
   IMP.request_pay({
       pg : 'html5_inicis', // version 1.1.0부터 지원. 
           /*
               'kakaopay':카카오페이,
               'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
               'nice':나이스,
               'jtnet':jtnet,
               'uplus':LG유플러스
           */
       pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
       merchant_uid : 'merchant_' + new Date().getTime(),
       name :booktitle,
       amount : sumprice, //$('#totalpay').text(), //.attr("data-price"), // 여기
       buyer_email : 'iamport@siot.do',
       buyer_name : '구매자이름',
       buyer_tel : '010-1234-5678',
       buyer_addr : '서울특별시 강남구 삼성동',
       buyer_postcode : '123-456',
       app_scheme : 'iamporttest' //in app browser결제에서만 사용 
   }, function(rsp) {
       if ( rsp.success ) {
           var msg = '결제가 완료되었습니다.';
           msg += '고유ID : ' + rsp.imp_uid;
           msg += '상점 거래ID : ' + rsp.merchant_uid;
           msg += '결제 금액 : ' + rsp.paid_amount;
           msg += '카드 승인번호 : ' + rsp.apply_num;
           booktitle='';
           sumprice='';
       } else {
           var msg = '결제에 실패하였습니다.';
           msg += '에러내용 : ' + rsp.error_msg;
           booktitle='';
           sumprice='';
           
           location.href="../mypage/bookPurchaseList.do"
       }
   });
}

$(function(){
      
   // 총금액 결제
      $('.buyBtn').click(function(){ 
    	  
    	  
    	   
    	  // 총액, 이미지, 책제목, 권당가격, 아이디
      		let total=$(this).attr("data-sumprice");
      		let userid=$('.buyBtn').attr("data-value");
      		let isbn=$(this).attr("data-isbn");
      		console.log(isbn);
      		let stno=$(this).attr("data-stno");
      		booktitle = $(this).attr("data-booktitle");
      		sumprice=$(this).attr("data-sumprice");
         requestPay()
         
      // 총금액 서버로 전송
         $.ajax({
             type: "POST",
             url: "../bookStore/cartbookPurchase_ok.do",  // 보내지는 위치
             data: {"userid":userid, "isbn":isbn, sumprice:total, "stno":stno},
             success: function(response) {
                 console.log("서버 전송 완료");
                 
                 
             },
             error: function(error) {
                 console.error("서버 전송 오류");
             }
         });
      })
      
})
</script>
</head>
<body>
<div style="width: 100%;padding: 0; margin-top: 20px">
      장바구니 <font color="red">${totalcount }</font>건
      <hr style="margin-top: 10px;">
   </div>
   <div class="row">
      <table class="table">
         <tr>
         	<th width="10%" class="text-center">장바구니번호</th>
            <th width="10%" class="text-center">이미지</th>
            <th width="35%" class="text-center">도서명</th>
            <th width="10%" class="text-center">가격</th>
            <th width="10%" class="text-center">전체금액</th>
            <th width="15%" class="text-center">ISBN</th>
            <th width="10%" class="text-center">구매</th>
          </tr>
          <c:forEach var="vo" items="${list }">
             <tr class="cartList">
                <!-- 도서명 또는 이미지 클릭 시 전체 내역 출력 -->
                <td class="text-center" id="ctstno" data-stno="${vo.stno }">${vo.stno }</td>
                <td class="text-center" id="ctimage" data-image="${vo.image }"><img src="${vo.image }" style="width: 30px;height: 45px"></td>
                <td id="ctname" data-booktitle="${vo.booktitle }">${vo.booktitle }&nbsp;&nbsp;<font color="red"></font></td>
                <td class="text-center" id="ctsaleprice" data-saleprice="${vo.saleprice }">${vo.saleprice }&nbsp;원</td>
                <td class="text-center"><font color="red" id="totalpay">${vo.sumprice }</font>&nbsp;원</td>
                <td class="text-center ctisbn" data-isbn="${vo.isbn }">${vo.isbn }</td>
                <td class="text-center">
      				<input type="button" class="btn btn-sm btn-success buyBtn" value="구매" data-value="${sessionScope.email }"
      				 data-isbn="${vo.isbn }" data-booktitle="${vo.booktitle }" data-stno="${vo.stno }" data-sumprice="${vo.sumprice }"
      				style="border: none;background-color: skyblue;line-height: normal;padding: 0 0.9em;height: 30px;border-radius: 1px"
      				>
      				<a href="../mypage/mypage_cartDelete.do?stno=${vo.stno }" class="btn btn-sm btn-warning">삭제</a>
    			</td>
             </tr>
          </c:forEach>
      </table>
   </div>
	  <nav class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../mypage/mypage_cart.do?page=${startPage-1 }">&lt; 이전</a></li>			
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${i==curpage?"class=current":""}><a href="../mypage/mypage_cart.do?page=${i }">${i }</a></li>			
			</c:forEach>
		  	<c:if test="${endPage<totalpage }">
				<li><a href="../mypage/mypage_cart.do?page=${endPage+1 }">다음 &gt;</a></li>			
			</c:if>
		</ul> 
	</nav>	
</body>
</html>