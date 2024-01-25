package com.sist.temp;
import com.sist.vo.*;
import java.util.*;
public class MainClass {
	public static void main(String[] args) {
		//String s=reserveDayInput();
		//System.out.println(s);
		MyDAO dao=new MyDAO();
		/*List<Integer> list=dao.foodNumberData();
		for(int i:list)
		{
			FoodVO vo=new FoodVO();
			vo.setFno(i);
			vo.setRday(reserveDayInput());
			dao.foodDayInsert(vo);
		}
		System.out.println("저장 완료");*/
		for(int i=1;i<=31;i++)
		{
			ReserveDayVO vo=new ReserveDayVO();
			vo.setRno(i);
			vo.setRtime(reserveTimeInput());
			dao.foodTimeInsert(vo);
		}
		System.out.println("저장 완료");
	}
	public static String reserveTimeInput()
	{
		String result="";
		int[] com=new int[(int)(Math.random()*5)+5];
		int su=0;
		boolean bCheck=false;
		for(int i=0;i<com.length;i++)
		{
			bCheck=true;
			while(bCheck)
			{
				su=(int)(Math.random()*18)+1;
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==su)
					{
						bCheck=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		Arrays.sort(com);
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
		}
		result=result.substring(0, result.lastIndexOf(","));
		return result;
	}
	
	public static String reserveDayInput()
	{
		String result="";
		int[] com=new int[(int)(Math.random()*5)+10];
		int su=0;
		boolean bCheck=false;
		for(int i=0;i<com.length;i++)
		{
			bCheck=true;
			while(bCheck)
			{
				su=(int)(Math.random()*31)+1;
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==su)
					{
						bCheck=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		Arrays.sort(com);
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
		}
		result=result.substring(0, result.lastIndexOf(","));
		return result;
	}
}
