/*
 		doGet  : 화면 출력할 때
 		doPost : 데이터 처리할 때
 */
package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Servlet = Server + let
// let => 가벼운 프로그램 => Applet
/*
 		서블리스 => 장점 : 보안 => .java => .class
 				  단점 : HTML작성이 복잡하다. (문자열)
 				        수정시마다 컴파일해야한다.
 		JSP => 사용이 편리 / 보안이 취약 => .jsp(그대로)
 			   			  컴파일 없이 실행 => 스크립트
 			   HTML을 사용하지 않는다.
 			   자바와 HTML을 연결
 			   JSP + servlet 
 		M V C
 		V - View => JSP
 		C - Controller => Servlet
 		Spring => 처리 => 웹 => Servlet
 		=============================
 		1. 페이징 기법 (블록)
 		2. Cookie
 		3. Session
 		4. 요청 = 응답
 		
 		HTML : 정적 (화면 UI)
 		=> 서버로 데이터 전송
 		   GET / POST / PUT / DELETE => Restful
 		    |	  | => id, pwd, 데이터가 많은 경우 => <form>
 		   URL?데이터
 		   노출/주로 사용 => 단순 데이터 전송
 		   				  페이지 / 번호 / 검색...
 		   => 전송이 없는 경우 : GET default (POST를 안보내는 경우)
 		=================== CSS
 */

import org.eclipse.jdt.internal.compiler.codegen.AnnotationTargetTypeConstants;

import java.util.*;
import com.sist.dao.*;

@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 7. 전송방식 (HTML, XML) => 브라우저에 통보
		response.setContentType("text/html;charset=UTF-8");
		
		// 8. 브라우저에서 읽어갈 메모리 위치 확보
		PrintWriter out=response.getWriter();
		
		// 13. 사용자의 요청값을 받는다.
		String page=request.getParameter("page");
		if(page==null)
		{
			page="1"; // default => 빠지면 에러 발생
		}
		int curpage=Integer.parseInt(page);
		
		// 9. 데이터베이스 연동 => 요청한 데이터 가지고 온다.
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodListData(curpage); // 1 부분은 변경,모양 보기 위해 임의로 설정 => 13. curpage로 변경
		// 12. 총페이지 가지고오기
		int totalpage=dao.foodTotalPage();
		
		// 31. 쿠키 읽기
		List<FoodVO> clist=new ArrayList<FoodVO>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("food"))
				{
					String fno=cookies[i].getValue();
					FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
					clist.add(vo);
				}
			}
		}
		
		// 14. 블록 나누기
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		// 1 => 현재 페이지 => 1~10
		//				  => (10-1)/10*10=0
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
		{
			endPage=totalpage;
		}
		
		// 10. 데이터를 html을 이용해서 출력
		// html, head, body 틀 잡기 > link > style > body 안 div > div 안 div (맛집 => 페이지 => 최근방문지 순)
		out.write("<html>");
		out.write("<head>"); // CSS / JavaScript
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">"); // table-hover
		out.write("<style type=text/css>");
		// container: 전체 출력 영역 (큰 틀) => 이 틀을 벗어나지 않도록 한다.
		out.write(".container{margin-top:50px}"); // margin:10px  10px  10px  10px
												  //        top  right bottom left
		out.write(".row{margin-top:0px auto;width:900px}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>"); // 화면 UI
		out.write("<div class=container>");
		// 맛집 이미지 출력
		out.write("<div class=row>"); 
		for(FoodVO vo:list) // 12바퀴 돌려라
		{
			out.write("<div class=\"col-md-3\">"); // 12등분 되어있다. 3 => 3개출력 후 밑으로 내려감 / 4 => 4개출력 후 밑으로 내려감
			out.write("<div class=\"thumbnail\">");
			out.write("<a href=FoodBeforeServlet?fno="+vo.getFno()+">"); // # : 임의 설정  // 15. FoodDetailServlet으로 변경 (상세보기 부분으로 화면 이동) // 25. FoodBeforeServlet 으로 변경 (쿠키)
			out.write("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
			out.write("<div class=\"caption\">");
			out.write("<p>"+vo.getName()+"</p>");
			out.write("</div>");
			out.write("</a>");
			out.write("</div>");
			out.write("</div>");
		}
		out.write("</div>");
		
		// 페이지 출력
		out.write("<div class=row>"); 
		out.write("<div class=text-center>");
		out.write("<ul class=\"pagination\">");
		if(startPage>1)
		{
			out.write("<li><a href=FoodListServlet?page="+(startPage-1)+">&lt;</a></li>"); // &lt; 이동 버튼
		}
		for(int i=startPage;i<=endPage;i++)
		{
			out.write("<li "+(i==curpage?"class=active":"")+"><a href=FoodListServlet?page="+i+">"+i+"</a></li>"); // active : 색깔이 바뀐다.
		}
		if(endPage>1)
		{
			out.write("<li><a href=FoodListServlet?page="+(endPage+1)+">&gt;</a></li>"); // &gt; 이동 버튼
		} 
		out.write("</ul>");
		out.write("</div>");
		out.write("</div>");
		
		// 최근 방문지 출력
		out.write("<div class=row>");
		
		// 32. 쿠키
		if(clist.size()!=0)
		{
			for(FoodVO vo:clist)
			{
				out.write("<a href=FoodDetailServlet?fno="+vo.getFno()+">");
				out.write("<img src="+vo.getPoster()+" style=\"width:100px;height:100px;margin:10px 0 0 5px;\">"); // &nbsp; 공백주기
																						// margin-top:10px;margin-left:5px; => margin 사용하려면 &nbsp 지워야한다.
																						// margin:10px 0 0 5px; => 한꺼번에 출력 (top bottom right left) ..? 맞나?
				out.write("</a>");
			}
		}
		else
		{
			out.write("<h3>방문한 기록이 없습니다.</h3>");
		}
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

}
















