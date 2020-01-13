<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="update"/>
    <input type="hidden" id="userId" name="userId" value="${bean.userId}"/>
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>用户名：</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" id="userName" autocomplete="off" class="layui-input" lay-verify="required|username|maxlength" value="${bean.userName }" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>真实姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="realName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.realName }">
            </div>
        </div>     
        <div class="layui-inline">
            <label class="layui-form-label">所属分组：</label>
            <div class="layui-input-inline">
                <select id="groupId" name="groupId" value="${bean.groupId }">
                	<option value="">--请选择--</option>
	                <c:forEach items="${groupList}" var="groupBean">
	                	<option value="${groupBean.groupId }" <c:if test="${groupBean.groupId == bean.groupId }">selected</c:if>>${groupBean.groupName }</option>
	                </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话：</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" autocomplete="off" class="layui-input" lay-verify="phoneOrBlank" value="${bean.phone }">
            </div>
        </div>
        
        <div class="layui-inline">
            <label class="layui-form-label">证件号：</label>
            <div class="layui-input-inline">
                <select name="idType" value="${bean.idType }">
                    <option value="" >--请选择--</option>
	                <option value="1" <c:if test="${bean.idType == '1' }">selected</c:if>>中士</option>
	                <option value="2" <c:if test="${bean.idType == '2' }">selected</c:if>>列兵</option>
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
<script type="text/javascript" src="${contextPath }/static/js/admin/system/omUser/create.js"></script>


