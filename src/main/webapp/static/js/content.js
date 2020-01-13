/**
 * 下方右部的数据
 */
function resquestByKV() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : WEBROOT+"/content/data",// 从HomeController.contentData方法获取数据
		// 提交的请求参数
		// data:{name:"sanmao", password:"sanmaoword"},
		// 返回数据的格式
		datatype : "json",// "xml", "html", "script", "json", "jsonp", "text".
		// 在请求之前调用的函数
		beforeSend : function() {
			$("#msg").html("logining");
		},
		// 成功返回之后调用的函数
		success : function(data) {
			// $("#msg1").html(decodeURI(data));
			var result = data;
			succFunction(result);
		},
		// 调用执行后调用的函数
		/*
		 * complete : function(XMLHttpRequest, textStatus) {
		 * alert(XMLHttpRequest.responseText); alert(textStatus);
		 * //HideLoading(); },
		 */
		// 调用出错执行的函数
		error : function() {
			alert("请求出错处理");
		}
	});
}

/**
 * 获取左部用户菜单
 */
function getUserMenu() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : WEBROOT+"/menu/use_menu",// 从HomeController.contentData方法获取数据
		// 提交的请求参数
		// data:{name:"sanmao", password:"sanmaoword"},
		// 返回数据的格式
		datatype : "json",// "xml", "html", "script", "json", "jsonp", "text".
		// 成功返回之后调用的函数
		success : function(data) {
			// $("#msg1").html(decodeURI(data));
			var result = data;
			handleMenu(result.menuList);
		},
		// 调用执行后调用的函数
		/*
		 * complete : function(XMLHttpRequest, textStatus) {
		 * alert(XMLHttpRequest.responseText); alert(textStatus);
		 * //HideLoading(); },
		 */
		// 调用出错执行的函数
		error : function() {
			alert("请求出错处理");
		}
	});
}

function succFunction(result) {
	handleUserList(result.userList);
	handleRoleList(result.roleList);
	handleUserRoleList(result.userRoleList);
	handlePermissionList(result.permissionList);
	handleRolePermisList(result.rolePermisList);
	handleMenuList(result.menuList);
}

// 处理用户信息
function handleUserList(userList) {
	var user = "";
	for (var i = 0; i < userList.length; i++) {
		user = "<tr>" + "<td>" + userList[i].userId + "</td>" + "<td>"
				+ userList[i].userName + "</td>" + "<td>"
				+ userList[i].password + "</td>" + "<td>" + userList[i].mobile
				+ "</td>" + "<td>" + userList[i].status + "</td>" + "<td>"
				+ userList[i].remark + "</td>" + "</tr>";
		$("#userTable").append(user);
	}
}

// 处理角色信息
function handleRoleList(roleList) {
	var role = "";
	for (var i = 0; i < roleList.length; i++) {
		role = "<tr>" + "<td>" + roleList[i].roleId + "</td>" + "<td>"
				+ roleList[i].roleCode + "</td>" + "<td>"
				+ roleList[i].roleName + "</td>" + "<td>" + roleList[i].type
				+ "</td>" + "<td>" + roleList[i].remark + "</td>" + "</tr>";
		$("#roleTable").append(role);
	}
}

// 处理用户-角色关系信息
function handleUserRoleList(userRoleList) {
	var userRole = "";
	for (var i = 0; i < userRoleList.length; i++) {
		userRole = "<tr>" + "<td>" + userRoleList[i].id + "</td>" + "<td>"
				+ userRoleList[i].userId + "</td>" + "<td>"
				+ userRoleList[i].userName + "</td>" + "<td>"
				+ userRoleList[i].roleId + "</td>" + "<td>"
				+ userRoleList[i].roleName + "</td>" + "</tr>";
		$("#userRoleTable").append(userRole);
	}
}

// 处理权限信息
function handlePermissionList(permissionList) {
	var permission = "";
	for (var i = 0; i < permissionList.length; i++) {
		permission = "<tr>" + "<td>" + permissionList[i].permisId + "</td>"
				+ "<td>" + permissionList[i].permisName + "</td>" + "<td>"
				+ permissionList[i].permisCode + "</td>" + "</tr>";
		$("#permissionTable").append(permission);
	}
}

// 处理角色-权限关系信息
function handleRolePermisList(rolePermisList) {
	var rolePermis = "";
	for (var i = 0; i < rolePermisList.length; i++) {
		rolePermis = "<tr>" + "<td>" + rolePermisList[i].id + "</td>" + "<td>"
				+ rolePermisList[i].roleId + "</td>" + "<td>"
				+ rolePermisList[i].roleName + "</td>" + "<td>"
				+ rolePermisList[i].roleCode + "</td>" + "<td>"
				+ rolePermisList[i].permisId + "</td>" + "<td>"
				+ rolePermisList[i].permisName + "</td>" + "<td>"
				+ rolePermisList[i].permisCode + "</td>" + "</tr>";
		$("#rolePermisTable").append(rolePermis);
	}
}

/**
 * 处理菜单信息
 * 
 * @param menuList
 */
function handleMenuList(menuList) {
	var menu = "";
	for (var i = 0; i < menuList.length; i++) {
		menu = "<tr>" + "<td>" + menuList[i].menuId + "</td>" + "<td>"
				+ menuList[i].parentId + "</td>" + "<td>"
				+ menuList[i].permisId + "</td>" + "<td>"
				+ menuList[i].menuText + "</td>" + "<td>" + menuList[i].menuUrl
				+ "</td>" + "</tr>";
		$("#menuTable").append(menu);
	}
}

/**
 * 输出餐单
 * 
 * @param menuList
 */
function handleMenu(menuList) {
	var menuUl = "";

	for (var i = 0; i < menuList.length; i++) {
		menuUl = "<li><a href=" + menuList[i].menuUrl + ">"
				+ menuList[i].menuText + "</a></li>";
		$("#menuUl").append(menuUl);
	}
}
