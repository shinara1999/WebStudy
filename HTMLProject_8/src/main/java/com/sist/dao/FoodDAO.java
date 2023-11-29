package com.sist.dao;

// DBCP => 미리 커넥션을 연결 => POOL안에 저장
// 사용 후에 다시 POOL안에 반환 => 재사용
// Connection 생성을 제한 => 연결시간을 줄일 수 있다.
// 웹 개발을 하는 업체는 DBCP를 사용한다.
// MyBatis / JPA의 기본은 DBCP

import java.util.*;
import java.sql.*;	// Connection , ResultSet , PreparedStatement
import javax.sql.*; // 데이터베이스 정보 => DataSource
import javax.naming.*; // 이름으로 객체 찾는 경우 => Context

public class FoodDAO {
	private Connection conn; // 오라클 연결 객체 / 오라클 서버와 연결하는 Socket
	private PreparedStatement ps; // 송수신 => SQL을 전송 결과값 받기 / OuputStream , BufferedReader
	private static FoodDAO dao; // 싱글턴 : 하나 당 하나의 dao만 생성 / 클라이언트 1명당 한개만 사용 가능
	
	// 1. 메모리 누수현상 방지 => GC (Garbage Collection)
	public void getConnection()
	{
		try {
			Context init=new InitialContext();
			// POOL연결
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 2. 미리 생성된 connection 주소 얻기
	// 3. 반환
	public void disConnection()
	{
		try 
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	// 4. 싱글턴
	public static FoodDAO newInstance()
	{
		if(dao==null)
		{
			dao=new FoodDAO();
		}
		return dao;
	}
	
	// 5. 기능을 설정
	public List<FoodVO> foodListData(int page)
	{
		List<FoodVO>list=new ArrayList<FoodVO>();
		try {
			getConnection();
			// 6. SQL문장 만들기 => 페이지 나누는 인라인뷰
			String sql="SELECT fno, name, poster, num "
					+"FROM (SELECT fno, name, poster, rownum as num "
					+"FROM (SELECT /*+INDEX_ASC(food_location pk_food_location)+*/ fno, name, poster " // pk_food_location(인덱스명칭)을 ASC해라.
					+"FROM food_location)) "
					+"WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql); // 오라클로 전송
			int rowSize=12;	// 한 페이지에 출력할 갯수 설정 / 1페이지 => 1~12개 , 2페이지 => 13~24개...
			int start=(rowSize*page)-(rowSize-1); // 12*1 - 12-1 = 12-11=1 => rownum은 1부터 시작하므로 -1을 해준다.
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 실행요청
			ResultSet rs=ps.executeQuery();
			while(rs.next()) // 첫번째 row부터 읽어서 마지막까지 읽는다.
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String poster=rs.getString(3);
				poster=poster.substring(0, poster.indexOf("^")); // ???
				vo.setPoster(poster);
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	// 11. 총페이지 구하기
	public int foodTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+"FROM food_location";
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
	// 19. 상세보기 
	public FoodVO foodDetailData(int fno)
	{
		FoodVO vo=new FoodVO();
		try {
			getConnection();
			String sql="UPDATE food_location SET "
					+"hit=hit+1 "
					+"WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); // commit을 포함 => INSERT/UPDATE/DELETE
			// SELECT => 실행된 결과를 읽어온다. => executeQuery
			// 실제 데이터를 가지고 온다.
			sql="SELECT fno, name, score, address, tel, poster, type, price, parking, menu, time "
					+"FROM food_location "
					+"WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setAddress(rs.getString(4));
			vo.setPhone(rs.getString(5));
			vo.setPoster(rs.getString(6));
			vo.setType(rs.getString(7));
			vo.setPrice(rs.getString(8));
			vo.setParking(rs.getString(9));
			vo.setMenu(rs.getString(10));
			vo.setTime(rs.getString(11));
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
}
















