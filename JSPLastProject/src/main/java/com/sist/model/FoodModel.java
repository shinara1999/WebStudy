package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
/*
 *    사용자 요청        *.do
 *    ========  --- DisptacherServlet 
 *      .do         |
 *                  1) Model클래스 찾기 ==> XML
 *                  2) 해당 메소드 찾기 역할 
 *                     =========
 *                      => 구분자 (어노테이션)
 *                  2-1) 메소드 호출 => invoke()
 *                  2-2) request를 받아 온다 
 *                       ======= setAttribute()
 *                  2-3) request를 해당 JSP로 전송 
 *                       ======================
 *                        forward(request,response)
 *       => DAO (DBCP) => MyBatis
 *       => Front(Ajax,Jquery) => Vue
 *          1. hosting 
 *     --------------------------------
 *       => MyBatis => JPA (MySQL)
 *       => Front => Vue => React / Redux
 *       => Spring => Spring-Boot
 *       ==============================
 *       => 1. ThymeLeaf 
 *       => 2. React
 *       => 3. Redux => React-Query
 *       ============================== 서버를 개별적으로 
 *       => 통합 (MSA=>Spring Cloud)
 */
/*
String[] guList = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
		"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
		"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
		"강동구" };
*/
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class FoodModel {
  private String[] guList = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
			"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
			"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
			"강동구" };
  @RequestMapping("food/location.do")
  public String food_location(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  
	  
	  
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  
	  String gu=request.getParameter("gu");
	  if(gu==null)
		  gu="4";
	  String address=guList[Integer.parseInt(gu)];
	  /*String address=request.getParameter("address");
	  if(address==null)
		  address="마포구";*/
	  
	  
	  FoodDAO dao=FoodDAO.newInstance();
	  List<FoodVO> list=dao.foodFindData(curpage, address);
	  int totalpage=dao.foodFindTotalPage(address);
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // 출력할 데이터 전송 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	  request.setAttribute("gu", gu);
	  request.setAttribute("address", address);
	  // => main.jsp에 include되는 파일 지정 
	  request.setAttribute("main_jsp", "../food/location.jsp");
	  CommonsModel.commonsFooterData(request);
	  return "../main/main.jsp";
  }
  @RequestMapping("food/location_detail.do")
  /*
   *       vo.setPoster(rs.getString(1));
		   vo.setName(rs.getString(2));
		   vo.setScore(rs.getDouble(3));
		   vo.setPhone(rs.getString(4));
		   vo.setAddress(rs.getString(5));
		   vo.setType(rs.getString(6));
		   vo.setTheme(rs.getString(7));
		   vo.setPrice(rs.getString(8));
		   vo.setSeat(rs.getString(9));
		   vo.setTime(rs.getString(10));
		   vo.setContent(rs.getString(11));
   */
  public void foodFindDetailData(HttpServletRequest request, HttpServletResponse response)
  {
	  // JSON 전송 
	  String fno=request.getParameter("fno");
	  FoodDAO dao=FoodDAO.newInstance();
	  FoodVO vo=dao.foodFindDetailData(Integer.parseInt(fno));
	  JSONObject obj=new JSONObject();
	  obj.put("poster", vo.getPoster());//{"poster":값,......}
	  obj.put("name", vo.getName());
	  obj.put("score", vo.getScore());
	  obj.put("phone", vo.getPhone());
	  obj.put("address", vo.getAddress());
	  obj.put("time", vo.getTime());
	  obj.put("theme", vo.getTheme());
	  obj.put("type", vo.getType());
	  obj.put("price", vo.getPrice());
	  obj.put("seat", vo.getSeat());
	  obj.put("content", vo.getContent());
	  
	  // 전송 => ajax
	  try
	  {
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  PrintWriter out=response.getWriter();
		  out.write(obj.toJSONString());
	  }catch(Exception ex) {}
  }
  
  @RequestMapping("food/list.do")
  public String food_list(HttpServletRequest request, HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  
	  FoodDAO dao=FoodDAO.newInstance();
	  List<FoodVO> list=dao.foodListData(curpage);
	  int totalpage=dao.foodListTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // 출력할 데이터 전송 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	 
	  request.setAttribute("main_jsp", "../food/list.jsp");
	  CommonsModel.commonsFooterData(request);
	  return "../main/main.jsp";
  }
  
  @RequestMapping("food/detail.do")
  public String food_find_detail(HttpServletRequest request, HttpServletResponse response)
  {
	  String fno=request.getParameter("fno");
	  FoodDAO dao=FoodDAO.newInstance();
	  FoodVO vo=dao.foodFindDetailData(Integer.parseInt(fno));
	  vo.setFno(Integer.parseInt(fno));
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../food/detail.jsp");
	  CommonsModel.commonsFooterData(request);
	  return "../main/main.jsp";
  }
  
  @RequestMapping("food/food_before_detail.do")
  public String food_before(HttpServletRequest request, HttpServletResponse response)
  {
	  String fno=request.getParameter("fno");
	  Cookie cookie=new Cookie("food_"+fno, fno);
	  cookie.setPath("/");
	  cookie.setMaxAge(60*60*24);
	  response.addCookie(cookie);
	  return "redirect:../food/food_detail.do?fno="+fno;
  }
  
  @RequestMapping("food/food_detail.do")
  public String food_detail(HttpServletRequest request, HttpServletResponse response)
  {
	  String fno=request.getParameter("fno");
	  FoodDAO dao=FoodDAO.newInstance();
	  FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
	  String addr=vo.getAddress();
	  String addr1=addr.substring(addr.indexOf(" ")+1);
	  String addr2=addr1.substring(0,addr1.indexOf(" "));
	  System.out.println(addr2.trim());
	  request.setAttribute("addr", addr2.trim());
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../food/food_detail.jsp");
	  return "../main/main.jsp";
  }
}






















