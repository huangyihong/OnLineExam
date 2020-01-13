<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>

<body class="bg-color-white">
<div class="layui-fluid" id="conditionDiv">
  <div class="layui-card">
    <form id="formQuery" onsubmit="return false;">
    <input type="hidden" name="status"  value="${status }"/>
    <div class="layui-form" style="border-bottom: 1px solid #f6f6f6;">
      <div class="layui-form-item">
      	<div class="p-t-20"></div>
         <div class="right-search" id="tableQuery">
             <div class="layui-row">       
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md3">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">考试名称</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="planName" class="layui-input"	/>
                         </dd>
                     </ol>
                 </div>
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md3">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">试卷名称</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="paperName" class="layui-input"	/>
                         </dd>
                     </ol>
                 </div>
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md3">
                     <ol class="layui-row">
                         <dd class="layui-col-md12 ">
                             <button class="layui-btn bg-color-blue-3195db layui-btn-sm" data-type="reload">查 询</button>
                             <button class="layui-btn layui-btn-primary layui-btn-sm " onclick="reset1()">重 置</button>
                         </dd>
                     </ol>
                 </div>
             </div>
        </div>
      </div>
    </div>
     </form>

    <div class="layui-card-body">
      <table class="layui-hide" id="Table" lay-filter="Table"></table>
    </div>

  </div>
</div>
</body>
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omExamSubmit/mygradeList.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonSearchMore.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonList.js"></script>
