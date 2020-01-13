<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${contextPath }/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="${contextPath}/static/h-ui/js/H-ui.js"></script>
<link rel="stylesheet" href="${contextPath }/css/map/public.css" />
<link rel="stylesheet" href="${contextPath }/css/login.css?v=1" />
<link rel="stylesheet" href="${contextPath }/css/fonts/iconfont.css">
<title>亚信医保风控系统</title>
</head>
<body>
	<div class="login-bg">
		<div class="login-body">
			<div class="login-head">
				<p>亚信医保风控系统</p>
			</div>
			<form class="login-box" action="${contextPath}/admin/login" method="post">
				<div class="group">
					<input type="text" class="inputWidth" id="username" name="username" placeholder="用户名" />
					<span class="lIcon lIcon-user"></span>
				</div>
				<div class="group">
					<input id="password" name="password" type="password" class="inputWidth" placeholder="密码" />
					<span class="lIcon lIcon-pwd"></span>
				</div>
				<%-- <div class="group">
					<input type="text" id="captcha" name="captcha" class="inputWidth inputYzm" placeholder="验证码" />
					<span class="lImg">
						<img id="kaptchaImage" src="${contextPath}/Captcha.jpg" width='166px' height='56px' />
						<script type="text/javascript">
							$('#kaptchaImage').click(function() {
								$(this).hide().attr('src', '${contextPath}/Captcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
							})
						</script>
					</span>
				</div> --%>
				<button type="submit" class="btn" id="btnSubmit">立即登录</button>
			</form>
		</div>
	</div>
	<!-- 
	<div class="wrap">
	    <div class="logo"><img src="${contextPath}/images/logo.png"/></div>
	    <div class="main">
	        <img src="${contextPath}/images/banner.jpg" width="700" class="fl banner"/>
	        <div class="login fl">
	            <h6>登录</h6>
	            
		            <input id="username" name="username" type="text"  placeholder="用户名" class="current InputW300" value=""><br/>
		            <input id="password" name="password" type="password"  placeholder="密码" class="InputW300" value=""><br/>
		            <input id="captcha" name="captcha" type="text" placeholder="验证码" class="InputW120"  value=""/>
				    <img id="kaptchaImage" src="${contextPath}/Captcha.jpg" /> <script type="text/javascript">$('#kaptchaImage').click(function () { $(this).hide().attr('src', '${contextPath}/Captcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); })</script>
				    <div class="cl"></div>
		         
		            <div class="cl"></div>
		            <div class="loginBtn">
		                <a id="btnSubmit" href="#" class="current">登 录</a>
		                <a id="btnReset" href="#"  class="loginBtnMl">重 置</a>
		            </div>
		            <div class="cl"></div>
	        </div>
	        <div class="cl"></div>
	    </div>
	    <div class="foot">Copyright © 2017 All Rights Reserved. 	
	    	<p><small>建议使用1280×800分辨率以上 IE11.0以上版本浏览器，推荐使用<a href="http://www.google.cn/chrome/browser/desktop/" target="_blank">Chrome（谷歌浏览器）</small></p>
	    </div>
	</div> -->
	<script>
		var WEB = {};
		WEB.getCookie = function(l) {
			var i = "", I = l + "=";
			if (document.cookie.length > 0) {
				offset = document.cookie.indexOf(I);
				if (offset != -1) {
					offset += I.length;
					end = document.cookie.indexOf(";", offset);
					if (end == -1)
						end = document.cookie.length;
					i = unescape(document.cookie.substring(offset, end))
				}
			}
			;
			return i
		};

		WEB.setCookie = function(O, o, l, I) {
			var i = "", c = "";
			if (l != null) {
				i = new Date((new Date).getTime() + l * 3600000);
				i = "; expires=" + i.toGMTString();
			}
			if (I != null) {
				c = ";domain=" + I;
			}
			document.cookie = O + "=" + escape(o) + i + c
		};
		if ('${errorMsg}' != '') {

			layer.msg('${errorMsg}', {
				icon : 2,
				time : 2000,
				shade : 0.3
			});

		}
		$(function() {
			var s = WEB.getCookie("rememberMe");

			$("#rememberMe").attr("checked", s == "1");

			/*  $("#username").val(s=="1"?WEB.getCookie("username"):"");     
			 $("#password").val(s=="1"?WEB.getCookie("password"):""); */
			$("#username").focus();

			$("#btnSubmit").click(function() {
				if ($.trim($("#username").val()) == '') {
					layer.msg('请输入用户名', {
						icon : 2,
						time : 2000,
						shade : 0.3
					});
					return false;

				}
				if ($.trim($("#password").val()) == '') {
					layer.msg('请输入密码', {
						icon : 2,
						time : 2000,
						shade : 0.3
					});
					return false;

				}
				/* if($('#rememberMe').is(':checked')){
					
					 WEB.setCookie("username",$("#username").val(),100);
				   WEB.setCookie("password",$("#password").val(),100);
				   WEB.setCookie("rememberMe","1",100);
				}else{
					WEB.setCookie("username","",100);
				   WEB.setCookie("password","",100);
				   WEB.setCookie("rememberMe","",100);
				} */

				$("form").submit();
			});
			$("#btnReset").click(function() {
				$("form")[0].reset();
			})
			$("body").keydown(function() {
				if (event.keyCode == "13") {//keyCode=13是回车键
					$('#btnSubmit').click();
				}
			});
		})
	</script>
	<script type="text/javascript">
		try {
			if (top.location != self.location) {
				top.location = self.location;
				top.window.location.href = window.location.href;
				window.location.reload();
			}
		} catch (e) {
			;
		}
		var error = '${msg}';
		if (error != '') {
			layer.msg(error, {
				icon : 2,
				time : 2000,
				shade : 0.3
			});

		}
	</script>
</body>
</html>