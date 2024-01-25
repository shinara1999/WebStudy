<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/sideBar.css">
<link rel="stylesheet" href="../assets/css/seat.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.seat_show>li{
		display:block;
		float:left;
		border:1px solid black;
		width:50%;
		text-align:center;
		height:40px;
		line-height:40px;
		font-size: 1.1em;
    	font-weight: 700;
    	padding:0px;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	let number = '';
	seat_number = '';
	function seat_find(e){
		seat_number = $(e).attr('data-source');
		$.ajax({
			type:"post",
			url:"../seatReserve/seatCheck.do",
			data:{"snum":seat_number},
			success:function(res){
				$('#use_other_seat').hide();
				if(res!='Y'){
					if(number===''){
						//번호가 처음 들어왔을 경우
						number = e;
						$(number).css('background-color','#2dcff8');
					}
					else if(number===e){
						//번호가 같을 경우 현재 색상 유지
						number = e;
						$(number).css('background-color','#2dcff8');
					}
					else{
						//번호가 다를 경우 이전 li 색상 변경 후 현재 li 색상 변경
						$(e).css('background-color','#2dcff8');
						$(number).css('background-color','#fd6d8e');
						number = e;
					}
				}
				else{
					$(number).css('background-color','#fd6d8e');
					$('#seat'+seat_number).css("background-color",'#2dcff8');
					let aval = $('#seat_available').attr('data-source')-1;
					$('#seat_available').text("이용 가능 좌석 "+aval);
					$('#seat_disavailable').text("사용 중인 좌석 "+(96-aval));
					number = '';
					$('#use_other_seat').show();
				}
			}
		})
	}
	$(function(){
		
		$('.seat_reserve_btn').click(function(){
			if($(this).attr('data-source')===''){
				alert("로그인 후 이용해주세요");
				return ;
			}
			let id = $(this).attr('data-source');
			if(id!=null){
				let compare = $('.seat_reserve_btn').attr("data-seat");
				if(compare!=''){
					seat_number = $('.seat_reserve_btn').attr("data-seat");	
				}
			}
			if(seat_number===''){
				alert("좌석을 선택해주세요");
				return;
			}
			$.ajax({
				type:"post",
				url:"../seatReserve/userResCheck.do",
				data:{"id":id},
				success:function(nowSeat){
					if(nowSeat==='0'){
						$.ajax({
							type:"post",
							url:"../seatReserve/seatCheck.do",
							data:{"snum":seat_number},
							success:function(res){
								if(res==='Y'){
									$('#seat'+seat_number).css("background-color",'#2dcff8');
									let aval = $('#seat_available').attr('data-source')-1;
									$('#seat_available').text("이용 가능 좌석 "+aval);
									$('#seat_disavailable').text("사용 중인 좌석 "+(96-aval));
									$('#use_other_seat').show();
								}
								else{
									if(confirm(seat_number+"번 자리를 예약하시겠습니까?")===true){
										$.ajax({
											type:"post",
											url:"../seatReserve/seatOK.do",
											data:{"id":id,"snum":seat_number},
											success:function(result){
												if(result==='OK'){
													alert("좌석 예약이 완료되었습니다.");
													number='';
													$('#seat'+seat_number).css("background-color",'#2dcff8');
													$('#seat'+seat_number).attr('disabled',true);
													let aval = $('#seat_available').attr('data-source')-1;
													$('#seat_available').text("이용 가능 좌석 "+aval);
													$('#seat_disavailable').text("사용 중인 좌석 "+(96-aval));
													let btn = document.querySelector(".seat_reserve_btn");
													btn.value = '퇴 실';
												}
											}
										})
									}
									else{
										return false;
									}
								}
							}
						})//ajax
					}
					else{
						if(confirm(nowSeat+"번 자리를 이용중입니다. 퇴실하시겠습니까?")===true){
							$.ajax({
								type:"post",
								url:"../seatReserve/seatCancel.do",
								data:{"id":id,"snum":nowSeat},
								success:function(cancel){
									alert("퇴실이 완료됐습니다.");
									location.href="../seatReserve/main.do";
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
			
	})
</script>
</head>
<body>
	<div class="col-sm-2">
		<div style="margin-top: 50px;">
			<div class="panel">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<p>도서관이용</p>
					</h3>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="../libUse/libTimeInfo.do">도서관 이용 시간</a></li>
					<li class="list-group-item"><a href="../libUse/libReserveInfo.do">대출.반납.연장.예약</a></li>
					<li class="list-group-item"><a href="../seatReserve/main.do">자리예약</a></li>
					<li class="list-group-item"><a href="../libUse/libWayInfo.do">찾아오시는길</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-sm-8">
		<section id="one" class="wrapper" style="padding:3em 0;">
			<div class="inner">
				<h4 style="color:black">자리예약</h4>
				<hr style="padding:2px;background: skyblue;">
				<div style="height:40px;">
					<ul class="seat_show">
						<li id="seat_available" data-source="${96-totalY }">
							이용 가능 좌석 ${96-totalY }
						</li>
						<li id="seat_disavailable" data-source="${totalY }">사용 중인 좌석 ${totalY }</li>
					</ul>
				</div>
				<div style="height:350px;">
					<div class="seat-arrangement" style="width:70%;">
						<ul class="seat-line" style="">
							<c:forEach var="vo" items="${list }">
							    <li id="seat${vo.sno}" class="seat" data-source="${vo.sno }" style="<c:if test="${vo.reserve=='Y'}">background-color:#2dcff8</c:if>" onclick="seat_find(this)">
							       	<label for="${vo.sno }" style="padding-left:0px !important;">${vo.sno }</label>
							    </li>
							</c:forEach>
						</ul>
					 </div>
				</div>
				<div id="use_other_seat" class="text-right" style="display: none;">
					<h6 style="color:red;margin-top:0px;">해당 좌석은 현재 사용중입니다.</h6>
				</div>
				<div class="text-right">
					<input class="seat_reserve_btn" data-source="${sessionScope.email }" data-seat="<c:if test="${userbtn_change!=0 }">${main_res_data }</c:if>" type="button" value="<c:if test="${userbtn_change==0 }">자리 예약</c:if><c:if test="${userbtn_change!=0 }">퇴 실</c:if>" style="background-color: #008CBA;border-radius: 10px;">
				</div>
			</div>
		</section>
	</div>
	<div class="col-sm-2">
	</div>
	<div style="clear:both;"></div>
</body>
</html>