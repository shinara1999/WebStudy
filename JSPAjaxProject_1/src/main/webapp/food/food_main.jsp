<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
h1{
	text-align: center;
}
p{
	width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'food_list.jsp',
		success:function(result)
		{
			$('.col-sm-8').html(result)
		}
	})
})
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-8">
		
		</div>
		<div class="col-sm-4">
		
		</div>
	</div>
</body>
</html>


































