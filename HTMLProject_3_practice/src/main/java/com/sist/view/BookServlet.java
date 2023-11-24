package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;
import com.sist.dao.BookVO;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// html 전송
		PrintWriter out=response.getWriter();
		
		BookDAO dao=BookDAO.newInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy");
		List<BookVO> list=dao.bookListData(1);
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>책</h1>");
		out.println("<table border=1 width=800>");
		out.println("<tr>");
		out.println("<th width=20%>ISBN</th>");
		out.println("<th width=15%></th>");
		out.println("<th width=30%>제목</th>");
		out.println("<th width=25%>작가</th>");
		out.println("<th width=10%>출간년도</th>");
		out.println("</tr>");
		for(BookVO vo:list)
		{
			out.println("<tr>");
			out.println("<th width=20%>"+vo.getIsbn()+"</th>");
			out.println("<th width=15%><img src="+vo.getImage()+" width=30 height=30></th>");
			out.println("<th width=30%>"+vo.getBooktitle()+"</th>");
			out.println("<th width=15%>"+vo.getBookperson()+"</th>");
			out.println("<th width=20%>"+formatter.format(vo.getBookdate())+"</th>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}
