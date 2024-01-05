package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;
import java.io.*;
public class DataBoardDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   // xml => parse
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // => 단순 (1. JOIN , 2. 동적쿼리) 
   //  목록 출력 
   /*
    *   <select id="databoardListData" resultType="DataboardVO" parameterType="hashmap">
		    SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
		    FROM (SELECT no,subject,name,regdate,hit,rownum as num 
		    FROM (SELECT no,subject,name,regdate,hit 
		    FROM project_databoard ORDER BY no DESC))
		    WHERE num BETWEEN #{start} AND #{end}
       </select>
    */
   public static List<DataBoardVO> databoardListData(Map map)
   {
	   SqlSession session=null;
	   List<DataBoardVO> list=new ArrayList<DataBoardVO>();
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("databoardListData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   /*
    *   <select id="databoardRowCount" resultType="int">
         SELECT COUNT(*) FROM project_databoard
        </select>
    */
   public static int databoardRowCount()
   {
	   SqlSession session=null;
	   int count=0;
	   try
	   {
		   // getConnection()
		   session=ssf.openSession();
		   count=session.selectOne("databoardRowCount");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // 반환 => disConnection()
		   // => Connection/PreparedStatement
	   }
	   return count;
   }
   public static void databoardInsert(DataBoardVO vo)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);//autocommit
		   session.insert("databoardInsert",vo);
		   //session.commit(); => update,delete
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	  
   }
   
   public static DataBoardVO databoardDetailData(int no)
   {
	   
	   DataBoardVO vo=new DataBoardVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);
		   session.update("hitIncrement",no);
		   
		   vo=session.selectOne("databoardDetailData",no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   public static DataBoardVO databoardFileInfoData(int no)
   {
	   DataBoardVO vo=new DataBoardVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   vo=session.selectOne("databoardFileInfoData",no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   public static String databoardDelete(int no,String pwd)
   {
	   // var res:String="no" val
	   String res="no";
	   SqlSession session=ssf.openSession(true);
	   String db_pwd=session.selectOne("databoardGetPassword",no);
	   if(db_pwd.equals(pwd))
	   {
		   res="yes";
		   session.delete("databoardDelete",no);
	   }
	   return res;
   }
   public static DataBoardVO databoardUpdateData(int no)
   {
	   SqlSession session=ssf.openSession();
	   DataBoardVO vo=session.selectOne("databoardDetailData",no);
	   session.close();
	   return vo;
   }
   /*
    *      <select id="databoardGetPassword" resultType="string" parameterType="int">
		     SELECT pwd FROM project_databoard
		     <include refid="where-no"/>
		   </select>
		   
		   <update id="databoardUpdate" parameterType="DataBoardVO">
		     UPDATE project_databoard SET
		     name=#{name},subject=#{subject},content=#{content}
		     <include refid="where-no"/>
		   </update>
    */
   public static String databoardUpdate(DataBoardVO vo)
   {
	   String res="no";
	   SqlSession session=ssf.openSession(true);
	   String db_pwd=session.selectOne("databoardGetPassword",vo.getNo());
	   if(db_pwd.equals(vo.getPwd()))
	   {
		   res="yes";
		   session.update("databoardUpdate",vo);
	   }
	   session.close();
	   return res;
   }
}