<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
 <script src="https://unpkg.com/vue@3"></script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 100%;
}

</style>
</head>
<body>
  <div class="container">
   <div class="row">
    <div class="text-center">
     <input type="button" value="한식" class="btn-lg btn-danger" style="margin-left: 5px" @click="sendData(1)">
     <input type="button" value="양식" class="btn-lg btn-info" style="margin-left: 5px" @click="sendData(2)">
     <input type="button" value="중식" class="btn-lg btn-warning" style="margin-left: 5px" @click="sendData(3)">
     <input type="button" value="일식" class="btn-lg btn-success" style="margin-left: 5px" @click="sendData(4)">
    </div>
   </div>
   <div style="height: 20px"></div>
   <div class="row">
    <div class="col-md-3" v-for="vo in list">
		    <div class="thumbnail">
		      <a href="#">
		        <img :src="vo.poster" style="width:100%">
		        <div class="caption">
		          <p>{{vo.name}}</p>
		        </div>
		      </a>
		    </div>
		</div>
   </div>
   <div style="height: 20px"></div>
   <div class="row">
     <div class="text-center">
       <ul class="pagination">
          <li v-if="startPage>1"><a style="cursor: pointer" @click="prev()">&lt;</a></li>
		  <li v-for="i in range(startPage,endPage)" :class="i==page?'active':''"><a style="cursor: pointer" @click="pageChange(i)">{{i}}</a></li>
		  <li v-if="endPage<totalpage"><a style="cursor: pointer" @click="next()">&gt;</a></li>
	    </ul>
     </div>
   </div>
  </div>
  <script>
  Vue.component('modal', {
	  template:
	          '<div class="modal" id="myModal" style="display: block;">'
	          +'<div class="modal-dialog">'
	          +'<div class="modal-content">'
	          +'<div class="modal-header">'
	          +'<h4 class="modal-title">Modal Heading</h4>'
	          +'<button type="button" class="close" data-dismiss="modal">×</button>'
	          +'</div>'
	          +'<div class="modal-body">'
	          +'<slot></slot>'
	          +'</div>'
	          +'<div class="modal-footer">'
	          +'<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
	          +'</div>'
	          +'</div>'
	          +'</div>'
	          +'</div>'
	});
    const {createApp}=Vue
    createApp({
    	data(){
    		return {
    			list:[],
    			page:1,
    			type:1,
    			totalpage:1,
    			startPage:1,
    			endPage:1
    		}
    	},
    	mounted(){
    		axios.get('http://localhost/JSPMybatisProject_3/vue/list_vue.do')
    		 .then(response=>{
    			 console.log(response.data)
    			 this.list=response.data
    			 this.page=response.data[0].curpage
    			 this.totalpage=response.data[0].totalpage
    			 this.startPage=response.data[0].startPage
    			 this.endPage=response.data[0].endPage
    			 this.type=response.data[0].type
    		 })
    	},
    	methods:{
    		sendData(type){
    			this.page=1;
    			this.type=type;
    			axios.get('http://localhost/JSPMybatisProject_3/vue/list_vue.do',{
    				 params:{
    					 page:this.page,
    					 type:this.type
    				 }
    			}).then(response=>{
       			     console.log(response.data)
       			     this.list=response.data
       			     this.page=response.data[0].curpage
     			     this.totalpage=response.data[0].totalpage
     			     this.startPage=response.data[0].startPage
     			     this.endPage=response.data[0].endPage
     			     this.type=response.data[0].type
       		    })
    		},
    		range:function(start,end){
    			let arr=[];
    			let length=end-start;
    			for(let i=0;i<=length;i++)
    			{
    			   arr[i]=start;
    			   start++;
    			}
    			return arr;
    		},
    		pageChange(page){
    			this.page=page;
    			this.recvData()
    		},
    		prev(){
    			this.page=this.startPage-1;
    			this.recvData()
    		},
    		next(){
    			this.page=this.endPage+1;
    			this.recvData()
    		},
    		recvData(){
    			
    			axios.get('http://localhost/JSPMybatisProject_3/vue/list_vue.do',{
    				 params:{
    					 page:this.page,
    					 type:this.type
    				 }
    			}).then(response=>{
       			     console.log(response.data)
       			     this.list=response.data
       			     this.page=response.data[0].curpage
     			     this.totalpage=response.data[0].totalpage
     			     this.startPage=response.data[0].startPage
     			     this.endPage=response.data[0].endPage
     			     this.type=response.data[0].type
       		    })
    		}
    	}
    }).mount('.container')
  </script>
</body>
</html>