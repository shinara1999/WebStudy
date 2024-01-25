package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

public class VueModel {
  @RequestMapping("vue/list_vue.do")
  public void vue_list(HttpServletRequest request,
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
	   
	   JSONArray arr=new JSONArray();
	   int i=0;
	   for(FoodVO vo:list)
	   {
		   JSONObject obj=new JSONObject();
		   obj.put("fno", vo.getFno());
		   obj.put("name", vo.getName());
		   obj.put("poster", "https://www.menupan.com"+vo.getPoster());
		   
		   if(i==0)
		   {
			  obj.put("curpage", curpage);
			  obj.put("totalpage", totalpage);
			  obj.put("startPage", startPage);
			  obj.put("endPage", endPage);
			  obj.put("type", type);
		   }
		   
		   arr.add(obj);
		   i++;
	   }
	   try
	   {
		   response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		   PrintWriter out=response.getWriter();
		   out.write(arr.toJSONString());
	   }catch(Exception ex) {}
	   
  }
}
