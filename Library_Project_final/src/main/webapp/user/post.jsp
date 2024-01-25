<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top:20px;
}
.row{
	margin:0px auto;
	width:450px;
}
.dataTr:hover{
	cursor:pointer;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').on('click',function(){
		let dong=$('#dong').val()
		if(dong.trim()==="")
		{
			$('#dong').focus()
			return;
		}
		
		$.ajax({
			type:'post',
			url:'../user/post_ok.do',
			data:{"dong":dong},    // dong?=aaa
			success:function(json)
			{
				let res=JSON.parse(json);
				//console.log(res)
				let count=res[0].count
				//console.log("count="+count)
				let html='';
				if(count===0)
				{
					html+='<table class="table">'
					    +'<tr>'
					    +'<td class="text-center">'
					    +'검색 결과가 없습니다'
					    +'</td>'
					    +'</tr>'
					    +'</table>'
				}
				else
				{
					html+='<table class="table">'
					    +'<tr>'
					    +'<th class="text-center" width="20%">우편번호</th>'
					    +'<th class="text-center" width="80%">주소</th>'
					    +'</tr>'
					for(let vo of res)
					{
						html+='<tr onclick="ok(\''+vo.zipcode+'\',\''+vo.address+'\')" class="dataTr">'
						    +'<td class="text-center" width="20%">'+vo.zipcode+'</td>'
						    +'<td width="80%">'+vo.address+'</td>'
						    +'</tr>'
					}
					html+='</table>'
				}
				$('#print').html(html);
			}
		})
	})
})
function ok(zip,addr)
{
	parent.frm.post1.value=zip;
	parent.frm.addr1.value=addr;
	parent.Shadowbox.close();
}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td>
          입력:<input type="text" size="20" class="input-sm" id="dong">
          <input type="button" value="우편번호 검색" class="btn btn-sm btn-info" id="findBtn">
          </td>
        </tr>
      </table>
    </div>
    <div style="height:20px"></div>
    <div class="row" id="print">
        
    </div>
  </div>
</body>
</html>