package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class SeoulDAO {
	private static SqlSessionFactory ssf=CommonsDataBase.getSsf();
	/*
	 	<select id="seoulLocationListData" resultType="SeoulVO" parameterType="hashmap">
			SELECT no, poster, title, num
			FROM(SELECT no, poster, title, rownum as num
			FROM (SELECT no, poster, title
			FROM seoul_location ORDER BY no ASC))
			WHERE num BETWEEN #{start} AND #{end}
		</select>
	 */
	// 1. 서울지역맛집 리스트 출력
	public static List<SeoulVO> seoulLocationListData(Map map)
	{
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("seoulLocationListData", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// 2. 총페이지
	public static int seoulLocationTotalPage()
	{
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("seoulLocationTotalPage");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	// 3. 서울지역명소 리스트 출력
		public static List<SeoulVO> seoulNatureListData(Map map)
		{
			List<SeoulVO> list=new ArrayList<SeoulVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("seoulNatureListData", map);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		// 4. 총페이지
		public static int seoulNatureTotalPage()
		{
			int total=0;
			SqlSession session=null;
			try {
				session=ssf.openSession();
				total=session.selectOne("seoulNatureTotalPage");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return total;
		}
		
		// 5. 서울샵 리스트 출력
		public static List<SeoulVO> seoulShopListData(Map map)
		{
			List<SeoulVO> list=new ArrayList<SeoulVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("seoulShopListData", map);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		// 6. 총페이지
		public static int seoulShopTotalPage()
		{
			int total=0;
			SqlSession session=null;
			try {
				session=ssf.openSession();
				total=session.selectOne("seoulShopTotalPage");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return total;
		}
		
		/*
		 	<update id="seoulLocationHitIncrement" parameterType="int">
				UPDATE seoul_location SET
				hit=hit+1
				WHERE no=#{no}
			</update>
			<select id="seoulLocationDetailData" resultType="SeoulVO" parameterType="int">
				SELECT * FROM seoul_location
				WHERE no=#{no}
			</select>
		 */
		public static SeoulVO seoulLocationDetailData(int no)
		{
			// 매개변수는 사용자 요청값
			SeoulVO vo=new SeoulVO();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				session.update("seoulLocationHitIncrement", no);
				session.commit();
				vo=session.selectOne("seoulLocationDetailData", no);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return vo;
		}
		/*
		 	<select id="seoulFoodData" resultType="FoodVO" parameterType="string">
				SELECT fno, name, poster, rownum
				FROM food_menu_house
				WHERE address LIKE '%'||#{address}||'%'
				AND rownum&lt;=5
			</select>
		 */
		public static List<FoodVO> seoulFoodData(String address)
		{
			List<FoodVO> list=new ArrayList<FoodVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("seoulFoodData", address);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
}








