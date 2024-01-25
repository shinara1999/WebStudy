package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class UpdateModel {
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 등록");
		// ===================================== 이 데이터를
		return "update.jsp"; // => 얘한테 보내라. (request를 받아서 출력)
	}
}
