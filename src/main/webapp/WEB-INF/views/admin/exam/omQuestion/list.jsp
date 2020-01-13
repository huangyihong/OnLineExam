<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>

<body class="bg-color-white">
<div class="layui-fluid" >
<div class="layui-col-sm2">
 		<div class="layui-card" style="padding: 20px 10px">
 	    <div id="leftTree" style="overflow:auto"></div> 
 	    </div>
</div>
<div class="layui-col-sm10">	
	<div class="layui-card" id="conditionDiv">
	    <form id="formQuery" onsubmit="return false;">
	    <input type="hidden" name="courseId" id="courseId" value="">
	    <div class="layui-form" style="border-bottom: 1px solid #f6f6f6;">
	      <div class="layui-form-item">
	      	<div class="p-t-20"></div>
	         <div class="right-search" id="tableQuery">
	             <div class="layui-row">
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
	                     <ol class="layui-row">
	                         <dt class="layui-col-md4 ellipsis">题型</dt>
	                         <dd class="layui-col-md8">
	                         	 <select name="questionType">
				                	<option value="">--请选择--</option>
				                	<option value="1">单选题</option>
				                	<option value="2">多选题</option>
				                	<option value="3">判断题</option>
				                	<option value="4">填空题</option>
				                	<option value="5">简答题</option>
				                </select>
	                         </dd>
	                     </ol>
	                 </div>       
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
	                     <ol class="layui-row">
	                         <dt class="layui-col-md4 ellipsis">题目</dt>
	                         <dd class="layui-col-md8">
	                         	 <input type="text" name="questionName" class="layui-input"	/>
	                         </dd>
	                     </ol>
	                 </div>
	                <%--  <div class="layui-col-xs6 layui-col-sm6 layui-col-md3">
	                     <ol class="layui-row">
	                         <dt class="layui-col-md4 ellipsis">所属科目</dt>
	                         <dd class="layui-col-md8">
	                         	<select name="courseId">
				                	<option value="">--请选择--</option>
					                <c:forEach items="${courseList}" var="courseBean">
					                	<option value="${courseBean.courseId }">${courseBean.courseName }</option>
					                </c:forEach>
							 	</select>
	                         </dd>
	                     </ol>
	                 </div> --%>
	                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
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
	        <!--操作 begin-->
	        <div class="operation-content margin-center p-t-10" id="tableTool">
	            <div class="layui-btn-group">
	                <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="add">
	                    <i class="iconfont iconxinzeng t-grey-999 " title="新增" ></i> 新增
	                </button>
	                <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="update">
	                    <i class="iconfont icontianxie t-grey-999"></i> 修改
	                </button>
	
	                <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="del">
	                    <i class="iconfont iconshanchu t-grey-999"></i> 删除
	                </button>
	                
	                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="importQuestion()">
	                    <i class="iconfont">&#xe64d;</i> 导入
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
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omQuestion/list.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonSearchMore.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonList.js"></script>
<script type="text/javascript">
$(function () {
    //初始列表
    setTreeHeight();
});

//设置左侧树的高度
function setTreeHeight() {
    var treeHeight = $(window).height()-40;
    $("#leftTree").height(treeHeight);
}

//获取数据源（左侧列表数据）
function getTreeList(){
 	var dataList = []
 	$.ajax({
		type:"POST",
		async:false,
		url:WEBROOT + "/admin/exam/omCourse/getTreeList",
		success:function (data) {
			if (data.code == "200") {
				dataList =  data.data;
			}else{
				layer.msg('查询失败：'+data.message, {icon: 5});
			}
		}
	});
 	return dataList;
 }
</script>
