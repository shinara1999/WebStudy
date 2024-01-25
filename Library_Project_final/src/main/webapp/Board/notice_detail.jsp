<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table tbody tr:nth-child(2n+1){
		background-color: white;
	}
	.table > tbody > tr > td,.table > tbody > tr > th{
		border-top: none;
	}
</style>
</head>
<body>
	<div>
		<h2>${vo.typeno==0?"공지사항":"보도자료" }</h2>
		<hr>
	</div>
	<table class="table" style="border-collapse: unset;">
		<tr style="background-color: rgba(144, 144, 144, 0.075);">
			<th width="80%" rowspan="2" style="font-size: 18px;">${vo.title }</th>
			<td width="20%" class="text-right">${vo.dbday }</td>
		</tr>
		<tr style="border-bottom: 1px solid #dbdbdb;background-color: rgba(144, 144, 144, 0.075);">
			<td class="text-right">조회&nbsp;${vo.hit }</td>
		</tr>
		<tr>
			<td colspan="2">
				<%-- <textarea rows="10" cols="" readonly style="background-color: white;resize: none;border: none;outline: none;">${vo.content }</textarea> --%>
				<pre style="background-color: white;border: none;">${vo.content }</pre>
			</td>
		</tr>
		<c:if test="${vo.filesize gt 0 }">
			<tr>
				<td width="100%" colspan="2">
					첨부파일&nbsp;&nbsp;<a href="../Board/notice_download.do?fn=${vo.filename }">${vo.filename }</a>(${vo.filesize }Bytes)
				</td>
			</tr>		
		</c:if>
		<tr>
			<td colspan="2" class="text-center">
				<input type="button" value="목록" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
			</td>
		</tr>
	</table>
</body>
</html>