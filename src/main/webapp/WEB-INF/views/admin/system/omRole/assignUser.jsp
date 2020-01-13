<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<style>
.treeDiv {
    margin: 10px 20px;
    width: 300px;
    padding: 10px;
    border: 1px solid #ddd;
    overflow: auto;
    height:400px
}
</style>


<div class="page-container">
    <input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
	<div id="userSelect" class="demo-transfer"></div>
	<br>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <a id="btnSave" onclick="save()"
                   class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">确定</a>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">取消</a>

        </span>
   </div>
</div>
<script>
var transfer = null;
var layer = null;
layui.use(['transfer', 'layer'], function(){
  transfer = layui.transfer
  layer = layui.layer
 
  //初始右侧数据
  transfer.render({
    elem: '#userSelect'
    ,title: ['可授权的用户', '已授权的用户']  //自定义标题
    ,id: 'userSelect' //定义唯一索引
    ,showSearch: true
    ,data: getAllUserList()
    ,value: getRoleUserList()
  })
  
});

//用户列表
function getAllUserList(){
	var dataUser = []
	$.ajax({
		type:"POST",
		async:false,
		url:WEBROOT + "/admin/system/omUser/getAllUserList",
		success:function (data) {
			if (data.code == "200") {
				var userList = data.data;
				$.each(userList, function(i,user){ 
					var userType = user.userType;
					if(userType=='1'){
						userType= '管理员'
					}
					if(userType=='2'){
						userType= '教官'					
					}
					if(userType=='3'){
						userType= '学员'
					}
					dataUser[i] = {value:user.userId , title: '['+userType+']'+user.realName};
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataUser;
}

//角色授权的用户
function getRoleUserList(){
	var roleId = $("#roleId").val();
	var userData = [];
	$.ajax({
		type:"POST",
		async:false,
		data:{roleId:roleId},
		url:WEBROOT + "/admin/system/omRole/getRoleUserList",
		success:function (data) {
			if (data.code == "200") {
				var userRoleList = data.data;
				$.each(userRoleList, function(i,userRole){ 
					userData.push(userRole.userId)
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return userData;
}

function save(){
	var selectData = transfer.getData('userSelect'); //获取右侧数据
	var userIdArr = [];
	$.each(selectData, function(i,data){      
		userIdArr.push(data.value);
	}); 
	var roleId = $("#roleId").val();
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/system/omRole/saveRoleUser",
		data:{
				roleId:roleId,
				userIds:userIdArr.join(',')
			 },
		beforeSend: function () {
            $("#btnSave").addClass('layui-btn-disabled');// 禁用
        },
        complete: function () {
            $("#btnSave").removeClass('layui-btn-disabled');// 启用
        },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('保存成功', {icon: 1});
				parent.layer.closeAll()
			}else{
				layer.msg('保存失败：'+data.messgae, {icon: 5});
			}
		}
	});
}

</script>



