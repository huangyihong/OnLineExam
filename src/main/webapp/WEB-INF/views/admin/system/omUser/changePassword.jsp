<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>

<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width:130px"><span class="c-red">*</span>当前密码：</label>
            <div class="layui-input-inline">
                <input type="password" id="password" name="password" autocomplete="off" class="layui-input" lay-verify="required" value="">
            </div>
        </div>
    </div>    
    <div class="layui-form-item">    
        <div class="layui-inline">
            <label class="layui-form-label" style="width:130px"><span class="c-red">*</span>新密码：</label>
            <div class="layui-input-inline">
               <input type="password" id="newPassword"  name="newPassword" autocomplete="off" class="layui-input" lay-verify="required|pass" value="">
            </div>
        </div>
    </div>
     <div class="layui-form-item">    
        <div class="layui-inline">
            <label class="layui-form-label" style="width:130px"><span class="c-red">*</span>确认新密码：</label>
            <div class="layui-input-inline">
               <input type="password" id="rPassword" name="rPassword" autocomplete="off" class="layui-input" lay-verify="required|pass" value="">
            </div>
        </div>
    </div>      
    

    <br>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <a lay-submit lay-filter="formSubmit" id="btnSave" href="javascript:void(0)"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">确定</a>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">关闭</a>
        </span>
	</div>
</form>
</div>
<script type="text/javascript" src="${contextPath }/static/js/common/commonCreate.js"></script>
<script type="text/javascript">
var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var newPassword = $("#newPassword").val();
		var rPassword = $("#rPassword").val();
	    if(newPassword!=rPassword){
			layer.msg('确认新密码与新密码不一致', {icon: 5});
			return false;
		}  
		save(data);	
		return false;
	 });
});

function save(data){
	var url = WEBROOT + '/admin/system/omUser/saveChangePassword';
	// 按钮禁用
    var isDisabled = $("#btnSave").hasClass('layui-btn-disabled');
    if (isDisabled) {
        return false;
    }
	$.ajax({
		type:"POST", 
		async:true, 
		url:url,
		data:data.field,
		beforeSend: function () {
            $("#btnSave").addClass('layui-btn-disabled');// 禁用
        },
        complete: function () {
            $("#btnSave").removeClass('layui-btn-disabled');// 启用
        },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('修改密码成功,请重新登录！', {
					icon : 1,
					time : 2000
				}, function() {
					location.href = WEBROOT + '/admin/logout';
				});
			}else{
				layer.msg('修改密码失败：'+data.message, {icon: 5});
			}
		}
	});	
}
</script>


