<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/calendar.css">
<style type="text/css">
	/* 공지사항,보도자료 출력부분 */
	section#one {
		padding: 1.5em 0;
		background-color: white;
	}
	section#one .inner {
		/* float: left;
		margin-left: 15%; */
		margin-bottom: 30px;
	}
	section#one .notice{
		width: 35%;
		float: left;
		margin-right: 3%;
	}
	section#one .notice .tit p{
		margin-bottom: 0;
		display: inline-block;
		color: black;
		font-weight: bold;
		font-size: 20px;
		width: 85%;
	}
	section#one .notice .tit span{
		display: inline-block;
		padding-left: 10%;
		text-align: right;
	}
	section#one .notice .tit hr{
		border: 1.5px solid skyblue;
		margin: 3px 0;
	}
	section#one .notice ul{
		margin-bottom: 0;
	}
	section#one .notice .new_notice{
		background: url("../images/icon_noti.gif") no-repeat left top;
		padding-left: 60px;
		padding-bottom: 15px;
		margin-top: 15px;
		border-bottom: 1px solid lightgray;
	}
	section#one .notice .new_notice a{
		display: block;
		color: black;
		font-size: 20px;
		font-weight: bold;
		width: 95%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;		
	}
	section#one .notice .new_notice span{
		color: black;
	}
	section#one .notice .notice_list{
		list-style: none;
		padding: 0;
		padding-top: 15px;
	}
	section#one .notice .notice_list li{
		padding: 5px 0;
	}
	section#one .notice .notice_list li a{
		color: black;
		font-size: 16px;
		font-weight: bold;
		display: inline-block;
		width: 80%;
		padding-right: 5%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	section#one .notice .notice_list li span{
		color: black;
		font-size: 16px;
		display: inline-block;
		width: 15%;
		text-align: right;
		vertical-align: top;
	}
	/* ------------------ */
	/* 요약정보출력 */
	section#one #lib_summary_info{
		width: 24%;
		height: 30%;
		float: left;
	}
	section#one #lib_summary_info table{
		margin: 0;
		border-collapse: unset;
		color: black;
	}
	section#one #lib_summary_info td{
		background-color: white;
		padding-top: 0.3em;
		padding-bottom: 0.3em;
		font-size: 14px;
	}
	section#one #lib_summary_info td[rowspan="2"]{
		background: url("../images/banner_icon06_off.png") no-repeat top center;
		background-size: 65px 65px;
		background-color: white;
	}
	section#one #lib_summary_info .summary_date{
		font-size: 17px;
		font-weight: bold;
	}
	/* ------------------ */
	/*  */
	section#one #small_slide{
		width: 24%;
		height: 210px;
		float: left;
		background: url("../images/banner_sm1.png") no-repeat top left;
		background-size: cover;
		/* background-position: -15px 0; */
		margin-top: 10px;
		border: 1px solid skyblue;
		border-radius: 10px;
	}
	/* ------------------ */
	 a{
		text-decoration: none;
		color: black;
	}
	 div.inner{
		/* max-width: 90em; */
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		let t0_title=$('#t0 li:first-child() a').text()
		let t0_href=$('#t0 li:first-child() a').attr('href')
		let t0_date=$('#t0 li:first-child() span').text()
		$('#t0_recent a').text(t0_title)
		$('#t0_recent a').attr('href',t0_href)
		$('#t0_recent span').text(t0_date)
		
		
		let t1_title=$('#t1 li:first-child() a').text()
		let t1_href=$('#t1 li:first-child() a').attr('href')
		let t1_date=$('#t1 li:first-child() span').text()
		$('#t1_recent a').text(t1_title)
		$('#t1_recent a').attr('href',t1_href)
		$('#t1_recent span').text(t1_date)
	})
</script>
</head>
<body>
<section id="one" class="wrapper">
	<div class="inner">
		<div class="notice">
		<div class="tit">
				<p>공지사항</p><span><a href="../Board/notice.do?typeno=0">
					<img src="../images/plus_on.png">
				</a></span>
			</div>
			<div class="new_notice" id="t0_recent">
				<a href="#"></a><span></span>
			</div>
			<ul class="notice_list" id="t0">
				<c:set var="check" value="0"/>
				<c:forEach var="vo" items="${noticeMainData }">
				<c:if test="${vo.typeno==0 && check<5}">
					<c:set var="check" value="${check+1 }"/>
					<li>
						<a href="../Board/notice_detail.do?no=${vo.no }&typeno=${vo.typeno }">${vo.title }</a><span>${vo.dbday }</span>
					</li>
				</c:if>
				</c:forEach>
			</ul>
		</div>
		<div class="notice">
		<div class="tit">
				<p>보도자료</p><span><a href="../Board/notice.do?typeno=1">
					<img src="../images/plus_on.png">
				</a></span>
			</div>
			<div class="new_notice" id="t1_recent">
				<a href="#"></a><span></span>
			</div>
			<ul class="notice_list" id="t1">
				<c:set var="check" value="0"/>
				<c:forEach var="vo" items="${noticeMainData }">
				<c:if test="${vo.typeno==1 && check<5}">
					<c:set var="check" value="${check+1 }"/>
					<li>
						<a href="../Board/notice_detail.do?no=${vo.no }&typeno=${vo.typeno }">${vo.title }</a><span>${vo.dbday }</span>
					</li>
				</c:if>
				</c:forEach>
			</ul>
		</div>
		<div id="lib_summary_info">
			<table class="table">
				<tr>
					<td rowspan="2"></td>
					<td class="summary_date">
					<c:set var="now" value="<%=new java.util.Date()%>"/>
    					<fmt:formatDate value="${now}" pattern="yyyy년 MM월 DD일" />
					</td>
				</tr>
				<tr>
					<td>09:00&nbsp;~&nbsp;21:00</td>
				</tr>
				<tr>
					<td>대출&nbsp;:&nbsp;856권</td>
					<td>반납&nbsp;:&nbsp;751권</td>
				</tr>
				<tr>
					<td>예약&nbsp;:&nbsp;116권</td>
					<td>새로들어온책&nbsp;:&nbsp;${newArrivalCnt}권</td>
				</tr>
			</table>
		</div>
		<div id="small_slide"></div>
	</div>
			</section>
			<!-- slide include -->
			<jsp:include page="../etc/slide/slide.jsp"></jsp:include>
			<!-- Three --><section id="three" class="wrapper special"><div class="inner" style="max-width: 90em">
					<header class="align-center"><h2>행사달력 및 도서관 현황</h2>
						<p>행사 정보를 이곳에서 확인하세요</p>
					</header><div class="flex flex-5">
						<!-- 달력 -->
						<article><div class="calendar">
  							<span>TODAY</span>				<!-- 서버날짜 들어가게 변경 예정 -->

  							<div class="calendarbox">
    							<c:set var="now" value="<%=new java.util.Date()%>"/>
    							<span><fmt:formatDate value="${now}" pattern="E요일" /></span>
    							<span><fmt:formatDate value="${now}" pattern="MM월 dd일" /></span>
    							<span><fmt:formatDate value="${now}" pattern="yyyy" /></span>
  							</div>
 								</div>
							<!-- partial -->
  							<script  src="./script.js"></script>
  							<header>
  							<p class="calendar_id"><a href="../program/programList.do">+ 이달의 행사 정보 더보기</a></p>  <!-- # : 행사..? -->
  							<p class="calendar_id2"><a href="../Board/calendar.do">+ 이달의 휴관일 확인하기</a></p>	<!-- # : 달력 상세로 넘어가도록 -->
							</header>
						</article>
							
						<!-- 도서관 현황 -->
						<article>
							<table class="calendar_table">
								<tr>
									
									<td><img src="../images/direct01.gif">&nbsp;&nbsp;
									<a href="../libUse/libWayInfo.do">찾아오시는 길</a></td>
								</tr>
								<tr>
									<td><img src="../images/direct02.gif">&nbsp;&nbsp;
									<a href="">전화번호</a></td>
								</tr>
								<tr>
									<td><img src="../images/direct03.gif">&nbsp;&nbsp;
									<a href="https://lib.seoul.go.kr/apload/guide/libraryGuide_2023.pdf">이용 안내 리플릿</a></td>
								</tr>
								<tr>
									<td><img src="../images/direct04.gif">&nbsp;&nbsp;
									<a href="https://blog.naver.com/seoul_library">서울도서관 X 동네 책방</a></td>
								</tr>
							</table>
						</article>
						
						<article>
							<div class="homeinfo_banner">
  								<div class="homeinfo_slide">
            						<a href="https://web.seoul.flybookscreen.kr/"><img src="../images/homeinfo_banner_img1.jpg" width="470px" height="350px"></a>
        						</div>
        						<div class="homeinfo_slide">
            						<a href="http://culture.seoul.go.kr"><img src="../images/homeinfo_banner_img2.jpg" width="470px" height="350px"></a>
        						</div>
        						<div class="homeinfo_slide">
            						<a href="https://news.seoul.go.kr/gov/archives/511987"><img src="../images/homeinfo_banner_img3.jpg" width="470px" height="350px"></a>
        						</div>
 							</div>
 							<script>
						        let currentSlide = 0;
						        const slides = document.querySelectorAll('.homeinfo_slide');
						        const slideCount = slides.length;
						 
						        function showSlide(n) {
						            slides.forEach(slide => slide.style.display = 'none');
						            slides[n].style.display = 'block';
						        }
						 
						        function nextSlide() {
						            currentSlide = (currentSlide + 1) % slideCount;
						            showSlide(currentSlide);
						        }
						 
						        function prevSlide() {
						            currentSlide = (currentSlide - 1 + slideCount) % slideCount;
						            showSlide(currentSlide);
						        }
						 
						        document.addEventListener('DOMContentLoaded', () => {
						            showSlide(currentSlide);
						            setInterval(nextSlide, 3000); // 1초마다 자동 슬라이드
						        });
						    </script>
						</article>
						</div>
				</div>
			</section>
</body>
</html>