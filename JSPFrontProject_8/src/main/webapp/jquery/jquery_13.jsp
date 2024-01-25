<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 800px;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	let html='<li><a href="#">&lt;</a></li>'
	for(i=1;i<=10;i++)
	{
		html+='<li><a href="#">'+i+'</a></li>'
	}
	html+='<li><a href="#">&gt;</a></li>'
	
	$('#page').html(html)
})
function range(start, end)
{
	
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<ul class="pagination" id="page">
					
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
















