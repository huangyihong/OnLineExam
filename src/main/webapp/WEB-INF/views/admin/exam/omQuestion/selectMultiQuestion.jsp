<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	%>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<style>
.layui-form-item .layui-inline .layui-input-inline {
    width: 550px;
}
.layui-table-cell .layui-form-checkbox[lay-skin=primary] {
    top: 5px;
}
</style>
<div class="layui-fluid" >
  <br>
  <div class="layui-card">
   <form id="formQuery" onsubmit="return false;">
    <div class="layui-form" style="border-bottom: 1px solid #f6f6f6;">
      <div class="layui-form-item" style="margin: 0 0 0 0;" id="conditionDiv">
        <input type="hidden" id="questionType" name="questionType" value="${questionType }" />
        <input type="hidden" id="courseId" name="courseId" value="${courseId }" />
        <div class="layui-inline" id="tableQuery">
        	<div class="layui-inline">
          		<label class="layui-form-label ellipsis"><span>题目：</span></label>
          		<div class="layui-input-inline">
          			<input type="text" name="questionName" class="layui-input"	/>
               	</div>
          	</div>
          	<div class="layui-inline">
          		<button class="layui-btn bg-color-blue-3195db layui-btn-sm" data-type="reload">查 询</button>
		        <button class="layui-btn layui-btn-primary layui-btn-sm " onclick="reset1()">重 置</button>
          	</div>
        </div>
      </div>
    </div>
    </form>

    <div class="layui-card-body">
	    <div class="layui-tab" lay-allowClose="true" lay-filter="layTab">
	        <ul class="layui-tab-title">
	        </ul>
	    </div>
	    <table class="layui-hide" id="Table" lay-filter="Table"></table>
    </div>

  </div>
</div>
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omQuestion/selectMultiQuestion.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonSearchMore.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonList.js"></script>
</html>
