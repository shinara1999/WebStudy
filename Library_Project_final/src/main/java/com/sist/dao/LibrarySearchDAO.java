package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.*;

public class LibrarySearchDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn = new CreateDBCPConnection();
	private static LibrarySearchDAO dao;
	
	public static LibrarySearchDAO newInstance() {
		if(dao==null) dao = new LibrarySearchDAO();
		
		return dao;
	}
	
	public ArrayList<OtherLibVO> OtherLibSearch(int page){
		ArrayList<OtherLibVO> list = new ArrayList<OtherLibVO>();
		try {
			/*
			 * private int no;
				private String name,time,closetime,tel,homepage,gu,address;
			 * */
			conn = dbconn.getConnection();
			String sql = "SELECT no,name,time,closetime,tel,homepage,gu,address,num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address,rownum as num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address "
					+ "FROM seoul_library ORDER BY name)) WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowsize = 4;
			int start = (rowsize*page)-(rowsize-1);
			int end = (rowsize*page);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OtherLibVO vo = new OtherLibVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setTime(rs.getString(3));
				vo.setClosetime(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setHomepage(rs.getString(6));
				vo.setGu(rs.getString(7));
				vo.setAddress(rs.getString(8));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public int OtherLibTotal(int type,String lib,String book) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "";
			if(type==0) {
				sql = "SELECT COUNT(*) "
					+ "FROM seoul_library";
			}
			else if(type==1) {
				sql = "SELECT COUNT(*) "
						+ "FROM seoul_library "
						+ "WHERE REGEXP_LIKE(name,?)";
			}
			else if(type==2) {// 책관련 만들기
				sql = "SELECT COUNT(DISTINCT name) "
						+ "FROM seoul_library JOIN seoul_lib_book "
						+ "ON seoul_library.no = seoul_lib_book.no WHERE REGEXP_LIKE(booktitle,?)";
			}
			else if(type==3) {
				sql = "SELECT COUNT(DISTINCT name) "
						+ "FROM seoul_library JOIN seoul_lib_book "
						+ "ON seoul_library.no = seoul_lib_book.no WHERE REGEXP_LIKE(booktitle,?) AND REGEXP_LIKE(name,?)";
			}
			ps = conn.prepareStatement(sql);
			if(type==1) {
				ps.setString(1, lib);
			}
			else if(type==2) {
				ps.setString(1, book);
			}
			else if(type==3) {
				ps.setString(1, book);
				ps.setString(2, lib);
			}
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
	
	public ArrayList<OtherLibVO> OtherLibSearchLib(int page,String lib){
		ArrayList<OtherLibVO> list = new ArrayList<OtherLibVO>();
		try {
			/*
			 * private int no;
				private String name,time,closetime,tel,homepage,gu,address;
			 * */
			conn = dbconn.getConnection();
			String sql = "SELECT no,name,time,closetime,tel,homepage,gu,address,num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address,rownum as num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address "
					+ "FROM seoul_library WHERE REGEXP_LIKE(name,?) ORDER BY name)) WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowsize = 4;
			int start = (rowsize*page)-(rowsize-1);
			int end = (rowsize*page);
			ps.setString(1, lib);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OtherLibVO vo = new OtherLibVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setTime(rs.getString(3));
				vo.setClosetime(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setHomepage(rs.getString(6));
				vo.setGu(rs.getString(7));
				vo.setAddress(rs.getString(8));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public ArrayList<OtherLibVO> OtherLibSearchBook(int page,String bookname){
		//책 관련 검색 변경
		//0번의 경우 도서관 중복값 제거 데이터 찾기
		//1번의 경우 도서관 내 책 결과값 가져오기
		ArrayList<OtherLibVO> list = new ArrayList<OtherLibVO>();
		try {
			/*
			 * private int no;
				private String name,time,closetime,tel,homepage,gu,address;
			 * */
			conn = dbconn.getConnection();
			String sql = "SELECT no,name,time,closetime,tel,homepage,gu,address,num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address,rownum as num "
					+ "FROM (SELECT seoul_library.no,name,time,closetime,tel,homepage,gu,address "
					+ "FROM seoul_library WHERE name in(SELECT DISTINCT name FROM seoul_library JOIN seoul_lib_book ON seoul_library.no = seoul_lib_book.no WHERE REGEXP_LIKE(booktitle,?)) ORDER BY name)) WHERE num BETWEEN ? AND ?";
			
			ps = conn.prepareStatement(sql);
			int rowsize = 4;
			int start = (rowsize*page)-(rowsize-1);
			int end = (rowsize*page);
			ps.setString(1, bookname);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OtherLibVO vo = new OtherLibVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setTime(rs.getString(3));
				vo.setClosetime(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setHomepage(rs.getString(6));
				vo.setGu(rs.getString(7));
				vo.setAddress(rs.getString(8));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public ArrayList<OtherLibVO> OtherLibSearchBook_Lib(int page,String libName,String bookname){
		//책 관련 검색 변경
		ArrayList<OtherLibVO> list = new ArrayList<OtherLibVO>();
		try {
			/*
			 * private int no;
				private String name,time,closetime,tel,homepage,gu,address;
			 * */
			conn = dbconn.getConnection();
			String sql = "SELECT no,name,time,closetime,tel,homepage,gu,address,num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address,rownum as num "
					+ "FROM (SELECT no,name,time,closetime,tel,homepage,gu,address "
					+ "FROM seoul_library WHERE name in(SELECT DISTINCT name FROM seoul_library JOIN seoul_lib_book ON seoul_library.no = seoul_lib_book.no WHERE REGEXP_LIKE(booktitle,?) AND REGEXP_LIKE(name,?))ORDER BY name)) WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowsize = 4;
			int start = (rowsize*page)-(rowsize-1);
			int end = (rowsize*page);
			ps.setString(1, bookname);
			ps.setString(2, libName);
			ps.setInt(3, start);
			ps.setInt(4, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OtherLibVO vo = new OtherLibVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setTime(rs.getString(3));
				vo.setClosetime(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setHomepage(rs.getString(6));
				vo.setGu(rs.getString(7));
				vo.setAddress(rs.getString(8));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public ArrayList<OtherLibBookVO> OtherLibSearchBook_shadow(String libName,String bookname){
		//책 관련 검색 변경
		ArrayList<OtherLibBookVO> list = new ArrayList<OtherLibBookVO>();
		try {
			/*
			 * private int no;
				private String name,time,closetime,tel,homepage,gu,address;
			 * */
			conn = dbconn.getConnection();
			String sql = "SELECT count(isbn),isbn,booktitle "
					+ "FROM SEOUL_LIB_BOOK JOIN SEOUL_LIBRARY ON SEOUL_LIB_BOOK.NO = SEOUL_LIBRARY.NO "
					+ "WHERE REGEXP_LIKE(booktitle,?) AND REGEXP_LIKE(name,?) "
					+ "GROUP BY name,isbn,booktitle ORDER BY name";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bookname);
			ps.setString(2, libName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OtherLibBookVO vo = new OtherLibBookVO();
				vo.setCnt(rs.getInt(1));
				vo.setIsbn(rs.getString(2));
				vo.setBooktitle(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	public String apiFind(String id) {
		String apikey = "";
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT val FROM webfind WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			apikey = rs.getString(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return apikey;
	}

}
