<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="${fntype }"/>
    <input type="hidden" id="menuId" name="menuId" value="${bean.menuId}"/>
    <br>
    <div class="layui-form-item">
    	 <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>授权编码：</label>
            <div class="layui-input-inline">
                <input type="text" name="menuCode" autocomplete="off" class="layui-input" lay-verify="required" value="${bean.menuCode }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>菜单名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="menuName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.menuName }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>带单url：</label>
            <div class="layui-input-inline">
                <input type="text" id="menuUrl" name="menuUrl" autocomplete="off" class="layui-input" lay-verify="required" value="${bean.menuUrl }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">父级菜单：</label>
            <div class="layui-input-inline">
                <select name="parentId" lay-verify="required" value="${bean.parentId }">
	                <option value="0"  <c:if test="${bean.parentId == '0' }">selected</c:if>>根目录</option>
	                <c:forEach items="${parentMenuList}" var="parentMenu">
	                	<option value="${parentMenu.menuId }"  <c:if test="${bean.parentId == parentMenu.menuId  }">selected</c:if>>${parentMenu.menuName }</option>
	                </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">图标：</label>
            <div class="layui-input-inline">
                <input type="text" name="icon" autocomplete="off" class="layui-input" value="${bean.icon }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">排序号：</label>
            <div class="layui-input-inline">
                <input type="text" name="menuOrder" autocomplete="off" class="layui-input" lay-verify="number" value="${bean.menuOrder }">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">启用状态：</label>
            <div class="layui-input-inline">
                <select name="status" lay-verify="required" value="${bean.status }">
	                <option value="1"  <c:if test="${bean.status == '1' }">selected</c:if>>启用</option>
	                <option value="2"  <c:if test="${bean.status == '2' }">selected</c:if>>禁用</option>
                </select>
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
<script type="text/javascript" src="${contextPath }/static/js/admin/system/omMenu/create.js"></script>


