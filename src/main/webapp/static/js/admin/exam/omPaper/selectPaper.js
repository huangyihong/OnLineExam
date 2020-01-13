var form = null;
var table = null;
var element = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omPaper';
var listUrl = baseUrl +'/getList';

$(function () {
	//初始列表
	initGrid();
	
});

//初始列表
function initGrid() {
	var cols = [[
	             {type: 'radio',width:100,fixed: 'left'}
	             ,{field:'paperName', width:200, title: '试卷名称',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.paperId+"') >"+d.paperName+"</a></div>"
	              }}
	             ,{field:'paperScore', width:300, title: '试卷总分'}
	             ,{field:'paperTime', width:200, title: '考试时长'}
	             ,{field:'singleCount', width:100, title: '题目数量',templet:function(d){
	            	 var num = d.singleCount+d.multiCount+d.judgeCount+d.blankCount+d.answerCount
	                 return '<div>'+num+'</div>'
	              }}
	             ,{field:'courseName', width:100, title: '科目'}
	             ,{field:'addMode', width:200, title: '创建类型',templet:function(d){
	            	 var addMode = d.addMode;
	            	 if("1"==addMode){
	            		 return "固定试卷"
	            	 }else{
	            		 return "随机试卷" 
	            	 }
	              }}
	           ]];
	var keyFiled= "questionId";//主键
	var limit = 10;//分页
	var keyFiled= "paperId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&paperId='+id;
	 }
	 var title = '新增';
	 var isreload = 1;
	 if(fntype=='update'){
		 title = '修改';
	 }
	 if(fntype=='view'){
		 title = '查看';
		 isreload = 0;
	 }
	 title += '试卷信息';
	 commonCreateView(title,url,isreload,'500','500',1);
}

function getCheckedObjs(){
	 var checkStatus = table.checkStatus('layuiTable')
     ,data = checkStatus.data;
     if(data.length=='0'){
     	layer.alert("请选择一条数据");
     	return;
     }
     return data
}

var callbackdata = function () {
	var msg = "";
	var objs = getCheckedObjs()
	return {
		msg: msg
		,objs:objs
	};
}





