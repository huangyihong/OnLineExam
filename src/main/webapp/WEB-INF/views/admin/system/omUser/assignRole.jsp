<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
    <input type="hidden" id="userId" name="userId" value="${userId}"/>
	<div id="roleSelect" class="demo-transfer"></div>
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
    elem: '#roleSelect'
    ,title: ['可分配的角色', '已分配的角色']  //自定义标题
    ,id: 'roleSelect' //定义唯一索引
    ,data: getAllRoleList()
    ,value: getUserRoleList()
  })
  
});

//角色列表
function getAllRoleList(){
	var dataRole = []
	$.ajax({
		type:"POST",
		async:false,
		url:WEBROOT + "/admin/system/omRole/getAllRoleList",
		success:function (data) {
			if (data.code == "200") {
				var roleList = data.data;
				$.each(roleList, function(i,role){      
					dataRole[i] = {value:role.roleId , title: role.roleName};
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataRole;
}

function getUserRoleList(){
	var userId = $("#userId").val();
	var dataRole = []
	$.ajax({
		type:"POST",
		async:false,
		data:{userId:userId},
		url:WEBROOT + "/admin/system/omRole/getUserRoleList",
		success:function (data) {
			if (data.code == "200") {
				var userRoleList = data.data;
				$.each(userRoleList, function(i,userRole){      
					dataRole[i] = userRole.roleId;
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataRole;
}

function save(){
	var selectData = transfer.getData('roleSelect'); //获取右侧数据
	var roleIdArr = [];
	$.each(selectData, function(i,data){      
		roleIdArr.push(data.value);
	}); 
	var userId = $("#userId").val();
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/system/omRole/saveUserRole",
		data:{
				userId:userId,
				roleIds:roleIdArr.join(',')
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



