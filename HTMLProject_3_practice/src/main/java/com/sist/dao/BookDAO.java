package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BookDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static BookDAO dao; 
	// 드라이버 등록
	public BookDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {ex.printStackTrace();}
	}
	// 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL, "hr", "happy");
		}catch(Exception ex) {ex.printStackTrace();}
	}
	// 오라클 닫기
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public static BookDAO newInstance()
	{
		if(dao==null)
		dao=new BookDAO();
		return dao;
	}
	
	public ArrayList<BookVO> bookListData(int page)
	{
		ArrayList<BookVO> list=new ArrayList<BookVO>();
		try
		{
			getConnection();
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			String sql="SELECT b.isbn, image, booktitle, bookperson, bookdate, rownum "
					+"FROM BOOKIMAGE b " 
					+"JOIN BOOKINFO b2 "
					+"ON b.ISBN =b2.ISBN "
					+"WHERE rownum BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BookVO vo=new BookVO();
				vo.setIsbn(rs.getString(1));
				vo.setImage(rs.getString(2));
				vo.setBooktitle(rs.getString(3));
				vo.setBookperson(rs.getString(4));
				vo.setBookdate(rs.getDate(5));
				
				list.add(vo);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	public int bookTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20) FROM BOOKIMAGE";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
}





















