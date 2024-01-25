let httpRequest=null;
// 브라우저 내장객체를 생성하는 함수
// XMLhttpRequest => 서버에 요청 전송 => 서버로부터 결과값을 받는다.
function getXMLHttpRequest()
{
	// window.ActiveObject => Internet Explore
	if(window.XMLHttpRequest) // => 기타 브라우저 (크롬, 파이어폭스 등)
	{
		return new XMLhttpRequest();
	}
	else
	{
		return null;
	}
}

// 서버에 요청 => find.jsp?fd=aaa
/*
	$.ajax({
		type:post
		url:result.jsp => result.jsp?no=1
		data: {no:1}
		success:function(res){
			res=> 실행결과물 => html, json, 일반문자
			=> resText, resXML
		}
	})
*/
function sendRequest(url, params, callback, method)
{
	// XMLHttpRequest 얻기
	httpRequest=getXMLHttpRequest();
	
	// 전송방식처리 <form method="past">
	let httpMethod=method?method:'GET' // method가 null이 아닐 때
	if(httpMethod!='GET' && httpMethod!='POST')
	{
		httpMethod='GET'
	}
	// params => null이거나 ''일 경우
	// GET => url? => send()
	let httpParams=(params==null || params=='')?null:params
	let httpUrl=url;
	
	// GET 방식
	if(httpMethod=='GET' && httpParams!=null)
	{
		httpUrl=httpUrl+"?"+httpParams
		
	}
	
	// 연결
	httpRequest.open(httpMethod, httpUrl, true)
	// true => 비동기 방식
	httpRequest.setRequestHeader('Content-Type', "application/x-www-form-urlencoded")
	
	// 콜백 함수 지정 (자동 호춡)
	httpRequest.onreadystatechange=callback
	
	// 데이터 전송
	httpRequest.send(httpMethod=='POST'?httpParams:null)
}



























