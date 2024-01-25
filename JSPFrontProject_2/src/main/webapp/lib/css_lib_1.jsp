<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	border: 1px solid red;
	height: 600px;
}
.row{
	background-color: cyan;
	border: 1px solid black;
	height: 300px;
}
.col-sm-8{
	background-color: yellow;
	height: 300px;
}
.col-sm-4{
	background-color: green;
	border: 2px solid red;
	height: 300px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div style="height: 5px"></div>
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4"></div>
			</div>
			<div style="height: 5px"></div>
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4"></div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>
</body>
</html>















