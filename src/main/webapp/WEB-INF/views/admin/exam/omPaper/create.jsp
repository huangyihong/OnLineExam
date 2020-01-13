<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<style>
.multiSelect {
	min-height: 0;
}
</style>

<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
    <input type="hidden" id="fntype" name="fntype" value="${fntype }"/>
    <input type="hidden" id="paperId" name="paperId" value="${bean.paperId}"/>
    <input type="hidden" id="addMode" name="addMode" value="${addMode }"/>
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>试卷名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="paperName" autocomplete="off" class="layui-input" lay-verify="required|maxlength" value="${bean.paperName }">
            </div>
        </div>
   </div>     
   <div class="layui-form-item">     
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>所属科目：</label>
            <div class="layui-input-inline">
            	<select id="courseId" name="courseId" value="${bean.courseId }" lay-verify="required" lay-filter="courseId"  >
                	<option value="">--请选择--</option>
	                <c:forEach items="${courseList}" var="courseBean">
	                	<option value="${courseBean.courseId }" <c:if test="${courseBean.courseId == bean.courseId }">selected</c:if>>${courseBean.courseName }</option>
	                </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span class="c-red">*</span>考试时长：</label>
            <div class="layui-input-inline">
                <input type="text" name="paperTime" autocomplete="off" class="layui-input" lay-verify="number" value="${bean.paperTime }">
            </div>
        </div>
   </div>   
   <div class="layui-form-item" id="questionType1">      
        <div class="layui-inline">
            <label class="layui-form-label">单选题：</label>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm m-l-10" style="margin-top: 4px;" onclick="openSelectWin('1')">选择</a>
            </div>
            <div class="layui-input-inline" style="width: 90%;margin-left: 120px;">
	            <input type="hidden" name="questionIds" value="">
	            <input type="hidden" name="questionNames" value="">
	            <input type="hidden" id="singleCount" name="singleCount" autocomplete="off" class="layui-input" value="${bean.singleCount }">
	            <div class="multiSelect" style="margin-top: 0">
	            	
	            </div>
        	</div>
        </div>
   </div>     
   <div class="layui-form-item" id="questionType2">      
        <div class="layui-inline">
            <label class="layui-form-label">多选题：</label>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm m-l-10" style="margin-top: 4px;" onclick="openSelectWin('2')">选择</a>
            </div>
            <div class="layui-input-inline" style="width: 90%;margin-left: 120px;">
	            <input type="hidden" name="questionIds" value="">
	            <input type="hidden" name="questionNames" value="">
	            <input type="hidden" id="multiCount" name="multiCount" autocomplete="off" class="layui-input" value="${bean.multiCount }">
	            <div class="multiSelect" style="margin-top: 0">
	            	
	            </div>
        	</div>
        </div>
   </div>     
   <div class="layui-form-item" id="questionType3">      
        <div class="layui-inline">
            <label class="layui-form-label">判断题：</label>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm m-l-10" style="margin-top: 4px;" onclick="openSelectWin('3')">选择</a>
            </div>
            <div class="layui-input-inline" style="width: 90%;margin-left: 120px;">
	            <input type="hidden" name="questionIds" value="">
	            <input type="hidden" name="questionNames" value="">
	            <input type="hidden" id="judgeCount" name="judgeCount" autocomplete="off" class="layui-input" value="${bean.judgeCount }">
	            <div class="multiSelect" style="margin-top: 0">
	            	
	            </div>
        	</div>
        </div>
    </div>     
    <div class="layui-form-item" id="questionType4">     
        <div class="layui-inline">
            <label class="layui-form-label">填空题：</label>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm m-l-10" style="margin-top: 4px;" onclick="openSelectWin('4')">选择</a>
            </div>
            <div class="layui-input-inline" style="width: 90%;margin-left: 120px;">
	            <input type="hidden" name="questionIds" value="">
	            <input type="hidden" name="questionNames" value="">
	            <input type="hidden" id="blankCount" name="blankCount" autocomplete="off" class="layui-input" value="${bean.blankCount }">
	            <div class="multiSelect" style="margin-top: 0">
	            	
	            </div>
        	</div>
        </div>
     </div>     
     <div class="layui-form-item" id="questionType5">    
        <div class="layui-inline">
            <label class="layui-form-label">简答题：</label>
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-sm m-l-10" style="margin-top: 4px;" onclick="openSelectWin('5')">选择</a>
            </div>
            <div class="layui-input-inline" style="width: 90%;margin-left: 120px;">
	            <input type="hidden" name="questionIds" value="">
	            <input type="hidden" name="questionNames" value="">
	            <input type="hidden" id="answerCount" name="answerCount" autocomplete="off" class="layui-input" value="${bean.answerCount }">
	            <div class="multiSelect" style="margin-top: 0">
	            	
	            </div>
        	</div>
        </div>
        
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


