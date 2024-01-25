package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyDAO;
import com.sist.dao.SeoulDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;
import com.sist.vo.SeoulVO;

import java.util.*;
public class SeoulModel {
  @RequestMapping("seoul/weather.do")
  public String seoul_weather(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  try
	  {
		  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
		  Element section=doc.selectFirst("section#content");
		  String html="<section id=\"content\">";
		  html+=section.html();
		  html+="</section>";
		  html=html.replace("src=\"", "src=\"https://korean.visitseoul.net");
		  html=html.replace("제공 : 케이웨더(Kweather)", "");
		  request.setAttribute("html", html);
	  }catch(Exception ex) {}
	  request.setAttribute("main_jsp", "../seoul/weather.jsp");
	  return "../main/main.jsp";
  }
  /*
   *   어노테이션 (인덱스) : 구분자 
   *   @ => class 찾기 (메모리 할당) => TYPE
   *   class A
   *   {
   *      @ => 멤버변수 찾기 (초기값 설정) => FIELD
   *      B b;
   *      public A(@ => 매개변수 B b) => 호출 매개변수를 값을 설정
   *      {        PARAMETER
   *      }
   *      @ => 메소드 찾기 => invoke()를 이용해서 호출 => METHOD
   *      public void display()
   *      {
   *      }
   *   }
   *   
   */
  @RequestMapping("seoul/location.do")
  // 어노테이션 => 제어하는 곳 (항상 밑(옆)에 있다)
  public String seoul_location(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=20;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=(rowSize*curpage);
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> list=SeoulDAO.seoulLocationListData(map);
	  int totalpage=SeoulDAO.seoulLocationTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // location.jsp에서 출력에 필요한 데이터를 전송 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	  
	  request.setAttribute("main_jsp", "../seoul/location.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("seoul/nature.do")
  public String seoul_natrue(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=20;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=(rowSize*curpage);
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> list=SeoulDAO.seoulNatureListData(map);
	  int totalpage=SeoulDAO.seoulNatureTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // location.jsp에서 출력에 필요한 데이터를 전송 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	  
	  request.setAttribute("main_jsp", "../seoul/nature.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("seoul/shop.do")
  public String seoul_shop(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=20;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=(rowSize*curpage);
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> list=SeoulDAO.seoulShopListData(map);
	  int totalpage=SeoulDAO.seoulShopTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // location.jsp에서 출력에 필요한 데이터를 전송 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	  
	  request.setAttribute("main_jsp", "../seoul/shop.jsp");
	  return "../main/main.jsp";
  }
  // 상세보기 
  @RequestMapping("seoul/location_detail.do")
  public String location_detail(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  // 100-794 서울특별시 중구 남대문로 39, 한국은행건물 (남대문로3가)
	  // 서울특별시 중구 남대문로 39, 한국은행건물 (남대문로3가)
	  String no=request.getParameter("no");
	  SeoulVO vo=SeoulDAO.seoulLocationDetailData(Integer.parseInt(no));
	  request.setAttribute("vo", vo);
	  String addr=vo.getAddress().substring(8);
	  String addr1=addr.substring(addr.indexOf(" "));
	  String addr2=addr1.trim().substring(0,addr1.trim().indexOf(" "));
	  System.out.println(addr2.trim());
	  List<FoodVO> list=SeoulDAO.seoulFoodData(addr2);
	  request.setAttribute("list", list);
	  request.setAttribute("addr", addr2);
	  
	  // 댓글 전송 (프로시저)
	  ReplyDAO dao=ReplyDAO.newInstance();
	  List<ReplyVO> rList=dao.replyListData(1, Integer.parseInt(no));  // (int type, int cno) ==> 명소:1
	  request.setAttribute("rList", rList);
	  request.setAttribute("rcount", rList.size()); // 카운트값은 여기서 가지고 온다. (0이면 댓글이 없습니다 출력)
	  
	  request.setAttribute("main_jsp", "../seoul/location_detail.jsp");
	  return "../main/main.jsp";
  }
  // 브라우저(클라이언트) => 전송 => 서버
  // => 주소입력창 
  
	/*
	 * public static void main(String[] args) { String
	 * addr="100-794 서울특별시 중구 남대문로 39, 한국은행건물 (남대문로3가)"; addr=addr.substring(8);
	 * String addr1=addr.substring(addr.indexOf(" ")); String
	 * addr2=addr1.trim().substring(0,addr1.trim().indexOf(" "));
	 * System.out.println(addr2.trim()); }
	 */
}
