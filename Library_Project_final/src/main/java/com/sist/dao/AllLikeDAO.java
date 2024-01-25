package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.dbcp.*;
public class AllLikeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn = new CreateDBCPConnection();
	private static AllLikeDAO dao;
	
	public static AllLikeDAO newInstance() {
		if(dao==null) dao = new AllLikeDAO();
		
		return dao;
	}
	
	public int bookLikeCount(String isbn) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM ALL_LIKE "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
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
	
	public int bookLikeCheck(String isbn,String id,int typeno) {
		int status = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM ALL_LIKE "
					+ "WHERE no=? AND typeno=? AND userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setInt(2, typeno);
			ps.setString(3, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			status = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return status;
	}
	
	public void bookLikeChange(String id,String isbn,int type) {
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM "
					+ "ALL_LIKE WHERE no=? AND typeno=? AND userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setInt(2, type);
			ps.setString(3, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			rs.close();
			ps.close();
			if(cnt == 0) {
				sql = "INSERT INTO ALL_LIKE(ino,typeno,no,userid) VALUES(al_like_seq.nextval,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(2, isbn);
				ps.setInt(1, type);
				ps.setString(3, id);
				ps.executeUpdate();
			}
			else if(cnt == 1) {
				sql = "DELETE FROM ALL_LIKE WHERE no=? AND typeno=? AND userid=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, isbn);
				ps.setInt(2, type);
				ps.setString(3, id);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
}
