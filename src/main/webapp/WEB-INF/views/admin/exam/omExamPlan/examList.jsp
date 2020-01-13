<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<style>
.thumbnail {
    display: block;
    margin: 5px;
    line-height: 1.42857143;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 4px;
    -webkit-transition: border .2s ease-in-out;
    -o-transition: border .2s ease-in-out;
    transition: border .2s ease-in-out;
}
.thumbnail .caption {
    padding: 10px;
    color: #333;
}
.h3, h3 {
    font-size: 24px;
    padding:10px
}
p {
    margin: 0 0 10px;
}
.container {
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}
.caption{
    background-color: #f8f8f840;
}
</style>
<body class="bg-color-white">
<div class="layui-fluid container" style="margin-top: 10px;">
    <c:if test="${not empty planList&&fn:length(planList)>0 }">
	<div class="layui-row">
		<c:forEach items="${planList}" var="bean">
		<div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
			<div class="thumbnail">
				<div class="caption">
					<h3>${bean.planName}</h3>
					<p>试卷名称: ${bean.paperName }    </p>
					<p>题目数量: ${bean.singleCount+bean.multiCount+bean.judgeCount+bean.blankCount+bean.answerCount }    总分: ${bean.paperScore }  考试时长: <span id="examPaperTime5">${bean.paperTime }</span> 分钟  </p>
					<p class="beginTime">开始时间: ${bean.beginTime}</p>
					<p style="text-align: center;">
						<a href="#" class="layui-btn" onclick="startExam('${bean.paperId}','${bean.planId}')">进入考试</a>
					</p>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</c:if>
	<c:if test="${empty planList||fn:length(planList)==0 }">
	<h1>暂无待考信息</h1>
	<p>请等待分配</p>
	<p>
		<a href="#" class="layui-btn" onclick="location.reload()">刷新</a>
	</p>
	</c:if>
</div>
</body>
<script type="text/javascript">
function showPaper(paperId,planId){
	var baseUrl = WEBROOT + '/admin/exam/omPaper';
	var showUrl = baseUrl +'/exam';
	 var url =showUrl+'?paperId='+paperId+'&planId='+planId;
	 var title = '答卷';
	 var isreload = 1;
	 commonCreateView(title,url,isreload,'500','500',1);
}

function startExam(paperId,planId){
	$.ajax({
		type:"POST",
		async:false,
		data:{paperId:paperId,planId:planId},
		url:WEBROOT + "/admin/exam/omExamPlan/doStartExam",
		success:function (data) {
			console.dir(data)
			if (data.code == "200") {
				var flag = data.data;
				if(flag){
					showPaper(paperId,planId);
				}else{
					layer.msg(data.message, function(){
						//关闭后的操作
						window.location.reload();
					});
				}
			}else{
				layer.msg('查询失败：'+data.messgae, {icon: 5});
			}
		}
	});
	
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
	    		 window.location.reload();
	    	 }
		}
	 });
	 if(isfull){
		 parent.layer.full(index);
	 }
}
</script>

