<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<style>
.layadmin-font-em {
    font-size: 13px;
    color: #758697;
}
.layadmin-serach-list {
    margin-bottom: 10px;
    padding: 10px 0;
    border-bottom: 1px solid #f6f6f6;
}
</style>

<div class="page-container">
<form class="layui-form layui-form-pane" action="" id="form">
<input type="hidden" id="beginTime" value="${plan.beginTime }"/>
<input type="hidden" id="planId" value="${plan.planId }"/>
<input type="hidden" id="paperId" value="${bean.paperId }"/>
<input type="hidden" id="submitId" value="${examSubmit.submitId }"/>
<div class="layui-card-header" style="text-align: center;">
         <p style="font-size: 18px;">
           <strong>${bean.paperName }</strong> 
         </p>
         <p class="layadmin-font-em">总分：${bean.paperScore }<span class="p-l-50"></span>考试时长：<span id="examTime">${bean.paperTime }</span></p>
         <p>剩余时间：
			<span id="lastTime" style="color: #00A06B;font-size: 16px;font-weight: 900;">
				<span id="time_min">${examTime }</span>"
				<span id="time_sec">00</span>'
			</span>
		 </p>
</div>
<br>
<br>
<div class="layui-card-body">
	<div class="layui-serachlist-text">
	    <c:set var="questionTypeNum" value="0"/>
	    <c:if test="${not empty questionList1 }">
	    <div id="questionType1">
	    	<h4><strong>${questionTypeArr[questionTypeNum] }、单项选择题</strong></h4>
	    	<c:forEach items="${questionList1}" var="question" varStatus="status">
	    	    <div class="layui-form-item">
	    	    	<input type="hidden" name="questionId" value="${question.questionId }"/>
		    		<c:set var="type" value="radio"/>
		    		<c:if test="${question.questionType=='2' }"><c:set var="type" value="checkbox"/></c:if>
		    		<p class="questionName">${status.index+1 }、${question.questionName }<span style="font-weight: bold;">( ${question.questionScore} 分 )</span></p>
		            <div class="layui-upload-list" id="imgList">
		            <c:forEach items="${question.imgList}" var="imgBean" varStatus="status">
				      	<div class="imgdiv">
				      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"/>
				      	</div>
				    </c:forEach>
				    </div>
		            <c:if test="${not empty question.optionA}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="A" title="A、${question.optionA }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='A' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionB}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="B" title="B、${question.optionB }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='B' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionC}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="C" title="C、${question.optionC }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='C' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionD}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="D" title="D、${question.optionD }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='D' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionE}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="E" title="E、${question.optionE }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='E' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionF}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="F" title="F、${question.optionF }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='F' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionG}">
						<p><input type="${type }" name="rightResult_${question.questionId }" value="G" title="G、${question.optionG }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='G' }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionH}">
		            	<p><input type="${type }" name="rightResult_${question.questionId }" value="H" title="H、${question.optionH }" lay-skin="primary" lay-filter="rightResult" <c:if test="${question.answerResult=='H' }">checked</c:if>></p>
		            </c:if>
	            </div>
	    	</c:forEach>
	    	<c:set var="questionTypeNum" value="${questionTypeNum+1 }"/>
    	</div>
    	</c:if>
    	
    	<c:if test="${not empty questionList2 }">
	    <div id="questionType1">
	    	<h4><strong>${questionTypeArr[questionTypeNum] }、多项选择题</strong></h4>
	    	<c:forEach items="${questionList2}" var="question" varStatus="status">
	    	    <div class="layui-form-item">
	    	        <input type="hidden" name="questionId" value="${question.questionId }"/>
		    		<c:set var="type" value="radio"/>
		    		<c:if test="${question.questionType=='2' }"><c:set var="type" value="checkbox"/></c:if>
		    		<p class="questionName">${status.index+1 }、${question.questionName }<span style="font-weight: bold;">( ${question.questionScore} 分 )</span></p>
		            <div class="layui-upload-list" id="imgList">
		            <c:forEach items="${question.imgList}" var="imgBean" varStatus="status">
				      	<div class="imgdiv">
				      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"/>
				      	</div>
				    </c:forEach>
				    </div>
		            <c:if test="${not empty question.optionA}">
		            	<p><input type="${type }" name="rightResult" value="A" title="A、${question.optionA }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'A') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionB}">
		            	<p><input type="${type }" name="rightResult" value="B" title="B、${question.optionB }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'B') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionC}">
		            	<p><input type="${type }" name="rightResult" value="C" title="C、${question.optionC }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'C') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionD}">
		            	<p><input type="${type }" name="rightResult" value="D" title="D、${question.optionD }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'D') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionE}">
		            	<p><input type="${type }" name="rightResult" value="E" title="E、${question.optionE }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'E') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionF}">
		            	<p><input type="${type }" name="rightResult" value="F" title="F、${question.optionF }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'F') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionG}">
		            	<p><input type="${type }" name="rightResult" value="G" title="G、${question.optionG }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'G') }">checked</c:if>></p>
		            </c:if>
		            <c:if test="${not empty question.optionH}">
		            	<p><input type="${type }" name="rightResult" value="H" title="H、${question.optionH }" lay-skin="primary" lay-filter="rightResult" <c:if test="${fn:contains(question.answerResult,'H') }">checked</c:if>></p>
		            </c:if>
	            </div>
	    	</c:forEach>
	    	<c:set var="questionTypeNum" value="${questionTypeNum+1 }"/>
    	</div>
    	</c:if>
    	
    	<c:if test="${not empty questionList3 }">
    	<div id="questionType3">
	    	<h4><strong>${questionTypeArr[questionTypeNum] }、判断题</strong></h4>
	    	<c:forEach items="${questionList3}" var="question" varStatus="status">
	    		<div class="layui-form-item">
	    		    <input type="hidden" name="questionId" value="${question.questionId }"/>
		    		<p class="questionName">${status.index+1 }、${question.questionName }<span style="font-weight: bold;">( ${question.questionScore} 分 )</span></p>
		            <div class="layui-upload-list" id="imgList">
		            <c:forEach items="${question.imgList}" var="imgBean" varStatus="status">
				      	<div class="imgdiv">
				      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"/>
				      	</div>
				    </c:forEach>
				    </div>
		            <p><input type="radio" name="rightResult_${question.questionId }" value="1" title="正确" lay-filter="rightResult" <c:if test="${question.answerResult=='1' }">checked</c:if>>
		               <input type="radio" name="rightResult_${question.questionId }" value="0" title="错误" lay-filter="rightResult" <c:if test="${question.answerResult=='0' }">checked</c:if>>
		            </p>
	            </div>
	    	</c:forEach>
	    	<c:set var="questionTypeNum" value="${questionTypeNum+1 }"/>
    	</div>
    	</c:if>
    	
    	<c:if test="${not empty questionList4 }">
    	<div id="questionType4">
	    	<h4><strong>${questionTypeArr[questionTypeNum] }、填空题</strong></h4>
	    	<c:forEach items="${questionList4}" var="question" varStatus="status">
	    		<div class="layui-form-item">
	    		    <input type="hidden" name="answerResult" value="${question.answerResult }"/>
	    		    <input type="hidden" name="questionId" value="${question.questionId }"/>
	    			<p><span class="questionName">${status.index+1 }、${question.questionName }</span> <span style="font-weight: bold;">( ${question.questionScore} 分 )</span></p>
	    			<div class="layui-upload-list" id="imgList">
		            <c:forEach items="${question.imgList}" var="imgBean" varStatus="status">
				      	<div class="imgdiv">
				      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"/>
				      	</div>
				    </c:forEach>
				    </div>
	    		</div>
	    	</c:forEach>
	    	<c:set var="questionTypeNum" value="${questionTypeNum+1 }"/>
    	</div>
    	</c:if>
    	
    	<c:if test="${not empty questionList5 }">
    	<div id="questionType5">
	    	<h4><strong>${questionTypeArr[questionTypeNum] }、简答题</strong></h4>
	    	<c:forEach items="${questionList5}" var="question" varStatus="status">
	    		<div class="layui-form-item">
	    		    <input type="hidden" name="questionId" value="${question.questionId }"/>
		    		<p class="questionName">${status.index+1 }、${question.questionName }<span style="font-weight: bold;">( ${question.questionScore} 分 )</span></p>
		    		<div class="layui-upload-list" id="imgList">
		            <c:forEach items="${question.imgList}" var="imgBean" varStatus="status">
				      	<div class="imgdiv">
				      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"/>
				      	</div>
				    </c:forEach>
				    </div>
		    		<p>
		    		<textarea name="rightResult" class="layui-textarea"  placeholder="请输入答案" value="${question.answerResult }">${question.answerResult }</textarea>
		    		</p>
	    		</div>
	    	</c:forEach>
	    	<c:set var="questionTypeNum" value="${questionTypeNum+1 }"/>
	    </div>	
    	</c:if>
   </div>
</div>
<br>
<div class="layui-form-item" style="text-align: center">
    <span>
        <a onclick="submitPaper()" id="btnSave" href="javascript:void(0)"
               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-primary">交卷</a>
        <a onclick="parent.layer.closeAll()"
           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-reset btn btn-sm btn-default">关闭</a>
       </span>
</div>
</form>
</div>
<script type="text/javascript" src="${contextPath }/static/js/common/commonCreate.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/admin/exam/omPaper/exam.js"></script>
<%@ include file="/WEB-INF/views/admin/exam/omPaper/showImg.inc.jsp"%>



