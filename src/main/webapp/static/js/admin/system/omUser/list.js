var form = null;
var table = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});
var baseUrl = WEBROOT + '/admin/system/omUser';
var listUrl = baseUrl +'/getList';
var createUrl = baseUrl +'/create';
var delUrl = baseUrl +'/del';
var assignRoleUrl = baseUrl +'/assignRole';
var importUserUrl = baseUrl +'/importUser';

$(function () {
	//初始列表
	initGrid();
});

//初始列表
function initGrid() {
	var cols = [[
	             {type:'checkbox', fixed: 'left'}
	             ,{field:'userName', width:200, title: '用户名',templet:function(d){
	                 return "<div><a href='javascript:void(0)' class='layui-table-link'  onclick=createView('view','"+d.userId+"') >"+d.userName+"</a></div>"
	              }}
	             ,{field:'realName', width:200, title: '真实姓名'}
	             ,{field:'userType', width:200, title: '用户类型',templet:function(d){
	            	 var userType = d.userType;
	            	 var html =  '';
	            	 if(userType=='1'){
	            		html = '管理员'
	            	 }
	            	 if(userType=='2'){
		            	html = '教官'
		             }
	            	 if(userType=='3'){
		            	html = '学员'
		             }
	            	 return "<div>"+html+"</div>"
	              }}
	             ,{field:'groupName', width:200, title: '所属分组'}
	             ,{field:'phone', width:200, title: '手机号'}
	             ,{field:'status', width:200, title: '用户状态',templet:function(d){
	            	 var status = d.status;
	            	 var html =  '';
	            	 if(status=='1'){
	            		html = '启用'
	            	 }
	            	 if(status=='2'){
		            	html = '禁用'
		             }
	            	 return "<div>"+html+"</div>"
	              }}
	             ,{field:'remark', width:380, title: '备注'}
	           ]];
	var keyFiled= "userId";//主键
	var limit = 10;//分页
	commonInitGrid(listUrl,cols,keyFiled,limit)
}

//新增修改查看
function createView(fntype,id){
	 var url =createUrl+'?fntype='+fntype;
	 if(fntype!='add'){
		 url+='&userId='+id;
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
	 title += '用户信息';
	 commonCreateView(title,url,isreload);
}

function addStudent(){
	 var fntype='add';
	 var url =createUrl+'?userType=3&fntype='+fntype;
	 var title = '新增';
	 var isreload = 1;
	 title += '用户信息';
	 commonCreateView(title,url,isreload);
}

//删除操作
function del(ids){
	commonDelAjax(delUrl,ids,'ids');
}

//角色授权
function assignRole(){
	var keyFiled= "userId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
     	layer.alert("请选择1条记录");
     	return;
    }
    var id = data[0][keyFiled];
    var url =assignRoleUrl+'?userId='+id;
    commonCreateView('用户角色授权',url,0,'600','500');
}

function resetPassword(){
	var keyFiled= "userId";//主键
	var checkStatus = table.checkStatus('layuiTable');
    var data = checkStatus.data;
    if(data.length!='1'){
     	layer.alert("请选择1条记录");
     	return;
    }
    var id = data[0][keyFiled];
    var url = WEBROOT + '/admin/system/omUser/resetPassword';
        layer.confirm('确认重置密码为123456？', {
     	  btn: ['确定','取消'] //按钮
     	}, function(){
     	   $.ajax({
     			type:"POST", 
     			async:true, 
     			url:url,
     			data:{userId:id},
     			success:function (data) {
     				if (data.code == "200") {
     					layer.msg('重置密码成功', {icon: 1});
     				}else{
     					layer.msg('重置密码失败：'+data.message, {icon: 5});
     				}
     			}
     		});	
     	}, function(){
    	});
 
}

function importUser(userType){
	commonCreateView('用户导入',importUserUrl+"?userType="+userType,1,'800','500',0);
}






