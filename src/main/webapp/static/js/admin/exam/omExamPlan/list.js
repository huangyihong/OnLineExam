var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omExamPlan';
var listUrl = baseUrl +'/getList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var assignUserUrl = baseUrl +'/assignUser';
var showUrl = WEBROOT + '/admin/exam/omPaper/show';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
	             ,{field:'planName', width:300, title: '考试名称',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.planId+"') >"+d.planName+"</a></div>"
	              }}
	             ,{field:'paperName', width:200, title: '试卷名称',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"') >"+d.paperName+"</a></div>"
	              }}
	             /*,{field:'beginTime', width:200, title: '开考时间'}
	             ,{field:'status', width:200, title: '状态',templet:function(d){
	            	 var html = '';
	            	 if(d.status=='1'){
	            		 html = '待考';
	            	 }
	            	 if(d.status=='2'){
	            		 html = '正在考试';
	            	 }
	            	 if(d.status=='3'){
	            		 html = '考试结束';
	            	 }
	                 return '<div>'+html+'</div>'
	              }}*/
	             ,{field:'paperTime', width:100, title: '考试时长'}
	             ,{field:'paperScore', width:100, title: '总分'}
	             ,{field:'passingScore', width:100, title: '及格分',templet:function(d){
	            	 var html = '';
	            	 if(d.passingType=='1'){
	            		 html = d.passingScore+'分';
	            	 }
	            	 if(d.passingType=='2'){
	            		 html = d.passingScore+'%';
	            	 }
	            	 
	                 return '<div>'+html+'</div>'
	              }}
	             ,{field:'singleCount', width:100, title: '题目数量',templet:function(d){
	            	 var num = d.singleCount+d.multiCount+d.judgeCount+d.blankCount+d.answerCount
	                 return '<div>'+num+'</div>'
	              }}
	             //,{field:'courseName', width:100, title: '科目'}
	           ]];
	var keyFiled= "planId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&planId='+id;
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
	 title += '考试安排信息';
	 commonCreateView(title,url,isreload,'650','550');
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

//用户授权
function assignUser(type){
	var keyFiled= "planId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
     	layer.alert("请选择1条记录");
     	return;
    }
    var id = data[0][keyFiled];
    var url =assignUserUrl+'?examUserType='+type+'&planId='+id;
    var title = '考试人员授权';
    if(type=='2'){
    	title = '评卷人员授权';
    }
    commonCreateView(title,url,0,'600','500',1);
}

//预览
function showPaper(id){
	 var url =showUrl+'?paperId='+id;
	 var title = '试卷预览';
	 var isreload = 0;
	 commonCreateView(title,url,isreload,'500','500',1);
}




