<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath }/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${contextPath }/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<style>
.treeDiv {
    margin: 10px 20px;
    width: 300px;
    padding: 10px;
    border: 1px solid #ddd;
    overflow: auto;
    height:400px
}
.ztree li ul{ margin:0; padding:0 0 0 18px;height: auto;}
</style>


<div class="page-container">
    <input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
	<ul id="menuSelect" class="ztree"></ul>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <a id="btnSave" onclick="save()"
                   class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">确定</a>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">取消</a>

        </span>
   </div>
</div>
<script type="text/javascript">
var zTreeObj;
var setting = {
	check : {
		enable : true,
		autoCheckTrigger : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "s"
		}
	}
};

$(function () {
   var zNodes = getAllMenuList();	
   zTreeObj = $.fn.zTree.init($("#menuSelect"), setting, zNodes);
   getRoleMenuList();//初始选中节点
});

//菜单列表
function getAllMenuList(){
	var dataMenu = []
	$.ajax({
		type:"POST",
		async:false,
		url:WEBROOT + "/admin/system/omMenu/getAllMenuList",
		success:function (data) {
			if (data.code == "200") {
				dataMenu = data.data;
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataMenu;
}

//角色拥有的菜单
function getRoleMenuList(){
	var roleId = $("#roleId").val();
	$.ajax({
		type:"POST",
		async:false,
		data:{roleId:roleId},
		url:WEBROOT + "/admin/system/omMenu/getRoleMenuList",
		success:function (data) {
			if (data.code == "200") {
				var roleMenuList = data.data;
				$.each(roleMenuList, function(i,roleMenu){ 
					var node = zTreeObj.getNodeByParam("id",roleMenu.menuId);
					zTreeObj.checkNode(node,true,false);//初始选中节点
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
}

function save(){
	var checkedData = zTreeObj.getCheckedNodes(); //获取选中节点的数据
	var menuIdArr = getMenuIdArr(checkedData);
	var roleId = $("#roleId").val();
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/system/omMenu/saveRoleMenu",
		data:{
				roleId:roleId,
				menuIds:menuIdArr.join(',')
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
				layer.msg('保存失败：'+data.message, {icon: 5});
			}
		}
	});
}

function getMenuIdArr(checkedData){
	var menuIdArr = [];
	$.each(checkedData, function(i,data){ 
		menuIdArr.push(data.id);
	}); 
	return menuIdArr;
}
</script>



