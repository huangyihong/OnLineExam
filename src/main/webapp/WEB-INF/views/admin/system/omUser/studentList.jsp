<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>

<body class="bg-color-white">
<div class="layui-fluid" id="conditionDiv">
  <div class="layui-card">
    <form id="formQuery" onsubmit="return false;">
    <div class="layui-form" style="border-bottom: 1px solid #f6f6f6;">
      <div class="layui-form-item">
      	<div class="p-t-20"></div>
         <div class="right-search" id="tableQuery">
             <input type="hidden" name="userType" value="3"/>
             <div class="layui-row">
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">用户名</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="userName" class="layui-input"	/>
                         </dd>
                     </ol>
                 </div>
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">姓名</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="realName" class="layui-input"	/>
                         </dd>
                     </ol>
                 </div>
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">所属分组</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="groupName" class="layui-input"/>
                         </dd>
                     </ol>
                 </div>
                 <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                     <ol class="layui-row">
                         <dt class="layui-col-md4 ellipsis">电话</dt>
                         <dd class="layui-col-md8">
                         	 <input type="text" name="phone" class="layui-input"/>
                         </dd>
                     </ol>
                 </div>
                 
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
                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="addStudent()">
                    <i class="iconfont iconxinzeng t-grey-999 " title="新增" ></i> 新增
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="update">
                    <i class="iconfont icontianxie t-grey-999"></i> 修改
                </button>

                <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="del">
                    <i class="iconfont iconshanchu t-grey-999"></i> 删除
                </button>        
                
                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="importUser('3')">
                    <i class="iconfont">&#xe64d;</i> 导入
                </button>
            </div>
        </div>
        <!--操作 end-->
      <table class="layui-hide" id="Table" lay-filter="Table"></table>
    </div>

  </div>
</div>
</body>
<script type="text/javascript" src="${contextPath }/static/js/admin/system/omUser/list.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonSearchMore.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/common/commonList.js"></script>
