package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.*;
public class FoodDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDBCPConnection dbconn=
		        new CreateDBCPConnection();
  
   private static FoodDAO dao;
   
   // 싱글턴
   public static FoodDAO newInstance()
   {
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao;
   }
   // 평점별 12개 출력 
   public List<FoodVO> foodBestListData(String type)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT fno,name,poster,rownum "
				     +"FROM (SELECT fno,name,poster,type "
				     +"FROM food_menu_house ORDER BY score DESC) "
				     +"WHERE type=? AND rownum<=12";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, type);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setPoster("https://www.menupan.com"+rs.getString(3));
			   list.add(vo);
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return list;
   }
   public List<FoodVO> foodFindData(int page,String addr)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_menu_house WHERE address LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   
		   ps.setString(1, addr);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setPoster("https://www.menupan.com"+rs.getString(3));
		       list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return list;
   }
   public int foodFindTotalPage(String addr)
   {
	   int total=0;
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) "
				     +"FROM food_menu_house "
				     +"WHERE REGEXP_LIKE(address,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, addr);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return total;
   }
   public FoodVO foodFindDetailData(int fno)
   {
	   FoodVO vo=new FoodVO();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT poster,name,score,phone,address,type,"
				     +"theme,price,seat,time,content "
				     +"FROM food_menu_house "
				     +"WHERE fno="+fno;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setPoster("https://www.menupan.com"+rs.getString(1));
		   vo.setName(rs.getString(2));
		   vo.setScore(rs.getDouble(3));
		   vo.setPhone(rs.getString(4));
		   vo.setAddress(rs.getString(5));
		   vo.setType(rs.getString(6));
		   vo.setTheme(rs.getString(7));
		   vo.setPrice(rs.getString(8));
		   vo.setSeat(rs.getString(9));
		   vo.setTime(rs.getString(10));
		   vo.setContent(rs.getString(11));
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return vo;
   }
   
   // 전체
   public List<FoodVO> foodListData(int page)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_menu_house ORDER BY fno ASC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setPoster("https://www.menupan.com"+rs.getString(3));
		       list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return list;
   }
   public int foodListTotalPage()
   {
	   int total=0;
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) "
				     +"FROM food_menu_house";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return total;
   }
   
   // Top 7
   public List<FoodVO> foodTop7()
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try {
		   conn=dbconn.getConnection();
		   String sql="SELECT fno, name, rownum "
				   +"FROM (SELECT fno, name "
				   +"FROM food_menu_house "
				   +"ORDER BY hit DESC) "
				   +"WHERE rownum<=7";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
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
   
   public FoodVO foodDetailData(int fno)
   {
	   FoodVO vo=new FoodVO();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="UPDATE food_menu_house SET "
				   +"hit=hit+1 "
				   +"WHERE fno="+fno;
		   ps=conn.prepareStatement(sql);
		   ps.executeUpdate();
		   ps.close();
		   
		   sql="SELECT poster,name,score,phone,address,type,"
				     +"theme,price,seat,time,content "
				     +"FROM food_menu_house "
				     +"WHERE fno="+fno;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setPoster("https://www.menupan.com"+rs.getString(1));
		   vo.setName(rs.getString(2));
		   vo.setScore(rs.getDouble(3));
		   vo.setPhone(rs.getString(4));
		   vo.setAddress(rs.getString(5));
		   vo.setType(rs.getString(6));
		   vo.setTheme(rs.getString(7));
		   vo.setPrice(rs.getString(8));
		   vo.setSeat(rs.getString(9));
		   vo.setTime(rs.getString(10));
		   vo.setContent(rs.getString(11));
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return vo;
   }
}




























