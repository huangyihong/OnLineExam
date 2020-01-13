var form = null;
var table = null;
var tree = null;
layui.use(['form', 'tree'], function(){
	  form = layui.form;
	  tree = layui.tree
	  form.render();
	  
	  //渲染树
      var inst1 = tree.render({
        elem: '#leftTree'
        ,id: 'leftTree'
        ,onlyIconControl: true
        ,data:getTreeList()
        ,click: function (obj) {
  		  $("#courseId").val(obj.data.value);
  		  reloadTable();
        }
      });
});
var baseUrl = WEBROOT + '/admin/exam/omQuestion';
var listUrl = baseUrl +'/getList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var importQuestionUrl = baseUrl +'/importQuestion';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
	             ,{field:'questionType', width:100, title: '题型',templet:function(d){
	            	 var questionType = d.questionType;
	            	 var html = '';
	            	 if(questionType=='1'){
	            		 html = '单选题'
	            	 }
	            	 if(questionType=='2'){
	            		 html = '多选题'
	            	 }
	            	 if(questionType=='3'){
	            		 html = '判断题'
	            	 }
	            	 if(questionType=='4'){
	            		 html = '填空题'
	            	 }
	            	 if(questionType=='5'){
	            		 html = '简答题'
	            	 }
	            	 return '<div>'+html+'</div>'
	             }}
	             ,{field:'questionName', width:300, title: '题目',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.questionId+"') >"+d.questionName+"</a></div>"
	              }}
	             ,{field:'rightResult', width:300, title: '标准答案',templet:function(d){
	            	 var questionType = d.questionType;
	            	 if(questionType=='3'){//判断题
	            		 if(d.rightResult=='0'){
	            			 return '<div>错误</div>'
	            		 }
	            		 if(d.rightResult=='1'){
	            			 return '<div>正确</div>'
	            		 }
	            	 }
	            	 return '<div>'+d.rightResult+'</div>'
	             }}
	             ,{field:'questionScore', width:200, title: '题目分值'}
	             ,{field:'courseName', title: '所属科目'}
	           ]];
	var keyFiled= "questionId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&questionId='+id;
	 }
	 url+='&courseId='+$("#courseId").val();
	 var title = '新增';
	 var isreload = 1;
	 if(fntype=='update'){
		 title = '修改';
	 }
	 if(fntype=='view'){
		 title = '查看';
		 isreload = 0;
	 }
	 title += '题目信息';
	 commonCreateView(title,url,isreload,'500','500',1);
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

function importQuestion(){
	commonCreateView('试题导入',importQuestionUrl,1,'800','500',0);
}





