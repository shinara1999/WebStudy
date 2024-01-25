package com.sist.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.RequestMapping;
import com.sist.dao.AdminDAO;
import com.sist.dao.BoardDAO;
import com.sist.dao.ProgramDAO;
import com.sist.vo.BookReserve;
import com.sist.vo.BookReserveCountVO;
import com.sist.vo.NoticeVO;
import com.sist.vo.ProgramVO;
import com.sist.vo.QnaVO;

public class AdminModel {
	// 어드민 메인 화면
	@RequestMapping("admin/main.do")
	public String admin_main(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../adminpage/admin_info.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 프로그램 관리 화면
	@RequestMapping("admin/programList.do")
	public String admin_programList(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		AdminDAO dao=AdminDAO.newInstance();
		ProgramDAO pdao=ProgramDAO.newInstance();
		pdao.programStatusUpdate(0);
		List<ProgramVO> list=dao.programListData(curpage);
		int list_size=list.size();
		int totalcount=dao.programTotalCnt();
		int count=totalcount;
		int totalpage=(int)(Math.ceil(count/dao.getROW_SIZE()));
		count=count-((curpage*dao.getROW_SIZE())-dao.getROW_SIZE());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("count", count);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("admin_jsp", "../adminpage/admin_programList.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 프로그램 추가 화면
	@RequestMapping("admin/programInsert.do")
	public String admin_programInsert(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../adminpage/admin_programInsert.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 프로그램 추가
	@RequestMapping("admin/programInsert_ok.do")
	public String admin_programInsert_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
//			String path=request.getServletContext().getRealPath("images");
//			System.out.println(request.getServletContext().getContextPath());
			String path="C:\\download";
			String enctype="UTF-8";
			int max_size=1024*1024*500;
//			MultipartRequest mr=new MultipartRequest(request, path, max_size, enctype,new DefaultFileRenamePolicy());
			MultipartRequest mr=new MultipartRequest(request, path, max_size, enctype);
			String title=mr.getParameter("title");
			
			String[] targets= {
				"영유아","어린이","청소년","성인","누구나"	
			};
			String[] target1s=mr.getParameterValues("target1");
			String target1="";
			for(int i=0;i<target1s.length;i++) {
				if(i==0) {
					target1+=targets[Integer.parseInt(target1s[i])];
				}else {
					target1+=","+targets[Integer.parseInt(target1s[i])];
				}
			}
			
			String target2=mr.getParameter("target2");
			String place=mr.getParameter("place");
			
			SimpleDateFormat eduFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date edu1=eduFormat.parse(mr.getParameter("edu1"));
			Date edu2=eduFormat.parse(mr.getParameter("edu2"));
			
			String time1=mr.getParameter("time1");
			String time2=mr.getParameter("time2");
			String time=time1+"~"+time2;
			
			SimpleDateFormat acceptFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date accept1=acceptFormat.parse(mr.getParameter("accept1"));
			Date accept2=acceptFormat.parse(mr.getParameter("accept2"));
			
//			String[] weeks=mr.getParameterValues("week");
//			String week="";
//			for(int i=0;i<weeks.length;i++) {
//				if(i==0) {
//					week+=weeks[i];
//				}else {
//					week+=","+weeks[i];
//				}
//			}
			String dd=mr.getParameter("dd");
			dd=dd.substring(1);
			String capacity=mr.getParameter("capacity");
			String waitingCap=mr.getParameter("waitingCap");
			String poster=mr.getFilesystemName("poster");
			String content=mr.getParameter("content");
			
			ProgramVO vo=new ProgramVO();
			vo.setTitle(title);
			vo.setTarget1(target1);
			vo.setTarget2(target2);
			vo.setPlace(place);
			vo.setEdu1_str(eduFormat.format(edu1));
			vo.setEdu2_str(eduFormat.format(edu2));
			vo.setTime(time);
			vo.setAccept1_str(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(accept1));
			vo.setAccept2_str(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(accept2));
			vo.setWeek(dd);
			vo.setCapacity(Integer.parseInt(capacity));
			vo.setWaitingCap(Integer.parseInt(waitingCap));
			File file=new File(path+"\\"+poster);
			vo.setPoster(poster);
			vo.setPoster_size((int) file.length());
			vo.setContent(content);
			
			AdminDAO dao=AdminDAO.newInstance();
			dao.programInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../admin/programList.do";
	}
	// 어드민 프로그램 날짜 선택
	@RequestMapping("admin/programSelectDate.do")
	public String admin_programSelectDate(HttpServletRequest request,HttpServletResponse response) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
//		String today=sdf.format(new Date());
		String edu=request.getParameter("edu1");
		StringTokenizer st=new StringTokenizer(edu, "-");
		String sy=st.nextToken();
		String sm=st.nextToken();
		String sd=st.nextToken();
		int year=Integer.parseInt(sy);
		int month=Integer.parseInt(sm);
		int day=Integer.parseInt(sd);
		// 요일
		String[] strWeek= {
			"일","월","화","수","목","금","토"
		};
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		// 요일 구하기
		int week=cal.get(Calendar.DAY_OF_WEEK);
		int lastday=cal.getActualMaximum(Calendar.DATE);
		week=week-1;
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("week", week);
		request.setAttribute("lastday", lastday);
		request.setAttribute("strWeek", strWeek);
		
		String edu1=request.getParameter("edu1");
		String edu2=request.getParameter("edu2");
		
		LocalDate startDate=LocalDate.parse(edu1);
		LocalDate endDate=LocalDate.parse(edu2);
		
		String dates="";
		while (!startDate.isAfter(endDate)) {
			dates+=","+(startDate.format(DateTimeFormatter.ofPattern("d")));
            startDate = startDate.plusDays(1);
        }
		
		dates=dates.substring(1);
		System.out.println(dates);
		
		int[] rday=new int[32];
		st=new StringTokenizer(dates, ",");
		while(st.hasMoreTokens()) {
			int a=Integer.parseInt(st.nextToken());
			// 현재 날짜보다 큰 값만 판별
			if(a>=day) {
				rday[a]=1;
			}
		}
		
		request.setAttribute("rday", rday);
		
		return "../adminpage/select_Date.jsp";
	}
	// 어드민 프로그램 수정 화면
	@RequestMapping("admin/programUpdate.do")
	public String admin_programUpdate(HttpServletRequest request,HttpServletResponse response) {
		String pno=request.getParameter("pno");
		AdminDAO dao=AdminDAO.newInstance();
		ProgramVO vo=dao.programUpdateData(Integer.parseInt(pno));
		SimpleDateFormat acceptFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			vo.setAccept1_str(
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(
							acceptFormat.parse(vo.getAccept1_str())
					)
			);
			vo.setAccept2_str(
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(
							acceptFormat.parse(vo.getAccept2_str())
					)
			);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		System.out.println(vo.getPno());
//		System.out.println(vo.getPoster());
//		System.out.println(vo.getPoster_size());
//		System.out.println(vo.getTitle());
//		System.out.println(vo.getContent());
//		System.out.println(vo.getTarget1());
//		System.out.println(vo.getTarget2());
//		System.out.println(vo.getPlace());
//		System.out.println(vo.getEdu1_str());
//		System.out.println(vo.getEdu2_str());
//		System.out.println(vo.getTime());
//		System.out.println(vo.getWeek());
//		System.out.println(vo.getAccept1_str());
//		System.out.println(vo.getAccept2_str());
//		System.out.println(vo.getCapacity());
//		System.out.println(vo.getWaitingCap());
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../adminpage/admin_programUpdate.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 프로그램 수정
	@RequestMapping("admin/programUpdate_ok.do")
	public String admin_programUpdate_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String path="C:\\download";
			String enctype="UTF-8";
			int max_size=1024*1024*500;
//			MultipartRequest mr=new MultipartRequest(request, path, max_size, enctype,new DefaultFileRenamePolicy());
			MultipartRequest mr=new MultipartRequest(request, path, max_size, enctype);
			String pno=mr.getParameter("pno");
			String title=mr.getParameter("title");
			
			String[] targets= {
				"영유아","어린이","청소년","성인","누구나"	
			};
			String[] target1s=mr.getParameterValues("target1");
			String target1="";
			for(int i=0;i<target1s.length;i++) {
				if(i==0) {
					target1+=targets[Integer.parseInt(target1s[i])];
				}else {
					target1+=","+targets[Integer.parseInt(target1s[i])];
				}
			}
			
			String target2=mr.getParameter("target2");
			String place=mr.getParameter("place");
			
			SimpleDateFormat eduFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date edu1=eduFormat.parse(mr.getParameter("edu1"));
			Date edu2=eduFormat.parse(mr.getParameter("edu2"));
			
			String time1=mr.getParameter("time1");
			String time2=mr.getParameter("time2");
			String time=time1+"~"+time2;
			
			SimpleDateFormat acceptFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date accept1=acceptFormat.parse(mr.getParameter("accept1"));
			Date accept2=acceptFormat.parse(mr.getParameter("accept2"));
			
//			String[] weeks=mr.getParameterValues("week");
//			String week="";
//			for(int i=0;i<weeks.length;i++) {
//				if(i==0) {
//					week+=weeks[i];
//				}else {
//					week+=","+weeks[i];
//				}
//			}
			String dd=mr.getParameter("dd");
			dd=dd.substring(1);
			String capacity=mr.getParameter("capacity");
			String waitingCap=mr.getParameter("waitingCap");
			String poster=mr.getFilesystemName("poster");
			String content=mr.getParameter("content");
			
			ProgramVO vo=new ProgramVO();
			vo.setPno(Integer.parseInt(pno));
			vo.setTitle(title);
			vo.setTarget1(target1);
			vo.setTarget2(target2);
			vo.setPlace(place);
			vo.setEdu1_str(eduFormat.format(edu1));
			vo.setEdu2_str(eduFormat.format(edu2));
			vo.setTime(time);
			vo.setAccept1_str(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(accept1));
			vo.setAccept2_str(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(accept2));
			vo.setWeek(dd);
			vo.setCapacity(Integer.parseInt(capacity));
			vo.setWaitingCap(Integer.parseInt(waitingCap));
			File file=new File(path+"\\"+poster);
			vo.setPoster(poster);
			vo.setPoster_size((int) file.length());
			vo.setContent(content);
			
			AdminDAO dao=AdminDAO.newInstance();
			dao.programUpdate(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../admin/programList.do";
	}
	// 어드민 프로그램 삭제
	@RequestMapping("admin/programDelete.do")
	public String admin_programDelete(HttpServletRequest request,HttpServletResponse response) {
		String pno=request.getParameter("pno");
		AdminDAO dao=AdminDAO.newInstance();
		dao.programDelete(Integer.parseInt(pno));
		return "redirect:../admin/programList.do";
	}
	// 어드민 도서대출 관리
	@RequestMapping("admin/bookManagement.do")
	public String admin_bookManagement(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		AdminDAO dao=AdminDAO.newInstance();
		List<BookReserve> list=dao.bookReserveListData(curpage);
		int list_size=list.size();
		int totalcount=dao.bookReserveTotalCnt();
		int count=totalcount;
		int totalpage=(int)(Math.ceil(count/dao.getROW_SIZE()));
		count=count-((curpage*dao.getROW_SIZE())-dao.getROW_SIZE());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("count", count);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("admin_jsp", "../adminpage/admin_bookManagement.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 도서대출승인
	@RequestMapping("admin/bookReserveAuthorize.do")
	public String admin_bookReserveAuthorize(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		String isbn=request.getParameter("isbn");
		String userid=request.getParameter("userid");
		
		BookReserveCountVO vo=new BookReserveCountVO();
		vo.setBrno(Integer.parseInt(no));
		vo.setIsbn(isbn);
		vo.setUserid(userid);
		
		AdminDAO dao=AdminDAO.newInstance();
		dao.bookReserveAuthorize(vo);
		
		return "redirect:../admin/bookManagement.do";
	}
	// 어드민 공지사항 목록
	@RequestMapping("admin/noticeList.do")
	public String admin_noticeList(HttpServletRequest request,HttpServletResponse response) {
		String typeno=request.getParameter("typeno");
		String page=request.getParameter("page");
		
		if(page==null) page="1";
		if(typeno==null) typeno="0";
		int curpage=Integer.parseInt(page);
		BoardDAO dao=BoardDAO.newInstance();
		List<NoticeVO> list=dao.noticeListData(curpage, Integer.parseInt(typeno));
		int count=dao.noticeTotalCnt(Integer.parseInt(typeno));
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
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("admin_jsp", "../adminpage/admin_noticeList.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 공지사항 추가 화면
	@RequestMapping("admin/noticeInsert.do")
	public String admin_noticeInsert(HttpServletRequest request,HttpServletResponse response) {
		String typeno=request.getParameter("typeno");
		request.setAttribute("typeno", typeno);
		request.setAttribute("admin_jsp", "../adminpage/admin_noticeInsert.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	// 어드민 공지사항 추가
	@RequestMapping("admin/noticeInsert_ok.do")
	public String admin_noticeInsert_ok(HttpServletRequest request,HttpServletResponse response) {
		String path="C:\\download";
		String enctype="UTF-8";
		int max_size=1024*1024*500;
		MultipartRequest mr=null;
		try {
			mr=new MultipartRequest(request, path, max_size, enctype);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String typeno=mr.getParameter("typeno");
		String status=mr.getParameter("status");
		String title=mr.getParameter("title");
		String fileName=mr.getFilesystemName("file");
		String content=mr.getParameter("content");
		File file=new File(path+"\\"+fileName);
		
		NoticeVO vo=new NoticeVO();
		vo.setTypeno(Integer.parseInt(typeno));
		vo.setStatus(status);
		vo.setTitle(title);
		vo.setFilename(fileName);
		vo.setContent(content);
		vo.setFilesize((int) file.length());
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.noticeInsert(vo);
		
		return "redirect:../admin/noticeList.do?typeno="+typeno;
	}
	// 어드민 공지사항 수정 화면
	@RequestMapping("admin/noticeUpdate.do")
	public String admin_noticeUpdate(HttpServletRequest request,HttpServletResponse response) {
		String no=request.getParameter("no");
		String typeno=request.getParameter("typeno");
		BoardDAO dao=BoardDAO.newInstance();
		NoticeVO vo=dao.noticeDetailData(Integer.parseInt(no), Integer.parseInt(typeno));
		
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../adminpage/admin_noticeUpdate.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
	
	// 어드민 공지사항 수정
	@RequestMapping("admin/noticeUpdate_ok.do")
	public String admin_noticeUpdate_ok(HttpServletRequest request,HttpServletResponse response) {
		String path="C:\\download";
		String enctype="UTF-8";
		int max_size=1024*1024*500;
		MultipartRequest mr=null;
		try {
			mr=new MultipartRequest(request, path, max_size, enctype);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String no=mr.getParameter("no");
		String typeno=mr.getParameter("typeno");
		String status=mr.getParameter("status");
		String title=mr.getParameter("title");
		String fileName=mr.getFilesystemName("file");
		String content=mr.getParameter("content");
		File file=new File(path+"\\"+fileName);
		
		NoticeVO vo=new NoticeVO();
		vo.setNo(Integer.parseInt(no));
		vo.setTypeno(Integer.parseInt(typeno));
		vo.setStatus(status);
		vo.setTitle(title);
		vo.setFilename(fileName);
		vo.setContent(content);
		vo.setFilesize((int) file.length());
		
		BoardDAO dao=BoardDAO.newInstance();
		dao.noticeUpdate(vo);
		
		return "redirect:../admin/noticeList.do";
	}
	
	// 어드민 묻고답하기 관리
	@RequestMapping("admin/qnaList.do")
	public String admin_qnaList(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		BoardDAO dao=BoardDAO.newInstance();
		List<QnaVO> list=dao.qnaListData(curpage);
		
		int list_size=list.size();
		int totalcount=dao.qnaTotalCnt();
		int count=totalcount;
		int totalpage=(int)(Math.ceil(totalcount/dao.getROW()));
		count=count-((curpage*dao.getROW())-dao.getROW());
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("list_size", list_size);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("count", count);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("admin_jsp", "../adminpage/admin_qnaList.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminPage_main.jsp");
		return "../main/main.jsp";
	}
}
