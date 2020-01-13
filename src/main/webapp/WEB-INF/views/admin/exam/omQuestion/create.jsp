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
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">单选题</li>
    <li>多选题</li>
    <li>判断题</li>
    <li>填空题</li>
    <li>简答题</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show"><iframe id="iframe1" src="${contextPath }/admin/exam/omQuestion/create?fntype=add&questionType=1&courseId=${courseId}" width="100%" scrolling="auto" style="border:0px;" onload="iframeLoad()"></iframe></div>
    <div class="layui-tab-item"><iframe id="iframe2" src="${contextPath }/admin/exam/omQuestion/create?fntype=add&questionType=2&courseId=${courseId}" width="100%" scrolling="auto" style="border:0px;" onload="iframeLoad()"></iframe></div>
    <div class="layui-tab-item"><iframe id="iframe3" src="${contextPath }/admin/exam/omQuestion/create?fntype=add&questionType=3&courseId=${courseId}" width="100%" scrolling="auto" style="border:0px;" onload="iframeLoad()"></iframe></div>
    <div class="layui-tab-item"><iframe id="iframe4" src="${contextPath }/admin/exam/omQuestion/create?fntype=add&questionType=4&courseId=${courseId}" width="100%" scrolling="auto" style="border:0px;" onload="iframeLoad()"></iframe></div>
    <div class="layui-tab-item"><iframe id="iframe5" src="${contextPath }/admin/exam/omQuestion/create?fntype=add&questionType=5&courseId=${courseId}" width="100%" scrolling="auto" style="border:0px;" onload="iframeLoad()"></iframe></div>
  </div>
</div> 
</div>
<script>
layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
});

function iframeLoad(){
	var height = $(window).height() - 100;
	var i=1;
	while(i<=5){
		document.getElementById("iframe"+i).height=height;
		i++;
	}
}
</script>

