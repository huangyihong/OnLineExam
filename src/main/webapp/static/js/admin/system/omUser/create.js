var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var fntype = $("#fntype").val();  
	    if(fntype=='add'&&isExistName()){
			layer.msg('用户名已存在，请重新输入', {icon: 5});
			return false;
		}  
		save(data);	
		return false;
	 });
});

function save(data){
	var url = WEBROOT + '/admin/system/omUser/save';
	if($("#groupId").val()){
		var groupName = $("#groupId option:selected").text();
		data.field.groupName = groupName;
	}
	commonSave(data,url);	
}

function isExistName(){
	var userName = $("#userName").val();
	var flag = true;
	$.ajax({
			type:"POST",
			async:false,
			url:WEBROOT + "/admin/system/omUser/isExistName",
			data:{
				userName:userName
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