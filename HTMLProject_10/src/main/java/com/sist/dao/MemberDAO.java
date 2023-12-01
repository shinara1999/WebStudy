package com.sist.dao;
// 회원, 로그인
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class MemberDAO {
	
		// 2. 연결	
		private Connection conn;
		private PreparedStatement ps;
		
		private static MemberDAO dao;
		
		public void getConncetion()
		{
			try {
				Context init=new InitialContext();
				Context c=(Context)init.lookup("java://comp/env");
				DataSource ds=(DataSource)c.lookup("jdbc/oracle");
				conn=ds.getConnection();
			} catch (Exception e) {}
		}
		public void disConnection()
		{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {}
		}
		public static  MemberDAO newInstance()
		{
			if(dao==null)
				dao=new MemberDAO();
			return dao;
		}
		
		// 기능 처리
		public MemberVO memberLogin(String id, String pwd)
		{
			MemberVO vo=new MemberVO();
			try {
				getConncetion();
				String sql="SELECT COUNT(*) FROM member_servlet "
						+"WHERE id=?";
				// 로그인처리
				/*
				 	 1. id 존재여부 확인 => COUNT()
				 	 2. id(x) => 종료 => NOID
				 	    id(o)
				 	    3. pwd 확인
				 	       pwd(x) => 종료 => NOPWD
				 	       pwd(o) => 종료 => ok
				 	       ======================
				 	        개인 정보중에 프로그램 종료시까지 유지해야 되는 데이터를 세션에 저장
				 	        											 === 모든 서블릿/JSP 필요시마다 사용 가능
				 	        											 === 전역변수
				 	        세션 / 쿠키 ==> 저장공간
				 	        	   | 브라우저에 저장
				 	         | 서버에 저장
				 	           1) 저장
				 	           2) 수정
				 	           3) 일부 삭제
				 	           4) 모든 정보 삭제
				 	           5) 저장 기간 설정
				 */
				ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			if(count==0) // id가 없는 상태
			{
				vo.setMsg("NOID");
			}
			else // id가 존재하는 상태
			{
				sql="SELECT pwd, name FROM member_servlet "
						+"WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				
				rs=ps.executeQuery();
				rs.next();
				String db_pwd=rs.getString(1);
				String name=rs.getString(2);
				rs.close();
				if(db_pwd.equals(pwd))
				{
					vo.setMsg("OK");
					vo.setName(name);
				}
				else
				{
					vo.setMsg("NOPWD");
				}
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				disConnection();
			}
			return vo;
		}
}
















