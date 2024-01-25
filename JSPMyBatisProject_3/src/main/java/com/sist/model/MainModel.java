package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_main(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   
	   String type=request.getParameter("type");
	   String[] types={"","한식","양식","중식","일식"};
	   if(type==null)
		   type="1";
	   
	   int rowSize=20;
	   int start=(curpage*rowSize)-(rowSize-1);
	   int end=curpage*rowSize;
	   
	   Map map=new HashMap();
	   map.put("type", types[Integer.parseInt(type)]);
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<FoodVO> list=FoodDAO.foodListData(map);
	   for(FoodVO vo:list)
	   {
		   String name=vo.getName();
		   if(name.length()>15)
		   {
			   name=name.substring(0,15)+"...";
			   vo.setName(name);
		   }
		   vo.setName(name);
	   }
	   int totalpage=FoodDAO.foodTotalPage(map);
	   
	   // home.jsp에 출력할 내용물 전송 
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage",startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("list", list);
	   request.setAttribute("type", type);
	   
	   request.setAttribute("main_jsp", "../main/home.jsp");
	   return "../main/main.jsp";
   }
}
