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
	<div id="menuSelect" class="treeDiv"></div>
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
var tree = null;
var layer = null;
layui.use(['tree', 'layer'], function(){
	tree = layui.tree
	layer = layui.layer
	tree.render({
	  elem: '#menuSelect'
	  ,data: getAllMenuList()
	  ,showCheckbox: true  //是否显示复选框
	  ,id: 'menuSelect'
	});
	//初始化勾选
	getRoleMenuList();
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
				layer.msg('查询失败：'+data.ERRINFO, {icon: 5});
			}
		}
	});
	return dataMenu;
}

//角色拥有的菜单
function getRoleMenuList(){
	var roleId = $("#roleId").val();
	var menuData = [];
	$.ajax({
		type:"POST",
		async:false,
		data:{roleId:roleId},
		url:WEBROOT + "/admin/system/omMenu/getRoleMenuList",
		success:function (data) {
			if (data.code == "200") {
				var roleMenuList = data.data;
				$.each(roleMenuList, function(i,roleMenu){ 
					menuData.push(roleMenu.menuId)
				});
			}else{
				layer.msg('查询失败：'+data.ERRINFO, {icon: 5});
			}
		}
	});
	tree.setChecked('menuSelect', menuData);
}

function save(){
	var checkedData = tree.getChecked('menuSelect'); //获取选中节点的数据
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
				layer.msg('保存失败：'+data.ERRINFO, {icon: 5});
			}
		}
	});
}

function getMenuIdArr(checkedData){
	var menuIdArr = [];
	$.each(checkedData, function(i,data){      
		menuIdArr.push(data.id);
		if(data.children){
			$.each(data.children, function(i,chilData){ 
				menuIdArr.push(chilData.id);
			})
		}
	}); 
	return menuIdArr;
}
</script>



