var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var questionType = $("#questionType").val();
		if(questionType=='1'||questionType=='2'||questionType=='3'){
			var rightResult = $('input[name="rightResult"]:checked');
			if(rightResult.length==0){
				layer.msg('请选择问题的答案', {icon: 5});
				return false;
			}
			if(questionType=='1'||questionType=='2'){
				if(!$('input[name="optionA"]').val()&&!$('input[name="optionB"]').val()&&!$('input[name="optionC"]').val()&&!$('input[name="optionD"]').val()
				  &&!$('input[name="optionE"]').val()&&!$('input[name="optionF"]').val()&&!$('input[name="optionG"]').val()&&!$('input[name="optionH"]').val()){
					layer.msg('请至少输入一个选项', {icon: 5});
					return false;
				}
			}
		}
		save(data);	
		return false;
	 });
});


function save(data){
	var url = WEBROOT + '/admin/exam/omQuestion/save';
	var questionType = $("#questionType").val();
	if(questionType=='2'){
		var arr = new Array();
	    $("input:checkbox[name='rightResult']:checked").each(function(i){
	    	arr[i] = $(this).val();
	    });
	    data.field.rightResult = arr.join(",");
	}
	var courseName = $("#courseId option:selected").text();
	data.field.courseName = courseName;
	commonSave(data,url);	
	parent.parent.layer.closeAll();
}

//插入空格
function insertBlank(t_this){
	var blank = "(____)";
	var txtArea = $("#questionName")[0];
    var content = txtArea.value;
    var start = txtArea.selectionStart; //初始位置
    txtArea.value = content.substring(0, txtArea.selectionStart) + blank + content.substring(txtArea.selectionEnd, content.length);
    var position = start + blank.length;
    $("#questionName").focus();
    txtArea.setSelectionRange(position, position);
}
