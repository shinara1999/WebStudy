<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<style type="text/css">

	.separator-line{
		margin:16px;
	}
	.material-symbols-outlined:hover{
		-webkit-transition:color .4s ease-out;
		color:red;
		cursor: pointer;
	}
	.col{
		width:130px;
	}
	.table1 tr{
		background:white !important;
	}
	.table1 tr>th{
		padding :11px;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).ready(function(){
			$(".material-symbols-outlined").click(function(){
				if(${sessionScope.email==null}){
					alert("로그인 후 이용해주세요.")
					return;
				}
				let id = $(".material-symbols-outlined").attr('value');
				let isbn = $(".isbn").text();
				$.ajax({
					type:"post",
					url:"../searchBook/LikeCount.do",
					data:{"id":id,"isbn":isbn},
					success:function(result){
						let json = JSON.parse(result);
						$('#star_count').text(json[0].cnt);
						if(json[0].status==1){
							$(".material-symbols-outlined").css("color","red");
							$(".material-symbols-outlined").text("heart_plus");
						}
						else{
							$(".material-symbols-outlined").css("color","");
							$(".material-symbols-outlined").text("favorite");
							  
						}
					}
				})
			})
			
			$('#reserve').click(function(){
				if(${sessionScope.email==null}){
					alert("로그인 후 이용해주세요.");
					return;
				}
				let id = $(".material-symbols-outlined").attr('value');
				let isbn = $(".isbn").text();
				let type = $('.reservecheck').text();
				$.ajax({
					type:"post",
					url:"../searchBook/bookreserve_ok.do",
					data:{"id":id,"isbn":isbn},
					success:function(res){
						if(res===""){
							//예약이 등록 안되있는 상태 예약
							if(confirm("도서를 예약하시겠습니까?")==true){
								$.ajax({
									type:"post",
									url:"../searchBook/bookreserve.do",
									data:{"id":id,"isbn":isbn},
									success:function(res){
										alert("예약이 완료되었습니다.");
										location.href = "../searchBook/alqDetail_before.do?isbn="+isbn;
									}
								})
							}
							else{
								return;
							}
						}
						else if(res==='y'){
							//반납이 필요한 상태
							if(confirm("도서를 반납하시겠습니까?")==true){
								$.ajax({
									type:"post",
									url:"../searchBook/bookreserve_return.do",
									data:{"id":id,"isbn":isbn},
									success:function(res){
										alert("도서가 반납이 되었습니다.");
										location.href = "../searchBook/alqDetail_before.do?isbn="+isbn;
									}
								})
							}
						}
						else{
							//예약이 이미 되있는 상태 취소 등록
							if(confirm("도서가 이미 예약되어있습니다.\n취소하시겠습니까?")==true){
								$.ajax({
									type:"post",
									url:"../searchBook/bookreserve_cancel.do",
									data:{"id":id,"isbn":isbn},
									success:function(res){
										alert("예약이 취소되었습니다.");
										location.href = "../searchBook/alqDetail_before.do?isbn="+isbn;
									}
								})
							}
							else{
								return false;
							}
						}
					}
				})
			})
		});
	})
</script>
</head>
<body>
	<section id="one" class="wrapper" style="padding:3em 0;"><div class="inner">
			<h3>상세정보</h3>
			<hr>
			<div class="container mt-4" style="color:#0C243C;">
			<div class="back_page" style="background-color: #e2e2e2;text-align:left;padding-left:10px;">
				<a href="javascript:history.back()" style="text-decoration: none;"><font style="font-weight: bold;color:black;">&lt;</font><font style="font-weight: bold;color:#e72900;">검색 결과 돌아가기</font></a>
			</div>
			<div style="height:10px;">
			</div>
		<div class="row">
			<div class="col-md-3" style="width:20%">
				<img src="${vo.image }" class="img-fluid" alt="Book Image" style="width:100%;height:300px;">
			</div>
			<div class="col-md-9" style="width:80%">
				<div class="card">
					<div class="card-body" >
						<h1 style="font-size: 25px">${vo.booktitle }</h1>
						<p>${vo.bookauthor }</p>
			<table class="table1" style="border-collapse: unset;">
				<tr>
					<th>자료유형</th>
					<td style="text-align:left;">${vo.bookdtype }</td>
				</tr>
				<tr>
					<th>개인저자</th>
					<td style="text-align:left;">${vo.bookperson }</td>
				</tr>
				<tr>
					<th>서명/저자사항</th>
					<td style="text-align:left;">${vo.booksign }</td>
				</tr>
				<tr>
					<th>발행사항</th>
					<td style="text-align:left;">${vo.bookpublisher }</td>
				</tr>
			</table>
			<div style="text-align:right;">
                  <a href="../bookStore/bookPurchase.do?isbn=${vo.isbn }" class='btn btn-primary'>구매하기</a>
            </div>
            <hr class="separator-line">

			<div class="row text-center my-3" style="display:inline-block;">
                <div class="col">
                
                  <span class="material-symbols-outlined" style="font-size:30px;<c:if test="${status!=0}">color:red;</c:if>" value="${sessionScope.email }"><c:if test="${status==0}">favorite</c:if><c:if test="${status!=0}">heart_plus</c:if></span>
                  <p id="star_count">${star_count }</p>
                </div>
                
                <div class="col">
                  <img src="https://cdn-icons-png.flaticon.com/128/2/2273.png" style="height:30px;">
                  <p class="isbn">${vo.isbn }</p>
                </div>  
                <div class="col">
                  <img src="https://cdn-icons-png.flaticon.com/128/747/747310.png" style="height:30px;">
                  <p>${vo.bookdate }</p>
                </div>
                <div class="col">
                  <img src="https://cdn-icons-png.flaticon.com/128/814/814587.png" style="height:30px;">
                  <p>${vo.bookpublisher }</p>
                </div>
                <div class="col">
                  <img src="https://cdn-icons-png.flaticon.com/128/2702/2702134.png" style="height:30px;">
                  <p>${vo.bookdtype }</p>
                </div>
            </div>
					</div>
				</div>
      </div>
		</div>
		<hr class="separator-line">
		<table class="table">
			<tr>
				<th style="text-align:center">등록번호</th>
				<th style="text-align:center">청구기호</th>
				<th style="text-align:center">자료실/서가</th>
				<th style="text-align:center">도서상태</th>
				<th style="text-align:center">반납예정일</th>
				<th style="text-align:center">예약/신청</th>
			</tr>
			<tr>
				<td>${vo.bookaccessionno }</td>
				<td>${vo.bookcallnum }</td>
				<td>${vo.booklocation }</td>
				<td>
					<c:if test="${vo.brvo.status==null }"><font class="reservecheck" style="color:skyblue;">대출가능</font></c:if>
					<c:if test="${vo.brvo.status=='y' }"><font class="reservecheck" style="color:red;">대출중</font></c:if>
					<c:if test="${vo.brvo.status=='n' }"><font class="reservecheck" style="color:#74A16F;">예약진행중</font></c:if>
				</td>
				<td>
					<c:if test="${vo.brvo.enddate==null }"></c:if>
					<c:if test="${vo.brvo.enddate!=null }"><c:if test="${vo.brvo.status=='y' }">${vo.brvo.enddate }</c:if></c:if>
				</td>
				<td><input id="reserve" data-value="${sessionScope.email }" type="button" class="btn btn-sm" value="<c:if test="${reserveBtn=='y' }">반 납</c:if><c:if test="${reserveBtn=='' }">예약 신청</c:if><c:if test="${reserveBtn=='n' }">예약 취소</c:if>" style="border-radius: 10px !important;line-height: 0px !important;"></td>
			</tr>
		</table>
	</div>
	</div>
	</section>
</body>
</html>