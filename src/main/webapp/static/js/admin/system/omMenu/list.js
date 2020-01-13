var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/system/omMenu';
var listUrl = baseUrl +'/getList';
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
	             ,{field:'menuName', width:200, title: '菜单名称',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.menuId+"') >"+d.menuName+"</a></div>"
	              }}
	             ,{field:'menuUrl', width:300, title: '菜单url'}
	             ,{field:'menuOrder', width:200, title: '排序号'}
	           ]];
	var keyFiled= "menuId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&menuId='+id;
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
	 title += '菜单信息';
	 commonCreateView(title,url,isreload,'500','500');
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}





