<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="${fntype }"/>
    <input type="hidden" id="groupId" name="groupId" value="${bean.groupId}"/>
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>分组名称：</label>
            <div class="layui-input-inline">
                <input type="text" id="groupName" name="groupName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.groupName }">
            </div>
        </div>    
   </div>     

    <br>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <c:if test="${fntype!='view' }">
                <a lay-submit lay-filter="formSubmit" id="btnSave" href="javascript:void(0)"
                   class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">保存</a>
            </c:if>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">关闭</a>

        </span>
	</div>
</form>
</div>
<script type="text/javascript" src="${contextPath }/static/js/common/commonCreate.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/admin/system/omUserGroup/create.js"></script>


