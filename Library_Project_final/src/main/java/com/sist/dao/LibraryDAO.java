package com.sist.dao;
import com.sist.dbcp.*;
import com.sist.vo.*;
import java.util.*;
import java.sql.*;

public class LibraryDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn = new CreateDBCPConnection();
	private static LibraryDAO dao;
	
	public static LibraryDAO newInstance() {
		if(dao==null) dao = new LibraryDAO();
		
		return dao;
	}
	
	public ArrayList<MajorctVO> Major_Classification(){
		ArrayList<MajorctVO> list = new ArrayList<MajorctVO>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT cate,cno,icon "
					+ "FROM majorct";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MajorctVO vo = new MajorctVO();
				vo.setCate(rs.getString(1));
				vo.setCno(rs.getInt(2));
				vo.setIcon(rs.getString(3));
				
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
	
	public ArrayList<bookInfoVO> BookInfoData(int cno,String mno,int page){

		ArrayList<bookInfoVO> list = new ArrayList<bookInfoVO>();
		try {
			conn = dbconn.getConnection();

			String sql = "SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,isbn,status,num "
					+ "FROM (SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,isbn,status,rownum as num "
					+ "FROM (SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,bookinfo.isbn,status "
					+ "FROM bookinfo LEFT OUTER JOIN (SELECT * FROM (SELECT NO,isbn,regdate,userid,status,enddate,ROW_NUMBER() OVER(PARTITION BY isbn ORDER BY NO ASC) AS rankno FROM BOOKRESERVATION b ) WHERE rankno=1) dat ON bookinfo.ISBN = dat.ISBN WHERE bookinfo.isbn in(SELECT isbn FROM BOOKMAIN WHERE MNO in(SELECT mno FROM MIDDLECT JOIN MAJORCT ON MIDDLECT.cno = MAJORCT.cno WHERE MAJORCT.cno=? AND MIDDLECT.mno=?)) "
					+ ")) WHERE num BETWEEN ? AND ?";
			
			int row_size = 12;
			int start = (row_size*page)-(row_size-1);
			int end = (row_size*page);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setString(2, mno);
			ps.setInt(3, start);
			ps.setInt(4, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bookInfoVO vo = new bookInfoVO();
				vo.setBooktitle(rs.getString(1));
				vo.setImage(rs.getString(2));
				vo.setBookauthor(rs.getString(3));
				vo.setBookpublisher(rs.getString(4));
				vo.setBookcallnum(rs.getString(5));
				vo.setBookdate(rs.getString(6));
				vo.setBookaccessionno(rs.getString(7));
				vo.setBooklocation(rs.getString(8));
				vo.setIsbn(rs.getString(9));
				vo.getBrvo().setStatus(rs.getString(10));
				
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
	
	public int BookInfoTotal(int cno,String mno) {
		int total= 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM BOOKMAIN WHERE MNO in(SELECT mno FROM MIDDLECT JOIN MAJORCT ON MIDDLECT.cno = MAJORCT.cno WHERE MAJORCT.cno=? AND MIDDLECT.mno=?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setString(2, mno);
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
	
	public bookInfoVO BookDetailSearch(String isbn) {
		bookInfoVO vo = new bookInfoVO();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT bookinfo.isbn,booktitle,bookauthor,bookpublisher,bookdtype,bookperson,booksign,bookdate,bookaccessionno,bookcallnum,booklocation,image,bookinfo,enddate,status "
					+ "FROM bookinfo LEFT OUTER JOIN (SELECT * FROM (SELECT NO,isbn,regdate,userid,status,enddate,ROW_NUMBER() OVER(PARTITION BY isbn ORDER BY NO ASC) AS rankno FROM BOOKRESERVATION b ) WHERE rankno=1) dat ON bookinfo.ISBN = dat.ISBN WHERE bookinfo.isbn=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setIsbn(rs.getString(1));
			vo.setBooktitle(rs.getString(2));
			vo.setBookauthor(rs.getString(3));
			vo.setBookpublisher(rs.getString(4));
			vo.setBookdtype(rs.getString(5));
			vo.setBookperson(rs.getString(6));
			vo.setBooksign(rs.getString(7));
			vo.setBookdate(rs.getString(8));
			vo.setBookaccessionno(rs.getString(9));
			vo.setBookcallnum(rs.getString(10));
			vo.setBooklocation(rs.getString(11));
			vo.setImage(rs.getString(12));
			vo.setBookinfo(rs.getString(13));
			vo.getBrvo().setEnddate(rs.getDate(14));
			vo.getBrvo().setStatus(rs.getString(15));
			
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	public String SearchCookieData(String isbn) {
		String data = "";
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT booktitle FROM bookinfo WHERE isbn=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			data = rs.getString(1);
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		
		return data;
	}
	
	public ArrayList<MiddlectVO> SearchSubmenuData(int range,String mno) {
		ArrayList<MiddlectVO> list = new ArrayList<MiddlectVO>();
		try {
			conn = dbconn.getConnection();
			String sql ="";
			if(range==0) {
				sql = "SELECT mno,cno,cate FROM middlect";
			}
			else if(range==1) {
				sql = "SELECT mno,cno,cate FROM middlect "
						+ "WHERE mno = ?";
			}
			
			ps = conn.prepareStatement(sql);
			if(range==1) {
				ps.setString(1, mno);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MiddlectVO vo = new MiddlectVO();
				vo.setMno(rs.getString(1));
				vo.setCno(rs.getInt(2));
				vo.setCate(rs.getString(3));
				
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
	
	public ArrayList<bookInfoVO> newArrivalBookData(int page,int acq){
		ArrayList<bookInfoVO> list = new ArrayList<bookInfoVO>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT image,isbn,booktitle,bookauthor,bookpublisher,bookdate,ACQUISITION,num "
					+ "FROM (SELECT image,isbn,booktitle,bookauthor,bookpublisher,bookdate,ACQUISITION,rownum as num "
					+ "FROM (SELECT image,isbn,booktitle,bookauthor,bookpublisher,bookdate,ACQUISITION "
					+ "FROM bookinfo WHERE acquisition>(SYSDATE-?) "
					+ "ORDER BY acquisition DESC)) WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowpage = 100;
			int start = (page*rowpage)-(rowpage-1);
			int end = (page*rowpage);
			ps.setInt(1, acq);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bookInfoVO vo = new bookInfoVO();
				vo.setImage(rs.getString(1));
				vo.setIsbn(rs.getString(2));
				vo.setBooktitle(rs.getString(3));
				vo.setBookauthor(rs.getString(4));
				vo.setBookpublisher(rs.getString(5));
				vo.setBookdate(rs.getString(6));
				vo.setAcquisition(rs.getDate(7));
				vo.setCount(rs.getInt(8));
				
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
	
	public int newArrivalTotal(int acq) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) "
					+ "FROM bookinfo WHERE acquisition>(SYSDATE-?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, acq);
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
	
	public void bookdatareserve(String id,String isbn) {
		try {
			conn = dbconn.getConnection();
			String sql = "INSERT INTO BOOKRESERVATION(no,isbn,userid,status) VALUES(br_seq.nextval,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, id);
			ps.setString(3, "n");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public String bookdatareserveCancel(String isbn,String id) {
		String res = "0";
		try {
			conn= dbconn.getConnection();
			String sql = "DELETE FROM BOOKRESERVATION WHERE isbn=? AND status=? AND userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, "n");
			ps.setString(3, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		
		return res;
	}
	
	public String bookdatareserveok(String isbn,String id) {
		String res = null;
		try {
			conn= dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM BOOKRESERVATION WHERE isbn=? AND userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			rs.close();
			ps.close();
			if(cnt!=0) {
				sql = "SELECT status FROM BOOKRESERVATION WHERE isbn=? AND userid=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, isbn);
				ps.setString(2, id);
				rs = ps.executeQuery();
				rs.next();
				res = rs.getString(1);
				rs.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		
		return res;
	}
	
	public void bookdatareservereturn(String isbn,String id) {
		try {
			conn= dbconn.getConnection();
			String sql = "DELETE FROM BOOKRESERVATION WHERE isbn=? AND userid=? AND status=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, id);
			ps.setString(3, "y");
			ps.executeUpdate();
			ps.close();
			sql = "UPDATE BOOKRESERVATION_COUNT SET enddate=SYSDATE WHERE isbn=? AND userid=? AND enddate is null";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public ArrayList<FavorLoanVO> favorLoanBookData(int page,int acq){
		ArrayList<FavorLoanVO> list = new ArrayList<FavorLoanVO>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT isbn,cnt,image,booktitle,BOOKAUTHOR,BOOKPUBLISHER,BOOKDATE,num FROM (SELECT isbn,cnt,image,booktitle,BOOKAUTHOR,BOOKPUBLISHER,BOOKDATE,rownum as num FROM (SELECT DISTINCT dat.isbn,cnt,image,booktitle,bookauthor,BOOKPUBLISHER,bookdate "
					+ "FROM (SELECT COUNT(*) AS cnt,image,bookinfo.isbn,booktitle,bookauthor,BOOKPUBLISHER,bookdate,ROW_NUMBER() OVER(ORDER BY count(*) DESC) AS rank "
					+ "FROM BOOKRESERVATION_COUNT JOIN BOOKINFO ON BOOKRESERVATION_COUNT.isbn = BOOKINFO.isbn WHERE BOOKRESERVATION_COUNT.REGDATE >SYSDATE-? GROUP BY bookinfo.isbn,image,booktitle,bookauthor,BOOKPUBLISHER ,bookdate) dat "
					+ "RIGHT OUTER JOIN BOOKRESERVATION_COUNT ON dat.isbn=BOOKRESERVATION_COUNT.isbn ORDER BY cnt DESC)) WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowpage = 100;
			int start = (page*rowpage)-(rowpage-1);
			int end = (page*rowpage);
			ps.setInt(1, acq);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FavorLoanVO vo = new FavorLoanVO();
				vo.setIsbn(rs.getString(1));
				vo.setCnt(rs.getInt(2));
				vo.setImage(rs.getString(3));
				vo.setBooktitle(rs.getString(4));
				vo.setBookauthor(rs.getString(5));
				vo.setBookpublisher(rs.getString(6));
				vo.setBookdate(rs.getString(7));
				vo.setNum(rs.getInt(8));
				
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
	
	public int favorLoanTotal(int acq) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM (SELECT isbn,cnt,image,booktitle,BOOKAUTHOR,BOOKPUBLISHER,BOOKDATE,rownum as num FROM (SELECT DISTINCT dat.isbn,cnt,image,booktitle,bookauthor,BOOKPUBLISHER,bookdate "
					+ "	FROM (SELECT COUNT(*) AS cnt,image,bookinfo.isbn,booktitle,bookauthor,BOOKPUBLISHER,bookdate,ROW_NUMBER() OVER(ORDER BY count(*) DESC) AS rank "
					+ "	FROM BOOKRESERVATION_COUNT JOIN BOOKINFO ON BOOKRESERVATION_COUNT.isbn = BOOKINFO.isbn WHERE BOOKRESERVATION_COUNT.REGDATE >SYSDATE-? GROUP BY bookinfo.isbn,image,booktitle,bookauthor,BOOKPUBLISHER ,bookdate) dat "
					+ "	RIGHT OUTER JOIN BOOKRESERVATION_COUNT ON dat.isbn=BOOKRESERVATION_COUNT.isbn ORDER BY cnt DESC))";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, acq);
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
	
	public ArrayList<bookInfoVO> SearchBookData(String name,int page){
		ArrayList<bookInfoVO> list = new ArrayList<bookInfoVO>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,isbn,status,num "
					+ "FROM (SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,isbn,status,rownum as num "
					+ "FROM (SELECT booktitle,image,bookauthor,bookpublisher,bookcallnum,bookdate,bookaccessionno,booklocation,bookinfo.isbn,status "
					+ "FROM bookinfo LEFT OUTER JOIN (SELECT * FROM (SELECT NO,isbn,regdate,userid,status,enddate,ROW_NUMBER() OVER(PARTITION BY isbn ORDER BY NO ASC) AS rankno FROM BOOKRESERVATION b ) WHERE rankno=1) dat ON bookinfo.ISBN = dat.ISBN WHERE REGEXP_LIKE(booktitle,?))) WHERE num BETWEEN ? AND ?";
			
			int row_size = 12;
			int start = (row_size*page)-(row_size-1);
			int end = (row_size*page);
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bookInfoVO vo = new bookInfoVO();
				vo.setBooktitle(rs.getString(1));
				vo.setImage(rs.getString(2));
				vo.setBookauthor(rs.getString(3));
				vo.setBookpublisher(rs.getString(4));
				vo.setBookcallnum(rs.getString(5));
				vo.setBookdate(rs.getString(6));
				vo.setBookaccessionno(rs.getString(7));
				vo.setBooklocation(rs.getString(8));
				vo.setIsbn(rs.getString(9));
				vo.getBrvo().setStatus(rs.getString(10));
				
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
	public int SearchBookTotal(String name) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) "
					+ "FROM bookinfo WHERE REGEXP_LIKE(booktitle,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
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
	
	public String bookDetailbtnCheck(String id,String isbn) {
		String answer = "";
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM bookreservation "
					+ "WHERE userid=? AND isbn=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			rs.close();
			if(cnt!=0) {
				sql = "SELECT status FROM bookreservation "
						+ "WHERE userid=? AND isbn=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, isbn);
				rs = ps.executeQuery();
				rs.next();
				answer = rs.getString(1);
				rs.close();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return answer;
	}
}
