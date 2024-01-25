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

public class FoodModel {
  @RequestMapping("food/list.do")
  public void food_list(HttpServletRequest request, HttpServletResponse response)
  {
	   Map map=new HashMap();
	   map.put("type", "한식");
	   map.put("start", 1);
	   map.put("end", 20);
	   
	   List<FoodVO> list=FoodDAO.foodListData(map);
	   
	   JSONArray arr=new JSONArray(); // [] {"poster":"http~~"}
	   for(FoodVO vo:list)
	   {
		   JSONObject obj=new JSONObject();
		   obj.put("poster", "https://www.menupan.com"+vo.getPoster());
		   obj.put("name", vo.getName());
		   arr.add(obj);
	   }
	   try
	   {
		   response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		   PrintWriter out=response.getWriter();
		   out.write(arr.toJSONString());
	   }catch (Exception e) {}
  }
}








