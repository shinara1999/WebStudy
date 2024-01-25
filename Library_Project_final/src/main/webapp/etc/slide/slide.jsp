<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/slide.css">
<script type="text/javascript" src="../assets/js/slide.js"></script>
<script type="text/javascript">
	$(function() {
		$('#link').click(function() {
			$(location).attr('href','../program/programList.do');
		});
		$('#link2').click(function() {
			$(location).attr('href','../searchBook/newarrival.do');
		});
		$('#link3').click(function() {
			$(location).attr('href','../searchBook/favorLoan.do');
		});
	});
</script>
<style type="text/css">
	section#two{
		background: #F0F8FF;
		clear: both;
	}
	section#two .slide{
		margin-top: 3rem;
	}
	section#two div.box.person{
		padding: 0;
		border-radius: 10px;
		overflow: hidden;
		background-color: #F5F5F5;
		border: 1.5px solid skyblue;
		padding-bottom: 15px;
	}
	section#two div.image{
		width: 100%;
	}
	section#two header{
		position: relative;
		margin-bottom: 3em;
	}
	section#two h2{
		color: black;
		font-weight: bold;
	}
	section#two hr{
		border: 1px solid skyblue;
		margin: 1em 0;
	}
	section#two #link,#link2,#link3{
		position: absolute;
		right: 0;
		bottom: 0;
		cursor: pointer;
		font-size: 50px;
		padding: 0;
	}
	section#two #link:hover,#link2:hover,#link3:hover{
		color: #ED8E6C;
	}
	section#two .slide-nav div{
		background-color: transparent;
	}
	section#two .slide-btn{
		color: lightgray;
	}
	section#two .flex .image img{
		position: relative;
	}
	section#two .flex .image .img_dark{
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: black;
		opacity: 0.1;
	}
	section#two .flex .image:hover .img_dark{
		display: block;
	}
</style>
</head>
<body>
<section id="two" class="wrapper style1 special" style="height: 700px;padding-top: 0;">
			<div class="slider">
        <div class="slide-viewer" style="height: 700px;">
            <div class="slide-group">
                <div class="slide slide-1">
                	<div class="inner">
					<header><h2>독서 문화 프로그램</h2>
						<span id="link">+</span>
						<hr>
					</header><div class="flex flex-4">
						<c:forEach var="vo" items="${programList }">
							
							<div class="box person">
							<a href="../program/programDetail_before.do?pno=${vo.pno }">
							<div class="image">
								<c:choose>
				      				<c:when test="${fn:startsWith(vo.poster,'https') }">
				        				<img src="${vo.poster }" alt="Person 1" width="168">
				        			</c:when>
				        			<c:otherwise>
				        				<img src="https://www.junggulib.or.kr/attachfile/editor/${vo.poster }" alt="Person 1" width="168">
				        			</c:otherwise>
			        			</c:choose>
								<%-- <img src="${vo.poster }" alt="Person 1" width="168"> --%>
								<span class="img_dark"></span></div>
							<h3 style="color: black;font-size: 16px;">${fn:length(vo.title) gt 13 ? fn:substring(vo.title,0,13)+='...' : vo.title }</h3>
							</a>
						</div>
						</c:forEach>
					</div>
				</div>
                </div>
                <div class="slide slide-2">
                	<div class="inner">
					<header><h2>새로 들어온 책</h2>
						<span id="link2">+</span>
						<hr>
					</header>
						<c:if test="${newArrivalCnt!=0}">
							<div class="flex flex-4">
							<c:forEach var="vo" begin="0" end="3" items="${newArrival }">
								<div class="box person">
								<a href="../searchBook/alqDetail.do?isbn=${vo.isbn }">
								<div class="image">
									<img src="${vo.image }" alt="Person 1" width="168">
									<span class="img_dark"></span></div>
								<h3 style="color: black;font-size: 16px;">${fn:length(vo.booktitle) gt 13 ? fn:substring(vo.booktitle,0,13)+='...' : vo.booktitle }</h3>
								</a>
							</div>
							</c:forEach>
							</div>
						</c:if>
						<c:if test="${newArrivalCnt==0 }">
							<div class="flex flex-4">
								<h3 class="text-center" style="color:black;">이번달 등록된 도서가 없습니다.</h3>
							</div>
						</c:if>
				</div>
                </div>
                <div class="slide slide-3">
                	<div class="inner">
					<header><h2>대출이 많은 책</h2>
						<span id="link3">+</span>
						<hr>
					</header>
						<c:if test="${favorLoancnt!=0}">
							<div class="flex flex-4">
							<c:forEach var="vo" begin="0" end="3" items="${favorLoan }">
								<div class="box person">
								<a href="../searchBook/alqDetail.do?isbn=${vo.isbn }">
								<div class="image">
									<img src="${vo.image }" alt="Person 1" width="168">
									<span class="img_dark"></span></div>
								<h3 style="color: black;font-size: 16px;">${fn:length(vo.booktitle) gt 13 ? fn:substring(vo.booktitle,0,13)+='...' : vo.booktitle }</h3>
								</a>
							</div>
							</c:forEach>
							</div>
						</c:if>
						<c:if test="${favorLoancnt==0 }">
							<div class="flex flex-4">
								<h3 class="text-center" style="color:black;">대출된 내역이 존재하지 않습니다.</h3>
							</div>
						</c:if>
				</div>
                </div>
            </div>
        </div>
        <div class="slide-nav">
            <div class="prev"></div>
            <div class="next"></div>
        </div>
        <div class="slide-buttons"></div>
    </div>
    </section>
</body>
</html>
<!-- 
	
 -->