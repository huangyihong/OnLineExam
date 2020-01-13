<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"	%>
<%
    org.apache.shiro.subject.Subject subject = org.apache.shiro.SecurityUtils
            .getSubject();
    com.plg.shiro.entity.OmUser sessionUser = (com.plg.shiro.entity.OmUser) subject.getSession().getAttribute(com.plg.shiro.common.BaseConstant.USER_SESSION_KEY);
    request.setAttribute("sessionUser", sessionUser);
%>
<!--top begin-->
<div class="wrapper top-total gradient-bule">
    <div class="top-total-list">
        <!--logo begin-->
        <div class="logo-list">
            <div class="logo-image m-r-10 fl "><img src="${contextPath }/static/images/admin/logo_1.png" width="50%"></div>
            <div class="logo-name fl m-t-6">当前时间：<span id="date_time" style="color: #FFF;"></span></div>
        </div>
        <!--logo end-->
        <!--logo begin-->
        <div class="menu-list">
            <ul>
            </ul>
        </div>
        <!--logo end-->
        <!--person begin-->
        <div class="person-list">
            <div class="person-photo fl">
                <img src="${contextPath }/static/images/admin/photo.png">
            </div>
            <div class="person-name ft-16  m-t-15 fl m-l-10" onclick="showPerson(this)">
                <a href="javascript:void(0)" class="t-white">${sessionUser.realName } <i class="iconfont"></i></a>
            </div>
        </div>
        <!--person end-->

        <!--下拉菜单 begin-->
        <div class="person-drop-down hidden">
            <ul>
                <li><a onclick="toUpdateInfo()" href="javascript:void(0)"><i class="iconfont iconxueyuanguanli" ></i> 个人设置</a> </li>
                <li><a onclick="toUpdatePwd()" href="javascript:void(0)"><i class="iconfont iconfabutudi" ></i> 修改密码</a> </li>
                <li><a onclick="logout()" href="javascript:void(0)"><i class="iconfont icondingbudaohang-zhangh" ></i> 退 出</a> </li>
            </ul>
        </div>
        <!--下拉菜单 end-->
    </div>
</div>
<!--top end-->

<script type="text/javascript">
   	date_time();
   	
   	setInterval("date_time()", 1000);
   	
   	$(function(){
   		$(".person-drop-down").mouseleave(function(){
   			  $(".person-drop-down").hide();
   			  $(".person-list").find('.iconfont').html('&#xe65b;');
   		});
   	});
   	
   	function date_time() {
   		var date = new Date();
   		var year = date.getFullYear();
   		var month = date.getMonth()+1;
   		var day = date.getDate();
   		var hour = date.getHours();
   		var minutes = date.getMinutes();
   		var seconds = date.getSeconds();
   		if(parseInt(seconds) >= 0 && parseInt(seconds) < 10) {
   			seconds = "0"+seconds;
   		}
   		$("#date_time").text(year+"年"+month+"月"+day+"日"+hour+"时"+minutes+"分"+seconds+"秒");
   	}

   	function showPerson(t_this){
   		if($(".person-drop-down").is(':hidden')){
   			 $(".person-drop-down").show();
   			 $(t_this).find('.iconfont').html('&#xe609;');
   		}else{
   			$(".person-drop-down").hide();
   			 $(t_this).find('.iconfont').html('&#xe65b;');
   		}
   	}
   	
   	function logout(){
	   	layer.confirm('确定要退出系统吗？', {
	   	  btn: ['确定','取消'] //按钮
	   	}, function(){
	   		window.location.href = '${contextPath}/admin/logout';
	   	}, function(){
	   		
	   	});
   	}
   	
   	function toUpdatePwd() {
		var url = "${contextPath}/admin/system/omUser/changePassword";
		layer.open({
		  type: 2,
		  title: '修改密码',
		  skin: 'layui-layer-rim', //加上边框
		  area: ['480px', '330px'], //宽高
		  content: url,
		  maxmin: true
		});

	}
	function toUpdateInfo() {
		var url = "${contextPath}/admin/system/omUser/userInfo";
		layer.open({
		  type: 2,
		  title: '修改用户信息',
		  skin: 'layui-layer-rim', //加上边框
		  area: ['500px', '450px'], //宽高
		  content: url,
		  maxmin: true
		});

	}
</script>

