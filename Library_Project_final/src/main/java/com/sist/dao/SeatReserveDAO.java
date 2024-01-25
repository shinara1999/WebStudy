package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.*;
public class SeatReserveDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn = new CreateDBCPConnection();
	private static SeatReserveDAO dao;
	
	public static SeatReserveDAO newInstance() {
		if(dao==null) dao = new SeatReserveDAO();
		
		return dao;
	}
	
	public String seatCheck_db(int snum) {
		String res="";
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT RESERVE FROM seat WHERE sno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, snum);
			ResultSet rs = ps.executeQuery();
			rs.next();
			res=rs.getString(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return res;
	}
	
	public String seatRes_OK(int snum,String id) {
		String res = "";
		try {
			conn = dbconn.getConnection();
			String sql = "UPDATE SEAT SET reserve = ? WHERE sno=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "Y");
			ps.setInt(2, snum);
			ps.executeUpdate();
			ps.close();
			sql = "INSERT INTO seatreserve(no,sno,userid) VALUES(sr_seq.nextval,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, snum);
			ps.setString(2, id);
			ps.executeUpdate();
			res = "OK";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return res;
	}
	public ArrayList<SeatVO> seatAllData(){
		ArrayList<SeatVO> list = new ArrayList<SeatVO>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT sno,reserve FROM seat";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SeatVO vo = new SeatVO();
				vo.setSno(rs.getInt(1));
				vo.setReserve(rs.getString(2));
				
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
	
	public int userSeatRes(String id) {
		int userSeat = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM seatreserve WHERE userid=? AND intime=outtime";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			rs.close();
			ps.close();
			if(cnt==1) {
				sql = "SELECT sno FROM seatreserve WHERE userid=? AND intime=outtime";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				userSeat = rs.getInt(1);
				rs.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return userSeat;
	}
	public void userSeatCancel(String id,int sno) {
		try {
			conn = dbconn.getConnection();
			String sql = "UPDATE SEAT SET reserve = ? WHERE sno=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "N");
			ps.setInt(2, sno);
			ps.executeUpdate();
			ps.close();
			sql = "UPDATE seatreserve SET outtime = TO_CHAR(SYSDATE,'HH24:mi:ss') "
					+ "WHERE userid=? AND sno=? AND intime=outtime";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, sno);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
	}
	public int SeatTotalCount() {
		int cnt = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM seat WHERE RESERVE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "Y");
			ResultSet rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return cnt;
	}
	public int userbtn_Change(String id){
		int cnt = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT COUNT(*) FROM seatreserve WHERE userid=? AND intime=outtime";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(conn, ps);
		}
		return cnt;
	}
}
