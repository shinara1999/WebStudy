package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.*;
public class BoardDAO {
	   private Connection conn;
	   private PreparedStatement ps;
	   private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	   private static BoardDAO dao;
	   
	   public static BoardDAO newInstance()
	   {
		   if(dao==null)
			   dao=new BoardDAO();
		   return dao;
	   }
	   
	   public List<BoardVO> boardListData(int page)
	   {
		   List<BoardVO> list=new ArrayList<BoardVO>();
		   try {
			conn=dbconn.getConnection();
			String sql="SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD'), hit, num "
					+"FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+"FROM (SELECT /*+ INDEX_DESC(project_board pb_no_pk)*/no, subject, name, regdate, hit "
					+"FROM project_board)) "
					+"WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
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
	   public int boardRowCount()
	   {
		   int total=0;
		   try {
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM project_board";
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
	   
	   
	   // 추가
	   public void boardInsert(BoardVO vo)
	   {
		   try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO project_board(no, name, subject, content, pwd) "
					+"VALUES(pb_no_seq.nextval, ?, ?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
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
	   public BoardVO boardInfoData(int no, int type)
	   {
		   BoardVO vo=new BoardVO();
		   try {
			conn=dbconn.getConnection();
			if(type==1)
			{
				String sql="UPDATE project_board SET "
						+"hit=hit+1 "
						+"WHERE no="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
			}
			
			String sql="SELECT no, name, subject, content,"
					+"TO_CHAR(regdate, 'YYYY-MM-DD'), hit "
					+"FROM project_board "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
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
	   
	   // 삭제하기
	   public String boardDelete(int no, String pwd)
	   {
		   String result="";
		   try {
			conn=dbconn.getConnection();
			String sql="SELECT pwd FROM project_board "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			ps.close();
			
			if(db_pwd.equals(pwd))
			{
				result="yes";
				sql="DELETE FROM project_reply "
						+"WHERE bno="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				
				sql="DELETE FROM project_board "
						+"WHERE no="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
			}
			else
			{
				result="no";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		   finally {
			dbconn.disConnection(conn, ps);
		}
		   return result;
	   }
	   
	   // 수정하기
	   public String boardUpdate(BoardVO vo)
	   {
		   String res="no";
		   try {
			conn=dbconn.getConnection();
			String sql="SELECT pwd FROM project_board "
					+"WHERE no="+vo.getNo();
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			ps.close();
			
			if(db_pwd.equals(vo.getPwd()))
			{
				res="yes";
				sql="UPDATE project_board SET "
						+"name=?, subject=?, content=? "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		   finally {
			dbconn.disConnection(conn, ps);
		}
		   return res;
	   }
}





















