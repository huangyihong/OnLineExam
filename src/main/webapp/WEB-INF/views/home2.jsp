<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>

<link rel="stylesheet" href="static/css/home.css" type="text/css" />
<script type="text/javascript" src="static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="static/js/content.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		resquestByKV();
		getUserMenu();
	});
</script>


</head>

<body bgcolor="#F4F4F4">
	<div class="head">
		<div style="text-align:center">
			<div><h1>您好，${currentUser }！</h1></div>
			<div style="color:red">登录时间：${serverTime }</div>
		</div>
		<div class="logout_button">
			<input type="button" value="退出" onclick="logout()" />
		</div>
	</div>

	<!-- 左侧菜单栏  -->
	<div class="left">
		<ul id="menuUl"></ul>
	</div>


	<!-- 这是下方右部 -->
	<div class="right">
		<h2>
			<div id="msg1"></div>
		</h2>

		<!-- 用户列表 -->
		<div id="userList">
			<h5 style="color: red">============== 所有用户 =============</h5>
			<table id="userTable" width="60%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">用户ID</th>
					<th width="80">用户名称</th>
					<th width="90">登录密码（明文：123456）</th>
					<th width="60">手机号</th>
					<th width="30">状态</th>
					<th width="80">备注</th>
				</tr>
			</table>
		</div>


		<!-- 角色列表 -->
		<div id="roleList">
			<h5 style="color: red">============== 所有角色 =============</h5>
			<table id="roleTable" width="60%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">角色ID</th>
					<th width="80">角色代码</th>
					<th width="90">角色名称</th>
					<th width="60">角色类型</th>
					<th width="30">备注</th>
				</tr>
			</table>
		</div>

		<!-- 用户-角色关系列表 -->
		<div id="roleList">
			<h5 style="color: red">============== 所有用户-角色关系 =============</h5>
			<table id="userRoleTable" width="60%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">ID</th>
					<th width="80">用户ID</th>
					<th width="90">用户名称</th>
					<th width="60">角色ID</th>
					<th width="30">角色名称</th>
				</tr>
			</table>
		</div>

		<!-- 权限列表 -->
		<div id="permissionList">
			<h5 style="color: red">============== 所有权限 =============</h5>
			<table id="permissionTable" width="60%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">权限ID</th>
					<th width="90">权限名称</th>
					<th width="60">权限编码</th>
				</tr>
			</table>
		</div>

		<!-- 角色-权限关系列表 -->
		<div id="rolePermisList">
			<h5 style="color: red">============== 所有角色-权限关系 =============</h5>
			<table id="rolePermisTable" width="68%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">ID</th>
					<th width="70">角色ID</th>
					<th width="90">角色名称</th>
					<th width="80">角色编码</th>
					<th width="70">权限ID</th>
					<th width="90">权限名称</th>
					<th width="60">权限编码</th>
				</tr>
			</table>
		</div>

		<!-- 菜单列表 -->
		<div id="menuList">
			<h5 style="color: red">============== 所有菜单 =============</h5>
			<table id="menuTable" width="60%" border="1px" cellpadding="3"
				cellspacing="1" bgcolor="#c1c1c1">
				<tr>
					<th width="30">菜单ID</th>
					<th width="70">上级菜单ID</th>
					<th width="90">权限ID</th>
					<th width="80">菜单文本</th>
					<th width="70">菜单url</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>