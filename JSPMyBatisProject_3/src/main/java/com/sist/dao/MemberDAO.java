package com.sist.dao;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
public class MemberDAO {
   private static SqlSessionFactory ssf=
		            CommonsDataBase.getSsf();
   // 기능 설정 
   public static  MemberVO isLogin(String id,String pwd)
   {
	   MemberVO vo=new MemberVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();//getConnection()
		   int count=session.selectOne("memberIdCount",id);
		 // resultType과 일치                        #{id}
		   if(count==0)
		   {
			   vo.setMsg("NOID");
		   }
		   else
		   {
			   MemberVO dvo=session.selectOne("memberPwd",id);
			   System.out.println("pwd:"+pwd+",db:"+dvo.getPwd());
			   if(pwd.equals(dvo.getPwd()))
			   {
				   
				   vo.setMsg("OK");
				   vo.setId(dvo.getId());
				   vo.setName(dvo.getName());
				   vo.setAdmin(dvo.getAdmin());
				   vo.setPhone(dvo.getPhone());
				   vo.setAddr1(dvo.getAddr1());
				   vo.setAddr2(dvo.getAddr2());
				   vo.setPost(dvo.getPost());
				   vo.setEmail(dvo.getEmail());
			   }
			   else
			   {
				   vo.setMsg("NOPWD");
			   }
		   }
		   
	   }catch(Exception ex)
	   {
		   // 에러 처리 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // ps.close(),conn.close()
		   // DBCP => 재사용 (반환)
	   }
	   return vo;
   }
   
}
