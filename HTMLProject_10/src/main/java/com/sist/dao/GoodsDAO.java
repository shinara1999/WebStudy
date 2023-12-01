package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class GoodsDAO {

	// 2. 연결	
	private Connection conn;
	private PreparedStatement ps;
	
	private static GoodsDAO dao;
	private String[] tables= {"", "goods_all", "goods_special", "goods_best", "goods_new"};
	
	// 4.
	private final int ROW=12;
	
	public void getConncetion()
	{
		try {
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static  GoodsDAO newInstance()
	{
		if(dao==null)
			dao=new GoodsDAO();
		return dao;
	}
	
	// 3. 목록
	public List<GoodsVO> goodsListData(int page, int type)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		
		try {
			getConncetion();
			String sql="SELECT no, goods_name, goods_poster, num "
					+"FROM (SELECT no, goods_name, goods_poster, rownum as num "
					+"FROM (SELECT no, goods_name, goods_poster "
					+"FROM "+tables[type]+" ORDER BY no ASC)) "
					+"WHERE num BETWEEN ? AND ?";
			// ps.setString(1, "goods_all") ==> 'goods_all' => 따옴표 때문에 오류 발생
			ps=conn.prepareStatement(sql);
			int start=(page*ROW)-(ROW-1);
			int end=page*ROW;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
	}
	
	// 5. 
	public int goodsTotalPage(int type)
	{
		int total=0;
		try {
			getConncetion();
			String sql="SELECT CEIL(COUNT(*)/"+ROW+") FROM "+tables[type];
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return total;
	}
	
	// 18. 상세보기
	public GoodsVO goodsDetailData(int no, int type)
	{
		GoodsVO vo=new GoodsVO();
		try {
			getConncetion();
			String sql="SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster "
					+"FROM "+tables[type]+" "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSub(rs.getString(3));
			vo.setPrice(rs.getString(4));
			vo.setDiscount(rs.getInt(5));
			vo.setPrice(rs.getString(6));
			vo.setDelivery(rs.getString(7));
			vo.setPoster(rs.getString(8));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
}























