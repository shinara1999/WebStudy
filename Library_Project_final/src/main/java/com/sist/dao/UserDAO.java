package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.*;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static UserDAO dao;
	
	public static UserDAO newInstance()
	{
		if(dao==null)
			dao=new UserDAO();
		return dao;
	}
	
	// 힌트 질문
	public List<String> hintQuestion()
	{
		List<String> list=new ArrayList<String>();
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT hintQ FROM hint";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				list.add(rs.getString(1));
			}
			rs.close();
			
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	// ID중복체크
	public int UserIdCheck(String id)
	{
		int count=0;
		if(id.equals(""))
		{
			return count=2;
		}
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM userjoin "
					  +"WHERE UserID=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return count;
	}
		
	// 우편
	public List<ZipcodeVO> post(String dong)
	{
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT zipcode,sido,gugun, "
					  +"dong,NVL(bunji,' ') "
					  +"FROM zipcode "
					  +"WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ZipcodeVO vo=new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return list;
		}
	
	public int postCount(String dong)
	{
		int count=0;
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					  +"FROM zipcode "
					  +"WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return count;
	}
		
	// 전화번호 체크
	public int userPhoneCheck(String phone)
	{
		int count=0;
		if(phone.equals("")) {
			return 2;
		}
		if(phone.length()!=11) {
			return 3;
		}
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM userjoin WHERE phone=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return count;		
	}
		
	// 회원가입
	public void UserInsert(UserVO vo)
	{
		try 
		{
			conn=dbconn.getConnection();
			String sql="INSERT INTO userjoin VALUES("
					  +"?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,'n') ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getUserID());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getGender());
			ps.setString(5, vo.getPost());
			ps.setString(6, vo.getAddr1());
			ps.setString(7, vo.getAddr2());
			ps.setString(8, vo.getPhone());
			ps.setString(9, vo.getBirth());
			ps.setString(10, vo.getEmail());
			ps.setInt(11, vo.getHno());
			ps.setString(12, vo.getHintA());
			ps.executeUpdate();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
	}
	
	// 로그인
	public UserVO UserLogin(String id,String pwd)
	{
	   UserVO vo=new UserVO();
	   try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					  +"FROM userjoin "
					  +"WHERE userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				sql="SELECT userid,name,pwd,admin,hintQ,hintA "
				   +"FROM userjoin "
				   +"JOIN hint "		
				   +"ON userjoin.hno=hint.hno "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_id=rs.getString(1);
				String name=rs.getString(2);
				String db_pwd=rs.getString(3);
				String admin=rs.getString(4);
				String hintQ=rs.getString(5);
				String hintA=rs.getString(6);
				rs.close();
				
				if(db_pwd.equals(pwd))
				{
					vo.setUserID(db_id);
					vo.setName(name);
					vo.setAdmin(admin);
					vo.setHintQ(hintQ);
					vo.setHintA(hintA);
					vo.setMsg("OK");
				}
				else
				{
					vo.setMsg("NOPWD");
				}
				
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	// 아이디 찾기
	public String findId(String name,String email) 
	{
		String result="";
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM userjoin "
					  +"WHERE name=? AND email=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0)
			{
				// 정보틀림
				result="FINDNO";
			}
			else
			{
				result="FINDYES";
				sql="SELECT RPAD(SUBSTR(userid,1,2),LENGTH(userid),'*') "
					+"FROM userjoin "
					+"WHERE name=? AND email=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, email);
				
				rs=ps.executeQuery();
				rs.next();
				result=rs.getString(1);
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return result;
	}
	
	// 비밀번호 찾기
	public String findPwd(String userid,int hno,String hintA)
	{
		String result="";
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) FROM userjoin "
					  +"WHERE userid=? AND hno=? AND hintA=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setInt(2, hno);
			ps.setString(3, hintA);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			System.out.println(count);
			rs.close();
			
			if(count==0) 
			{
				// 틀림
				result="NO";
			}
			else if(count==1)
			{
				result="OK";
				
				sql="SELECT RPAD(SUBSTR(pwd,1,2),LENGTH(pwd),'*') "
				   +"FROM userjoin "
				   +"WHERE userid=? AND hno=? AND hintA=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setInt(2, hno);
				ps.setString(3, hintA);
				rs=ps.executeQuery();
				rs.next();
				result=rs.getString(1);
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return result;
	}
	
	// 회원정보 수정 페이지
	public UserVO userupdateinfo(String userID)
	{
		UserVO vo=new UserVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT userid,name,gender,post,addr1,addr2,phone,birth,email "
					  +"FROM userjoin "
					  +"WHERE userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				vo.setUserID(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setPost(rs.getString(4));
				vo.setAddr1(rs.getString(5));
				vo.setAddr2(rs.getString(6));
				vo.setPhone(rs.getString(7));
				vo.setBirth(rs.getString(8));
				vo.setEmail(rs.getString(9));
			}
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	// 회원정보 수정
	public void userupdate(UserVO vo)
	{
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE userjoin SET "
				      +"pwd=?,email=?,post=?,addr1=?,addr2=?,phone=? "
				      +"WHERE userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getPwd());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPost());
			ps.setString(4, vo.getAddr1());
			ps.setString(5, vo.getAddr2());
			ps.setString(6, vo.getPhone());
			
			ps.setString(7, vo.getUserID());
			ps.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
	}

	// 탈퇴하기
	public String delete(String userID,String pwd)
	{
		String result="";
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT pwd FROM userjoin "
					  +"WHERE userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,userID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String del_pwd=rs.getString(1);
			rs.close();
			
			if(del_pwd.equals(pwd))
			{
				result="yes";
				
				sql="DELETE FROM bookboard "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
						
				sql="DELETE FROM bookdelivery "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
				
				sql="DELETE FROM bookcart "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
						
				sql="DELETE FROM program_application "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
								
				sql="DELETE FROM all_like "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
										
				sql="DELETE FROM all_reply "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
												
				sql="DELETE FROM seatreserve "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();

				sql="DELETE FROM seoul_qna "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
				
				sql="DELETE FROM all_reply "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
				
				sql="DELETE FROM userjoin "
				   +"WHERE userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.executeUpdate();
				ps.close();
								
			}
			else
			{
				result="no";
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return result;
	}
}