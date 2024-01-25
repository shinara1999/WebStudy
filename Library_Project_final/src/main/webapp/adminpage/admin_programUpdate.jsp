<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input[type="button"]{
		height: 1em;
		line-height: 0;
		padding: 1em 1em;
		font-weight: normal;
	}
	input[type="checkbox"]{
		display: inline;
		float: none;
	}
	input[type="checkbox"] + label{
		padding-left: 2em;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$('#edu2').change(function() {
		if($('#edu1').val()===''){
			alert('운영시작시간을 먼저 입력해주세요')
			$('#edu2').val('')
			return
		}
		
		let edu1=$('#edu1').val()
		let edu2=$('#edu2').val()
		
		$.ajax({
			type: 'POST',
			url: '../admin/programSelectDate.do',
			data: {"edu1":edu1,"edu2":edu2},
			success:function(result){
				$('#select_Date').html(result)
			}
		})
	})
})
</script>
</head>
<body>
	<div class="col-md-10">
	<h2>프로그램 수정</h2>
	<hr>
	<div style="height: 20px;"></div>
	<form method="post" action="../admin/programUpdate_ok.do" enctype="multipart/form-data">
	<input type="hidden" name="pno" value="${vo.pno }">
	<table class="table">
		<tr>
			<th width="10%">제목</th>
			<td width="90%" colspan="3">
				<input type="text" name="title" size="75" required class="input-sm" value="${vo.title }">
			</td>
		</tr>
		<tr>
			<th width="10%">대상</th>
			<td width="90%" colspan="3">
				<input type="checkbox" name="target1" id="t_0" value="0" ${fn:contains(vo.target1,'영유아')?"checked":"" }>
				<label for="t_0">영유아</label>
				<input type="checkbox" name="target1" id="t_1" value="1" ${fn:contains(vo.target1,'어린이')?"checked":"" }>
				<label for="t_1">어린이</label>
				<input type="checkbox" name="target1" id="t_2" value="2" ${fn:contains(vo.target1,'청소년')?"checked":"" }>
				<label for="t_2">청소년</label>
				<input type="checkbox" name="target1" id="t_3" value="3" ${fn:contains(vo.target1,'성인')?"checked":"" }>
				<label for="t_3">성인</label>
				<input type="checkbox" name="target1" id="t_4" value="4" ${fn:contains(vo.target1,'누구나')?"checked":"" }>
				<label for="t_4">누구나</label>
				<!-- <select name="target1" multiple>
					<option value="0">영유아</option>
					<option value="1">어린이</option>
					<option value="2">청소년</option>
					<option value="3">성인</option>
					<option value="4">누구나</option>
				</select> -->
			</td>
		</tr>
		<tr>
			<th width="10%">상세대상</th>
			<td width="90%" colspan="3">
				<input type="text" name="target2" size="50" required class="input-sm" value="${vo.target2 }">
			</td>
		</tr>
		<tr>
			<th width="10%">장소</th>
			<td width="90%" colspan="3">
				<input type="text" name="place" size="50" required class="input-sm" value="${vo.place }">
			</td>
		</tr>
		<tr>
			<th width="10%">운영기간</th>
			<td width="40%">
				<input type="date" name="edu1" required class="input-sm" id="edu1">&nbsp;~&nbsp;
				<input type="date" name="edu2" required class="input-sm" id="edu2">
			</td>
			<th width="10%">시간</th>
			<td width="40%">
				<input type="time" name="time1" required class="input-sm" value="${fn:substring(vo.time,0,5) }">&nbsp;~&nbsp;
				<input type="time" name="time2" required class="input-sm" value="${fn:substring(vo.time,fn:indexOf(vo.time,'~')+1,fn:length(vo.time)) }">
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				<div id="select_Date"></div>
			</td>
			<td></td>
		</tr>
		<tr>
			<th width="10%">접수기간</th>
			<td width="90%" colspan="3">
				<input type="datetime-local" name="accept1" required class="input-sm" value="${vo.accept1_str }">&nbsp;~&nbsp;
				<input type="datetime-local" name="accept2" required class="input-sm" value="${vo.accept2_str }">
			</td>
		</tr>
		<%-- <tr>
			<th width="10%">요일</th>
			<td width="90%" colspan="3">
				<input type="checkbox" name="week" id="w_0" value="0" ${fn:contains(vo.week,'0')?"checked":"" }>
				<label for="w_0">일</label>
				<input type="checkbox" name="week" id="w_1" value="1" ${fn:contains(vo.week,'1')?"checked":"" }>
				<label for="w_1">월</label>
				<input type="checkbox" name="week" id="w_2" value="2" ${fn:contains(vo.week,'2')?"checked":"" }>
				<label for="w_2">화</label>
				<input type="checkbox" name="week" id="w_3" value="3" ${fn:contains(vo.week,'3')?"checked":"" }>
				<label for="w_3">수</label>
				<input type="checkbox" name="week" id="w_4" value="4" ${fn:contains(vo.week,'4')?"checked":"" }>
				<label for="w_4">목</label>
				<input type="checkbox" name="week" id="w_5" value="5" ${fn:contains(vo.week,'5')?"checked":"" }>
				<label for="w_5">금</label>
				<input type="checkbox" name="week" id="w_6" value="6" ${fn:contains(vo.week,'6')?"checked":"" }>
				<label for="w_6">토</label>
				<!-- <select name="week" multiple>
					<option value="0">일</option>
					<option value="1">월</option>
					<option value="2">화</option>
					<option value="3">수</option>
					<option value="4">목</option>
					<option value="5">금</option>
					<option value="6">토</option>
				</select> -->
			</td>
		</tr> --%>
		<tr>
			<th width="10%">정원</th>
			<td width="40%">
				<input type="text" name="capacity" required class="input-sm" value="${vo.capacity }">명
			</td>
			<th width="10%">대기</th>
			<td width="40%">
				<input type="text" name="waitingCap" required class="input-sm" value="${vo.waitingCap }">명
			</td>
		</tr>
		<tr>
			<th width="10%">포스터</th>
			<td width="90%" colspan="3">
				<input type="file" name="poster" required>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea rows="10" cols="" name="content">${vo.content }</textarea>
			</td>
		</tr>
	</table>
	<div style="height: 20px;"></div>
	<div class="text-center">
		<input type="submit" value="수정" class="btn btn-lg btn-success">
		<input type="button" value="취소" class="btn btn-lg btn-danger">
	</div>
	</div>
	</form>
</body>
</html>