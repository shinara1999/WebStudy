<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
<script type="text/javascript">
function calc(){
	let price=document.querySelector("#price").getAttribute("data-price");
	let count=document.querySelector("#sel").value;
	let total=price*count;
	document.querySelector("#total").innerText=total;
	/*
		이벤트 : 사용자가 태그에 행위를 가했을 경우 발생
		  button =>
			<input type=button>
			<input type=image>
			<input type=submit>
			<button> =============> click
		  select =>
			===========> change
		  모든 태그에 적용 : mouseover , mouseout
		  				 mousedown , mouseup , focus
		  입력창 ===> keyup , keydown
	*/
}
</script>
</head>
<body>
<div class="container">
	<div class="row">
		  <table class="table">
			  <tr>
				  <td width="35%" align="center" rowspan="9">
					  <img src="https://recipe1.ezmember.co.kr/cache/data/goods/21/12/51/1000024877/1000024877_detail_040.jpg" id="image">
				  </td>
				  <td width="65%" align="center">
					  <span id="title">
					  	부샤드 초콜릿 (씨솔트,밀크,다크) 초콜릿 모음전
					  </span>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <span id="sub">달달한 초콜렛 , 초콜렛계의 에르메스!</span>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <span id="percent">0%</span>&nbsp;
					  <span id="price" data-price="4200">4,200원</span>
					  <p>
						  <del id="psub">4,200원</del>
					  </p>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <span id="label">첫구매할인가</span>
					  <span id="price2">3,990원</span>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <span id="star">★★★★★</span>&nbsp;
					  <span id="bold">4.9</span>
					  <span id="count">(642)</span>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <img src="https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png">
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <span id="delevery">3,000원 (30,000원이상 무료배송)</span>
				  </td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <select id="sel" onchange="calc()">
						  <option value="1">1개</option>
						  <option value="2">2개</option>
						  <option value="3">3개</option>
						  <option value="4">4개</option>
						  <option value="5">5개</option>
					  </select>
				  </td>
			  </tr>
			  <tr>
			  	<td width="65%" class="text-right">
			  	주문금액&nbsp;&nbsp;
			  	<span style="color: green;font-size:20px" id="total"></span>원
			  	</td>
			  </tr>
			  <tr>
				  <td width="65%">
					  <input type="button" value="장바구니" id="cart">
					  <input type="button" value="바로구매" id="buy">
				  </td>
			  </tr>
		  </table>
	  </div>
</div>
</body>
</html>