var form = null;
layui.use('form', function(){
	  form = layui.form;
	  form.render();
	  
	//监听提交
	  form.on('submit(formSubmit)', function(data){
		var addMode = $("#addMode").val();
		if(addMode=='1'){//人工添加
			var id_arr = ['singleCount','multiCount','judgeCount','blankCount','answerCount'];
			var questionId_arr = setQuestionNum(id_arr);
			if(questionId_arr.length==0){
				layer.msg('请至少选择一个试题', {icon: 5});
				return;
			}
			data.field.questionIds = questionId_arr.join(",")
			//设置各类型题目数量
			var i=1;
			while(i<6){
				data.field[id_arr[i-1]]=$("#"+id_arr[i-1]).val();
				i++;
			}
			var courseName = $("#courseId option:selected").text();
			data.field.courseName = courseName;
		}else{
			data.field.detaiDTOListJson = beforeSave();
		}
		save(data);	
		return false;
	 });
	  
	  form.on('select(courseId)', function(data){
		  var addMode = $("#addMode").val();
		  if(addMode=='1'){//人工添加
			  $(".layui-form-item").find("[name='questionIds']").val("");
			  $(".layui-form-item").find("[name='questionNames']").val("");
			  $(".layui-form-item").find('.multiSelect').empty()
		  }
	  }); 
});

$(function () {
	//初始列表
	var addMode = $("#addMode").val();
	if(addMode=='1'){//人工添加
		getPaperQuestionList();
	}
});


function save(data){
	var url = WEBROOT + '/admin/exam/omPaper/save';
	commonSave(data,url);	
}

//得出选中的各类型题目数量
function setQuestionNum(id_arr){
	var i=1;
	var questionId_arr=[];
	while(i<6){
		var $questionId = $("#questionType"+i).find("[name='questionIds']")
	    var $questionName = $("#questionType"+i).find("[name='questionNames']")
	    var questionIds = $questionId.val().length > 0 ? $questionId.val().split('|') : [];
	    var questionNames = $questionName.val().length > 0 ? $questionName.val().split('|') : [];
	    $("#"+id_arr[i-1]).val(questionIds.length);
	    $.each(questionIds, function(i,o){
			questionId_arr.push(o);
		});
	    i++;
	}
	return questionId_arr;
}

function openSelectWin(questionType){
	var courseId = $("#courseId").val();
	var url = WEBROOT + '/admin/exam/omQuestion/selectMultiQuestion?courseId='+courseId+'&questionType='+questionType;
	commonSelectView('选择试题',url,'','',1,questionType);	
}

function commonSelectView(title,url,width,height,isfull,questionType) {
	if(!width){
		width = 900
	}
	if(!height){
		height = 450
	}
    layui.use(['element'], function () {
        var index = top.layer.open({
            type: 2,
            title: title,
            skin: 'layui-layer-rim', //加上边框
            area: [width+'px', height+'px'], //宽高
            content: url,
            btn: ['确定', '取消'],
            maxmin: true, 
            yes: function (index) {
            	//当点击‘确定’按钮的时候，获取弹出层返回的值
                var res = top.window["layui-layer-iframe" + index].callbackdata();
                var msg = res.msg;
                if (msg != '') {
                    top.layer.alert(msg, {icon: 2, time: 2000, shade: 0.3});
                } else {
                    //选中的回写到页面
                    setTab($("#questionType"+questionType).find('.multiSelect'),res.objs)
                    top.layer.close(index);
                }
            },
            cancel: function () {
                //右上角关闭回调
            }
        });
        if(isfull){
        	top.layer.full(index);
   	    }
    });
}

//删除选中的题目
function rmChoose(target) {
    var index = $(target).parent().index()
    var $multiSelect = $(target).closest('.multiSelect')
    $multiSelect.find('a:eq(' + index + ')').remove()
    var $questionId = $multiSelect.parent().find("[name='questionIds']")
    var $questionName = $multiSelect.parent().find("[name='questionNames']")
    var questionIds = $questionId.val().length > 0 ? $questionId.val().split('|') : [];
    var questionNames = $questionName.val().length > 0 ? $questionName.val().split('|') : [];
    questionIds.splice(index, 1)
    questionNames.splice(index, 1)
    $questionId.val(questionIds.join("|"))
    $questionName.val(questionNames.join("|"))
}

//选中后回写到页面
function setTab($multiSelect,objs) {
	$multiSelect.empty()
	var fntype = $("#fntype").val()
	var html = '<i onclick="rmChoose(this)"></i>';
	if (fntype === 'view') {
		html = '';
	} 
	var questionIds = [];
    var questionNames = [];
	$.each(objs, function(i,obj){
		var name =  obj.name;
		var id = obj.id;
		if(!name){
			name =  obj.questionName;
		}
		if(!id){
			id =  obj.questionId;
		}
		$multiSelect.append('<a><span>' + name + '</span>'+html+'</a>')
		questionIds.push(id);
		questionNames.push(name);
	});
	$multiSelect.parent().find("[name='questionIds']").val(questionIds.join("|"));
	$multiSelect.parent().find("[name='questionNames']").val(questionNames.join("|"));
}

//提供给子页面获取选中的初始值
top.getSelectItems = function (questionType) {
	var $questionId = $("#questionType"+questionType).find('.multiSelect').parent().find("[name='questionIds']")
    var $questionName =$("#questionType"+questionType).find('.multiSelect').parent().find("[name='questionNames']")
    var questionIds = $questionId.val().length > 0 ? $questionId.val().split('|') : [];
    var questionNames = $questionName.val().length > 0 ? $questionName.val().split('|') : [];
    return {
        ids: questionIds,
        names: questionNames
    }
}

function getPaperQuestionList(){
	var paperId = $("#paperId").val();
	$.ajax({
		type:"POST",
		async:false,
		data:{paperId:paperId},
		url:WEBROOT + "/admin/exam/omPaper/getPaperQuestionList",
		success:function (data) {
			if (data.code == "200") {
				var questionList = data.data;
				var questionMap = {}
				$.each(questionList, function(i,question){
					var questionListJson = questionMap["questionType"+question.questionType];
					if(!questionListJson){
						questionListJson = new Array;
					}
					questionListJson.push(question);
					questionMap["questionType"+question.questionType]=questionListJson;
				});
				var i=1;
				while(i<6){
					var objs = questionMap["questionType"+i]
					if(objs){
						setTab($("#questionType"+i).find('.multiSelect'), questionMap["questionType"+i])
					}
					i++;
				}
				
			}else{
				layer.msg('查询失败：'+data.message, {icon: 5});
			}
		}
	});
}