<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>


<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="${fntype }"/>
    <input type="hidden" id="paperId" name="paperId" value="${bean.paperId}"/>
    <input type="hidden" id="addMode" name="addMode" value="${addMode }"/>
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 150px;"><span class="c-red">*</span>试卷名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="paperName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.paperName }">
            </div>
        </div>
   </div>     
   <div class="layui-form-item">    
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 150px;"><span class="c-red">*</span>考试时长(分钟)：</label>
            <div class="layui-input-inline">
                <input type="text" name="paperTime" autocomplete="off" class="layui-input" lay-verify="number" value="${bean.paperTime }">
            </div>
        </div> 
   </div>
   <div>   
   <c:forEach items="${detailList}" var="detailBean">  
   <div class="layui-form-item">     
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 150px;"><span class="c-red">*</span>所属科目(专业)：</label>
            <div class="layui-input-inline">
            	<select name="courseId" value="${bean.courseId }" lay-verify="required" >
                	<option value="">--请选择--</option>
	                <c:forEach items="${courseList}" var="courseBean">
	                	<option value="${courseBean.courseId }" <c:if test="${courseBean.courseId == detailBean.courseId }">selected</c:if>>${courseBean.courseName }</option>
	                </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>题型：</label>
            <div class="layui-input-inline" style="width: 150px;">
            	<select name="questionType" lay-verify="required">
                	<option value="">--请选择--</option>
                	<option value="1" <c:if test="${'1' == detailBean.questionType }">selected</c:if>>单选题</option>
                	<option value="2" <c:if test="${'2' == detailBean.questionType }">selected</c:if>>多选题</option>
                	<option value="3" <c:if test="${'3' == detailBean.questionType }">selected</c:if>>判断题</option>
                	<option value="4" <c:if test="${'4' == detailBean.questionType }">selected</c:if>>填空题</option>
                	<option value="5" <c:if test="${'5' == detailBean.questionType }">selected</c:if>>简答题</option>
                </select>
            </div>
        </div>
       <div class="layui-inline">
            <label class="layui-form-label">题数：</label>
            <div class="layui-input-inline" style="width: 80px;">
                <input type="text" name="questionCount" autocomplete="off" class="layui-input" lay-verify="number" value="${detailBean.questionCount }">
            </div>
     	</div>
   		<div class="layui-inline">
	            <label class="layui-form-label" style="width: 130px;">每题分值(分)：</label>
	            <div class="layui-input-inline" style="width: 80px;">
	                <input type="text" name="questionScore" autocomplete="off" class="layui-input" lay-verify="number" value="${detailBean.questionScore }">
	            </div>
	            <c:if test="${fntype!='view' }">
	             <a class="layui-btn layui-btn-sm" onclick="addRow($(this).parent().parent())"><i class="layui-icon"></i></a>
	            <a class="layui-btn layui-btn-sm" onclick="delRow($(this).parent().parent())"><i class="layui-icon"></i></a>
	            </c:if> 
	    </div>
   </div> 
   </c:forEach>
   </div>    
    <br>
    <div class="layui-form-item" style="text-align: center">
        <span>
            <c:if test="${fntype!='view' }">
                <a lay-submit lay-filter="formSubmit" id="btnSave" href="javascript:void(0)"
                   class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">保存</a>
            </c:if>
            <a onclick="parent.layer.closeAll()"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">关闭</a>

        </span>
	</div>
</form>
</div>
<script type="text/javascript" src="${contextPath }/static/js/common/commonCreate.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omPaper/create.js"></script>
<script type="text/javascript">
//插入一行
function addRow(obj){
	var cl = obj.clone(false);
	cl.find("[name='questionCount']").val("");
	cl.find("[name='questionScore']").val("");
	cl.find("[name='del']").attr("style","");
	$(obj).after(cl);
	//重新渲染下
	layui.use('form', function() {
        var form = layui.form;
        form.render();
    });
}
//删除一行
function delRow(obj){
	if($(obj).siblings().length==0){
		$(obj).children("select").val(" ");
		return;
	}
	$(obj).remove();
}

function beforeSave(){
	//不合规行为详情定义
	var detailDTOList = new Array();
	var	courseIdTypeList = new Array();
	var	haveSameCourseId = false;
	$("#form [name='courseId']").each(function(i, o){
		if($(o).val()!=null && $(o).val()!=" "){
			var courseId = $(o).val();
			var courseName =  $(o).find("option:selected").text();
			var bean = new Object();
		    bean.courseId= courseId;
		    bean.courseName =courseName;
		    bean.orderNum = i;
		    bean.questionType = $(o).closest('.layui-form-item').find("[name='questionType']").val();
		    var questionTypeName =  $(o).closest('.layui-form-item').find("[name='questionType']").find("option:selected").text();
		    bean.questionCount = $(o).closest('.layui-form-item').find("[name='questionCount']").val();
		    bean.questionScore = $(o).closest('.layui-form-item').find("[name='questionScore']").val();
		    var courseIdType = courseId+'_'+questionTypeName;
		    if ($.inArray(courseIdType,courseIdTypeList)!=-1) {
				layer.msg(courseName+"的"+questionTypeName+"已存在，请重新选择", {icon: 5});
				haveSameCourseId = true;
				return;
			}
		    courseIdTypeList.push(courseIdType);
		    detailDTOList[i] = bean;
		}
	});
	if(haveSameCourseId){
		return;
	}
	if(detailDTOList.length==0){
		layer.msg('请选择科目、题型和输入题数、分值等信息', {icon: 5});
		return;
	}
    return JSON.stringify(detailDTOList);
}
</script>




