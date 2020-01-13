layui.use('form', function(){
	  form = layui.form;
	  form.verify({
          phoneOrBlank : function(value){
		      if(value.length > 0){
		    	  var reg = /^1[3|4|5|7|8]\d{9}$/;
		    	  if(!reg.test(value)){
		    		  return '手机必须11位，只能是数字！';
		    	  }
			  }
		  }
		  ,pass: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格']
		  ,username: function(value){
			    if(value.length<4){
			    	return '请输入至少4位';
			    }
			    if(!new RegExp("^[a-zA-Z0-9_\\s·]+$").test(value)){
			        return '不能有特殊字符或中文';
			    }
			    if(/(^\_)|(\__)|(\_+$)/.test(value)){
			        return '首尾不能出现下划线\'_\'';
			    }
//			    if(/^\d+\d+\d$/.test(value)){
//			        return '不能全为数字';
//			    }
		  	}
		  	,maxlength: function(value){
		  		if(value.length>50){
			    	return '长度不能超过50';
			    }
		  	}
      });
});

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  lay('.dateInput').each(function(){
		var type = 'date';
	    var format = $(this).data('format') || 'yyyy-MM-dd';
	    if(format==null||format=='') {
	    	type = 'date';
	    } else if(format=='yyyy-MM-dd' || format=='yyyy/MM/dd' || format=='yyyyMMdd') {
	    	type = 'date';
	    } else if(format=='yyyy-MM' || format=='yyyy/MM' || format=='yyyyMM') {
	    	type = 'month';
	    } else if(format=='yyyy') {
	    	type = 'year';
	    }else if(format=='yyyy-MM-dd HH:mm:ss') {
	    	type = 'datetime';
	    } 
	    var param = { elem: this ,trigger: 'click' ,type: type};
	    var min = $(this).data('datemin');
	    var max = $(this).data('datemax');
	    if(min||min=='0'){
	    	param.min = min
	    }
	    if(max||max=='0'){
	    	param.max = max
	    }
	    laydate.render(param);
	  });
});

$(function () {
	var fntype = $("#fntype").val();
	//查看只读
	if(fntype == 'view'){
		$('input').attr("readonly","readonly");
		$("select").each(function(i, o){
			var text =  $(o).find("option:selected").text();
			if(!$(o).val()){
				text = '';
			}
			$(o).parent().html('<input type="text" autocomplete="off" class="layui-input" value="'+text+'" alt="'+text+'" title="'+text+'" readonly>');
		});
		$('textarea').attr("disabled","disabled");
	}
});

function commonSave(data,url){
	// 按钮禁用
    var isDisabled = $("#btnSave").hasClass('layui-btn-disabled');
    if (isDisabled) {
        return false;
    }
	$.ajax({
		type:"POST", 
		async:true, 
		url:url,
		data:data.field,
		beforeSend: function () {
            $("#btnSave").addClass('layui-btn-disabled');// 禁用
        },
        complete: function () {
            $("#btnSave").removeClass('layui-btn-disabled');// 启用
        },
		success:function (data) {
			if (data.code == "200") {
				layer.msg('保存成功', {icon: 1});
				parent.layer.closeAll()
			}else{
				layer.msg('保存失败：'+data.message, {icon: 5});
			}
		}
	});
}



