<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/storestyle.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략 가능
let star_review = '';
IMP.init("imp76004233");  // 예: imp00000000 (내 식별코드 쓰기)
function requestPay() {
   console.log('clicked');
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
       name : $('#gd_name').text(),
       amount :  parseInt($('#totalpay').text().replace(/[^0-9]/g, ''), 10), //$('#totalpay').text(), //.attr("data-price"), // 여기
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
       } else {
           var msg = '결제에 실패하였습니다.';
           msg += '에러내용 : ' + rsp.error_msg;
           
           location.href="../mypage/bookPurchaseList.do"
       }
   });
}
// 총금액 계산
$(function(){
	let bcheck=false;
   $('#sel').change(function(){
      let count=$(this).val();
      let price=$('#saleprice').attr("data-price")
      let total=Number(count) * Number(price);
      $('#totalpay').text(total)
      console.log($('#totalpay').text());
      
      // 서버로 보낼 값 (id, name, admin), (booktitle, isbn, saleprice, (총액은 위에 있음))
      let userid=$('#buy').attr("data-value");
      let isbn=$('#bookisbn').text();
      
   // 총금액 결제
      $('#buy').click(function(){
         if(${sessionScope.email==null})
         {
            alert("로그인 후 이용해주세요.")
            return;
         }
         requestPay()
      // 총금액 서버로 전송
         $.ajax({
             type: "POST",
             url: "../bookStore/bookPurchase_ok.do",  // 보내지는 위치
             data: {"userid":userid, "isbn":isbn, sumprice:total}, //sumprice에 total값 저장
             success: function(response) {
                 console.log("서버 전송 완료");
                 
             },
             error: function(error) {
                 console.error("서버 전송 오류");
             }
         });
      })
      $('#cart').click(function(){
         if(${sessionScope.email==null})
         {
            alert("로그인 후 이용해주세요.")
            return;
         }
         // cart로 데이터 전송
         $.ajax({
             type: "POST",
             url: "../bookStore/cart_insert.do",  // 보내지는 위치
             data: {"userid":userid, "isbn":isbn, sumprice:total}, //sumprice에 total값 저장
             success: function(response) {
                 console.log("서버 전송 완료");
                 location.href="../mypage/mypage_cart.do"
             },
             error: function(error) {
                 console.error("서버 전송 오류");
             }
         });
      })
   })
   
   $('#reviewBtn').click(function(){
	   let id = $('#userid_data').val();
	   let reviewText = $('#reviewText').val();
	   if(id===''){
		   alert("로그인 후 이용해주세요");
		   return ;
	   }
	   if(star_review===''){
		   alert("별점을 입력해주세요");
		   return ;
	   }
	   if(reviewText===''){
		   alert("내용을 입력해주세요");
		   return ;
	   }
	   let isbn = $('#bookisbn').text();
	   $.ajax({
		   type:'post',
		   url:'../reply/reviewinsert.do',
		   data:{"typeno":0,"rscore":star_review,"isbn":isbn,"r_content":reviewText,"email":id},
		   success:function(res){
			   location.href="../bookStore/bookPurchase.do?isbn="+res;
		   },
		   error: function(error) {
               console.error("서버 전송 오류");
           }
	   })
   })
   $('.reviewmodify').click(function(){
	   if(bcheck===false){
		   let content = $(this).attr('data-content');
		   $('#data-change').html('<textarea id="change_text" style="width:100%;">'+content+'</textarea>');
		   $('.reviewchange').show();
		   $(this).val('취소');
		   bcheck=true;
	   }
	   else{
		   let content = $(this).attr('data-content');
		   $('#data-change').html(content);
		   $('.reviewchange').hide();
		   $(this).val('수정');
		   bcheck=false;
	   }
   })
   $('.reviewchange').click(function(){
	   let rno = $(this).attr('data-source');
	   let isbn = $('#bookisbn').text();
	   let r_content = $('#change_text').val();
	   $.ajax({
		   type:'post',
		   url:'../reply/reviewupdate.do',
		   data:{'rno':rno,'typeno':0,'isbn':isbn,'r_content':r_content},
		   success:function(res){
			   $('#data-change').html(res);
			   $('.reviewchange').hide();
			   $('.reviewmodify').val('수정');
			   bcheck=false;
		   }
	   })
   })
   $('.reviewdelete').click(function(){
	   let rno = $(this).attr('data-source');
	   let isbn = $('#bookisbn').text();
	   $.ajax({
		   type:'post',
		   url:'../reply/reviewdelete.do',
		   data:{'rno':rno,'typeno':0,'isbn':isbn},
		   success:function(res){
			   location.href="../bookStore/bookPurchase.do?isbn="+isbn;
		   }
	   })
   })
})

// 스크롤
$(".pruchaseBtn").click(function(event){
   event.preventDefault();
   x=$(this).attr("href");
   $("html, body").stop().animate({scrollTop : $(x).offset().top-0}, 1000, "easeInOutExpo");
})

//별점
function star_count(e){
	star_review = e.value;
	$('#star_number').text(e.value+".0"+"/5.0");
}
</script>
</head>
<body>
   <h2>도서 구매</h2>
   <hr>
   <div class="imgArea">
      <img src="${vo.image }" class="img-fluid" style="width:330px;height:530px;">
   </div>

   <div class="titleArea">
        <div class="titleArea_top">
           <span id="dtype" class="dtype">
            <span class="iconC_dtype"><em class="txt">${vo.bookdtype }</em></span></span>
           <div class="gd_titArea">
              <span id="gd_name">${vo.booktitle }</span>
              <span id="bookisbn">${vo.isbn }</span>
         </div>
         <ul id="purchaseUl">
            <span id="bookauthor">${vo.bookauthor }</span>
            <span id="divi">|</span>
            <span id="bookauthor">${vo.bookpublisher }</span>
            <span id="divi">|</span>
            <span id="bookdate">${vo.bookdate }</span>
         </ul>
         <span id="star">★★★★★</span>&nbsp;
         <span id="bold">4.5</span>
         <span id="count">(299)</span>
       </div>
    </div>
    
<div class="flex-container" id="bookpurchase_flex">
<div class="pricefullArea">
    <div class="priceArea">
      <ul id="priceUl">
        정가<del id="fixedprice">${vo.fixedprice }원</del>
        <br>
        판매가&nbsp;&nbsp;<span id="saleprice" data-price="${vo.saleprice }">${vo.saleprice }원</span>
       <span id="percent">(${100-(vo.saleprice/vo.fixedprice*100)}&nbsp;% 할인)</span>
     </ul>
    </div>
    
    <div class="bookinfoArea">
       <a class="purchasebtn" href="#bookinfo_scr"><span id="bookinfo">책소개</span></a>
       <a class="purchasebtn" href="#bookauthor_scr"><span id="bookinfo">저자소개</span></a>
       <a class="purchasebtn" href="#bookreview_scr"><span id="bookinfo">리뷰</span></a>
    </div>
    
    <table class="table" id="purchasetb">
      <tr>
              <td width="50%">
                 <select id="sel" class="input-sm">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                 </select>
                 전체금액<span id="totalpay"></span>&nbsp;&nbsp;원
            
              </td>
           </tr>
           <tr>
              <td width="50%">
                 <input type="button" value="장바구니" id="cart" data-value="${sessionScope.email }">

                 <input type="button" value="바로구매" id="buy" data-value="${sessionScope.email }">
                 <a href="javascript:history.back()"><input type="button" value="뒤로가기" id="backto"></a>
              </td>
           </tr>
        
   </table>
</div>

</div>
     
     <hr>
     <h2 id="bookinfo_scr">책소개</h2>
     <hr>
     <p style="margin-top:2%;width: 65%;height: 400px">${vo.bookinfo}</p>
     <h2 id="bookauthor_scr">저자소개</h2>
     <hr>
     <p style="margin-top:2%;width: 65%;height: 400px">${vo.authorinfo}</p>
   <div class="bookpurchase_review">
   <h2 id="bookreview_scr">도서리뷰</h2>
     <hr>
    
     
<!-- ##### 별점 그래프 영역 시작 ##### -->
<div class="star-rating space-x-4 mx-auto">
   <input type="radio" id="5-stars" name="rating" value="5" v-model="ratings" onclick="star_count(this)"/>
   <label for="5-stars" class="star pr-4">★</label>
   <input type="radio" id="4-stars" name="rating" value="4" v-model="ratings" onclick="star_count(this)"/>
   <label for="4-stars" class="star">★</label>
   <input type="radio" id="3-stars" name="rating" value="3" v-model="ratings" onclick="star_count(this)"/>
   <label for="3-stars" class="star">★</label>
   <input type="radio" id="2-stars" name="rating" value="2" v-model="ratings" onclick="star_count(this)"/>
   <label for="2-stars" class="star">★</label>
   <input type="radio" id="1-star" name="rating" value="1" v-model="ratings" onclick="star_count(this)"/>
   <label for="1-star" class="star">★</label>
</div>
<div id="star_number">
   0.0/5.0
</div>
     <section id="one" class="wrapper" style="padding:3em 0;"><div class="inner">
		<div class="flex flex-3">
			<table id="review_show" class="table" style="margin:0px;width:100%;height:100%">
				<c:forEach var="rvo" items="${rvo }">
					<tr>
						<td width=100%>
							${rvo.userid}(${rvo.dbday })
						</td>
	      			</tr>
	      			<tr>
	      				<td id="data-change">${rvo.r_content }</td>
	      			</tr>
	      			<c:if test="${sessionScope.email==rvo.userid }">
		      			<tr>
		      				<td id="reviewbtnall">
								<input type=button value="삭제" class="btn btn-xs btn-danger reviewdelete" data-source="${rvo.rno }" style="float:right;">
								<input type=button value="수정" class="btn btn-xs btn-danger reviewmodify" data-source="${rvo.rno }" data-content="${rvo.r_content }" style="float:right;margin-right:5px;">
								<input type=button value="수정" class="btn btn-xs btn-danger reviewchange" data-source="${rvo.rno }" data-content="${rvo.r_content }" style="float:right;margin-right:5px;display:none">
							</td>
						</tr>
					</c:if>
      			</c:forEach>
        		<tr>
         			<td style="padding:0px;" colspan=2>
          				<form method=post>
          					<input type="hidden" id="userid_data" value="${sessionScope.email }">
            				<textarea rows="5" cols="40" id="reviewText" style="width:100%"></textarea>
          				</form>
         			</td>
        		</tr>
      		</table>
      		<input type=button value="리뷰쓰기 >" class="btn btn-xs btn-danger" id="reviewBtn" style="width:100%;float:right;margin-left:90%;">
      	</div>
      	</div>
      </section>
      </div>
   </div>
   <div style="clear:both;padding-bottom: 20px;"></div>
</body>
</html>