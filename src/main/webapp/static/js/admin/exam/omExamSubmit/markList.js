var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omExamSubmit';
var listUrl = baseUrl +'/getMarkList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
	             ,{field:'markUser', width:200, title: '操作',templet:function(d){
            		 return "<div><a href='javascript:void(0)' class='layui-btn bg-color-blue-3195db layui-btn-sm'  onclick=showPaper('"+d.paperId+"','"+d.planId+"','"+d.userId+"','markExam') >人工评卷</a></div>"
                 }}
	             ,{field:'planName', width:200, title: '考试名称'}
	             ,{field:'paperName', width:200, title: '试卷名称'}
//	            	 ,templet:function(d){
//	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"','"+d.planId+"','historyExam') >"+d.paperName+"</a></div>"
//	              }}
	             ,{field:'realName', width:200, title: '考试人员'}
	             /**
	             ,{field:'status', width:200, title: '状态',templet:function(d){
	            	 var status = d.status;
	            	 var html = '';
	            	 if(status=='1'){
	            		 html = '开始答题'
	            	 }
	            	 if(status=='2'){
	            		 html = '提交试卷'
	            	 }
	            	 if(status=='3'){
	            		 html = '阅卷完成'
	            	 }
	            	 if(status=='4'){
	            		 html = '成绩发布'
	            	 }
	            	 return '<div>'+html+'</div>'
	             }}
	             ,{field:'startTime', width:200, title: '开始答题时间'}
	             ,{field:'submitTime', width:200, title: '提交试卷时间'}
	             ,{field:'markTime', width:200, title: '阅卷完成时间'}
	             ,{field:'publishTime', width:200, title: '成绩发布时间'}
	             **/
	             ,{field:'totalScore', width:200, title: '得分'}
	             ,{field:'markUser', width:200, title: '阅卷人'}
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




