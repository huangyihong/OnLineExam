var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var fntype = $("#fntype").val();  
		save(data);	
		return false;
	 });
});

function save(data){
	var url = WEBROOT + '/admin/exam/omExamPlan/save';
	commonSave(data,url);	
}