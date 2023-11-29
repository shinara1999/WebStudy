package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 		1. /CategoryServlet
 		1) 톰캣에 의해 메모리 할당 => new CategoryServlet()
 		2) init()
 		3) service() : Thread => 사용자가 요청시마다 호출되는 메소드
 		   =========
 		    => GET / POST => 동시처리
 		    => ===   ====
 		              | doPost => 데이터 전송 (처리 요청)
 		        | doGet => default (화면 UI)
 		4) destroy() : 사용자가 사이트 이동 => 메모리 해제
 		===========================================
 		  => JSP
 		     _jspInit() => _jspService() => _jspDestroy()
 		     			   =============
 		     			    | 조건으로 POST / GET
 		  => 메소드 영역이다. ==> servlet으로 변경
 */

import java.util.*;
import com.sist.dao.*;

@WebServlet("/CategoryServlet") // 클래스를 찾는 인덱스
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송 방법 => 어떤 파일 형태로 전송할지 여부
		// 브라우저로 전송 => html , xml, json
		//				 text/html , texl/xml , text/plain
		response.setContentType("text/html;charset=UTF-8");
		// <% page contentType="text/html;charset=UTF-8" %>
		PrintWriter out=response.getWriter();
		
		// 조립
		
		// 요청값 받기
		String cno=request.getParameter("cno");
		if(cno==null)
		{
			cno="1";
		}
		
		// 데이터베이스 읽기
		FoodDAO dao=FoodDAO.newInstance();
		List<CategoryVO> list=dao.categorylistData(Integer.parseInt(cno));
		
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
		
		out.write("<div class=row>");
		out.write("<a href=CategoryServlet?cno=1 class=\"btn btn-sm btn-danger\">믿고 보는 맛집 리스트</a>&nbsp;");
		out.write("<a href=CategoryServlet?cno=2 class=\"btn btn-sm btn-success\">지역별 맛집 리스트</a>&nbsp;");
		out.write("<a href=CategoryServlet?cno=3 class=\"btn btn-sm btn-primary\">메뉴별 맛집 리스트</a>");
		out.write("<body>");
		out.write("<body>");
		out.write("</div>");
		/*
    <div class="col-md-4">
    <div class="thumbnail">
      <a href="/w3images/nature.jpg">
        <img src="/w3images/nature.jpg" alt="Nature" style="width:100%">
        <div class="caption">
          <p>Lorem ipsum...</p>
        </div>
      </a>
    </div>
  </div>
		 */
		out.write("<div class=row>");
		for(CategoryVO vo:list)
		{
			out.write("<div class=\"col-md-4\">");
			out.write("<div class=\"thumbnail\">");
			out.write("<a href=FoodListServlet?cno="+vo.getCno()+">");
			// FoodListServlet로 cno를 전송한다.
			out.write("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
			out.write("<div class=\"caption\">");
			out.write("<p>"+vo.getTitle()+"</p>");
			out.write("</div>");
			out.write("</a>");
			out.write("</div>");
			out.write("</div>");
		}
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

}















