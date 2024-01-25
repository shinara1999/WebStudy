<%--다른 도서관 책 관련 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	body div{
		font-family:NanumBarunGothic,sans-serif;
	}
	input{
		font-style: ;
	    font-variant-ligatures: ;
	    font-variant-caps: ;
	    font-variant-numeric: ;
	    font-variant-east-asian: ;
	    font-variant-alternates: ;
	    font-variant-position: ;
	    font-weight: ;
	    font-stretch: ;
	    font-size: ;
	    font-family: ;
	    font-optical-sizing: ;
	    font-kerning: ;
	    font-feature-settings: ;
	    font-variation-settings: ;
	    text-rendering: auto;
	    color: fieldtext;
	    letter-spacing: normal;
	    word-spacing: normal;
	    line-height: normal;
	    text-transform: none;
	    text-indent: 0px;
	    text-shadow: none;
	    display: inline-block;
	    text-align: start;
	    appearance: auto;
	    -webkit-rtl-ordering: logical;
	    cursor: text;
	    background-color: field;
	    margin: 0em;
	    padding: 1px 0px;
	    border-width: 2px;
	    border-style: inset;
	    border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
	    border-image: initial;
	    padding-block: 1px;
	    padding-inline: 2px;
	}
	#SearchOtherLib{
		padding: 25px 0 10px;
	    border-top: 1px solid #e3e3e3;
	    border-bottom: 1px solid #e3e3e3;
	}
	#SearchOtherLib fieldset .excel{
		display: inline-block;
	    margin-top: 20px;
	    padding-left: 25px;
	    background: url("https://lib.seoul.go.kr/image/ko/solution/common/ico/excel.png") no-repeat 0 50%;
	    color: #085b2c;
	}
	#SearchOtherLib fieldset{
		border: none;
	    margin: 0;
	    padding: 0;
	    font: inherit;
	}
	#SearchOtherLib fieldset>div ul{
		list-style:none;
	}
	#SearchOtherLib fieldset>div ul li{
		list-style:none;
		margin-top: 10px;
	}
	#SearchOtherLib fieldset>div ul li input[type="text"]{
		margin: 0;
    	padding: 0;
    	border: 1px solid #d1d0cf;
    	vertical-align: middle;
    	width: 188px;
    	height: 33px;
		padding-block: 1px;
    	padding-inline: 2px;
    	-webkit-box-shadow: none;
	    -webkit-appearance: none;
	    -webkit-border-radius: 0;
	    background:white;
	    display : unset !important;
	}
	#SearchOtherLib fieldset>div input[type="submit"]{
		width: 80px;
	    margin-left: 5px;
	    border: 1px solid #f5c238;
	    background-color: #fece4c;
	    color: #383838 !important;
	    font-size: 14px !important;
	    height: 78px !important;
	    vertical-align: middle !important;
	    -webkit-appearance: none;
    	border-radius: 0;
    	margin: 0;
    	padding: 0;
    	white-space: pre;
	    padding-block: 1px !important;
	    padding-inline: 6px !important;
	    text-align: center !important;
	    box-sizing: border-box !important;
	    -webkit-text-size-adjust: none;
	    word-wrap: break-word !important;
	    line-height:0 !important;
	    font-family: 'Noto Sans KR', sans-serif !important;
	}
	#SearchOtherLib fieldset>div ul li span{
		display: inline-block !important;
	    width: 64px;
	    margin-right: 15px !important;
	    color: #383838 !important;
	    font-family: NanumSquareR, sans-serif;
	    font-size: 8px;
	    text-align: right !important;
	    border: none;
	    margin: 0;
	    padding: 0;
	    font: inherit;
	}
	.searchLibList > ul > li:first-child {
    	border-top: none;
	}
	.searchLibList {
	    height: 696px;
	    overflow-y: scroll;
	    overflow-x: hidden;
	}
	.SearchLibList > ul > li{
		position: relative;
	    margin-right: 8px;
	    padding: 20px 0 20px 14px;
	}
	.searchLibList > ul{
    	display: block;
	    list-style-type: disc;
	    margin-block-start: 1em;
	    margin-block-end: 1em;
	    margin-inline-start: 0px;
	    margin-inline-end: 0px;
	}
	.searchLibList > ul > li dl {
    	padding-right: 0;
	}
	.searchLibList > ul > li dl {
	    display: block;
	    margin-block-start: 1em;
	    margin-block-end: 1em;
	    margin-inline-start: 0px;
	    margin-inline-end: 0px;
	}
	
	.searchLibList > ul > li dl dd ul li span.detail {
	    display: block;
	    padding-left: 80px;
	    color: #828282;
	    font-size: 14px;
	}
	.searchLibList li {
    	text-align: -webkit-match-parent;
    	list-style: none;
    	padding-left:0 !important;
	}
	.searchLibList ul{
		padding-left:0 !important;
	}
	.searchLibList > ul > li div.searchLibBtn .searchLibBtn1 {
    	background: url(https://lib.seoul.go.kr/image/ko/solution/local/searchLibLink1.png) no-repeat 0 0;
	}
	.searchLibList > ul > li div.searchLibBtn a {
	    display: inline-block;
	    margin: 0 5px;
	    padding-left: 25px;
	    text-align: center;
	    color: #0093d0;
	    text-decoration: underline;
	}
	.searchLibList > ul > li dl dd ul li span.detail.tel {
	    color: #ec4900;
	}
	.searchLibList > ul > li dl dd ul li span.title {
	    position: absolute;
	    top: 0;
	    left: 0;
	    width: 80px;
	    background: url(https://lib.seoul.go.kr/image/ko/solution/local/bullet1.gif) no-repeat 2px 5px;
	    padding-left: 10px;
	    color: #515151;
	    font-size: 14px;
	}
	.searchLibList > ul > li dl dd ul li span {
	    display: inline-block;
	}
	dl {
	    display: block;
	    margin-block-start: 1em;
	    margin-block-end: 1em;
	    margin-inline-start: 0px;
	    margin-inline-end: 0px;
	}
	dd {
	    display: block;
	    margin-inline-start: 40px;
	}
	.searchLibList ul ul {
	    list-style-type: circle;
	    margin-block-start: 0px;
	    margin-block-end: 0px;
	}
	.searchLibList > ul > li dl dd ul li {
	    position: relative;
	    margin-top: 10px;
	    font-size: 14px;
	}
	.paging{
		text-align:center;
	}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
	$(function(){
		$(".bookindex").click(function(){
			let name = $(this).attr("data-name");
			let booktitle = $(this).attr("data-value");
			$.ajax({
				type:"post",
				url:"../otherLib/bookindex.do",
				data:{"name":name,"booktitle":booktitle},
				success:function(result){
					let json = JSON.parse(result);
					let html = "<tr><th width=10% class='text-center'>수량</th><th width=70% class='text-center'>책제목</th><th width=20% class='text-center'>ISBN</th></tr>";
					for(let i =0;i<json.length;i++){
						html+="<tr><td width=10% class='text-center'>"+json[i].cnt+"</td>"
									+"<td width=70% class='text-left'>"+json[i].booktitle+"</td>"
									+"<td width=20% class='text-center'>"+json[i].isbn+"</td></tr>";
					}
					$("#otherlibBookdetail").html(html);
					$( "#dialog" ).dialog({
						width:750,
						height:580,
						modal:true
					});
				}
			})
		})
	})
</script>
</head>
<body>
	<section id="one" class="wrapper" style="padding:3em 0;">
		<div class="inner">
			<h4 style="color:black;font-weight: bold;">도서관 찾기</h4>
			<hr style="padding:2px;background: skyblue;">
			<div class="flex flex-3">
				<div class="col-md-5" style="border:1px solid black;">
					<form id="SearchOtherLib" method="post" action="../otherLib/obs.do">
						<fieldset>
							<div style="float:left;padding-right:5px;">
								<ul>
									<li>
										<span>도서관명</span>
										<input type="text" name="libname" value="${name }">
										<input type="hidden" name="page" value="1"> 
										<!-- <input type="submit" value="검 색" class="SearchBtn"> -->
									</li>
									<li>
										<span>서적명</span>
										<input type="text" name="libbook" value="${book }">
										<input type="hidden" name="page" value="1">
									</li>
										<!-- <input type="submit" value="검 색" class="SearchBtn" style="float:left;"> -->
								</ul>
							</div>
							<div style="float:left;padding:10px 10px 0px 10px; height:87px;">
								<input type="submit" value="검 색" class="SearchBtn">
							</div>
							<div style="clear:both;"></div>
							<a href="#" class="excel">Excel</a>
							<span style="margin-top: 20px;padding-right: 5px;float: right;display:inline-block"><font style="font-weight: bold">총 ${total }건</font></span>
						</fieldset>
					</form>
					<div class="searchLibList">
						<ul>
							<c:forEach var="data" items="${list }">
								<li>
									<dl>
										<dt><a class="OhterLibName" href="#!" data-addr="${data.address }">${data.name }</a></dt>
										<dd>
											<ul>
												<li>
													<span class="title">이용시간</span>
													<span class="detail">${data.time }</span>
												</li>
												<li>
													<span class="title">휴관일</span>
													<span class="detail">${data.closetime}</span>
												</li>
												<li>
													<span class="title">전화번호</span>
													<span class="detail tel">${data.tel }</span>
												</li>
												<li>
													<span class="title">홈페이지</span>
													<span class="detail">${data.homepage }</span>
												</li>
												<li>
													<span class="title">주소</span>
													<span class="detail">${data.address }</span>
												</li>
												<c:if test="${not empty fn:trim(book)}">
													<li>
														<span class="title">도서목록</span>
														<span class="detail"><input class="bookindex" type="button" class="btn btn-sm btn-primary" style="height:20px;line-height:0px !important;" value="도서 보기" data-name="${data.name }" data-value="${book }"></span>
													</li>
												</c:if>
											</ul>
										</dd>
									</dl>
									<div class="searchLibBtn">
										<a href="https://lib.seoul.go.kr/lbrrybbs/list/14?lbrry_seq_no=${data.no }" class="searchLibBtn1">알림마당</a>
									</div>
								</li>
								<hr>
							</c:forEach>
							<li>
								<div class="paging">
									<span>
										<c:if test="${startPage>1 }">
											<a class="start" href="../otherLib/obs.do?page=${startPage-1}">&lt;</a>
										</c:if>
										<c:forEach var="i" begin="${startPage }" end="${endPage }">
											<span style="${i==curpage?'padding: 0 10px;text-decoration:underline':'padding: 0 10px;'}"><a class="cur" href="../otherLib/obs.do?page=${i }&libname=${name}&libbook=${book}" style="${i==curpage?'color:#000':'color:#747474'}">${i }&nbsp;</a></span>
										</c:forEach>
										<c:if test="${totalpage>endPage}">
											<a class="end" href="../otherLib/obs.do?page=${endPage+1 }">&gt;</a>
										</c:if>
									</span>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7" style="border:1px solid black;padding:0 !important;">
					<jsp:include page="${mapinfo_jsp }"></jsp:include>
				</div>
			</div>
		</div>
		<div id="dialog" title="도서목록" style="display:none;">
			<table class="table" id="otherlibBookdetail">
			</table>
		</div>
	</section>
</body>
</html>