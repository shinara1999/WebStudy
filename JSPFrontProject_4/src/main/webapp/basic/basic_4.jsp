<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// type => "text/javascript" / ES6 => "text-babel"
window.onload=function(){
	// do-while
	document.write("<h3>do~while</h3>")
	let i=1;
	do{
		document.write("i="+i+"<br>");
		i++;
	}while(i<=10)
	document.write("<hr>");
	
	// while
	document.write("<h3>while</h3>");
	i=1;
	while(i<=10)
	{
		document.write("i="+i+"<br>")
		i++;
	}
	document.write("<hr>");
	
	// for
	document.write("<h3>for</h3>");
	for(i=1;i<=10;i++)
	{
		document.write("i="+i+"<br>");	
	}
	document.write("<hr>");
	
	// break
	document.write("<h3>break</h3>");
	for(i=1;i<=10;i++)
	{
		if(i==5) break;
		document.write("i="+i+"<br>");
	}
	document.write("<hr>");
	
	// continue
	document.write("<h3>continue</h3>");
	for(i=1;i<=10;i++)
	{
		if(i==5) continue;
		document.write("i="+i+"<br>");
	}
	document.write("<hr>");
	
}
</script>
</head>
<body>

</body>
</html>