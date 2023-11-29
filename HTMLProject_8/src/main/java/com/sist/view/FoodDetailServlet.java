package com.sist.view;
// 16. FoodDetailServlet 만들기
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import java.util.*;

@WebServlet("/FoodDetailServlet")
public class FoodDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 17
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		// 18. 사용자가 보내준 데이터 받기
		String fno=request.getParameter("fno");
		
		// . fno에 해당되는 데이터 정보를 가지고 온다. => FoodDAO
		
		// 24. 최근 방문 (쿠키)
		
		
		// 20. 데이터베이스 연동
		FoodDAO dao=FoodDAO.newInstance();
		FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
		
		// 21. 읽어온 데이터를 html출력
		out.write("<html>");
		out.write("<head>"); // CSS / JavaScript
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">"); // table-hover
		out.write("<style type=text/css>");
		// container: 전체 출력 영역 (큰 틀) => 이 틀을 벗어나지 않도록 한다.
		out.write(".container{margin-top:50px}"); // margin:10px  10px  10px  10px
												  //        top  right bottom left
		out.write(".row{margin-top:0px auto;width:900px}");
		
		// 23. 좋아요 버튼 간격 띄우기
		out.write(".a{margin-left:5px}");
		
		out.write("</style>");
		out.write("</head>");
		out.write("<body>"); // 화면 UI
		out.write("<div class=container>");
		out.write("<div class=row>");
		out.write("<table class=table>");
		out.write("<tr>");
		StringTokenizer st=new StringTokenizer(vo.getPoster(),"^");
		while(st.hasMoreTokens())
		{
			out.write("<td>");
			out.write("<img src="+st.nextToken()+" style=\"width:100%\" class=img-rounded>"); // class=img-rounded : 이미지 모양 변경하기
			out.write("</td>");
		}
		out.write("<td>");
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</div>");
		// 22. 화면분할
		out.write("<div style=\"height:30px\"></div>");
		out.write("<div class=row>");
		out.write("<div class=col-sm-8>"); // 전체 12에서 가로 8만큼
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<td colspan=2>");
		out.write("<h3>"+vo.getName()+"&nbsp;<span style=\"color:orange\">"+vo.getScore()+"</span></h3>"); // 제목
		out.write("</td>");
		out.write("</tr>");
		// 주소
		out.write("<tr>");
		out.write("<th width=15%>주소</th>");
		out.write("<td width=85%>"+vo.getAddress()+"</td>");
		out.write("</tr>");
		// 전화
		out.write("<tr>");
		out.write("<th width=15%>전화</th>");
		out.write("<td width=85%>"+vo.getPhone()+"</td>");
		out.write("</tr>");
		// 음식종류
		out.write("<tr>");
		out.write("<th width=15%>음식종류</th>");
		out.write("<td width=85%>"+vo.getType()+"</td>");
		out.write("</tr>");
		// 가격대
		out.write("<tr>");
		out.write("<th width=15%>가격대</th>");
		out.write("<td width=85%>"+vo.getPrice()+"</td>");
		out.write("</tr>");
		// 영업시간
		out.write("<tr>");
		out.write("<th width=15%>영업시간</th>");
		out.write("<td width=85%>"+vo.getTime()+"</td>");
		out.write("</tr>");
		// 주차
		out.write("<tr>");
		out.write("<th width=15%>주차</th>");
		out.write("<td width=85%>"+vo.getParking()+"</td>");
		out.write("</tr>");
		
		if(!vo.getMenu().equals("no"))
		{
			// 메뉴
			out.write("<tr>");
			out.write("<th width=15%>메뉴</th>");
			out.write("<td width=85%>");
			out.write("<ul>");
			st=new StringTokenizer(vo.getMenu(), "원");
			while(st.hasMoreTokens())
			{
				out.write("<li>"+st.nextToken()+"원</li>");
			}
			out.write("</ul>");
			out.write("</td>");
			out.write("</tr>");
		}
		
		// 좋아요 버튼
		out.write("<tr>");
		out.write("<td colaspan=2 class=text-right>");
		out.write("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		out.write("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		out.write("<a href=# class=\"btn btn-xs btn-info\">예약</a>");
		out.write("<a href=FoodListServlet class=\"btn btn-xs btn-primary\">목록</a>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("</table>");
		out.write("</div>");
		out.write("<div class=col-sm-4>"); // 전체 12에서 가로 4만큼
		out.write("</div>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
		
		// . 자동으로 브라우저에서 읽어간다.
	}

}
