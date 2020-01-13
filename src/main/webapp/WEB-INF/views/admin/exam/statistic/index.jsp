<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<style>

</style>
<body class="bg-color-white">
<div class="layui-fluid" id="conditionDiv">
  <div class="layui-card">
  	<div class="layui-col-sm2">
  	   <c:if test="${empty list||fn:length(list)==0 }">暂无考试成绩发布</c:if>
  	   <c:if test="${not empty list&&fn:length(list)>0 }">      
  		<table class="layui-table layuiadmin-page-table" lay-skin="line">
            <thead>
              <tr>
                <th>考试列表</th>
              </tr> 
            </thead>
            <tbody>
            <c:forEach items="${list}" var="planBean">
            	<tr>
                  <td><a onclick="changeIframe('${planBean.planId}')" href="javascript:void(0)">${planBean.planName }</a></td>
                </tr>
            </c:forEach>
           </tbody>
         </table>
      </c:if>              
  	</div>
  	<div class="layui-col-sm10">
  	    <c:if test="${empty list||fn:length(list)==0 }">
  	    	<iframe id="echartIframe" width="100%" style="border: 0" src=""></iframe>
  	    </c:if>
  	    <c:if test="${not empty list&&fn:length(list)>0 }">
  	    	<iframe id="echartIframe" width="100%" style="border: 0" src="${contextPath}/admin/exam/statistic/showRange?planId=${list[0].planId}"></iframe>
  	    </c:if>
  		
  	</div>
  </div>
 </div>
 </body> 
 <script type="text/javascript">
 $(function () {
     setIframeHeight();
 });

 function setIframeHeight() {
     var iframeHeight = $(window).height()-50 ;
     console.log($(window).height())
     $("#echartIframe").height(iframeHeight);
 }
 
 function changeIframe(planId){
	 var url='${contextPath}/admin/exam/statistic/showRange?planId='+planId;
	 $("#echartIframe").attr('src',url);
 }
</script> 

