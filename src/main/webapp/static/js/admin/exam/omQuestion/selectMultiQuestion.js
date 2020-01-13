var form = null;
var table = null;
var element = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/exam/omQuestion';
var listUrl = baseUrl +'/getList';

//选中的所有对象
var checked = {}
//id的位置索引
var idIndex = {}
//是否通过点击tab的图标删除
var isDeletedTab = false

$(function () {
	elementDel();
	//初始列表
	initGrid();
	
});

//初始列表
function initGrid() {
	var cols = [[
	             {width:50,fixed: 'left',templet: function (d) {
                     if(checked[d.questionId]){
                         return "<input type='checkbox' name='layTableCheckbox' lay-skin='primary' checked=''>"
                     } else {
                         return "<input type='checkbox' name='layTableCheckbox' lay-skin='primary'>"
                     }
                  }}
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
	             ,{field:'questionName', width:200, title: '题目',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link' >"+d.questionName+"</a></div>"
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
	           ]];
	var keyFiled= "questionId";//主键
	var limit = 10;//分页
	initCheckGrid(listUrl,cols,keyFiled,limit)
}

function initCheckGrid(listUrl,cols,keyFiled,limit){
	var tableHeight = $(window).height();
	var where = getParams();
	layui.use('table', function(){
	  table = layui.table;
	  form = layui.form;
	  var index = layer.load(0, {shade: [0.3,'#fff']});
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
	    ,done: function(res, curr, count) {
	    	layer.close(index);
            idIndex = []
            for(var i in res.data){
                idIndex[res.data[i].questionId]=i
            }
	    }
	  });
	  
		$('#tableQuery .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
		
		//监听行单击事件
		rowCheck();
	});
}

//element组件，监听tab的删除点击
function elementDel(){
	 layui.use('element', function(){
	        //一些事件监听
	        element = layui.element;
	        element.on('tabDelete(layTab)', function(data){
	            if(isDeletedTab){
	                isDeletedTab = false
	                return
	            }
	            var id = this.parentElement.getAttribute("lay-id")
	            delete checked[id]
	            // 设置checkbox未选中
	            var index = idIndex[id]
	            var tr = $('.layui-table-box .layui-table-fixed').find('tr[data-index=' + index +']')
	            $(tr).find('input[type=checkbox]').attr('checked', false)
	            $(tr).find('.layui-form-checked').removeClass('layui-form-checked')
	        });
	        // 传值，默认选中
	        var checkedList = getCheckedList();
	        for(var i=0;i<checkedList.length;i++){
	            var id = checkedList[i].id
	            var name = checkedList[i].name
	            checked[id] = {id:id,name:name}
	            element.tabAdd('layTab', {
	                title: name
	                ,id: id
	            });
	        }
	});
}

//监听行单击事件
function rowCheck(){
    table.on('row', function(obj){
        var id = obj.data.questionId
        var index = idIndex[id]
        var tr = $('.layui-table-box .layui-table-fixed').find('tr[data-index=' + index +']')
        if(checked[id]){
            // 设置checkbox未选中
            $(tr).find('input[type=checkbox]').attr('checked', false)
            $(tr).find('.layui-form-checkbox').removeClass('layui-form-checked')
            delete checked[id]
            isDeletedTab = true
            element.tabDelete('layTab', id); //删除 lay-id="xxx" 的这一项
        } else {
            $(tr).find('input[type=checkbox]').attr('checked', true)
            $(tr).find('.layui-form-checkbox').addClass('layui-form-checked')
            var name = obj.data.questionName
            checked[id] = {id:id,name:name}
            element.tabAdd('layTab', {
                title: name
                ,id: id
            });
        }
    });
}

//从父窗口获取初始选中的值
function getCheckedList(){
	var questionType = $("#questionType").val()
	//传值，默认选中
	var checkedList = [];
	var obj = top.getSelectItems(questionType)
	var ids = obj.ids
	var names = obj.names
	for(var i = 0, len = ids.length; i < len; i++){
		checkedList.push({
			id: ids[i],
			name: names[i],
		})
	}
	return checkedList;
}

function getCheckedObjs() {
    var array = []
    for ( var i in checked){
        if(checked[i]){
            array.push(checked[i])
        }
    }
    return array
}

var callbackdata = function () {
	var msg = "";
	var objs = getCheckedObjs()
	return {
		msg: msg
		,objs:objs
	};
}





