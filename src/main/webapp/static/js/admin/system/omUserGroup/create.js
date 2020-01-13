var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var fntype = $("#fntype").val();  
	    if(isExistName()){
			layer.msg('用户分组名称已存在，请重新输入', {icon: 5});
			return false;
		}  
		save(data);	
		return false;
	 });
});

function save(data){
	var url = WEBROOT + '/admin/system/omUserGroup/save';
	commonSave(data,url);	
}

function isExistName(){
	var groupName = $("#groupName").val();
	var groupId =  $("#groupId").val();
	var flag = true;
	$.ajax({
			type:"POST",
			async:false,
			url:WEBROOT + "/admin/system/omUserGroup/isExistName",
			data:{
				groupName:groupName,
				groupId:groupId
			},
			success:function (data) {
				if (data.code == "200") {
					flag = data.data;
				}else{
					layer.msg('查询失败：'+data.message, {icon: 5});
				}
			}
		});
	return flag;
}