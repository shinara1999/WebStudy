package com.sist.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.AllReplyDAO;
import com.sist.dao.BoardDAO;
import com.sist.vo.AllReplyVO;
import com.sist.vo.NoticeVO;
import com.sist.vo.ProgramVO;
import com.sist.vo.QnaCommentVO;
import com.sist.vo.QnaVO;

public class BoardModel {
	private String[] types= {
			"title",
			"content"
	};
	@RequestMapping("Board/notice.do")
	public String board_notice(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String typeno=request.getParameter("typeno");
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		// 검색
		String search=request.getParameter("search");
		String searchType=request.getParameter("searchType");
		//----------
		
		BoardDAO dao=BoardDAO.newInstance();
		List<NoticeVO> list=null;
		int totalcnt=0;
		if(search==null||search=="") {
			list=dao.noticeListData(curpage, Integer.parseInt(typeno));
			totalcnt=dao.noticeTotalCnt(Integer.parseInt(typeno));
		}else {
			list=dao.noticeListData(curpage, Integer.parseInt(typeno),search,types[Integer.parseInt(searchType)]);
			totalcnt=dao.noticeTotalCnt(Integer.parseInt(typeno),search,types[Integer.parseInt(searchType)]);
		}
		
		int count=totalcnt;
		int totalpage=(int) Math.ceil(count/(double)dao.getROW());
		count=count-((curpage*dao.getROW())-dao.getROW());
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("typeno", typeno);
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("totalcnt", totalcnt);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("board_jsp", "../Board/notice.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/notice_detail.do")
	public String board_notice_detail(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		String typeno=request.getParameter("typeno");
		BoardDAO dao=BoardDAO.newInstance();
		NoticeVO vo=dao.noticeDetailData(Integer.parseInt(no), Integer.parseInt(typeno));
		
		request.setAttribute("vo", vo);
		request.setAttribute("board_jsp", "../Board/notice_detail.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/notice_download.do")
	public void notice_download(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fn=request.getParameter("fn");
		System.out.println(fn);
		try {
			File file=new File("C:\\download\\"+fn);
			// 1. 헤더 설정 => filename,filesize
			response.setHeader("Content-Disposition", "Attachment;filename="+URLEncoder.encode(fn, "UTF-8"));
			response.setContentLength((int) file.length());
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); // 서버에서 읽기
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); // 클라이언트에 저장
			byte[] buffer=new byte[1024]; // 1024씩
			int i=0;
			while((i=bis.read(buffer, 0, 1024))!=-1) {
				bos.write(buffer, 0, i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("Board/qna.do")
	public String board_qna(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		// 검색
		String search=request.getParameter("search");
		String searchType=request.getParameter("searchType");
		//----------
		
		BoardDAO dao=BoardDAO.newInstance();
		List<QnaVO> list=null;
		int totalcnt=0;
		if(search==null||search=="") {
			list=dao.qnaListData(curpage);
			totalcnt=dao.qnaTotalCnt();
		}else {
			list=dao.qnaListData(curpage,search,types[Integer.parseInt(searchType)]);
			totalcnt=dao.qnaTotalCnt(search,types[Integer.parseInt(searchType)]);
		}

		int count=totalcnt;
		int totalpage=(int) Math.ceil(count/(double)dao.getROW());
		count=count-((curpage*dao.getROW())-dao.getROW());
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("totalcnt", totalcnt);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("board_jsp", "../Board/qna.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/qna_detail.do")
	public String board_qna_detail(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		BoardDAO dao=BoardDAO.newInstance();
		QnaVO vo=dao.qnaDetailData(Integer.parseInt(no));
		
		QnaCommentVO qcvo=null;
		if(vo.getStatus().equals("y")) {
			qcvo=new QnaCommentVO();
			qcvo=dao.qnaSelectComment(Integer.parseInt(no));
		}
		
		AllReplyDAO rdao=AllReplyDAO.newInstance();
		List<AllReplyVO> rList=rdao.replyListData(1, Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("qcvo", qcvo);
		request.setAttribute("rList", rList);
		request.setAttribute("board_jsp", "../Board/qna_detail.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/qna_insert.do")
	public String board_qna_insert(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("board_jsp", "../Board/qna_insert.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/qna_insert_ok.do")
	public String board_qna_insert_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String locking=request.getParameter("locking");
		
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("email");
		String name=(String) session.getAttribute("name");
		
		QnaVO vo=new QnaVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setLocking(locking);
		vo.setUserid(userid);
		vo.setName(name);
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaInsertData(vo);
		
		return "redirect:../Board/qna.do";
	}
	@RequestMapping("Board/qna_update.do")
	public String board_qna_update(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		BoardDAO dao=BoardDAO.newInstance();
		QnaVO vo=dao.qnaUpdateData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("board_jsp", "../Board/qna_update.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("Board/qna_update_ok.do")
	public String board_qna_update_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String no=request.getParameter("no");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String locking=request.getParameter("locking");
		
		QnaVO vo=new QnaVO();
		vo.setNo(Integer.parseInt(no));
		vo.setTitle(title);
		vo.setContent(content);
		vo.setLocking(locking);
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaUpdate(vo);
		
		return "redirect:../Board/qna.do";
	}
	@RequestMapping("Board/qna_delete.do")
	public String board_qna_delete(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaDelete(Integer.parseInt(no));
		
		return "redirect:../Board/qna.do";
	}
	// 관리자 QNA
	@RequestMapping("Board/qna_comment_ok.do")
	public String qna_comment_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String no=request.getParameter("sqno");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		QnaCommentVO vo=new QnaCommentVO();
		vo.setSqno(Integer.parseInt(no));
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaInsertComment(vo);
		
		return "redirect:../admin/qnaList.do";
	}
	@RequestMapping("Board/qna_comment_delete.do")
	public String qna_comment_delete(HttpServletRequest request,HttpServletResponse response) {
		String commentno=request.getParameter("commentno");
		String no=request.getParameter("no");
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaDeleteComment(Integer.parseInt(commentno));
		return "redirect:../Board/qna_detail.do?no="+no;
	}
	@RequestMapping("Board/qna_select_comment.do")
	public void qna_select_comment(HttpServletRequest request,HttpServletResponse response) {
		String sqno=request.getParameter("sqno");
		BoardDAO dao=BoardDAO.newInstance();
		QnaCommentVO vo=dao.qnaSelectComment(Integer.parseInt(sqno));
		
		JSONObject obj=new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("title", vo.getTitle());
		obj.put("content", vo.getContent());
		obj.put("dbday", vo.getDbday());
		
		try {
			response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("Board/qna_comment_update.do")
	public String qna_comment_update(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		System.out.println(no);
		System.out.println(title);
		System.out.println(content);
		
		QnaCommentVO vo=new QnaCommentVO();
		vo.setNo(Integer.parseInt(no));
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.qnaUpdateComment(vo);
		
		return "redirect:../admin/qnaList.do";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("Board/calendar.do")
	public String board_calendar(HttpServletRequest request,HttpServletResponse response) {
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		StringTokenizer st=new StringTokenizer(today, "-");
		int year=Integer.parseInt(st.nextToken());
		int month=Integer.parseInt(st.nextToken());
		int day=Integer.parseInt(st.nextToken());
		String strYear=request.getParameter("year");
		if(strYear!=null) year=Integer.parseInt(strYear);
		String strMonth=request.getParameter("month");
		if(strMonth!=null) month=Integer.parseInt(strMonth);
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK);
		week=week-1; 
		int lastday=cal.getActualMaximum(Calendar.DATE);
		BoardDAO dao=BoardDAO.newInstance();
		List<ProgramVO> calendarProgramList=dao.calendarProgramData(String.valueOf(year), String.valueOf(month));
		
		request.setAttribute("week", week);
		request.setAttribute("lastday", lastday);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("cplist", calendarProgramList);
		request.setAttribute("board_jsp", "../Board/calendar.jsp");
		request.setAttribute("main_jsp", "../Board/board_main.jsp");
		return "../main/main.jsp";
	}
//	@RequestMapping("Board/calendar_ajax.do")
//	public void board_calendar_ajax(HttpServletRequest request,HttpServletResponse response) {
//		String year=request.getParameter("year");
//		String month=request.getParameter("month");
//		Calendar cal=Calendar.getInstance();
//		cal.set(Calendar.YEAR, Integer.parseInt(year));
//		cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
//		cal.set(Calendar.DATE, 1);
//		int week=cal.get(Calendar.DAY_OF_WEEK);
//		week=week-1; 
//		int lastday=cal.getActualMaximum(Calendar.DATE);
//		BoardDAO dao=BoardDAO.newInstance();
//		List<ProgramVO> calendarProgramList=dao.calendarProgramData(String.valueOf(year), String.valueOf(month));
//		
//		JSONArray arr=new JSONArray();
//		for(ProgramVO vo:calendarProgramList) {
//			JSONObject obj=new JSONObject();
//			obj.put("pno", String.valueOf(vo.getPno()));
//			obj.put("title", vo.getTitle());
//			obj.put("edu1", vo.getEdu1_str());
//			obj.put("edu2", vo.getEdu2_str());
//			obj.put("week", vo.getWeek());
//			arr.add(obj);
//		}
//		JSONObject obj=new JSONObject();
//		obj.put("week", String.valueOf(week));
//		obj.put("lastday", String.valueOf(lastday));
//		obj.put("year", String.valueOf(year));
//		obj.put("month", String.valueOf(month));
//		obj.put("arr", arr);
//		/* obj.put("cplist", calendarProgramList); */
//		try {
//			response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.write(obj.toJSONString());
//			/* out.write(arr.toJSONString()); */
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
