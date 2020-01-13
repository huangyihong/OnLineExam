var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		data.field.status = '3';//阅卷完成状态  
		save(data);	
		return false;
	  });
	  form.on('submit(formSubmit2)', function(data){
		data.field.status = '4';//阅卷完成并发布成绩状态  
		save(data);	
		return false;
	  });
});

function save(data){
	var url = WEBROOT + '/admin/exam/omExamSubmit/saveMarkExam';
	var omExamAnswerList = new Array();
	var totalScore =0;
	$('.layui-form-item').find("[name='questionId']").each(function(i, o){
		var questionId = $(this).val();
		var answerId = $(this).closest('.layui-form-item').find("[name='answerId']").val();
		var markScore = $(this).closest('.layui-form-item').find("[name='markScore']").val().trim();
		var markText = $(this).closest('.layui-form-item').find("[name='markText']").val();
		var omExamAnswer = new Object();
		omExamAnswer.questionId=questionId;
		omExamAnswer.answerId=answerId;
		omExamAnswer.markScore=markScore;
		omExamAnswer.markText=markText;
		totalScore +=parseInt(markScore);
		omExamAnswerList.push(omExamAnswer);
	});
	data.field.totalScore = totalScore;
	data.field.omExamAnswerListJson=JSON.stringify(omExamAnswerList);
	commonSave(data,url);	
}

$(function () {
	var totalScore = 0;
	$(".questionscore_span").each(function(i, o){
		var questionScore = $(o).text();
		totalScore += parseInt(questionScore.trim());
	});
	$("#totalScore").html("&emsp;"+totalScore+"&emsp;");
});

function gradeExam(id,status){
	gradeExamFun(id,status)
}

function gradeExamFun(ids,status){
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/exam/omExamSubmit/gradeExam",
		data:{
				ids:ids,
				status:status
			 },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('操作成功', {icon: 1});
				parent.layer.closeAll()
			}else{
				layer.msg('操作失败：'+data.message, {icon: 5});
			}
		}
	});
}