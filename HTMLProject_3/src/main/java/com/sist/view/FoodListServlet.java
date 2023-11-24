package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 브라우저를 통해서 요청시에 처리해서 브라우저로 HTML을 전송하는 메소드
		// 톰캣에 의해 자동 호출
		// 메소드 영역 => JSP다. => service
		// JSP => 실행 => class변경 => 컴파일해서 실행
		// 전송타입 => html, xml, json
		response.setContentType("text/html;charset=UTF-8");
		
		// html 전송
		PrintWriter out=response.getWriter();
		
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodListData(1);
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>맛집 TOP1000</h1>");
		out.println("<table border=1 width=800>");
		out.println("<tr>");
		out.println("<th width=10%>순위</th>");
		out.println("<th width=15%></th>");
		out.println("<th width=20%>업체명</th>");
		out.println("<th width=15%>업종</th>");
		out.println("<th width=40%>주소</th>");
		out.println("</tr>");
		for(FoodVO vo:list)
		{
			out.println("<tr>");
			out.println("<th width=10%>"+vo.getFno()+"</th>");
			out.println("<th width=15%><img src="+vo.getPoster()+" width=30 height=30></th>");
			out.println("<th width=20%>"+vo.getName()+"</th>");
			out.println("<th width=15%>"+vo.getType()+"</th>");
			out.println("<th width=40%>"+vo.getAddress()+"</th>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}

}

















