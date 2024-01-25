package com.sist.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class ReserveDAO {
  private Connection conn;
  private PreparedStatement ps;
  private static SqlSessionFactory ssf=
		        CommonsDataBase.getSsf();
  private static ReserveDAO dao;
  
  /*public static ReserveDAO newInstance()
  {
	  if(dao==null)
		  dao=new ReserveDAO();
	  return dao;
  }*/
  public static List<FoodVO> reserveFoodListData(int no)
  {
	  SqlSession session=ssf.openSession();
	  Map map=new HashMap();
	  map.put("no", no);
	  List<FoodVO> list=
			  session.selectList("reserveFoodListData",map);
	  session.close();
	  return list;
  }
  public static String reserveDays(int fno)
  {
	  SqlSession session=ssf.openSession();
	  String rdays=session.selectOne("reserveDays",fno);
	  session.close();
	  return rdays;
  }
  /*
   *   <select id="reserveTimes" resultType="string" parameterType="int">
	    SELECT rtime FROM reserve_day
	    WHERE rno=#{rno}
	  </select>
	  <select id="reserveGetTime" resultType="string" parameterType="int">
	    SELECT time FROM reserve_time
	    WHERE tno=#{tno}
	  </select>
   */
  public static String reserveTimes(int rno)
  {
	  SqlSession session=ssf.openSession();
	  String rtimes=session.selectOne("reserveTimes",rno);
	  session.close();
	  return rtimes;
  }
  public static String reserveGetTime(int tno)
  {
	  SqlSession session=ssf.openSession();
	  String rtimes=session.selectOne("reserveGetTime",tno);
	  session.close();
	  return rtimes;
  }
  public static void reserveInsert(ReserveInfoVO vo)
  {
	  SqlSession session=ssf.openSession(true);
	  session.insert("reserveInsert",vo);
	  session.close();
  }
  /*
   *   <select id="reserveMyPageListData" resultMap="myMap" parameterType="string">
	    SELECT no,r.fno,poster,name,address,phone,
	           day,time,inwon,regdate
	    FROM reserve_info r , food_menu_house f
	    WHERE r.fno=f.fno
	    AND id=#{id}
	    ORDER BY no DESC
	  </select>
   */
  public static List<ReserveInfoVO> reserveMyPageListData(String id)
  {
	  SqlSession session=ssf.openSession();
	  List<ReserveInfoVO> list=session.selectList("reserveMyPageListData",id);
	  session.close();
	  return list;
  }
  public static List<ReserveInfoVO> reserveAdminPageListData()
  {
	  SqlSession session=ssf.openSession();
	  List<ReserveInfoVO> list=session.selectList("reserveAdminPageListData");
	  session.close();
	  return list;
  }
  public static void reserveAdminOk(int no)
  {
	  SqlSession session=ssf.openSession(true);
	  session.update("reserveAdminOk", no);
	  session.close();
  }
}
