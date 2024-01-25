package com.sist.model;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicTextAreaUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.UserDAO;
import com.sist.vo.UserVO;
import com.sist.vo.UserVO;
import com.sist.vo.ZipcodeVO;;

public class UserModel {
	
	// 로그인 페이지로
	@RequestMapping("user/login.do")
	public String user_login(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("user_jsp", "../user/login.jsp");
		request.setAttribute("main_jsp", "../user/user_main.jsp");
		return "../main/main.jsp";
	}
	
	// 로그인
	@RequestMapping("user/login_ok.do")
	public void user_login_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("email");
		String pwd=request.getParameter("pass");
		
		UserDAO dao=UserDAO.newInstance();
		
		UserVO vo=dao.UserLogin(id, pwd);
		System.out.println(vo.getMsg());
		if(vo.getMsg().equals("OK"))
		{
			// 세션에 저장
			HttpSession session=request.getSession();
			session.setAttribute("email", vo.getUserID());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
			session.setAttribute("hintQ", vo.getHintQ());
			session.setAttribute("hintA", vo.getHintA());
		}
		
		// ajax로 전송
		try
		{
			PrintWriter out=response.getWriter();
			out.write(vo.getMsg());
		}
		catch(Exception ex) {}
	}
	
	// 로그아웃
	@RequestMapping("user/logout.do")
	public String user_logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();    // 저장된 내용 다 지움
		return "redirect:../main/main.do";
	}

	// 회원가입 페이지
	@RequestMapping("user/userjoin.do")
	public String user_join(HttpServletRequest request, HttpServletResponse response)
	{
		// 힌트목록
		UserDAO dao=UserDAO.newInstance();
		List<String> hList=dao.hintQuestion();
		request.setAttribute("hList", hList);
		
		request.setAttribute("user_jsp", "../user/userjoin.jsp");
		request.setAttribute("main_jsp", "../user/user_main.jsp");
		
		return "../main/main.jsp";
	}
	
	// 아이디 체크
	@RequestMapping("user/idcheck.do")
	public String user_idcheck(HttpServletRequest request,HttpServletResponse response)
	{
		return "../user/idcheck.jsp";
	}
	
	@RequestMapping("user/idcheck_ok.do")
	public void user_idcheck_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id");
		UserDAO dao=UserDAO.newInstance();
		int count=dao.UserIdCheck(id);
		try
		{
			// Ajax로 값을 전송
			PrintWriter out=response.getWriter();
			out.write(String.valueOf(count));
		} catch (Exception ex){}
	}
	
	// 우편번호
	@RequestMapping("user/post.do")
	public String user_post(HttpServletRequest request,HttpServletResponse response)
	{
		return "../user/post.jsp";
	}
	
	@RequestMapping("user/post_ok.do")
	public void user_post_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch(Exception ex) {}
		
		String dong=request.getParameter("dong");
		UserDAO dao=UserDAO.newInstance();
		int count=dao.postCount(dong);
		
		JSONArray arr=new JSONArray();
		if(count==0)
		{
			JSONObject obj=new JSONObject();
			obj.put("count", count);
			arr.add(obj);			
		}
		else
		{
			int i=0;
			List<ZipcodeVO> list=dao.post(dong);
			for(ZipcodeVO vo:list)
			{
				JSONObject obj=new JSONObject();
				// {zipcode:111,addr:'...',count:2},{}
				obj.put("zipcode", vo.getZipcode());
				obj.put("address", vo.getAddress());
				if(i==0)
				{
					obj.put("count",count);
				}
				arr.add(obj);
				i++;
			}
		}
		
		try 
		{
			response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(arr.toJSONString());
		}
		catch (Exception ex) {}
	}
	
	// 전화번호 체크
	@RequestMapping("user/phonecheck_ok.do")
	public void phonecheck_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String phone=request.getParameter("phone");
		UserDAO dao=UserDAO.newInstance();
		int count=dao.userPhoneCheck(phone);
		try
		{
			PrintWriter out=response.getWriter(); //메모리에 저장
			out.println(count); // ajax result에 보낸다 
		}catch(Exception ex) {}
	}
		
	// 회원가입 
	@RequestMapping("user/join_ok.do")
	public String user_join_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try 
		{
			request.setCharacterEncoding("UTF-8");
		} 
		catch (Exception ex) {}
		String userid=request.getParameter("id1");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String post=request.getParameter("post1");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String phone=request.getParameter("phone");
		int hno=Integer.valueOf(request.getParameter("hno"));
		String hintA=request.getParameter("hintA");
		
		UserVO vo=new UserVO();
		vo.setUserID(userid);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirth(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone);
		vo.setHno(hno);
		vo.setHintA(hintA);
		
		UserDAO dao=UserDAO.newInstance();
		dao.UserInsert(vo);
		return "redirect:../main/main.do";
	}
	
	// 아이디/비번찾기 페이지로
	@RequestMapping("user/idPwd.do")
	public String user_id_pwd(HttpServletRequest request, HttpServletResponse response)
	{
		// 힌트목록
		UserDAO dao=UserDAO.newInstance();
		List<String> hList=dao.hintQuestion();
		request.setAttribute("hList", hList);
			
		request.setAttribute("user_jsp", "../user/idPwd.jsp");
		request.setAttribute("main_jsp", "../user/user_main.jsp");
		return "../main/main.jsp";
	}
		
	// 아이디 찾기
	@RequestMapping("user/findid_ok.do")
	public void user_findid_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch(Exception ex) {}
		 
		String name=request.getParameter("find_nid");
		String email=request.getParameter("find_eid");		 
		UserDAO dao=UserDAO.newInstance();
		String res=dao.findId(name, email);
		try
		{
			PrintWriter out=response.getWriter();
			out.println(res);
		}
	 catch(Exception ex) {}
	}
	
	// 비밀번호 찾기
	@RequestMapping("user/findpwd_ok.do")
	public void user_findpwd_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch (Exception ex) {}
		
		String userID=request.getParameter("find_ipwd");
		int hno=Integer.valueOf(request.getParameter("find_hno"));
		String hintA=request.getParameter("find_hintA");
		UserDAO dao=UserDAO.newInstance();
		String res=dao.findPwd(userID, hno, hintA);
		try
		{
			PrintWriter out=response.getWriter();
			out.println(res);
		}
		catch(Exception ex) {}
	}
	
	// 회원정보 수정 페이지
	@RequestMapping("user/userupdate.do")
	public String user_Update(HttpServletRequest request, HttpServletResponse response)
	{
		UserDAO dao=UserDAO.newInstance();
		List<String> hList=dao.hintQuestion();
		request.setAttribute("hList", hList);
		
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("email");
				
		dao=UserDAO.newInstance();
		UserVO uvo=dao.userupdateinfo(userID);
		request.setAttribute("uvo", uvo);
				
		request.setAttribute("user_jsp", "../user/userupdate.jsp");
		request.setAttribute("main_jsp", "../user/userupdate_main.jsp");		
		return "../main/main.jsp";
	}
		
	// 회원정보 수정
	@RequestMapping("user/update_ok.do")
	public String user_update_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch(Exception ex) {}
		
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("email");
		String pwd=request.getParameter("update_pwd1");
		String email=request.getParameter("update_email");
		String post=request.getParameter("update_post");
		String addr1=request.getParameter("update_addr1");
		String addr2=request.getParameter("update_addr2");
		String phone=request.getParameter("update_phone");
			
		UserVO vo=new UserVO();
		vo.setUserID(id);
		vo.setPwd(pwd);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone);
		
		UserDAO dao=UserDAO.newInstance();
		dao.userupdate(vo);
		
		return "redirect: ../mypage/mypage_main.do";
	}


	// 회원탈퇴 페이지
	@RequestMapping("user/userdelete.do")
	public String user_delete(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("user_jsp", "../user/userdelete.jsp");
		request.setAttribute("main_jsp", "../user/userupdate_main.jsp");
		return "../main/main.jsp";
	}
			
	// 탈퇴하기
	@RequestMapping("user/delete_ok.do")
	public void user_delete_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String userID=request.getParameter("userID");
		String pwd=request.getParameter("pwd");
		UserDAO dao=UserDAO.newInstance();
		String result=dao.delete(userID, pwd);
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		try
		{
		   PrintWriter out=response.getWriter();
		   out.write(result);
		}
		catch(Exception ex) {}
		
	}
	
}
