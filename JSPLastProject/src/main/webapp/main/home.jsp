<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <div class="flexslider basicslider">
      <ul class="slides">
        <c:forEach var="i" begin="1" end="5">
          <li><a href="#"><img class="radius-10" src="../images/demo/slides/${i }.jpg" style="width:978px;height:400px"></a></li>
        </c:forEach>
      </ul>
    </div>
    </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">오늘의 맛집</a></h6>
          <p><img src="https://www.menupan.com/restaurant/restimg/005/zzmenuimg/h2018053_z.jpg" style="width:273px;height: 273px" class="img-circle"></p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">오늘의 레시피</a></h6>
          <p><img src="https://recipe1.ezmember.co.kr/cache/recipe/2023/12/28/a3d62f17df0cac57a8c73deb58b3acad1_m.jpg" style="width:273px;height: 273px" class="img-circle"></p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">오늘의 서울 여행</a></h6>
          <p><img src="https://korean.visitseoul.net/comm/getImage?srvcId=MEDIA&amp;parentSn=8799&amp;fileTy=MEDIA&amp;fileNo=1&amp;thumbTy=L" style="width:273px;height: 273px" class="img-circle"></p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <h2 class="sectiontitle">인기 한식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${aList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 양식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${bList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 중식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${cList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 일식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${dList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">최근 방문 맛집 &nbsp;&nbsp;<a href="#">더보기</a></h2>
    <div class="inline">
      <c:forEach var="kvo" items="${kList }" varStatus="s">
       <c:if test="${s.index<9}">
        <img class="radius-10" 
           src="${kvo.poster }" style="width: 100px;height: 100px" title="${kvo.name }">
       </c:if>
      </c:forEach>
    </div>
    <div class="clear"></div>
  </main>
</div>
</body>
</html>