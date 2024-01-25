package com.sist.temp;
import java.util.*;
public class Container {
	private Map map=new HashMap();
	private String[] cls= {"com.sist.temp.BoardModel", "com.sist.temp.MemberModel", "com.sist.temp.FoodModel"};
	private String[] key= {"board", "member", "food"};
	// <bean id="" class="">
	public Container()
	{
		try {
			for(int i=0;i<cls.length;i++)
			{
				// 1차 => Spring => factorypattern
				Class clsName=Class.forName(cls[i]);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				map.put(key[i], obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Model getBean(String key)
	{
		return (Model)map.get(key);
	}
}
