package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class GoodsDAO {
	
	private static SqlSessionFactory ssf=CommonsDataBase.getSsf();
	
	// 기능 설정
	public static List<GoodsVO> goodsListData(Map map)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("goodsListData", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// 총페이지
	public static int goodsTotalPage(Map map)
	{
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("goodsTotalPage", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	/*
	 		1. xml
	 		2. annotation : Spring
	 		3. 실무 = XML + Annotation
	 */
	// 디테일
	public static GoodsVO goodsDetailData(Map map)
	{
		GoodsVO vo=new GoodsVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("goodsDetailData", map);
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
	
	// 마이페이지에 장바구니 출력
	public static void cartInsert(CartVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			int count=session.selectOne("cartIsGoodsCount", vo);
			// 구매가 안된 상품이 있는지 확인
			if(count!=0)
			{
				// 존재한다면 => 수량만 증가
				session.update("cartGoodsUpdate", vo);
			}
			else
			{	// 없다면 => 추가
				session.insert("cartGoodsInsert", vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static List<CartVO> mypageGoodsCartData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("mypageGoodsCartData", map);
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
	
	public static void cartBuyInsert(CartVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("cartBuyInsert", vo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static List<CartVO> mypageGoodsBuyData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("mypageGoodsBuyData", map);
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
	
	// 장바구니 삭제
	/*
		<delete id="cartDelete" parameterType="int">
			DELETE FROM cart
			WHERE cart_no=#{cart_no}
		</delete>
	 */
	public static void cartDelete(int cart_no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true); // commit을 날려야 하기 때문에 true
			session.delete("cartDelete", cart_no);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
	
	/*
			<update id="goodsCartBuy" parameterType="int">
				UPDATE cart SET
				issale=1
				WHERE cart_no=#{cart_no}
			</update>
	 */
	public static void goodsCartBuy(int cart_no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("goodsCartBuy", cart_no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}
}










