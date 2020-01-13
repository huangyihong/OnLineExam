<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>

<style>
.layui-inline{
	width:90%
}
.layui-form-label{
	width:20%!important
}
.layui-input-inline{
	width:70%!important
}
</style>

<div class="page-container">
<form class="layui-form layui-form-pane" action="${contextPath}/admin/exam/omQuestion/import" enctype="multipart/form-data" id="form">
	<br>	
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">选择文件：</label>
			<div class="layui-input-inline">
				<button type="button" class="layui-btn" id="importBtn">
				  <i class="layui-icon">&#xe67c;</i>导入Excel文件(.xls)
				</button>
				<a onclick="downloadExcel()" class="pl-20">Excel模板下载</a>
			</div>
		</div>
  	</div>


	<br>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">关闭</a>
        </span>
	</div
</form>
	<div class="layui-form-item" style="float: left;color:blue" id="success">
	</div>
	<div class="layui-form-item" style="float: left;color:red" id="error">
	</div>
</div>
<script type="text/javascript">
var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
});

layui.use('upload', function(){
	  var upload = layui.upload;
	   
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#importBtn' //绑定元素
    	,data: {
    	}
	    ,url: WEBROOT + '/admin/exam/omQuestion/import' //上传接口
	    ,accept: 'file' //普通文件
	    ,exts: 'xls' //允许上传的文件后缀
	    ,size: 10240 //最大10MB
    	,before: function(obj){ 
    	    layer.load();
    	    $("#success").html("正在导入..."); 
    	 }
	    ,done: function(res){
	      layer.closeAll('loading'); 	
	      //上传完毕回调
	      if(res.code=='200'){
	    	  $("#success").html(res.message); 
	    	  $("#error").html("");
	      }else{
	    	  $("#success").html("");
	    	  $("#error").html("数据导入失败:"+res.message);  
	      }
	    }
	    ,error: function(){
	      //请求异常回调
	    	layer.closeAll('loading'); 
	    }
	  });
	});
	
	function closeWin(){
		var i = parent.layer.getFrameIndex(window.name);
		parent.layer.close(i);
	}
	
	function downloadExcel(){
		var url = WEBROOT + '/admin/exam/omQuestion/downloadTemplate';
	    window.open(url);
	}
</script>

