package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dbcp.*;

public class BookStoreCartDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static BookStoreCartDAO dao;
	
	public static BookStoreCartDAO newInstance()
	{
		if(dao==null)
			dao=new BookStoreCartDAO();
		return dao;
	}
	
	public void CartInsert(BookDeliverVO vo)
	{
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM BOOKDELIVERY WHERE stno=? AND buy_ok<>1";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getStno());
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count!=0)
			{
				sql = "UPDATE BOOKDELIVERY SET b_count=b_count+"+vo.getB_count()+" WHERE stno=? AND userid=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getStno());
				ps.setString(2, vo.getUserid());
				ps.executeUpdate();
			}
			else {
				sql = "INSERT INTO BOOKDELIVERY(orderNum, stno, userid, b_count, sumprice) "
						+ "VALUES(bd_odnum_seq.nextval, ?, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, vo.getStno());
				ps.setString(2, vo.getUserid());
				ps.setInt(3, vo.getB_count());
				ps.setInt(4, vo.getSumprice());
				ps.executeUpdate();
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public List<BookDeliverVO> cartListData(String userid)
	{
		List<BookDeliverVO> list=new ArrayList<BookDeliverVO>();
		
		try 
		{
			conn=dbconn.getConnection();
			
			String sql="SELECT orderNum, BOOKSTORE.stno, booktitle, image, b_count, sumprice, orderdate, buy_ok, check_ok, fixedprice FROM BOOKDELIVERY "
					+"INNER JOIN BOOKSTORE "
					+"ON BOOKDELIVERY.stno=BOOKSTORE.stno "
					+"INNER JOIN BOOKINFO ON BOOKDELIVERY.isbn=BOOKINFO.isbn "
					+"WHERE BOOKDELIVERY.userid=? AND buy_ok<>1 "
					+"ORDER BY orderNum DESC";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookDeliverVO vo=new BookDeliverVO();
				vo.setOrderNum(rs.getInt(1));
				vo.setStno(rs.getInt(2));
				vo.setBooktitle(rs.getString(3));
				vo.setImage(rs.getString(4));
				vo.setB_count(rs.getInt(5));
				vo.setSumprice(rs.getInt(6));
				vo.setOrderDate(rs.getDate(7));
				vo.setBuy_ok(rs.getInt(8));
				vo.setCheck_ok(rs.getInt(9));
				vo.setPrice(rs.getInt(10));
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
	
	public void cartBuy(int orderNum)
	{
		try {
			conn = dbconn.getConnection();
			
			String sql = "UPDATE BOOKDELIVERY SET buy_ok=1 WHERE orderNum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderNum);
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
	}
}











