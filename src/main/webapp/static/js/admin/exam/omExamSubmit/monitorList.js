var form = null;
var table = null;
var slider = null;
layui.use('form', function(){
	  form = layui.form;	  
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omExamSubmit';
var listUrl = baseUrl +'/getMonitorList';
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
	             ,{field:'status', width:120, title: '操作',templet:function(d){
	            	 var html = '';
	            	 html = "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"','"+d.planId+"','"+d.userId+"','monitorExam','readonly') >查看答题详情</a></div>";
            		 return html
                 }}
	             ,{field:'planName', width:150, title: '考试名称'}
	             ,{field:'realName', width:100, title: '考试人员'}
	             ,{field:'userAnswerCount', width:150, title: '当前进度',templet: function(d){
	                  return '<div id="progress'+d.submitId+'" style="padding: 10px;"></div>'}}
	             ,{field:'userAnswerCount', width:100, title: '已答题数'}
	             ,{field:'paperCount', width:100, title: '试卷总题数'}	             
	             ,{field:'startTime', width:180, title: '开始答题时间'}
	             ,{field:'submitTime', width:180, title: '提交试卷时间'}
	             ,{field:'paperName', title: '试卷名称'}
//            	 ,templet:function(d){
//                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=showPaper('"+d.paperId+"','"+d.planId+"','historyExam') >"+d.paperName+"</a></div>"
//              }}
	           ]];
	var keyFiled= "submitId";//主键
	var limit = 10;//分页
	commonInitGrid2(listUrl,cols,keyFiled,limit)
}

//通用表格
function commonInitGrid2(listUrl,cols,keyFiled,limit){
	var tableHeight = $(window).height() - $('#conditionDiv').outerHeight(true)-20;
	var where = getParams();
	layui.use(['table','slider'], function(){
	  table = layui.table;
	  form = layui.form;
	  slider = layui.slider;
	  //var index = layer.load(0, {shade: [0.3,'#fff']});
	  //展示已知数据
	  table.render({
	    elem: '#Table'
	    ,url:listUrl
	    ,where:where
	    ,height:tableHeight
	    ,cellMinWidth: 120
	    ,cols: cols
	    ,id: 'layuiTable'
	    ,page: true
	    ,limit:limit
	    ,method: 'post'
	    ,response: { statusCode: 200}
	    ,parseData: function(res){
	        return {
	          "code": res.statusCode, //解析接口状态
	          "msg": res.message, //解析提示文本
	          "count": res.total, //解析数据长度
	          "data": res.data //解析数据列表
	        };
	     }
	    ,done: function(res, curr, count){
	    	//layer.close(index);
            var data = res.data;
            for (var index in data) {
            	slider.render({
    	    	    elem: '#progress'+data[index].submitId
    	    	    ,value: data[index].userAnswerCount
    	    	    ,min: 0 //最小值
    	    	    ,max: data[index].paperCount //最大值
    	    	});
            }
	    }
	  });

	  var $ = layui.$, active = {
		    //刷新表格		  
		    reload: function(){
		    	reloadTable2();
		    },
		    //新增
		    add: function(){
		    	createView('add','');
		    },
		    //查看
	        view: function(){
		        var checkStatus = table.checkStatus('layuiTable');
		        var data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('view',id)
	       },
	       //修改
	       update: function(){ 
		        var checkStatus = table.checkStatus('layuiTable');
		        var data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('update',id)
	       },
	       //复制新增
	       updateAdd: function(){ 
		        var checkStatus = table.checkStatus('layuiTable')
		        ,data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('updateAdd',id)
		   },
	       //删除
		   del: function(){
		        var checkStatus = table.checkStatus('layuiTable')
		        ,data = checkStatus.data;
		        if(data.length=='0'){
		        	layer.alert("请选择需要删除的数据");
		        	return;
		        }
		        var ids="";
		        for(var i=0;i<data.length;i++){
		        	ids += data[i][keyFiled]+",";
		  		}
		        del(ids.substring(0, ids.length - 1))
		    }
	  	};

		$('#tableQuery .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	
		$('#tableTool .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
}

//刷新表格
function reloadTable2(){
	//var index = layer.load(0, {shade: [0.3,'#fff']});
    //执行重载
	layui.table.reload('layuiTable', {
	      page: {curr: 1 }
		 ,where: getParams()
	     ,done: function(res, curr, count){
	    	    var data = res.data;
	            for (var index in data) {
	            	layui.slider.render({
	    	    	    elem: '#progress'+data[index].submitId
	    	    	    ,value: data[index].userAnswerCount
	    	    	    ,min: 0 //最小值
	    	    	    ,max: data[index].paperCount //最大值
	    	    	});
	            }
		  }
	});
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
function showPaper(paperId,planId,userId,type,seetype){
	var baseUrl = WEBROOT + '/admin/exam/omPaper';
	var showUrl = baseUrl +'/exam';
	 var url =showUrl+'?type='+type+'&paperId='+paperId+'&planId='+planId+'&answerUserId='+userId;
	 var title = '发布成绩';
	 var isreload = 1;
	 if(seetype=='readonly'){
		 title = '查看答卷'; 
		 isreload = 0;
	 }
	 commonCreateView(title,url,isreload,'500','500',1);
}

//发布成绩
function gradeExam(){
	var keyFiled = 'submitId';
	var checkStatus = table.checkStatus('layuiTable')
    ,data = checkStatus.data;
    if(data.length=='0'){
    	layer.alert("请选择数据");
    	return;
    }
    var ids="";
    for(var i=0;i<data.length;i++){
    	ids += data[i][keyFiled]+",";
		}
    layer.confirm('确定发布所选择的记录？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		gradeExamFun(ids.substring(0, ids.length - 1),'4');
	}, function(){
		
	});
}

function gradeExamFun(ids,status){
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/exam/omExamSubmit/gradeExam",
		data:{
				ids:ids,
				status:status
			 },
		beforeSend: function () {
            $("#btnSave").addClass('layui-btn-disabled');// 禁用
        },
        complete: function () {
            $("#btnSave").removeClass('layui-btn-disabled');// 启用
        },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('操作成功', {icon: 1});
				layer.closeAll();
				reloadTable();
			}else{
				layer.msg('操作失败：'+data.ERRINFO, {icon: 5});
			}
		}
	});
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




