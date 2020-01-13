var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omPaper';
var listUrl = baseUrl +'/getList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var showUrl = baseUrl +'/show';
var rebuildUrl = baseUrl +'/rebuild';
var exportPaperUrl = baseUrl +'/exportPaper';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
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
	             ,{field:'paperName', width:200, title: '预览',templet:function(d){
//	            	 var addMode = d.addMode;
//	            	 if("1"==addMode){
	            		 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"') >预览</a></div>"
//	            	 }else{
//	            		 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"') >预览</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' class='layui-table-link'  onclick=rebuild('"+d.paperId+"') >重新生成</a></div>" 
//	            	 }
	              }}
	           ]];
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

function addAuto(){
	 var url =createUrl+'?fntype=add&addMode=0';
	 var title = '新增试卷信息';
	 var isreload = 1;
	 commonCreateView(title,url,isreload,'500','500',1);
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

//预览
function showPaper(id){
	 var url =showUrl+'?paperId='+id;
	 var title = '试卷预览';
	 var isreload = 0;
	 commonCreateView(title,url,isreload,'500','500',1);
}

//随机试卷重新生成
function rebuild(id){
	var url =rebuildUrl+'?paperId='+id;
	$.ajax({
		type:"POST",
		async:false,
		url:url,
		success:function (data) {
			if (data.code == "200") {
				layer.msg('重新生成试卷成功', {icon: 1});
			}else{
				layer.msg('重新生成试卷失败：'+data.message, {icon: 5});
			}
		}
	});
}

function exportPaper(){
	var keyFiled= "paperId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
    	layer.alert("请选择1条记录");
    	return;
    }
    if(data[0].addMode!='1'){
    	layer.alert("请选择固定试卷导出");
    	return;
    }
    var id = data[0][keyFiled];
    var url = exportPaperUrl+'?paperId='+id;
    window.location.href = url;
}





