//通用表格
function commonInitGrid(listUrl,cols,keyFiled,limit){
	var tableHeight = $(window).height() - $('#conditionDiv').outerHeight(true)-20;
	var where = getParams();
	layui.use('table', function(){
	  table = layui.table;
	  form = layui.form;
	  var index = layer.load(0, {shade: [0.3,'#fff']});
	  //展示已知数据
	  table.render({
	    elem: '#Table'
	    ,url:listUrl
	    ,where:where
	    ,height:tableHeight
	    ,cellMinWidth: 120
	    ,cols: cols
	    ,id: 'layuiTable'
	    ,page: true
	    ,limit:limit
	    ,method: 'post'
	    ,response: { statusCode: 200}
	    ,parseData: function(res){
	        return {
	          "code": res.statusCode, //解析接口状态
	          "msg": res.message, //解析提示文本
	          "count": res.total, //解析数据长度
	          "data": res.data //解析数据列表
	        };
	     }
	    ,done: function() {
	    	layer.close(index);
	    }
	  });

	  var $ = layui.$, active = {
		    //刷新表格		  
		    reload: function(){
		    	reloadTable();
		    },
		    //新增
		    add: function(){
		    	createView('add','');
		    },
		    //查看
	        view: function(){
		        var checkStatus = table.checkStatus('layuiTable');
		        var data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('view',id)
	       },
	       //修改
	       update: function(){ 
		        var checkStatus = table.checkStatus('layuiTable');
		        var data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('update',id)
	       },
	       //复制新增
	       updateAdd: function(){ 
		        var checkStatus = table.checkStatus('layuiTable')
		        ,data = checkStatus.data;
		        if(data.length!='1'){
		        	layer.alert("请选择1条记录");
		        	return;
		        }
		        var id = data[0][keyFiled];
		        createView('updateAdd',id)
		   },
	       //删除
		   del: function(){
		        var checkStatus = table.checkStatus('layuiTable')
		        ,data = checkStatus.data;
		        if(data.length=='0'){
		        	layer.alert("请选择需要删除的数据");
		        	return;
		        }
		        var ids="";
		        for(var i=0;i<data.length;i++){
		        	ids += data[i][keyFiled]+",";
		  		}
		        del(ids.substring(0, ids.length - 1))
		    }
	  	};

		$('#tableQuery .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	
		$('#tableTool .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
}

//重置
function reset1(){
	$("#tableQuery input").val("");
	$("#tableQuery select").val("");
}

//刷新表格
function reloadTable(){
	var index = layer.load(0, {shade: [0.3,'#fff']});
    //执行重载
	layui.table.reload('layuiTable', {
	      page: {curr: 1 }
		 ,where: getParams()
	     ,done: function() {
		    layer.close(index);
		  }
	});
}

//查询框参数
function getParams(){
	var params = {};
	var data = $('#formQuery').serializeArray();
	$.each(data, function() {
		var value = $.trim(this.value);
		params[this.name] = value;
	});
	return params;
}

//打开新页面
function commonCreateView(title,url,isreload,width,height,isfull){
	if(!width){
		width = 900
	}
	if(!height){
		height = 450
	}
	 var index =  parent.layer.open({
	     type: 2
	    ,title: title
	    ,area: [width+'px', height+'px']
	    ,shade: 0.6
	    ,maxmin: false
	    ,content: url
	    ,yes: function(){
	      $(that).click();
	    }
	    ,btn2: function(){
	    	parent.layer.closeAll();
	    }
	    //,zIndex: parent.layer.zIndex //重点1
	    ,success: function(layero){
	    	//parent.layer.setTop(layero); //重点2
	    }
	    ,end: function () {
	    	 if(isreload){
	    		 reloadTable();;
	    	 }
		}
	 });
	 if(isfull){
		 parent.layer.full(index);
	 }
}

//删除
function commonDelAjax(url,ids,keyField){
	var data = {};
	data[keyField] = ids;
	layer.confirm('确定删除所选择的记录？', {
   	  btn: ['确定','取消'] //按钮
   	}, function(){
   		$.ajax({
			type:"POST",
			async:false,
			url:url,
			data:data,
			success:function (data) {
				if (data.code == "200") {
				  layer.open({
			        id: 'layerDemo' //防止重复弹出
			        ,content: '操作成功'
			        ,btn: '关闭'
			        ,btnAlign: 'c' //按钮居中
			        ,shade: 0 //不显示遮罩
			        ,yes: function(){
			          layer.closeAll();
			          reloadTable();
			        }
			        ,end: function () {
						reloadTable();
					}
			      });

				}
			}
		});
   	}, function(){

	});
}

