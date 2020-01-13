<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="${fntype }"/>
    <input type="hidden" id="roleId" name="roleId" value="${bean.roleId}"/>
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>角色编码：</label>
            <div class="layui-input-inline">
                <input type="text" id="roleCode" name="roleCode" autocomplete="off" class="layui-input" lay-verify="required|username|maxlength" value="${bean.roleCode }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>角色名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="roleName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.roleName }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">角色类型：</label>
            <div class="layui-input-inline">
                <select name="type" lay-verify="required" value="${bean.type }">
	                <option value="1"  <c:if test="${bean.type == '1' }">selected</c:if>>系统管理角色</option>
	                <option value="2"  <c:if test="${bean.type == '2' }">selected</c:if>>一般角色</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-inline">
                <input type="text" name="remark" autocomplete="off" class="layui-input" value="${bean.remark }">
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

</form>
</div>
<script type="text/javascript" src="${contextPath }/static/js/common/commonCreate.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/admin/system/omRole/create.js"></script>


