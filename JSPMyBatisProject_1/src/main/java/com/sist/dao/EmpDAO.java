package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class EmpDAO {
	// 파싱 (XML) => <select id="">SELECT~</SELECT>
	// map.put("id", "SELECT~")
	private static SqlSessionFactory ssf;
	
	// XML을 전송 => 파싱
	static
	{
		try 
		{
			// 1. XML 읽어온다.
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// Map
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 기능 설정
	public static List<EmpVO> empListData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empListData");
		session.close();
		return list;
	}
	
	public static List<EmpVO> empDeptJoinData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empDeptJoinData");
		session.close();
		return list;
	}
	
	public static List<EmpVO> empDeptSubQueryData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empDeptSubQueryData");
		session.close();
		return list;
	}
	
	public static List<String> empNameData()
	{
		SqlSession session=ssf.openSession();
		List<String> list=session.selectList("empNameData");
		session.close();
		return list;
	}
	public static List<EmpVO> empFindData(String[] names)
	{	
		SqlSession session=ssf.openSession();
		Map map=new HashMap();
		map.put("names", names);
		List<EmpVO> list=session.selectList("empFindData", map);
		session.close();
		return list;
	}
}


















