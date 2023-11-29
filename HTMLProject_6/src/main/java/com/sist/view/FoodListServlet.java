package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// html을 모아서 전송
		PrintWriter out=response.getWriter();
		
		// CategoryServlet에서 전송된 cno를 받는다.
		// FoodListServlet?cno=1 이렇게 썼으면
		//				  ===== 여기와    ===== 여기가 일치해야 한다.
		String cno=request.getParameter("cno");
		
		// Database 연동
		FoodDAO dao=FoodDAO.newInstance();
		CategoryVO cvo=dao.categoryInfoData(Integer.parseInt(cno));
		
		out.write("<html>");
		out.write("<head>");
		// 외부 css => 파일에 공통 적용되는 css => 파일로 만들어서 처리
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		// 내부 css => 한 개의 파일에서만 적용
		out.write("<style type=text/css>");
		/*
		 		bootstrap => 모든 내용이 class라는 속성 이용
		 		CSS의 기본
		 		1. * => 전체
		 		2. 태그명
		 		3. id ===> 중복이 없는 태그
		 		   #id명
		 		4. class ===> 중복이 있는 태그 (여러개를 동시 변경)
		 		   .class명
		 */
		out.write(".container{margin-top:50px}");
		out.write(".row{margin:0px auto;width:900px}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<div class=container>");
		out.write("<div class=jumbotron>"); // 회색 바탕으로 테두리를 주는 것
		out.write("<h3 class=text-center>"+cvo.getTitle()+"</h3>"); // title
		out.write("<h4 class=text-center>"+cvo.getSubject()+"</h4>"); // subject
		out.write("</div>");
		out.write("<div style=\"height:30px\"></div>");
		out.write("<div class=row>");
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<td>");
		// 목록 출력 위치 
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

}













