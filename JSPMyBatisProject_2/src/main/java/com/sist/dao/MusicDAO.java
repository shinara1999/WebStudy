package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
// musicFindData
public class MusicDAO {
	private static SqlSessionFactory ssf;
	// 초기화 => XML을 파싱
	static
	{
		// 자동 인식
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<MusicVO> musicFindData(Map map)
	{
		SqlSession session=null;
		List<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("musicFindData", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
}
