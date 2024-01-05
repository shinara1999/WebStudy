package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Robot;
import java.io.*;
import java.net.URLEncoder;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
// JDBC => DBCP => ORM (IBatis/Hibernate)
//                 google인수 => MyBatis
//                              DataSet => JPA
// JSP (View) => ThymeLeaf (html)
// 서버 / 클라이언트 => JSON
// === 통구조 / 분산구조 (MSA) 
public class DataBoardModel {
   @RequestMapping("databoard/list.do")
   public String databoard_list(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=10;
	   int start=(curpage*rowSize)-(rowSize-1);
	   int end=curpage*rowSize;
	   
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<DataBoardVO> list=DataBoardDAO.databoardListData(map);
	   
	   int count=DataBoardDAO.databoardRowCount();// 30 + 2
	   int totalpage=(int)(Math.ceil(count/10.0));
	   count=count-((curpage*rowSize)-rowSize);// 20
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("list", list);
	   request.setAttribute("count", count);
	   request.setAttribute("main_jsp", "../databoard/list.jsp");
	   CommonsModel.commonsFooterData(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("databoard/insert.do")
   public String databoard_insert(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   CommonsModel.commonsFooterData(request);
	   request.setAttribute("main_jsp", "../databoard/insert.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("databoard/insert_ok.do")
   public String databoard_insert_ok(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
		   String path="c:\\download";
		   String enctype="UTF-8";
		   int max_size=1024*1024*500;
		   MultipartRequest mr=
				   new MultipartRequest(request,path,max_size,enctype,
						   new DefaultFileRenamePolicy());
		   String name=mr.getParameter("name");
		   String subject=mr.getParameter("subject");
		   String content=mr.getParameter("content");
		   String pwd=mr.getParameter("pwd");
		   String filename=mr.getFilesystemName("upload");
		   DataBoardVO vo=
				   new DataBoardVO();
		   vo.setName(name);
		   vo.setContent(content);
		   vo.setSubject(subject);
		   vo.setPwd(pwd);
		   if(filename==null)
		   {
			   vo.setFilename("");
			   vo.setFilesize(0);
		   }
		   else
		   {
			   File file=new File(path+"\\"+filename);
			   vo.setFilename(filename);
			   vo.setFilesize((int)file.length());
		   }
		   DataBoardDAO.databoardInsert(vo);
	   }catch(Exception ex) {ex.printStackTrace();}
	   return "redirect:../databoard/list.do";
   }
   @RequestMapping("databoard/detail.do")
   public String databoard_detail(HttpServletRequest request,
        HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   DataBoardVO vo=DataBoardDAO.databoardDetailData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../databoard/detail.jsp");
	   CommonsModel.commonsFooterData(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("databoard/download.do")
   public void databoard_download(HttpServletRequest request,
	        HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String fn=request.getParameter("fn");
	   try
	   {
		   File file=new File("c:\\download\\"+fn);
		   //1. header설정 => filename,file크기
		   response.setHeader("Content-Disposition", 
				   "Attachment;filename="
				   +URLEncoder.encode(fn,"UTF-8"));
		   response.setContentLength((int)file.length());
		   
		   BufferedInputStream bis=
				   new BufferedInputStream(
						   new FileInputStream(file));
		   BufferedOutputStream bos=
				   new BufferedOutputStream(response.getOutputStream());
		   
		   byte[] buffer=new byte[1024];
		   int i=0;
		   while((i=bis.read(buffer, 0, 1024))!=-1)
		   {
			   bos.write(buffer, 0, i);
		   }
		   
		   bis.close();
		   bos.close();
	   }catch(Exception ex){}
   }
   @RequestMapping("databoard/delete_ok.do")
   public void databoard_delete(HttpServletRequest request,
	        HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   String pwd=request.getParameter("pwd");
	   // data:{"no":no,"pwd":pwd}
	   DataBoardVO vo=DataBoardDAO.databoardFileInfoData(Integer.parseInt(no));
	   String res=DataBoardDAO.databoardDelete(Integer.parseInt(no), pwd);
	   if(vo.getFilesize()>0)
	   {
		   try
		   {
			   File file=new File("c:\\download\\"+vo.getFilename());
			   file.delete();
		   }catch(Exception ex){}
	   }
	   
	   // ajax에 값을 전송 
	   try
	   {
		   PrintWriter out=response.getWriter();
		   out.write(res);// no , yes
	   }catch(Exception ex) {}
   }
   @RequestMapping("databoard/update.do")
   public String databoard_update(HttpServletRequest request,
	        HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   DataBoardVO vo=DataBoardDAO.databoardUpdateData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   CommonsModel.commonsFooterData(request);
	   request.setAttribute("main_jsp", "../databoard/update.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("databoard/update_ok.do")
   public void databoard_update_ok(HttpServletRequest request,
	        HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String no=request.getParameter("no");
	   String name=request.getParameter("name");
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   
	   DataBoardVO vo=new DataBoardVO();
	   vo.setNo(Integer.parseInt(no));
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   
	   String res=DataBoardDAO.databoardUpdate(vo);
	   try
	   {
		   PrintWriter out=response.getWriter();
		   out.write(res);
	   }catch(Exception ex) {}
   }
}




