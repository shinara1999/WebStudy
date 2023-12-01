package com.sist.dao;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private static ReplyDAO dao;
	
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
	public static  ReplyDAO newInstance()
	{
		if(dao==null)
			dao=new ReplyDAO();
		return dao;
	}
	
	// 기능
	// 1. 목록
	public List<ReplyVO> replyListData(int typeno, int gno)
	{
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try {
			getConncetion();
			String sql="SELECT rno, id, name, msg, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+"FROM reply1 "
					+"WHERE typeno=? AND gno=? "
					+"ORDER BY rno DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, typeno);
			ps.setInt(2, gno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ReplyVO vo=new ReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setMsg(rs.getString(4));
				vo.setDbday(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	// 2. 댓글
	public void replyInsert(ReplyVO vo)
	{
		try {
			getConncetion();
			String sql="INSERT INTO reply1 VALUES("
					+"reply1_rno_seq.nextval,"
					+"?,?,?,?,?,SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getTypeno());
			ps.setInt(2, vo.getGno());
			ps.setString(3, vo.getId());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getMsg());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	// 3. 수정 => jquery (자바스크립트)
	public void replyUpdate(int rno, String msg)
	{
		try {
			getConncetion();
			String sql="UPDATE reply1 SET "
					+"msg=? "
					+"WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, rno);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	// 4. 삭제
	public void replyDelete(int rno)
	{
		try {
			getConncetion();
			String sql="DELETE FROM reply1 "
					+"WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
}

















