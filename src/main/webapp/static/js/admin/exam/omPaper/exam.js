var lastTimeTimer;
var form = null;
layui.use('form', function(){
	form = layui.form;
	form.render();
	
	//单选题
	form.on('radio(rightResult)', function(data){
	   var rightResult = data.value;
	   console.dir(rightResult);
       var questionId =$(data.elem).closest('.layui-form-item').find("[name='questionId']").val();
       console.dir(questionId);
       saveQuestionResult(questionId,rightResult);
	});
	
	//多选题
	form.on('checkbox(rightResult)', function(data){
       var questionId =$(data.elem).closest('.layui-form-item').find("[name='questionId']").val();
       console.dir(questionId);
       var rightResult = new Array();
       $(data.elem).closest('.layui-form-item').find("input:checkbox[name='rightResult']:checked").each(function(i){
       	rightResult[i] = $(this).val();
       });
   		console.dir(rightResult);
   		saveQuestionResult(questionId,rightResult.join(","));
	});
});	  

$(function () {
	//填空题设置输入框
	var questions = $("#questionType4").find(".questionName");
	$("#questionType4").find(".questionName").each(function(i, o){
		var questionName = $(o).text();
		questionName = questionName.replace(/(____)/g,'<input type="text" name="rightResult" autocomplete="off" style="width:100px" value="">');
		$(o).html(questionName);
		//初始化填空题的输入结果
		var answerResult = $(o).closest('.layui-form-item').find("[name='answerResult']").val();
		if(answerResult.length>0){
			var answerResult_arr = answerResult.split("、");
			$.each(answerResult_arr, function(i,val){      
			      $(o).closest('.layui-form-item').find("[name='rightResult']").eq(i).val(val);
		   });  
		}
		
	});
	
	
	//倒计时
	getLeftTime();
	//考试计时
	lastTimeTimer = setInterval("subTime()", 1000);
	
	//监控填空题
	$("input:text[name='rightResult']").blur(function () {
		var questionId =$(this).closest('.layui-form-item').find("[name='questionId']").val();
	    console.dir(questionId);
	    var rightResult = new Array();
        $(this).closest('.layui-form-item').find("input:text[name='rightResult']").each(function(i){
        	rightResult[i] = $(this).val();
        });
    	console.dir(rightResult);
    	saveQuestionResult(questionId,rightResult.join("、"));
	});
	//监控简答题
	$("textarea[name='rightResult']").blur(function () {
		var questionId =$(this).closest('.layui-form-item').find("[name='questionId']").val();
	    console.dir(questionId);
	    var rightResult = $(this).val();
    	console.dir(rightResult);
    	saveQuestionResult(questionId,rightResult);
	});
	
	//第一次开始答题 保存记录
	if(!$("#submitId").val()){
		startAnswer('1');
	}
	
	
});


function getLeftTime(){
	var beginTime = $("#beginTime").val();
	var examTime = parseInt($("#examTime").text()); //剩余分钟数, 这个是考试时长
	
	//获取年月日
	var date = beginTime.split(" ")[0];
	var year = date.split("-")[0];
	var month = parseInt(date.split("-")[1])-1;
	var day = date.split("-")[2];

	//获取时分秒
	var time = beginTime.split(" ")[1];
	var hour = time.split(":")[0];
	var min = time.split(":")[1];
	var sec = (time.split(":")[2]).split(".")[0];

	//设置开始考试时间对象
	var beginDate = new Date();
	beginDate.setYear(year);
	beginDate.setMonth(month);
	beginDate.setDate(day);
	beginDate.setHours(hour);
	beginDate.setMinutes(min);
	beginDate.setSeconds(sec);
	
	//获取当前时间对象
	var nowDate = new Date();
	
	//计算考试时长的秒数
	var examTime_sec = examTime * 60 * 1000;
	//计算结束时间 对应的秒数
	var examEndTime_sec = beginDate.getTime() + examTime_sec;
	
	//获取当前时间对应的秒数
	var nowDate_sec = nowDate.getTime();
	
	//获取时间差 秒
	var subTime_sec = examEndTime_sec - nowDate_sec;
	
	//将时间差 秒 计算为分钟数
	if(subTime_sec > 60*1000) {
		//获取分钟数
		$("#time_min").text(parseInt(subTime_sec/(1000*60)));
		$("#time_sec").text(subTime_sec%(1000*60)/1000);
	} else {
		$("#time_sec").text(subTime_sec%(1000*60)/1000);
		$("#time_min").text(0);			
	}
}

//考试倒计时
function subTime() {
	var lastmin = parseInt($("#time_min").text());
	var lastsec = parseInt($("#time_sec").text());
	
	if(lastmin<=0 && lastsec<=1) {
		//自动提交试卷
		/**
		$("form").submit();
		clearInterval(lastTimeTimer);
		zeroModal.show({
			title: "提示",
			content: "考试结束!",
			width : '200px',
			height : '130px',
			overlay : true,
			ok : true,
			onClosed : function() {
				//考试结束回到考试中心页面
				location.href = "willexams?classId="+$('#examEndRefresh_classId').text().trim()+"&gradeId="+$('#examEndRefresh_gradeId').text().trim()+"&studentId="+$('#exam-studentId').text().trim()+"";
			}
		});
		**/
		clearInterval(lastTimeTimer);
		layer.alert('考试时间到，考试结束!', {
			 	closeBtn: 0
			}, function(){
				parent.layer.closeAll()
		});
		return false;
	}
	
	
	if(lastmin<=3||lastmin-1<=3) {
		$("#lastTime").css("color", "red");
	}
	
	if(lastsec-1 < 0) {
		$("#time_min").text(lastmin-1);
		$("#time_sec").text(59);
		return;
	}
	
	$("#time_sec").text(lastsec-1);
}

//保存每一题的结果
function saveQuestionResult(questionId,answerResult){
	 var planId = $("#planId").val();
	 var paperId = $("#paperId").val();
	 var url = WEBROOT + "/admin/exam/omPaper/saveQuestionResult";
	 $.ajax({
		type:"POST",
			async:true,
			url:url,
			data:{planId:planId,paperId:paperId,questionId:questionId,answerResult:answerResult},
			success:function (data) {
			}
	 });
}

//打开页面开始答题
function startAnswer(status){
	 var planId = $("#planId").val();
	 var paperId = $("#paperId").val();
	 var url = WEBROOT + "/admin/exam/omExamSubmit/saveExamSubmit";
	 $.ajax({
		type:"POST",
			async:true,
			url:url,
			data:{planId:planId,paperId:paperId,status:status},
			success:function (data) {
				if (data.code == "200") {
					var examSubmit = data.data;
					$("#submitId").val(examSubmit.submitId);
				}
			}
	 });	
}

//提交试卷
function submitPaper(){
	$.ajax({
		type:"POST", 
		async:true, 
		url:WEBROOT + "/admin/exam/omExamSubmit/saveExamSubmit",
		data:{
			submitId:$("#submitId").val(),
			planId:$("#planId").val(),
			paperId:$("#paperId").val(),
			status:'2'
		},
		beforeSend: function () {
            $("#btnSave").addClass('layui-btn-disabled');// 禁用
        },
        complete: function () {
            $("#btnSave").removeClass('layui-btn-disabled');// 启用
        },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('提交成功', {icon: 1});
				parent.layer.closeAll()
			}else{
				layer.msg('提交失败：'+data.message, {icon: 5});
			}
		}
	});
}