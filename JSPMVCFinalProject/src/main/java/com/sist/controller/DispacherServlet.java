package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
		XML 파싱
		Annotation => 클래스 찾기 => 메소드 찾기
		=> MVC 동작
		   1. 요청 (JSP파일에서) => <a> , <form>
		      => DispacherServlet(Controller) => 이미 제작
		      => @WebServlet("*.do") => .do를 주는 이유는 Controller를 찾기 위해서이다.
		      			 	 ====== list.do , insert.do
		      			 	 		====      ====== 구분문자 (.do 앞)
		      		  | 서버에서 받을 수 있는 부분 : URI , URL (서버 연결 주소란)
		      		  						   => URI를 이용해서 Model을 찾는다.
		   2. DispacherServlet(Controller)
		      사용자는 요청을 하고 결과값만 받으면 된다.
		      Controller의 역할: 요청을 받는 역할 (브라우저로부터 => 요청을 받을 수 있는 파일이 2개뿐이다.)
		      																==== JSP , Servlet
		      																JSP : 보안이 취약하기 때문에 화면 출력(View)만 담당한다.
		      																Servlet : 화면 출력이 아니라 Java와 Html을 연결해준다.
		      																						 =========== Java와 Html이 분리되는 작동이기 때문에 연결 필요
		      				    																				 분리된 자바 : Model , 분리된 html : View
		      				    																				 => 두명이서 작업이 가능하게 됨.
		      				    분리된 Java를 찾는 역할 ==================> 연결
		      				    					 request/ session
		   3. MVC 목적
		       1) 보안 문제 해결하여 유지보수 가능하게 한다. (JSP는 배포시에 소스를 통으로 전송해야 하기 때문에 보안이 취약하다. => 소스를 감춰야 하는데 JSP에서는 불가)
		       2) 여러명이 동시개발 할 수 있게 한다. (Front-Html / Back-Java)
		       3) JSP의 단점 : 확장성이 없다. (유지보수 하면서 기능추가가 어렵다.) , 재사용이 어렵다. ,  변경이 어렵다.
		       				  => 한번 쓰고 다른 사이트 제작시 버려야 한다.
		       				  => 이를 Java의 장점(재사용 가능)으로 해결 가능
		       4) 신규사원 확장 
		       	  => 더 분업화 : MVVM / MVVP
		   4. 소프트웨어 => 회귀
		      ===============
		   5. 동작
		   					  request
		   	  JSP (링크, 버튼) ========= DispacherServlet
		   	  								=> Model (DAO와 연동)
		   	  								   =====
		   	 	   (다시 servlet으로 돌아간다.) <= request에 결과값 담기 => setAttribute()
		   	  		 => request가 필요한 경우 : request를 JSP로 전송
		   	  		 						 JSP로 request를 전송하는 메소드 => forward(request, response)
		   	  		 						 							=> SELECT
		   	  		 						
		   	  		 => request가 필요없는 경우 : 화면만 이동	 
		   	  		 						  화면만 변경하는 메소드 => sendRedirect(파일명) ex) 회원가입, 로그인, 글쓰기 등
		   	  		 						  					=> INSERT , UPDATE , DELETE
		   	   => DispacherServlet은 수정을 하면 안되고 고정시켜야 한다. => .jar파일을 만드는 이유이다. (만들어서 라이브러리에 저장)
		
		1. 설정 파일
		   Spring : application-context.xml
		   			application-datasource.xml
		   			application-security.xml
		   			=> 태그 속성은 Spring에서 제공하는 것만 사용 가능하다.
		   			=> dtd : 태그를 제공해 주는 파일 (볼 줄 알아야 한다.)
		       
 */
import java.util.*;
import java.net.*;
@WebServlet("*.do")
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> clsList=new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		// 1. XML의 경로 읽기 => XML안에 클래스 등록
		try {
			URL url=this.getClass().getClassLoader().getResource("."); // 현재경로를 준다.
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator); // File.separator : window => \\, mac => / 인데 이것을 자동으로 변경해준다. (window, mac 둘 다 사용 가능)
			path=path.substring(0,path.lastIndexOf(File.separator));
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCFinalProject\WEB-INF\classes
			System.out.println(path);
			path=path+File.separator+"application.xml";
			
		// 2. XML 파싱 => XML안에서 내가 필요한 데이터 가져오기
			// 2-1. 파서기 생성
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			// 2-2. XML 전송
			Document doc=db.parse(new File(path));
			// 2-3. root태그 가져오기 => 데이터베이스의 테이블을 의미한다.
			Element root=doc.getDocumentElement(); // 이 root태그가 application.xml의 beans태그이다.
			// 2-4. root밑에 있는 태그(application.xml=>bean)를 모아서 데이터 추출한다.
			NodeList list=root.getElementsByTagName("bean");
			
			for(int i=0;i<list.getLength();i++) // for문 사용 시 bean태그가 몇개든지 다 가져올 수 있다.
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id"); // bean태그의 id 속성값 가져오기
				String cls=bean.getAttribute("class"); // bean태그의 class 속성값 가져오기
				System.out.println(id+":"+cls); // 확인
				
				clsList.add(cls);
			}
			
		} catch (Exception e) {}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		// http://localhost/JSPMVCFinalProject/board/list.do
		//					================================ uri
		// ================================================= url
		//				    ================== contextPath
		uri=uri.substring(request.getContextPath().length()+1); // contextPath까지 자르고 +1(/ 제거)을 하면 board/list.do가 남는다.
		try {
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance(); // 메모리 할당
				Method[] methods=clsName.getDeclaredMethods(); // 메모리에 든 데이터 읽기
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class); // Annotation은 하나만 올라가는 것이 아니라 여러개가 동시에 올라갈 수도 있다.
					if(rm.value().equals(uri))
					{
						String jsp=(String)m.invoke(obj, request, response);
						if(jsp==null) // void => ajax
						{
							return;
						}
						else if(jsp.startsWith("redirect"))
						{
							// return "redirect:list.do"
							jsp=jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
						}
						else 
						{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
			}
		} catch (Exception e) {}
		
	}

}
















