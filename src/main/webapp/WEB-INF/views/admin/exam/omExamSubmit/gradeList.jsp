<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>

<body class="bg-color-white">
<div class="layui-fluid" id="conditionDiv">
  <div class="layui-card">
    <div class="layui-col-sm3">
    	<table class="layui-table layuiadmin-page-table" lay-skin="line">
            <thead>
              <tr>
                <th>考试列表</th>
              </tr> 
            </thead>
            <tbody>
            <tr>
                  <td><a onclick="selectPlan('')" href="javascript:void(0)">全部</a></td>
            </tr>
            <c:forEach items="${planList}" var="planBean">
            	<tr>
                  <td><a onclick="selectPlan('${planBean.planId}')" href="javascript:void(0)">${planBean.planName }</a></td>
                </tr>
            </c:forEach>
           </tbody>
         </table>
    </div>
    <div class="layui-col-sm9">
    	<form id="formQuery" onsubmit="return false;">
	    <input type="hidden" name="status"  value="${status }"/>
	    <input type="hidden" name="planId" id="planId" value=""	/>
	    <div class="layui-form" style="border-bottom: 1px solid #f6f6f6;">
	      <div class="layui-form-item">
	      	<div class="p-t-20"></div>
	         <div class="right-search" id="tableQuery">
	             <div class="layui-row">       
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md5">
	                     <ol class="layui-row">
	                         <dt class="layui-col-md4 ellipsis">考试人员</dt>
	                         <dd class="layui-col-md8">
	                         	 <input type="text" name="realName" class="layui-input"	/>
	                         </dd>
	                     </ol>
	                 </div>
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md5">
	                     <ol class="layui-row">
	                         <dt class="layui-col-md4 ellipsis">试卷名称</dt>
	                         <dd class="layui-col-md8">
	                         	 <input type="text" name="paperName" class="layui-input"	/>
	                         </dd>
	                     </ol>
	                 </div>
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md2">
	                     <ol class="layui-row">
	                         <dd class="layui-col-md12 ">
	                             <button class="layui-btn bg-color-blue-3195db layui-btn-sm" data-type="reload" id="reload">查 询</button>
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
	      <!--操作 start-->
	      <div class="operation-content margin-center p-t-10" id="tableTool">
	            <div class="layui-btn-group">
	                
	                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="gradeExam()">
	                    	批量成绩发布
	                </button>
	                
	                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="exportGrade()">
	                    <i class="iconfont">&#xe651;</i> 导出
	                </button>
	                
	            </div>
	        </div>
	        <!--操作 end-->
	      <table class="layui-hide" id="Table" lay-filter="Table"></table>
	    </div>
    </div>
    

  </div>
  
</div>
</body>
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omExamSubmit/gradeList.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonSearchMore.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonList.js"></script>
<script type="text/javascript">
function selectPlan(planId){
	$("#planId").val(planId);
	$("#reload").click();
}
</script>
