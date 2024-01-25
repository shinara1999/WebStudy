package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DeleteModel {
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 삭제");
		// ===================================== 이 데이터를
		return "delete.jsp"; // => 얘한테 보내라. (request를 받아서 출력)
	}
}
