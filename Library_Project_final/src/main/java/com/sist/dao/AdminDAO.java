package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.BookReserve;
import com.sist.vo.BookReserveCountVO;
import com.sist.vo.ProgramVO;

public class AdminDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static AdminDAO dao;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private final int ROW_SIZE=10;
	
	public static AdminDAO newInstance() {
		if(dao==null) dao=new AdminDAO();
		return dao;
	}
	
	public int getROW_SIZE() {
		return ROW_SIZE;
	}

	// 프로그램 목록
	public List<ProgramVO> programListData(int page){
		List<ProgramVO> list=new ArrayList<ProgramVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT pno,title,"
					+ "TO_CHAR(edu1,'YYYY-MM-DD'),TO_CHAR(edu2,'YYYY-MM-DD'),"
					+ "TO_CHAR(accept1,'YYYY-MM-DD HH24:MI'),TO_CHAR(accept2,'YYYY-MM-DD HH24:MI'),TO_CHAR(regdate,'YYYY-MM-DD'),"
					+ "capacity,applicant,waiting,waitingCap,status,num "
					+ "FROM (SELECT pno,title,edu1,edu2,accept1,accept2,regdate,capacity,applicant,waiting,waitingCap,status,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(program pg_pno_pk)*/pno,title,edu1,edu2,accept1,accept2,regdate,"
					+ "capacity,applicant,waiting,waitingCap,status "
					+ "FROM program)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int start=(ROW_SIZE*page)-(ROW_SIZE-1);
			int end=ROW_SIZE*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ProgramVO vo=new ProgramVO();
				vo.setPno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setEdu1_str(rs.getString(3));
				vo.setEdu2_str(rs.getString(4));
				vo.setAccept1_str(rs.getString(5));
				vo.setAccept2_str(rs.getString(6));
				vo.setRegdate_str(rs.getString(7));
				vo.setCapacity(rs.getInt(8));
				vo.setApplicant(rs.getInt(9));
				vo.setWaiting(rs.getInt(10));
				vo.setWaitingCap(rs.getInt(11));
				vo.setStatus(rs.getInt(12));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	// 검색
	public List<ProgramVO> programListData(int page,String search,String type){
		List<ProgramVO> list=new ArrayList<ProgramVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT pno,title,"
					+ "TO_CHAR(edu1,'YYYY-MM-DD'),TO_CHAR(edu2,'YYYY-MM-DD'),"
					+ "TO_CHAR(accept1,'YYYY-MM-DD HH24:MI'),TO_CHAR(accept2,'YYYY-MM-DD HH24:MI'),TO_CHAR(regdate,'YYYY-MM-DD'),"
					+ "capacity,applicant,waiting,waitingCap,status,num "
					+ "FROM (SELECT pno,title,edu1,edu2,accept1,accept2,regdate,capacity,applicant,waiting,waitingCap,status,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(program pg_pno_pk)*/pno,title,edu1,edu2,accept1,accept2,regdate,"
					+ "capacity,applicant,waiting,waitingCap,status "
					+ "FROM program WHERE "+type+" LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, search);
			int start=(ROW_SIZE*page)-(ROW_SIZE-1);
			int end=ROW_SIZE*page;
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ProgramVO vo=new ProgramVO();
				vo.setPno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setEdu1_str(rs.getString(3));
				vo.setEdu2_str(rs.getString(4));
				vo.setAccept1_str(rs.getString(5));
				vo.setAccept2_str(rs.getString(6));
				vo.setRegdate_str(rs.getString(7));
				vo.setCapacity(rs.getInt(8));
				vo.setApplicant(rs.getInt(9));
				vo.setWaiting(rs.getInt(10));
				vo.setWaitingCap(rs.getInt(11));
				vo.setStatus(rs.getInt(12));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	// 프로그램 총 갯수
	public int programTotalCnt() {
		int count=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM program";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return count;
	}
	
	public int programTotalCnt(String search,String type) {
		int count=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM program "
					+ "WHERE "+type+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, search);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return count;
	}
	
	// 프로그램 등록
	public void programInsert(ProgramVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO program(pno,poster,poster_size,title,content,"
					+ "target1,target2,place,edu1,edu2,time,week,accept1,accept2,capacity,waitingCap) "
					+ "VALUES (pg_pno_seq.nextval,?,?,?,?,?,?,?,"
					+ "TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,"
					+ "TO_DATE(?,'YYYY-MM-DD HH24:MI'),TO_DATE(?,'YYYY-MM-DD HH24:MI'),?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getPoster());
			ps.setInt(2, vo.getPoster_size());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getTarget1());
			ps.setString(6, vo.getTarget2());
			ps.setString(7, vo.getPlace());
			ps.setString(8, vo.getEdu1_str());
			ps.setString(9, vo.getEdu2_str());
			ps.setString(10, vo.getTime());
			ps.setString(11, vo.getWeek());
			ps.setString(12, vo.getAccept1_str());
			ps.setString(13, vo.getAccept2_str());
			ps.setInt(14, vo.getCapacity());
			ps.setInt(15, vo.getWaitingCap());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	// 프로그램 업데이트 데이터
	public ProgramVO programUpdateData(int pno) {
		ProgramVO vo=new ProgramVO();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT pno,title,content,target1,target2,place,"
					+ "TO_CHAR(edu1,'YYYY-MM-DD'),TO_CHAR(edu2,'YYYY-MM-DD'),time,week,"
					+ "TO_CHAR(accept1,'YYYY-MM-DD HH24:MI'),TO_CHAR(accept2,'YYYY-MM-DD HH24:MI'),capacity,waitingCap "
					+ "FROM program "
					+ "WHERE pno="+pno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setPno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setContent(rs.getString(3));
			vo.setTarget1(rs.getString(4));
			vo.setTarget2(rs.getString(5));
			vo.setPlace(rs.getString(6));
			vo.setEdu1_str(rs.getString(7));
			vo.setEdu2_str(rs.getString(8));
			vo.setTime(rs.getString(9));
			vo.setWeek(rs.getString(10));
			vo.setAccept1_str(rs.getString(11));
			vo.setAccept2_str(rs.getString(12));
			vo.setCapacity(rs.getInt(13));
			vo.setWaitingCap(rs.getInt(14));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	// 프로그램 업데이트
	public void programUpdate(ProgramVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE program SET "
					+ "title=?,target1=?,target2=?,place=?,"
					+ "edu1=TO_DATE(?,'YYYY-MM-DD'),edu2=TO_DATE(?,'YYYY-MM-DD'),time=?,"
					+ "accept1=TO_DATE(?,'YYYY-MM-DD HH24:MI'),accept2=TO_DATE(?,'YYYY-MM-DD HH24:MI'),week=?,"
					+ "capacity=?,waitingCap=?,"
					+ "poster=?,poster_size=?,content=?"
					+ "WHERE pno="+vo.getPno();
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getTarget1());
			ps.setString(3, vo.getTarget2());
			ps.setString(4, vo.getPlace());
			ps.setString(5, vo.getEdu1_str());
			ps.setString(6, vo.getEdu2_str());
			ps.setString(7, vo.getTime());
			ps.setString(8, vo.getAccept1_str());
			ps.setString(9, vo.getAccept2_str());
			ps.setString(10, vo.getWeek());
			ps.setInt(11, vo.getCapacity());
			ps.setInt(12, vo.getWaitingCap());
			ps.setString(13, vo.getPoster());
			ps.setInt(14, vo.getPoster_size());
			ps.setString(15, vo.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	// 프로그램 삭제
	public void programDelete(int pno) {
		try {
			conn=dbconn.getConnection();
			// 삭제전 신청한 인원 조회
			// 프로그램 목록에서는 없어지나
			// 사용자의 프로그램 신청내역에서는 유지 -> X
			// status를 종료(2)로 변경 -> X
			// 삭제
			String sql="SELECT COUNT(*) "
					+ "FROM program_application "
					+ "WHERE pno="+pno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			ps.close();
			if(count>0) {
//				sql="UPDATE program_application SET "
//						+ "status=2 "
//						+ "WHERE pno="+pno;
				sql="DELETE FROM program_application "
						+ "WHERE pno="+pno;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			sql="DELETE FROM program "
					+ "WHERE pno="+pno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	// 도서 대출 예약 내역
	public List<BookReserve> bookReserveListData(int page){
		List<BookReserve> list=new ArrayList();
		try {
			conn=dbconn.getConnection();
			// /*+INDEX_DESC(bookreservation br_no_pk */
			String sql="SELECT no,isbn,booktitle,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI') AS regdate,userid,status,TO_CHAR(enddate,'YYYY-MM-DD HH24:MI') AS enddate,num "
					+"FROM (SELECT br.no,br.isbn,booktitle,br.regdate,userid,status,enddate,"
					+"ROW_NUMBER() OVER (PARTITION BY br.isbn ORDER BY no) as num "
					+"FROM bookreservation br JOIN bookinfo bi "
					+"ON br.isbn=bi.isbn) "
					+"WHERE num = 1 AND ROWNUM BETWEEN ? AND ? "
					+"ORDER BY no";
			ps=conn.prepareStatement(sql);
			int start=(page*ROW_SIZE)-(ROW_SIZE-1);
			int end=page*ROW_SIZE;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BookReserve vo=new BookReserve();
				vo.setNo(rs.getInt(1));
				vo.setIsbn(rs.getString(2));
				vo.setBooktitle(rs.getString(3));
				vo.setRegdate_str(rs.getString(4));
				vo.setUserid(rs.getString(5));
				vo.setStatus(rs.getString(6));
				vo.setEnddate_str(rs.getString(7));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public int bookReserveTotalCnt() {
		int count=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM bookreservation";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return count;
	}
	
	public void bookReserveAuthorize(BookReserveCountVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE bookreservation SET "
					+ "status='y',enddate=SYSDATE+14 "
					+ "WHERE no="+vo.getBrno();
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			sql="INSERT INTO bookreservation_count(bno,isbn,regdate,brno,userid) "
					+ "VALUES (brc_seq.nextval,?,SYSDATE,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getIsbn());
			ps.setInt(2, vo.getBrno());
			ps.setString(3, vo.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
}
