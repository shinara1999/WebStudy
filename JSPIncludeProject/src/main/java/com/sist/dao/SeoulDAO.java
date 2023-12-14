package com.sist.dao;
import java.util.*;

import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.SeoulVO;

import java.sql.*;
public class SeoulDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static SeoulDAO dao;
	
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	
	// 1. 기능 : 명소 seoul_location
	// 2. 기능 : 자연 seoul_nature
	// 3. 기능 : 쇼핑 seoul_shop
	// =========================== 3개를 한번에 처리하는 방법 : String tab을 준다. => sql 문장에서 FROM 테이블명 자리에 tab을 넣는다.
	public List<SeoulVO> seoulLocationListData(int page, String tab)
	{
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no, title, poster, num "
					+"FROM (SELECT no, title, poster,  rownum as num "
					+"FROM (SELECT no, title, poster "
					+"FROM "+tab+" ORDER BY no ASC)) "
					+"WHERE num BETWEEN ? AND ?";
			// 3. 미리 전송
			ps=conn.prepareStatement(sql);
			// 4. 
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			// 5. 실행 후의 결과값 받기
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SeoulVO vo=new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	
	// 총페이지
	public int seoulLocationTotalPage(String tab)
	{
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM "+tab;
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
			dbconn.disConnection(conn, ps);
		}
		return total;
	}	

}
