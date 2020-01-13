var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omExamSubmit';
var listUrl = baseUrl +'/getHistoryList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var exportGradeUrl = baseUrl +'/exportGrade';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {field:'markUser', width:200, title: '操作',templet:function(d){
            		 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"','"+d.planId+"','"+d.userId+"','historyExam') >回顾试卷</a></div>"
                 }}
	             ,{field:'totalScore', width:200, title: '得分'}
	             ,{field:'planName', width:200, title: '考试名称'}
	             ,{field:'paperName', width:200, title: '试卷名称'}
	             ,{field:'realName', width:200, title: '考试人员'}
	             ,{field:'markUser', width:200, title: '阅卷人'}
	             ,{field:'startTime', width:200, title: '开始答题时间'}
	             ,{field:'submitTime', width:200, title: '提交试卷时间'}
	             ,{field:'markTime', width:200, title: '阅卷完成时间'}
	             ,{field:'publishTime', width:200, title: '成绩发布时间'}
	           ]];
	var keyFiled= "submitId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&submitId='+id;
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
	 title += '科目信息';
	 commonCreateView(title,url,isreload,'500','250');
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

//人工评卷markExam  回顾试卷historyExam
function showPaper(paperId,planId,userId,type){
	var baseUrl = WEBROOT + '/admin/exam/omPaper';
	var showUrl = baseUrl +'/exam';
	 var url =showUrl+'?type='+type+'&paperId='+paperId+'&planId='+planId+'&answerUserId='+userId;
	 var title = '人工评卷';
	 var isreload = 1;
	 if(type=='historyExam'){
		 title = '回顾试卷'; 
		 isreload = 0;
	 }
	 commonCreateView(title,url,isreload,'500','500',1);
}

//导出成绩
function exportGrade(){
	layer.confirm('确定根据搜索的查询内容，导出全部记录？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			var url =exportGradeUrl+"?1=1";
			var data = $('#formQuery').serializeArray();
			$.each(data, function() {
				var value = $.trim(this.value);
				url +='&'+this.name+'='+value;
			});
			window.open(url);
			layer.closeAll();
		}, function(){
			
		});
}




