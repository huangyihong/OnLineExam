<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String basePath = request.getContextPath();
String contextPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+basePath;	    
%>
<c:set var="contextPath" value="<%=contextPath %>"></c:set>

<script type="text/javascript" src="${contextPath }/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/static/lib/layer/2.1/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/lib/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/static/lib/layui/css/modules/form.css" />
<script type="text/javascript" src="${contextPath }/static/lib/layui/layui.js"></script>
<link rel="stylesheet" href="${contextPath }/static/css/fonts/iconfont.css"/>
<script src="${contextPath }/static/css/fonts/iconfont.js"></script>
<!-- <link href="http://at.alicdn.com/t/font_1429101_zdoz8cz9d.css" rel="stylesheet" type="text/css"> -->
<link href="${contextPath }/static/css/public.css" rel="stylesheet" type="text/css">
<link href="${contextPath }/static/css/health.css" rel="stylesheet" type="text/css">
<link href="${contextPath }/static/css/developer.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	var WEBROOT = '${contextPath}';
</script>
<script type="application/javascript">
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window
</script>
