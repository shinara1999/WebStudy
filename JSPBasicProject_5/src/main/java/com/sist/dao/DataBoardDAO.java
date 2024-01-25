package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
public class DataBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateConnection dbconn=new CreateConnection();
	
	public List<DataBoardBean> boardListData()
	{
		List<DataBoardBean> list=new ArrayList<DataBoardBean>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no, subject, name, regdate, hint "
					+"FROM databoard "
					+"ORDER BY no DESC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				DataBoardBean vo=new DataBoardBean();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHint(rs.getInt(5));
				
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
	
	// 추가
	public void boardInsert(DataBoardBean vo)
	{
		try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO databoard(no, name, subject, content, pwd, filename, filesize) "
					+"VALUES(db_no_seq.nextval, ?, ?, ?, ?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	
	// 상세보기
	public DataBoardBean boardDetailData(int no)
	{
		DataBoardBean vo=new DataBoardBean();
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE databoard SET "
					+"hint=hint+1 "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="SELECT no, name, subject, content, regdate, hint,"
					+"filename, filesize "
					+"FROM databoard "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHint(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
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
	
	// 삭제
	public void boardDelete(int no)
	{
		try {
			conn=dbconn.getConnection();
			String sql="DELETE FROM databoard "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public DataBoardBean boardFileInfoData(int no)
	{
		DataBoardBean vo=new DataBoardBean();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT filename, filesize "
					+"FROM databoard "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFilename(rs.getString(1));
			vo.setFilesize(rs.getInt(2));
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
}













