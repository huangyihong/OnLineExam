var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/system/omRole';
var listUrl = baseUrl +'/getList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var assignMenuUrl = baseUrl +'/assignMenu';
var assignUserUrl = baseUrl +'/assignUser';
$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
	             ,{field:'roleCode', width:200, title: '角色编码',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.roleId+"') >"+d.roleCode+"</a></div>"
	              }}
	             ,{field:'roleName', width:200, title: '角色名称'}
	             ,{field:'type', width:200, title: '角色类型',templet:function(d){
	            	 var type = d.type;
	            	 var html =  '';
	            	 if(type=='1'){
	            		html = '系统管理角色'
	            	 }
	            	 if(type=='2'){
		            	html = '一般角色'
		             }
	            	 return "<div>"+html+"</div>"
	              }}
	             ,{field:'remark', width:380, title: '备注'}
	           ]];
	var keyFiled= "roleId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&roleId='+id;
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
	 title += '角色信息';
	 commonCreateView(title,url,isreload,'850','250');
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

//菜单授权
function assignMenu(){
	var keyFiled= "roleId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
     	layer.alert("请选择1条记录");
     	return;
    }
    var id = data[0][keyFiled];
    var url =assignMenuUrl+'?roleId='+id;
    commonCreateView('角色菜单授权',url,0,'500','550');
}

//角色用户授权
function assignUser(){
	var keyFiled= "roleId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
     	layer.alert("请选择1条记录");
     	return;
    }
    var id = data[0][keyFiled];
    var url =assignUserUrl+'?roleId='+id;
    commonCreateView('角色用户授权',url,0,'600','550');
}





