package com.sist.model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.AllReplyDAO;
import com.sist.vo.AllReplyVO;

public class AllReplyModel {
	private String[] returns= {
			"",
			"Board/qna_detail.do"
	};
	@RequestMapping("reply/insert.do")
	public String replyInsert(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String typeno=request.getParameter("typeno");
		String cno=request.getParameter("cno");
		String r_content=request.getParameter("r_content");
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		
		AllReplyVO vo=new AllReplyVO();
		vo.setTypeno(Integer.parseInt(typeno));
		vo.setCno(Integer.parseInt(cno));
		vo.setR_content(r_content);
		vo.setUserid(userid);
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyInsert(vo);
		return "redirect:../"+returns[Integer.parseInt(typeno)]+"?no="+cno;
	}
	@RequestMapping("reply/delete.do")
	public String replyDelete(HttpServletRequest request,HttpServletResponse response) {
		String rno=request.getParameter("rno");
		String typeno=request.getParameter("typeno");
		String cno=request.getParameter("cno");
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyDelete(Integer.parseInt(rno));
		
		return "redirect:../"+returns[Integer.parseInt(typeno)]+"?no="+cno;
	}
	@RequestMapping("reply/update.do")
	public String replyUpdate(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rno=request.getParameter("rno");
		String typeno=request.getParameter("typeno");
		String cno=request.getParameter("cno");
		String r_content=request.getParameter("r_content");
		
		AllReplyVO vo=new AllReplyVO();
		vo.setRno(Integer.parseInt(rno));
		vo.setR_content(r_content);
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyUpdate(vo);
		
		return "redirect:../"+returns[Integer.parseInt(typeno)]+"?no="+cno;
	}
	@RequestMapping("reply/reviewinsert.do")
	public void replyReviewInsert(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//typeno,r_score,userid,r_content,isbn
		String typeno=request.getParameter("typeno");
		String r_score = request.getParameter("rscore");
		String isbn=request.getParameter("isbn");
		String r_content=request.getParameter("r_content");
		String userid= request.getParameter("email");
		
		int score = Integer.parseInt(r_score);
		
		AllReplyVO vo=new AllReplyVO();
		vo.setTypeno(Integer.parseInt(typeno));
		vo.setR_score(score);
		vo.setIsbn(isbn);
		vo.setR_content(r_content);
		vo.setUserid(userid);
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyReviewInsert(vo);
		
		try {
			PrintWriter out = response.getWriter();
			out.write(isbn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@RequestMapping("reply/reviewdelete.do")
	public void replyReviewDelete(HttpServletRequest request,HttpServletResponse response) {
		String rno=request.getParameter("rno");
		String typeno=request.getParameter("typeno");
		String isbn=request.getParameter("isbn");
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyDelete(Integer.parseInt(rno));
	}
	@RequestMapping("reply/reviewupdate.do")
	public void replyReviewUpdate(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rno=request.getParameter("rno");
		String typeno=request.getParameter("typeno");
		String isbn=request.getParameter("isbn");
		String r_content=request.getParameter("r_content");
		
		AllReplyVO vo=new AllReplyVO();
		vo.setRno(Integer.parseInt(rno));
		vo.setR_content(r_content);
		
		AllReplyDAO dao=AllReplyDAO.newInstance();
		dao.replyUpdate(vo);
		
		try {
			response.setContentType("application/x-wwww-form-urlencoded; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(r_content);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
