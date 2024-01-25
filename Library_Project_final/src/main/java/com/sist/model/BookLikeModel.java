package com.sist.model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.AllLikeDAO;

public class BookLikeModel {
	@RequestMapping("searchBook/LikeCount.do")
	public void bookLikeChange(HttpServletRequest request,HttpServletResponse response) {
		// type 0번 => 책 좋아요
		String id = request.getParameter("id");
		String isbn = request.getParameter("isbn");
		int type = 0;
		
		AllLikeDAO dao = AllLikeDAO.newInstance();
		
		dao.bookLikeChange(id, isbn, type);
		
		int cnt = dao.bookLikeCount(isbn);
		int status = dao.bookLikeCheck(isbn, id, type);
		
		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);
		obj.put("status", status);
		
		JSONArray arr = new JSONArray();
		arr.add(obj);
		try {
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
