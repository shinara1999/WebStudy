package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.AllReplyVO;

public class AllReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static AllReplyDAO dao;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	
	public static AllReplyDAO newInstance() {
		if(dao==null) dao=new AllReplyDAO();
		return dao;
	}
	
	public List<AllReplyVO> replyListData(int typeno,int cno){
		List<AllReplyVO> list=new ArrayList<AllReplyVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT rno,typeno,cno,userid,r_content,TO_CHAR(r_date,'YYYY-MM-DD HH24:MI:SS') "
					+ "FROM all_reply "
					+ "WHERE typeno=? AND cno=? "
					+ "ORDER BY rno DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, typeno);
			ps.setInt(2, cno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AllReplyVO vo=new AllReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setTypeno(rs.getInt(2));
				vo.setCno(rs.getInt(3));
				vo.setUserid(rs.getString(4));
				vo.setR_content(rs.getString(5));
				vo.setDbday(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public void replyInsert(AllReplyVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO all_reply(rno,typeno,cno,userid,r_content) "
					+ "VALUES (ar_rno_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getTypeno());
			ps.setInt(2, vo.getCno());
			ps.setString(3, vo.getUserid());
			ps.setString(4, vo.getR_content());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public void replyUpdate(AllReplyVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE all_reply SET "
					+ "r_content=? "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getR_content());
			ps.setInt(2, vo.getRno());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public void replyDelete(int rno) {
		try {
			conn=dbconn.getConnection();
			String sql="DELETE FROM all_reply "
					+ "WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public List<AllReplyVO> replyReviewListData(int typeno,String isbn){
		List<AllReplyVO> list=new ArrayList<AllReplyVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT rno,typeno,userid,r_content,r_score,TO_CHAR(r_date,'YYYY-MM-DD HH24:MI:SS'),isbn "
					+ "FROM all_reply "
					+ "WHERE typeno=? AND isbn=? "
					+ "ORDER BY rno DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, typeno);
			ps.setString(2, isbn);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AllReplyVO vo=new AllReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setTypeno(rs.getInt(2));
				vo.setUserid(rs.getString(3));
				vo.setR_content(rs.getString(4));
				vo.setR_score(rs.getInt(5));
				vo.setDbday(rs.getString(6));
				vo.setIsbn(rs.getString(7));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public void replyReviewInsert(AllReplyVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO all_reply(rno,typeno,r_score,userid,r_content,isbn) "
					+ "VALUES (ar_rno_seq.nextval,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getTypeno());
			ps.setInt(2, vo.getR_score());
			ps.setString(3, vo.getUserid());
			ps.setString(4, vo.getR_content());
			ps.setString(5, vo.getIsbn());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(conn, ps);
		}
	}
}
