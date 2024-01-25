package com.sist.model;

import javax.servlet.http.HttpServletRequest;
/*
 		MVC
 		Model => java => 요청 처리
 		jsp => <% %>
 		View => jsp => 요청 처리 결과를 받아서 화면에 출력
 				-----------------------------------
 				자바를 사용하지 않는다. => ${} , JSTL
 				======== <% %>
 		Controller => servlet
 					  JSP에서 요청을 받아서
 					  => 처리에 해당되는 Model을 찾아주는 역할 수행
 					  => Model을 찾아서 메소드를 호출 => request가 jsp로 연결
 		==============================================================
 		Dispacher : 배달부 => Framework : 이미 제작됨
 		=> DispacherServlet
 		=> FilterDispacher
 		=> Controller => 서빙
 		   1) 주문을 받는다. => JSP 요청
 		   2) 주방을 거친다. => Model을 찾는다.
 		   3) 음식 배달 => request를 한번에 전송
 		   
 		   			요청(request)
 		   			ip						  	  request
 		사용자(JSP) =============== Controller =============== 주방 (패키지)
 		=> <a> , <form>										  종류별 요리
 															  ========
 															    Model
 															    => 처리
 								   Controller <==============
 										|	  	결과물을 주문서와 동시에 보내준다.
 										|	  	==========================
 										|	  	request.setAttribute()
 							  request를 JSP로 보내준다.
 							  ======================
 							  출력 => 브라우저에서 읽어간다.
 */
// 요청을 받아서 처리한 결과값 전송
public class ListModel {
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 목록");
		// ===================================== 이 데이터를
		return "list.jsp"; // => 얘한테 보내라. (request를 받아서 출력)
	}
}



















