package com.sist.temp;

import java.lang.reflect.Method;

class A{
	public void aaa()
	{
		System.out.println("A:aaa() Call...");
	}
	public void bbb()
	{
		System.out.println("A:bbb() Call...");
	}
	public void ccc()
	{
		System.out.println("A:ccc() Call...");
	}
}
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*A a=new A();
		a.aaa();
		a.bbb();
		a.ccc(); */ // => 이건 자바 코딩이 가능할때만 사용 가능하다. => 하지만 라이브러리에서는 자바코딩이 불가, 어떻게 메모리 할당이 가능하게 할것인지?
		
		// 이 방법을 사용한다.
		try {
			// A a=new A()
			Class clsName=Class.forName("com.sist.temp.A");
			Object obj=clsName.getDeclaredConstructor().newInstance();
			/*A a=(A)obj;
			a.aaa();
			a.bbb();
			a.ccc(); */
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
				m.invoke(obj, null); // null : public void aaa() 여기 괄호 안의 매개변수 자리이다. 없으니까 null 사용
			}
		} catch (Exception e) {	}
	}

}









