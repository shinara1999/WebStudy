package com.sist.model;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class MemberModel {
   @RequestMapping("member/login.do")
   public String member_login(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   return "../member/login.jsp";
   }
   @RequestMapping("member/login_ok.do")
   public void member_login_ok(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String id=request.getParameter("id");
	   String pwd=request.getParameter("pwd");
	   MemberVO vo=MemberDAO.isLogin(id, pwd);
	   if(vo.getMsg().equals("OK"))
	   {
		   HttpSession session=request.getSession();
		   session.setAttribute("id", vo.getId());
		   session.setAttribute("name", vo.getName());
		   session.setAttribute("admin", vo.getAdmin());
		   session.setAttribute("post", vo.getPost());
		   session.setAttribute("address", vo.getAddr1()+" "+vo.getAddr2());
		   session.setAttribute("email", vo.getEmail());
		   session.setAttribute("phone", vo.getPhone());
	   }
	   
	   try
	   {
		   // Ajax로 전송 
		   PrintWriter out=response.getWriter();
		   out.write(vo.getMsg());
	   }catch(Exception ex) {}
   }
   @RequestMapping("member/logout.do")
   public String member_logout(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   HttpSession session=request.getSession();
	   session.invalidate();
	   return "redirect:../main/main.do";
   }
}
