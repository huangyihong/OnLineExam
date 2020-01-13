<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
    <input type="hidden" id="planId" name="planId" value="${bean.planId}"/>
    <input type="hidden" id="paperId" name="paperId" value="${bean.paperId}"/>
    <input type="hidden" id="examUserType" name="examUserType" value="${examUserType}"/>
    <div class="layui-form-item">
    	 <div class="layui-row">
    	 	<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
    	 		<fieldset class="site-demo-button" style="margin: 0 30px 0 30px;">
					<legend>授权用户</legend>
    	 			<div id="userSelect" class="demo-transfer"></div>
    	 		</fieldset>
    	 	</div> 
    	 	<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
    	 		<fieldset class="site-demo-button" style="margin: 0 30px 0 30px;">
    	 			<legend>授权用户组</legend>
    	 			<div id="groupSelect" class="demo-transfer"></div>
    	 		</fieldset>
    	 	</div> 
    	 </div>
    </div>
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
    ,value: getExamUserList()[0]
  })
  
  transfer.render({
    elem: '#groupSelect'
    ,title: ['可授权的分组', '已授权的分组']  //自定义标题
    ,id: 'groupSelect' //定义唯一索引
    ,showSearch: true
    ,data: getAllGroupList()
    ,value: getExamUserList()[1]
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
					dataUser[i] = {value:user.userId , title: user.realName};
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataUser;
}

//分组列表
function getAllGroupList(){
	var dataGroup = []
	$.ajax({
		type:"POST",
		async:false,
		url:WEBROOT + "/admin/system/omUserGroup/getAllGroupList",
		success:function (data) {
			if (data.code == "200") {
				var groupList = data.data;
				$.each(groupList, function(i,group){      
					dataGroup[i] = {value:group.groupId , title: group.groupName};
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	return dataGroup;
}

function getExamUserList(){
	var planId = $("#planId").val();
	var examUserType = $("#examUserType").val();
	var dataUser = []
	var dataGroup = []
	$.ajax({
		type:"POST",
		async:false,
		data:{planId:planId,examUserType:examUserType},
		url:WEBROOT + "/admin/exam/omExamPlan/getExamUserList",
		success:function (data) {
			if (data.code == "200") {
				var examUserList = data.data;
				$.each(examUserList, function(i,examUser){
					if(examUser.userId){
						dataUser.push(examUser.userId);
					}
					if(examUser.groupId){
						dataGroup.push(examUser.groupId);
					}
				});
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	var data = [];
	data[0] = dataUser;
	data[1] = dataGroup;
	return data;
}

function save(){
	var selectData = transfer.getData('userSelect'); //获取右侧数据
	var userIdArr = [];
	$.each(selectData, function(i,data){      
		userIdArr.push(data.value);
	}); 
	var selectData2 = transfer.getData('groupSelect'); //获取右侧数据
	var groupIdArr = [];
	$.each(selectData2, function(i,data){      
		groupIdArr.push(data.value);
	}); 
	if(userIdArr.length==0&&groupIdArr.length==0){
		layer.msg('用户或者用户分组至少授权一项', {icon: 5});
		return;
	}
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/exam/omExamPlan/saveExamUser",
		data:{
				planId:$("#planId").val(),
				paperId:$("#paperId").val(),
				examUserType:$("#examUserType").val(),
				userIds:userIdArr.join(','),
				groupIds:groupIdArr.join(',')
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
</script>



