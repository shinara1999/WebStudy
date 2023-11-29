package com.sist.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FoodBeforeServlet")
public class FoodBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 26. FoodBeforeServlet 만들기
		
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food_"+fno, fno);
		//						 =========== 키 == 값 => map형식 (중복되면 튕겨나간다.)
		
		// 27. 저장위치
		cookie.setPath("/");
		
		// 28. 저장기간
		cookie.setMaxAge(60*60*24); // 하루 저장한다는 뜻 (초단위)
		
		// 29. 클라이언트로 전송
		response.addCookie(cookie);
		
		// 30. 상세보기로 이동
		response.sendRedirect("FoodDetailServlet?fno="+fno);
	}

}
