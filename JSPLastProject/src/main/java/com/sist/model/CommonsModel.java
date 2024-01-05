package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.manager.*;
public class CommonsModel {
   public static void commonsFooterData(HttpServletRequest request)
   {
	   // 1. 공지사항 => hit가 많은 
	   NoticeDAO ndao=NoticeDAO.newInstance();
	   List<NoticeVO> noList=ndao.noticeTop7();
	   request.setAttribute("noList", noList);
	   // 2. 맛집 뉴스 
	   List<NewsVO> nList=NewsManager.newsSearchData("맛집");
	   request.setAttribute("nList", nList);
	   // 3. 인기 맛집 => hit
	   FoodDAO dao=FoodDAO.newInstance();
	   List<FoodVO> fList=dao.foodTop7();
	   request.setAttribute("fList", fList);
   }
}