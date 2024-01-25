package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.BookStoreVO;

public class BookStoreDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static BookStoreDAO dao;
	
	public static BookStoreDAO newInstance()
	{
		if(dao==null)
			dao=new BookStoreDAO();
		return dao;
	}
	
	public BookStoreVO bookStoreAllDetailData(String isbn)
	{
		BookStoreVO vo=new BookStoreVO();
		try {
		
			conn=dbconn.getConnection();
			String sql="UPDATE bookstore SET "
					+"stno=stno+1"
					+"WHERE isbn=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.close();
			
			sql="SELECT isbn, booktitle, bookauthor, bookpublisher, bookinfo, contents, authorinfo, fixedprice, saleprice, image, bookdtype, bookdate "
					+"FROM bookinfo WHERE isbn=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			vo.setIsbn(rs.getString(1));
			vo.setBooktitle(rs.getString(2));
			vo.setBookauthor(rs.getString(3));
			vo.setBookpublisher(rs.getString(4));
			vo.setBookinfo(rs.getString(5));
			vo.setContents(rs.getString(6));
			vo.setAuthorinfo(rs.getString(7));
			vo.setFixedprice(rs.getInt(8));
			vo.setSaleprice(rs.getInt(9));
			vo.setImage(rs.getString(10));
			vo.setBookdtype(rs.getString(11));
			vo.setBookdate(rs.getString(12));
			}
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
	
	// 도서 구매
	public void bookPurchase_ok(String userid, String isbn, int sumprice)
	{
		try
		{	
			conn=dbconn.getConnection();
			String sql="INSERT INTO BOOKDELIVERY(orderNum, userid, isbn, sumprice) "
					+"VALUES(bd_odnum_seq.nextval,?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, isbn);
			ps.setInt(3, sumprice);
			ps.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	// 도서 카트 인서트
		public void cart_insert(String userid, String isbn, int sumprice)
		{
			try
			{	
				conn=dbconn.getConnection();
				String sql="INSERT INTO BOOKCART(stno, userid, isbn, sumprice) "
						+"VALUES(bc_stno_seq.nextval,?, ?, ?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setString(2, isbn);
				ps.setInt(3, sumprice);
				ps.executeUpdate();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
				dbconn.disConnection(conn, ps);
			}
		}
		
	// 카트 도서 결제
		public void cartbookPurchase_ok(String userid, String isbn, int sumprice, int stno)
		{
			try
			{	
				conn=dbconn.getConnection();
				System.out.println(userid);
				System.out.println(isbn);
				System.out.println(sumprice);
				System.out.println(stno);
				String sql="INSERT INTO BOOKDELIVERY(orderNum, userid, isbn, sumprice) "
						+"SELECT bd_odnum_seq.nextval,userid,isbn,sumprice FROM BOOKCART WHERE userid=? AND isbn=? AND sumprice=? AND stno=?";
						
				ps=conn.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setString(2, isbn);
				ps.setInt(3, sumprice);
				ps.setInt(4, stno);
				ps.executeUpdate();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
				dbconn.disConnection(conn, ps);
			}
		}
	
}
