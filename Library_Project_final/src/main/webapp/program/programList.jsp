<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#pg_list{
		width: 80%;
		float: left;
	}
	#pg_list .pro_thumbnail{
		position: relative;
		margin-bottom: 25px;
		padding: 2px;
	}
	#pg_list .pro_thumbnail:hover {
		border: 1px solid gray;
	}
	#pg_list .pro_thumbnail img{
		border: 0.5px solid #e2e2e2;
	}
	/* .pro_thumbnail::before{
		content: '손기정어린이';
		position: absolute;
		top: -20px;
		left: 48px;
		width: 160px;
		padding: 10px;
		background-color: orange;
		color: white;
		border-radius: 20px;
		text-align: center;
		font-weight: bold;
	} */
	#pg_list .caption .title{
		width: 100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	#pg_list .caption p:last-child {
		margin-bottom: 0;
	}
	#pg_list .caption p:nth-child(2),.caption p:nth-child(3) {
		margin-bottom: 5px;
	}
	#pg_cookie{
		width: 20%;
		height: 700px;
		float: left;
		padding-left: 30px;
	}
	#pg_cookie h3{
		font-size: 1.5em;
		margin-top: 0;
	}
	#pg_cookie_list{
		width: 80%;
		margin: 0 auto;
	}
	.least{
		position: relative;
		margin-bottom: 5px;
	}
	.least img{
		border: 0.5px solid #e2e2e2;
	}
	.least a.cookieDeleteBtn{
		position: absolute;
		top: 0;
		right: 0;
		color: black;
		background-color: white;
		padding: 0 2px;
		border-top: 0.5px solid #e2e2e2;
		border-right: 0.5px solid #e2e2e2;
		text-decoration: none;
		cursor: pointer;
	}
	.least a.cookieDeleteBtn:hover {
		color: #ed8e6c;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('.least  a.cookieDeleteBtn').click(function(){
			let pno=$(this).attr('data-pno')
			$.ajax({
				type: 'POST',
				url: '../program/programCookieDelete.do',
				data: {"pno":pno},
				success:function(json){
					location.href='../program/programList.do'
				}
			})			
		})
	})
</script>
</head>
<body>
	<div id="pg_list">
	<form method="post" action="../program/programList.do" id="searchForm">
	<div>
		<div style="padding: 15px;">
		<div>
			<%-- <input type="hidden" id="target_hidden" value="${target }"> --%>
			<select name="target" style="margin-right: 10px;line-height: 0;appearance: auto;" class="input-lg">
				<option value="0" ${target eq "0" ? "selected" : "" }>대상</option>
				<option value="1" ${target eq "1" ? "selected" : "" }>영유아</option>
				<option value="2" ${target eq "2" ? "selected" : "" }>어린이</option>
				<option value="3" ${target eq "3" ? "selected" : "" }>청소년</option>
				<option value="4" ${target eq "4" ? "selected" : "" }>성인</option>
				<option value="5" ${target eq "5" ? "selected" : "" }>누구나</option>
			</select>
			<select name="status" style="line-height: 0;appearance: auto;" class="input-lg">
				<option value="-1" ${status eq "-1" ? "selected" : "" }>접수상태</option>
				<option value="0" ${status eq "0" ? "selected" : "" }>접수예정</option>
				<option value="1" ${status eq "1" ? "selected" : "" }>접수중</option>
				<option value="2" ${status eq "2" ? "selected" : "" }>대기접수</option>
				<option value="3" ${status eq "3" ? "selected" : "" }>접수마감</option>
				<option value="4" ${status eq "4" ? "selected" : "" }>종료</option>
			</select>
		</div>
		<div style="margin-top: 10px;">
			<%-- <input type="hidden" id="searchType_hidden" value="${searchType }"> --%>
			<select id="searchOp" name="searchType" class="input-lg" style="appearance: auto;">
				<option value="title">프로그램명</option>
				<option value="place">장소</option>
			</select>
			<input type="text" size="15" style="float: left;width: 70%;margin-left: 10px;margin-right: 10px;line-height: 0;" name="search" class="input-lg" value="${search }">
			<input type="submit" class="btn btn-sm btn-info" value="검색">
		</div>
		</div>
	</div>
	</form>
	<div style="width: 100%;">
		검색 항목 <font color="red">${find_cnt }</font>건
		<hr style="margin-top: 10px;">
	</div>
	<div class="row" style="margin-top: 30px;">
		<c:if test="${list_size gt 0 }">
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
    			<div class="thumbnail pro_thumbnail">
	      			<a href="../program/programDetail_before.do?pno=${vo.pno }" style="text-decoration: none;">
	      				<c:choose>
		      				<c:when test="${fn:startsWith(vo.poster,'https') }">
		        				<img src="${vo.poster }" style="width:100%;height: 300px;">
		        			</c:when>
		        			<c:otherwise>
		        				<img src="https://www.junggulib.or.kr/attachfile/editor/${vo.poster }" style="width:100%;height: 300px;">
		        			</c:otherwise>
	        			</c:choose>
	        			<div class="caption">
	          				<p style="font-size: 20px;font-weight: bold;" class="title ${fn:length(vo.title)<9?'text-center':'' }">${vo.title }</p>
	          				<p class="text-center">
	          					<c:if test="${vo.status==0 }">접수예정</c:if>
	          					<c:if test="${vo.status==1 }">접수중</c:if>
	          					<c:if test="${vo.status==2 }">대기접수</c:if>
	          					<c:if test="${vo.status==3 }">접수마감</c:if>
	          					<c:if test="${vo.status==4 }">종료</c:if>
	          					&nbsp;|&nbsp;${vo.target1 }
	          				</p>
	          				<c:if test="${vo.edu1_str eq vo.edu2_str }">
	          					<p class="text-center" style="font-size: 16px;">${vo.edu1_str }</p>
	          				</c:if>
	          				<c:if test="${vo.edu1_str ne vo.edu2_str }">
	          					<p class="text-center" style="font-size: 16px;">${vo.edu1_str } ~ ${vo.edu2_str }</p>
	          				</c:if>
	          				<p class="text-center" style="font-size: 14px;">정원 : ${vo.applicant }/${vo.capacity }&nbsp;대기 : ${vo.waiting }/${vo.waitingCap }</p>
	        			</div>
	      			</a>
    			</div>
  			</div>
		</c:forEach>
		</c:if>
		<c:if test="${list_size eq 0 }">
			<div class="text-center">
				검색 내역이 존재하지 않습니다.
			</div>
		</c:if>
	</div>
	<div style="height: 20px;"></div>
	<div>
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${startBlockNum>1 }">
					<li><a href="../program/programList.do?page=${curpage gt 1 ? startBlockNum-1 : curpage }&target=${target }&searchType=${searchType }&search=${search }">&lt;</a></li>
				</c:if>
				<c:forEach var="i" begin="${startBlockNum }" end="${endBlockNum }">
					<li ${curpage eq i ? "class=active" : "" }><a href="../program/programList.do?page=${i }&target=${target }&searchType=${searchType }&search=${search }">${i }</a></li>
				</c:forEach>
				<c:if test="${endBlockNum<totalpage }">
					<li><a href="../program/programList.do?page=${curpage lt totalpage ? endBlockNum+1 : curpage }&target=${target }&searchType=${searchType }&search=${search }">&gt;</a></li>
				</c:if>
			</ul> 
		</div>
	</div>
	</div>
	<div id="pg_cookie">
	<div>
		<h3 class="text-left">최근 방문</h3>
		<hr>
		<div style="width: 100%;height: 150px;" id="leastList">
		<c:if test="${cList_size eq 0 }">
			<h3>방문 기록이 없습니다</h3>
		</c:if>
		<div id="pg_cookie_list">
		<c:if test="${cList_size gt 0 }">
			<c:set var="cnt" value="${cList_size-1 }"/>
			<c:forEach var="vo" items="${cList }" begin="0" end="8">
				<a href="../program/programDetail.do?pno=${vo.pno }" class="program_${vo.pno }">
				<ul style="list-style: none" class="program_${vo.pno }">
					<li style="float: left;" class="least">
						<c:choose>
							<c:when test="${fn:startsWith(vo.poster,'https') }">
								<img src="${vo.poster }" title="${vo.title }" style="width: 106px;height: 150px;">
							</c:when>
							<c:otherwise>
								<img src="https://www.junggulib.or.kr/attachfile/editor/${vo.poster }" title="${vo.title }" style="width: 106px;height: 150px;">	
							</c:otherwise>
						</c:choose>
						<a class="cookieDeleteBtn" data-pno="${vo.pno }">X</a>
					</li>
				</ul>
				</a>
			</c:forEach>
		</c:if>
		</div>
		</div>
	</div>
	</div>
</body>
</html>