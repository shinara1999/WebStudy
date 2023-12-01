package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.sist.dao.*;

@WebServlet("/GoodsAllServlet")
public class GoodsAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 9.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		// 13. 사용자가 요청한 값을 받는다.
		String page=request.getParameter("page");
		if(page==null)
		{
			page="1";
		}
		int curpage=Integer.parseInt(page);
		////
		
		// 14. dao연동
		GoodsDAO dao=GoodsDAO.newInstance();
		List<GoodsVO> list=dao.goodsListData(curpage, 1);
		int totalpage=dao.goodsTotalPage(1);
		final int BLOCK=10; // 1블록:1-10, 2블록:11-20... 을 유지하게 만드는 방법
		int startPage=((curpage-1)/BLOCK*BLOCK+1);
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		////
		
		out.write("<html>");
		out.write("<body>");
		out.write("<div class=row>");
		out.write("<h1 class=text-center>전체상품</h1>");
		
		// 16.
		for(GoodsVO vo:list)
		{
			String s=vo.getName();
			if(s.length()>30)
			{
				s=s.substring(0, 30)+"...";
				vo.setName(s);
			}
		}
		////
		
		// 15.
		for(GoodsVO vo:list)
		{
			out.write("<div class=\"col-md-3\">"); // 12등분 되어있다. 3 => 3개출력 후 밑으로 내려감 / 4 => 4개출력 후 밑으로 내려감
			out.write("<div class=\"thumbnail\">");
			out.write("<a href=MainServlet?mode=5&no="+vo.getNo()+"&type=1>"); // # : 임의 설정 => MainServlet으로 항상 돌아가야 한다. (main 유지)
			out.write("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
			out.write("<div class=\"caption\">");
			out.write("<p>"+vo.getName()+"</p>");
			out.write("</div>");
			out.write("</a>");
			out.write("</div>");
			out.write("</div>");
		}
		////
		
		out.write("</div>");
		
		// 17. 페이지 이동
		out.write("<div class=row>"); 
		out.write("<div class=text-center>");
		out.write("<ul class=\"pagination\">");
		if(startPage>1)
		{
			out.write("<li><a href=MainServlet?page="+(startPage-1)+"&mode=1>&lt;</a></li>"); // &lt; 이동 버튼
		}
		for(int i=startPage;i<=endPage;i++)
		{
			out.write("<li "+(i==curpage?"class=active":"")+"><a href=MainServlet?page="+i+"&mode=1>"+i+"</a></li>"); // active : 색깔이 바뀐다.
		}
		if(endPage>1)
		{
			out.write("<li><a href=MainServlet?page="+(endPage+1)+"&mode=1>&gt;</a></li>"); // &gt; 이동 버튼
		} 
		out.write("</ul>");
		out.write("</div>");
		out.write("</div>");
		
		out.write("</body>");
		out.write("</html>");
	}

}
