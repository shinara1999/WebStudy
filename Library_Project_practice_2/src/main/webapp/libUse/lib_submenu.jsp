<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>lib_submenu</title>
  <link rel="stylesheet" href="../etc/sidebar/dist/style.css">
</head>
<body>
<!-- partial:index.partial.html -->
<aside>
  <a>
  <svg version="1.1" id="nav-btn" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" enable-background="new 0 0 512 512" xml:space="preserve">
    <path id="list-view-icon" d="M462,108.857H50V50h412V108.857z M462,167.714H50v58.857h412V167.714z M462,285.429H50v58.857h412
   V285.429z M462,403.143H50V462h412V403.143z"/>
  </svg>
</a>
  <h1 id="libname">도서관 이용</h1>
  
  <nav>
    <ul>
      <li><a href="../libUse/libTimeInfo.do">도서관 이용 시간</a></li>
      <li><a href="#">대출.반납.연장.예약</a></li>
      <li><a href="#">자리 예약</a></li>
      <li><a href="#">찾아오시는 길</a></li>
    </ul>
  </nav>
  
  <div class="vertical-line"></div>  
</aside>

<jsp:include page="${libUse_jsp }"></jsp:include>

<!-- partial -->
  <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./script.js"></script>

</body>
</html>