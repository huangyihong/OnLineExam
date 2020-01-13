<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>

<body class="noScrollBar" style="height:100%;">
<%@ include file="/WEB-INF/views/common/top.inc.jsp"%>		
<div class="wrapper bg-color-white">
    <div class="layui-row o-hidden">
        <!--left begin-->
        <div class="left-content layui-col-lg2 leftsidebar_box" style="background-color: #F9F9F9;">
			<div class="line"></div>
			<c:forEach items="${menuList}" var="menuMap">
			    <c:set var="menu" value="${menuMap.menu}"/>
				<dl class="${menu.icon }">
					<dt <c:if test="${menu.menuUrl!='#' }"><a href="javascript:void(0)" class="nav-menu-active" data-type="tabAdd" data-url="${contextPath }${menu.menuUrl}" data-dirid="${menu.menuId}" </c:if>>
						<c:if test="${menu.menuUrl!='#' }"><a href="javascript:void(0)" class="nav-menu-active" data-type="tabAdd" data-url="${contextPath }${menu.menuUrl}" data-dirid="${menu.menuId}">${menu.menuName}</a></c:if>
						<c:if test="${menu.menuUrl=='#' }">${menu.menuName}</c:if>
						<c:if test="${not empty menuMap.childMenuList }"><img src="${contextPath }/static/images/left/select_xl01.png" /></c:if>
					</dt>
					<c:forEach items="${menuMap.childMenuList}" var="childMenu">
						<dd>
						<a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }${childMenu.menuUrl}" data-dirid="${childMenu.menuId}">${childMenu.menuName}</a>
						</dd>
					</c:forEach>
				</dl>
			</c:forEach>
			<!--  
			<dl class="home">
				<dt><a href="javascript:void(0)" class="nav-menu-active" data-type="tabAdd" data-url="${contextPath }/home" data-title="首页" data-dirid="100">系统首页</a></dt>
			</dl>
			
			<dl class="system_log">
				<dt>基本信息<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/admin/system/omUser/list" data-dirid="201">用户管理</a></dd>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/admin/system/omRole/list" data-dirid="201">角色管理</a></dd>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/admin/system/omMenu/list" data-dirid="201">菜单管理</a></dd>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/grades" data-dirid="201">年级管理</a></dd>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/courses" data-dirid="202">科目管理</a></dd>
			</dl>
		
			<dl class="custom">
				<dt>班级管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/classes" data-dirid="301">班级管理</a></dd>
				<dd><a  href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/preStudentCount" data-dirid="302">各班级总人数</a></dd>
			</dl>
			
			<dl class="custom">
				<dt >教师管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a  href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/teachers" data-dirid="401">所有教师</a></dd>
			</dl>
		
			<dl class="channel">
				<dt>学生管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/students" data-dirid="501">所有学生</a></dd>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/admin/charts/studentExamCount.jsp" data-dirid="502">学生考试信息</a></dd>
			</dl>
		
			<dl class="app">
				<dt>试卷管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a  href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/examPapers" data-dirid="601">所有试卷</a></dd>
			</dl>
		
			<dl class="cloud">
				<dt>试题管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/subjects" data-dirid="701">所有试题</a></dd>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/initImport" data-dirid="702">导入试题</a></dd>
			</dl>
		
			<dl class="syetem_management">
				<dt>考试安排管理<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/examPlans" data-dirid="801">待考信息</a></dd>
			</dl>
		
			<dl class="source">
				<dt>以往考试信息<img src="${contextPath }/static/images/left/select_xl01.png" /></dt>
				<dd ><a href="javascript:void(0)" class="handle-view nav-menu-active" data-type="tabAdd" data-url="${contextPath }/historys" data-dirid="901">所有记录</a></dd>
			</dl>
			-->
        </div>
        <!--left end-->


        <!--right begin-->
        <div class="right-content layui-col-lg10 p-l-10 ">
		  <div class="layui-tab layui-tab-brief" lay-filter="mainTab" lay-allowclose="true" id="mainTab" style="margin: -2px;">
		  <ul class="layui-tab-title">
			
		  </ul>
		  <div class="layui-tab-content">
			
		  </div>
		</div>
        </div>
        <!--right end-->


    </div>
</div>


<script>
var element = null;
var form = null;
layui.use('form', function(){
    var form = layui.form;
    //各种基于事件的操作，下面会有进一步介绍
});
	
	
	

//打开新Tab
function toShowDetailTab(url,title,id){
	if($(".layui-tab-title li[lay-id='"+id+"']").length > 0 ) {
   	  element.tabChange('mainTab', id);
   	  //已经存在
   	  $(".layui-tab-item.layui-show").find("iframe").attr("src",url);
     } else{
   	  //新增一个Tab项
   	  var h = $(window).height() - ($(".head").outerHeight(true)+$("#mainTab ul").outerHeight(true))-20;
         element.tabAdd('mainTab', {
           title: title
           ,content: '<iframe src="'+url+'" width="100%" height="'+h+'" scrolling="auto" style="border:0px;"></iframe>'
           ,id: id
         });
         element.tabChange('mainTab', id);
    }
}
 
$(function(){
	layui.use('element', function(){
	  var $ = layui.jquery;
	  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

	  //触发事件
	  var active = {
	    tabAdd: function(othis){
	      var title = othis.data('title');
	      if(!title){
	    	 title =  othis.text();
	      }
	      var url =  othis.data('url');
	      var id = othis.data('dirid');
	      if($(".layui-tab-title li[lay-id='"+id+"']").length > 0 ) {
	       	  element.tabChange('mainTab', id);
	       	  //已经存在
	       	  $(".layui-tab-item.layui-show").find("iframe").attr("src",url);
	         } else{
	       	  //新增一个Tab项
	       	  var h = $(window).height() - ($(".head").outerHeight(true)+$("#mainTab ul").outerHeight(true))-20;
	             element.tabAdd('mainTab', {
	               title: title
	               ,content: '<iframe src="'+url+'" width="100%" height="'+h+'" scrolling="auto" style="border:0px;"></iframe>'
	               ,id: id
	             });
	             element.tabChange('mainTab', id);
	        }
	    }
	  };
	  $('.nav-menu-active').on('click', function(){
	    var othis = $(this), type = othis.data('type');
	    active[type] ? active[type].call(this, othis) : '';
	  });
	  $('.nav-menu-active').eq(0).trigger('click');
	  $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');	
	});
});
</script>

 <script type="text/javascript">
$(function() {
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#F9F9F9"})
		$(this).css({"background-color": "#E5E5E5"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","${contextPath }/static/images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","${contextPath }/static/images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
});
</script>



</body>
</html>
