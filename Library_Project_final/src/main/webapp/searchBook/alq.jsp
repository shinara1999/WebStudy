<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	
	.menu-wrap li{
            width: calc((100% - 30px)/6);
		    float: left;
		    border-radius: 10px;
		    margin-bottom: 3px;
		    margin-right: 3px;
		    height: 148px;
		    text-align: center;
		    position: relative;
		    box-sizing: border-box;
		    padding-left:0px !important;
		    
		    border: none;
		    font: inherit;
		    
		    list-style: none;
		    cursor: pointer;
		    display: list-item;
     }
     .menu-wrap li:nth-child(6n){
     	margin-right:0;
     }
     .menu-wrap li>a{
     	width:100%;
     	border: none;
	    margin: 0;
	    padding: 0;
	    font: inherit;
     	font-size: 13px;
	    color: #000;
	    display: block;
	    padding-top: 110px;
	    text-decoration: none;
	    font-family: 'Noto Sans KR', sans-serif !important;
	    display: inline-block;
     }
     
     .menu-wrap #major .menu02{
     	display:none;
     }

     .menu-wrap #major .menu02>.more{
     	font-size: 16px;
	    color: #000;
	    font-weight: 300;
	    display: block;
	    position: relative;
	    white-space: nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    padding: 0 15px;
	    float:none;
     }
</style>
<script type="text/javascript">
	let menu_data = '';
	window.onload = function(){
			let majors = document.querySelectorAll("#major");
			for(let i =0; i<majors.length;i++){
				let menu2 = document.querySelector(".menu-wrap #major #sub1-"+(i+1))
				majors[i].addEventListener("mouseover",()=>{
					majors[i].style.background = majors[i].style.background.slice(0,92)+" #8ed1de";
				})
				majors[i].addEventListener("mouseout",()=>{
					majors[i].style.background = majors[i].style.background.slice(0,92)+" #ececec";
				})
				majors[i].addEventListener("click",()=>{
					if(menu_data===''){
						if(menu2.className==='menu02'){
							menu2.className = 'menu02 on';
							menu_data = (i+1);
							$('#sub1-'+(i+1)).show();
						}
					}
					else{
						if(menu_data===(i+1)){
							menu2.className = 'menu02';
							menu_data = '';
							$('#sub1-'+(i+1)).hide();
						}
						else{
							for(let j=0;j<majors.length;j++){
								let menu3 = document.querySelector(".menu-wrap #major #sub1-"+(j+1))
								if((j+1)===(i+1)){
									menu3.className = 'menu02 on';
									menu_data = (i+1);
									$('#sub1-'+(i+1)).show();
								}
								else{
									menu3.className = 'menu02';
									$('#sub1-'+(j+1)).hide();
								}
							}
						}
					}
					
				})
				let detail_more = document.querySelectorAll(".menu-wrap #major #sub1-"+(i+1)+" li a")
				
				if((i+1)%6!=0){
					menu2.style = "position: absolute;top: 0;right: -162px;width: 155px;border: 1px solid #8ed1de;z-index: 5;background: #fff;padding: 15px 0;box-sizing: border-box;min-height: 363px;";
				}
				else{
					menu2.style = "position: absolute;top: 0;left: -162px;width: 155px;border: 1px solid #8ed1de;z-index: 5;background: #fff;padding: 15px 0;box-sizing: border-box;min-height: 363px;";
				}
			}
			
	}
	
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">	
</head>
	<body>
		<section id="one" class="wrapper" style="padding:3em 0;"><div class="inner">
			<h4 style="color:black">자료 탐색</h4>
			<hr style="padding:2px;background: skyblue;">
			<div class="flex flex-3">
				<div class="menu">
					<ul class="menu-wrap">
						<c:forEach var="vo" items="${list }" >
								<li id="major" style="background:url(${vo.icon}) no-repeat center 32px #ececec;">
									<a  href="#!">${vo.cate }</a>
									<ul class="menu02" id="sub1-${vo.cno }">
										<c:forEach var="sub" items="${sub }">
											<c:if test="${sub.cno==vo.cno }">
												<li style="float: none;height: 40px;width: 100%;"><a href="../searchBook/alqResult.do?cno=${vo.cno}&mno=${sub.mno}" class="more" title="${sub.cate }" style="font-size: 16px;color: #000;font-weight: 300;display: block;position: relative;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;padding: 0 15px;text-align: left;">${sub.cate }</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		</section>
	</body>
</html>