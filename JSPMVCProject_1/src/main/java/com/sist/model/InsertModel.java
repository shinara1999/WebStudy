package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel {
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 추가");
		// ===================================== 이 데이터를
		return "insert.jsp"; // => 얘한테 보내라. (request를 받아서 출력)
	}
}
