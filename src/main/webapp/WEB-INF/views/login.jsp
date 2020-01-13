<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${contextPath }/static/css/login.css" />
<title>部队在线考试系统</title>
</head>
<body>
	<div class="login-bg">
		<div class="login-body">
			<div class="login-head">
				<p>部队在线考试系统</p>
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