package com.sist.dao;

import java.util.*;
import java.sql.*;

public class FoodDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao; 
	// 드라이버 등록
	public FoodDAO()
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
	// 싱클턴 패턴 => 메모리 공간 1개 생성
	public static FoodDAO newInstance()
	{
		if(dao==null)
	    dao=new FoodDAO();
		return dao;
	}
	// 기능 설정
	// => 목록(table) => 인라인뷰 => 페이지 설정
	public List<FoodVO> foodListData(int page)
	{
		// https://www.menupan.com/
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
				getConnection();
				// 페이지마다 데이터 읽기
				int rowSize=20; // 한 페이지당 20개씩 읽기
				// num BETWEEN ? AND ? => 1 page 1~20
				//						  2 page 21~40
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				String sql="SELECT fno, poster, name, type, address, num "
							+"FROM (SELECT fno, poster, name, type, address, rownum as num "
							+"FROM (SELECT /*+INDEX_ASC(food_menu_house fh_fno_pk)*/fno, poster, name, type, address "
							+"FROM food_menu_house)) "
							+"WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				// INDEX_ASC(테이블명 인덱스명, PK, UK) , INDEX_DESC() , INDEX()
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					FoodVO vo=new FoodVO();
					vo.setFno(rs.getInt(1));
					vo.setPoster("https://www.menupan.com"+rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setType(rs.getString(4));
					vo.setAddress(rs.getString(5));
					
					list.add(vo);
				}
				rs.close();
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
	// 홈페이지
	public int foodTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house";
			
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
	// => 상세보기 (table)
	
}
