<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div#dateControl{
		padding: 1em 0;
		background: whitesmoke;
	}
	div#dateControl span{
		font-size: 1.5em;
		margin-left: 20px;
	}
	div#dateControl span.controlBtn{
		padding: 0.2em 0.6em;
		cursor: pointer;
	}
	div#dateControl span.controlBtn:hover {
		color: orange;
	}
	div#dateControl span#curMonth{
		font-size: 1.2em;
		font-weight: bold;
		margin-left: 5px;
	}
	table#calendarTable tbody td{
		height: 125px;
		background: white;
	}
	table#calendarTable tbody td .calendarProgramList{
		display: inline-block;
		width: 110px;
		height: 105px;
		position: absolute;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		padding-top: 18px;
		line-height: 1.6em;
		color: #333;
	}
	table#calendarTable tbody td .calendarTable_day{
		cursor: pointer;
	}
	table#calendarTable tbody td .calendarTable_day:hover{
		text-decoration: underline;
	}
	span#curDay{
		font-size: 1.5em;
	}
	table#curDayProgramList{
		margin-top: 15px;
		border-top: 1px solid #EEEEEE;
	}
	table#curDayProgramList tbody tr:nth-child(2n+1){
		background-color: white;
	}
	table#calendarTable tbody td .calendarProgramList a,
	table#curDayProgramList tbody a{
		color: #333;
		text-decoration: none;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		let req_data={}
		
		$('#prevBtn').click(function() {
			let date=$('#date').text()
			let year=date.substring(0,4)
			let month=date.substring(6,date.length-1)
			if(Number(month)===1){
				month=12
				year=year-1
			}else{
				month=month-1	
			}
			location.href='../Board/calendar.do?year='+year+'&month='+month
			/* req_data.year=year
			req_data.month=month
			ajax_code(req_data) */
		})
		$('#nextBtn').click(function() {
			let date=$('#date').text()
			let year=date.substring(0,4)
			let month=date.substring(6,date.length-1)
			if(Number(month)===12){
				month=1
				year=Number(year)+1
			}else{
				month=Number(month)+1
			}
			location.href='../Board/calendar.do?year='+year+'&month='+month
			/* req_data.year=year
			req_data.month=month
			ajax_code(req_data) */
		})
		$('#curMonth').click(function() {
			location.href='../Board/calendar.do'
			/* 현재 날짜를 가져온다 */
/* 			let curDate=new Date()
			let curYear=curDate.getFullYear()
			let curMonth=curDate.getMonth()+1
			req_data.year=curYear
			req_data.month=curMonth
			ajax_code(req_data) */
		})
		/* calendat ajax 
		/* function ajax_code(req_data){
			$.ajax({
				type: 'POST',
				url: '../Board/calendar_ajax.do',
				data: req_data,
				success:function(result){
					let res=JSON.parse(result)
					$('#date').text(res.year+'년 '+res.month+'월')
					/* calendarTable 
					let html=''
					/* curDayProgramList 
					let html2=''
					/* 1일부터 해당월의 마지막 날짜까지 반복문 
					for(let i=1;i<=res.lastday;i++){
						/* 1일에 한하여 칸을 띄운다 
						if(i===1){
							html+='<tr>'
							for(let j=1;j<=res.week;j++){
								html+='<td style="border-right: 0.5px solid #EEEEEE"></td>'
							}
						}
						/* week값이 6(토요일) 미만인 td칸에 대하여 style 설정 => 수직선 
						let style=(res.week+1>6)?'':'style="border-right: 0.5px solid #EEEEEE"'
						html+='<td '+style+'>'
						html+='<span class="calendarTable_day">'+i+'</span>'
						html+='<span class="calendarProgramList">'
						if(Number(res.week)===1){
							html+='휴관일<br>'
						}
						for(let j of res.arr){
							/* curDayProgramList 초기화*/
							/* if(Number(j.week)===Number(res.week)){
								/* 날짜 포맷 -> Date로 포맷하는 방법은?
								let d=j.edu2.substring(j.edu2.lastIndexOf('-')+1)
								if(d.substring(0,1)==='0'){
									d=d.substring(1)
								}
								if(i<=Number(d)){
										<a href="../program/programDetail_before.do?pno=${vo.pno }">
												<span data-title="${vo.title }" title="${vo.title }">${vo.title }</span>
											</a>
											<br>
									html+='<a href="../program/programDetail_before.do?pno='+j.pno+'">'
									html+='<span data-title="'+j.title+'" data-pno="'+j.pno+'" title="'+j.title+'">'+j.title+'</span>'
									html+='</a><br>'
								}
							} 
							let ymd=res.year+'-'+res.month+'-'+j
							
							/*
								<c:set var="concatenatedDate" value="${year}-${month}-${i}" />
								<c:set var="weeks" value="${fn:split(vo.week,',')}" />
								<c:forEach var="w" items="${weeks }">
								<c:if test="${w==concatenatedDate}">
									<a href="../program/programDetail_before.do?pno=${vo.pno }">
										<span data-title="${vo.title }" data-pno="${vo.pno }" title="${vo.title }">${vo.title }</span>
									</a>
									<br>
								</c:if>
								</c:forEach>
							
						}
						html+='</span>'
						html+='</td>'
						/* 프로그램 리스트 출력후 week+1후 week값이 6(토요일) 초과시 0으로 초기화
						res.week=Number(res.week)+1
						if(res.week>6){
							res.week=0
							html+='</tr><tr>'
						}
					}
					/* 달력의 마지막 부분 style 설정
					for(let i=res.week;i<=6;i++){
						if(i===6){
							html+='<td></td>'
						}
						if(i!==6){
							html+='<td style="border-right: 0.5px solid #EEEEEE"></td>'
						}
					}
					html+='</tr>'
					$('#calendarTable tbody').html(html)
				}
			})
		} */
		/* 날짜 클릭시 curDay 및 curDayProgramList의 style변경 */	
		$('#calendarTable').on('click','.calendarTable_day',function(){
			/* 모든 td의 background색상을 white로 지정 후 클릭한 요소에만 style 지정 */
			$('#calendarTable tbody td').css('background','white')
			let td=$(this).parent()
			td.css('background','#E6FFFF')
			let date=$('#date').text()
			let year=date.substring(0,4)
			let day=$(this).text()
			let month=date.substring(date.indexOf(' ')+1,date.length-1)
			$('#curDay').text('일정 '+year+'년 '+month+'월 '+day+'일')
			
			let cplist=$(this).next()
			/* default 지정, 값이 있을시 값을 넣어준다 */
			let html=''
			html+='<tr><td colspan="2" class="text-center">등록된 일정이 없습니다.</td></tr>'
			$('#curDayProgramList tbody').html(html)
			html=''
			cplist.find('span').each(function() {
				html+='<tr><td class="text-center">프로그램</td>'
				html+='<td><a href="../program/programDetail_before.do?pno='+$(this).attr('data-pno')+'">'+$(this).text()+'</a></td></tr>'
				$('#curDayProgramList tbody').html(html)
			})
							
		})
	})
</script>
</head>
<body>
	<div>
		<div class="board_title">
			<h2>일정 및 행사</h2>
			<hr>
		</div>
		<div id="dateControl">
			<span class="controlBtn" id="prevBtn">&lt;</span>
			<span id="date">${year }년 ${month }월</span>
			<span class="controlBtn" id="nextBtn">&gt;</span>
			<span class="controlBtn" id="curMonth">이번달</span>
		</div>
		<table class="table" id="calendarTable">
			<thead>
				<tr>
					<th class="text-center">일</th>
					<th class="text-center">월</th>
					<th class="text-center">화</th>
					<th class="text-center">수</th>
					<th class="text-center">목</th>
					<th class="text-center">금</th>
					<th class="text-center">토</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="1" end="${lastday }">
					<c:if test="${i==1 }">
						<tr>
							<c:forEach var="j" begin="1" end="${week }">
								<td style="border-right: 0.5px solid #EEEEEE"></td>
							</c:forEach>
					</c:if>
						<td ${week+1>6 ? "":"style='border-right: 0.5px solid #EEEEEE'" }>
							<span class="calendarTable_day">${i }</span>
							<span class="calendarProgramList">
								<c:if test="${week==1 }">
									휴관일<br>
								</c:if>
								<c:forEach var="vo" items="${cplist }">
									<%-- <c:if test="${vo.week==week }">
										<fmt:parseDate var="parseDate" value="${vo.edu2_str }" pattern="yyyy-MM-dd"/>
										<fmt:formatDate var="fomattedDate" value="${parseDate }" pattern="d"/>
										<c:if test="${i<=fomattedDate }">
											<a href="../program/programDetail_before.do?pno=${vo.pno }">
												<span data-title="${vo.title }" data-pno="${vo.pno }" title="${vo.title }">${vo.title }</span>
											</a>
											<br>
										</c:if>
									</c:if> --%>
									<c:set var="concatenatedDate" value="${year}-${month}-${i}" />
									<c:set var="weeks" value="${fn:split(vo.week,',')}" />
									<c:forEach var="w" items="${weeks }">
									<c:if test="${w==concatenatedDate}">
										<a href="../program/programDetail_before.do?pno=${vo.pno }">
											<span data-title="${vo.title }" data-pno="${vo.pno }" title="${vo.title }">${vo.title }</span>
										</a>
										<br>
									</c:if>
									</c:forEach>
								</c:forEach>
							</span>
						</td>
					<c:set var="week" value="${week+1 }"/>
					<c:if test="${week>6 }">
						<c:set var="week" value="0"/>
						</tr>
						<tr>
					</c:if>
				</c:forEach>
				<c:forEach var="i" begin="${week }" end="6">
					<c:if test="${i==6 }"><td></td></c:if>
					<c:if test="${i!=6 }">
						<td style="border-right: 0.5px solid #EEEEEE"></td>
					</c:if>
				</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="height: 20px;"></div>
	<span id="curDay">일정</span>
	<table class="table" id="curDayProgramList">
		<thead>
			<tr>
				<th width="20%" class="text-center">구분</th>
				<th width="80%" class="text-center">내용</th>
			<tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="2" class="text-center">
					등록된 일정이 없습니다.
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>