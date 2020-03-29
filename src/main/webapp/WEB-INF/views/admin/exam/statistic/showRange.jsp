<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/include.inc.jsp"%>
<%@ include file="/WEB-INF/views/common/create.inc.jsp"%>
<script type="text/javascript" src="${contextPath}/static/lib/echart3/echarts.min.js"></script>
<style>
.span_lable{
 font-weight: bolder;
}
</style>
<div style="padding: 15px">
    <input type="hidden" id="planId" value="${bean.planId }"/>
    <span style="font-size: 20px;font-weight: 600;">${bean.planName }</span> 
    
    <div style="font-size: 14px;float: right;">
       <table>
       	<tr>
       		<td width="150px"><span class="span_lable">总分：&nbsp;</span>${bean.paperScore }</td>
       		<td width="300px" colspan="2"><%-- <span class="span_lable">开考时间：&nbsp;</span>${bean.beginTime } --%></td>
       	</tr>
       	<tr>
       		<td width="150px"><span class="span_lable">考试人数：&nbsp;</span>${scoreNumMaxMinMap.USER_NUM }人</td>
       		<td width="150px"><span class="span_lable">优秀人数：&nbsp;</span>${scoreNumMaxMinMap.PERFECT_NUM }人</td>
       		<td width="150px"><span class="span_lable">良好人数：&nbsp;</span>${scoreNumMaxMinMap.GOOD_NUM }人</td>
        </tr>
       	<tr>
       		
       		<td><span class="span_lable">及格人数：&nbsp;</span>${scoreNumMaxMinMap.PASS_NUM }人</td>
       		<td><span class="span_lable">不及格：&nbsp;</span>${scoreNumMaxMinMap.NOPASS_NUM }人</td>
       		<td><span class="span_lable">及格率：&nbsp;</span>
       		<c:if test="${scoreNumMaxMinMap.USER_NUM==0 }">
       		0%
       		</c:if>
       		<c:if test="${scoreNumMaxMinMap.USER_NUM!=0 }">
       		<fmt:formatNumber type="number" value="${((scoreNumMaxMinMap.PERFECT_NUM+scoreNumMaxMinMap.GOOD_NUM+scoreNumMaxMinMap.PASS_NUM) * 100)/scoreNumMaxMinMap.USER_NUM }" pattern="0.00" maxFractionDigits="2"/>%
       		</c:if>
       		</td>
       	</tr>
       	<tr>
       		<td><span class="span_lable">最高分：&nbsp;</span><span style="color: deepskyblue">${scoreNumMaxMinMap.MAX_SCORE }分</span></td>
       		<td><span class="span_lable">最低分：&nbsp;</span><span style="color: red">${scoreNumMaxMinMap.MIN_SCORE }分</span></td>
       		<td><span class="span_lable">平均分：&nbsp;</span><span style="color: mediumpurple"><fmt:formatNumber type="number" value="${scoreNumMaxMinMap.AVG_SCORE }" pattern="0.00" maxFractionDigits="2"/>分</span></td>
       	</tr>
       </table>
        <br>
    </div>
    <div>
    <button class="layui-btn layui-btn-sm" style="margin:10px;" onclick="exportGroupGrade()">导出Excel</button>
    </div>
</div>
<div id="echarDiv" style="position: absolute;top: 150px;bottom: 0;left: 2%;right: 2%"></div>

    <script type="text/javascript">
    var bean = JSON.parse('${detail}')
    var dataAxis = bean.dataAxis;
    var user_num_data = bean.user_num_data; 
    var perfect_num_data = bean.perfect_num_data; 
    var good_num_data = bean.good_num_data; 
    var pass_num_data = bean.pass_num_data; 
    var nopass_num_data = bean.nopass_num_data; 
    var max_score_data = bean.max_score_data; 
    var min_score_data = bean.min_score_data; 
    var avg_score_data = bean.avg_score_data; 
        option = {
        	    tooltip: {
        	        trigger: 'axis',
        	        axisPointer: {
        	            type: 'cross',
        	            crossStyle: {
        	                color: '#999'
        	            }
        	        },
        	        formatter:function(params){
        	        	var tip="";
        	        	if(params!=null&&params.length>0){
        	        		tip +=params[0].name +'<br/>';
        	        		for(var i=0;i<params.length;i++){
        	        			tip += params[i].marker + params[i].seriesName +": "+params[i].value+'<br/>';
        	        		}
        	        		if(params[0].value){
        	        			var bi = ((params[1].value+params[2].value+params[3].value)*100)/params[0].value
        	        			tip +="及格率："+bi.toFixed(2)+"%"
        	        		}
        	        		
        	        		
        	        	}
        	        	return tip;
        	        }
        	    },
        	    toolbox: {
        	        feature: {
        	            saveAsImage: {show: true}
        	        }
        	    },
        	    legend: {
        	        data:['考试人数','优秀人数','良好人数','及格人数','不及格人数','平均分','最高分','最低分']
        	    },
        	    xAxis: [
        	        {
        	            type: 'category',
        	            data: dataAxis,
        	            axisPointer: {
        	                type: 'shadow'
        	            }
        	        }
        	    ],
        	    yAxis: [
        	        {
        	            type: 'value',
        	            name: '人数',
        	            axisLabel: {
        	                formatter: '{value} 人'
        	            }
        	        },
        	        {
        	            type: 'value',
        	            name: '分数',
        	            min: 0,
        	            max: 100,
        	            interval: 10,
        	            axisLabel: {
        	                formatter: '{value} 分'
        	            }
        	        }
        	    ],
        	    series: [
        	        {
        	            name:'考试人数',
        	            type:'bar',
        	            data:user_num_data
        	        },
        	        {
        	            name:'优秀人数',
        	            type:'bar',
        	            data:perfect_num_data
        	        },
        	        {
        	            name:'良好人数',
        	            type:'bar',
        	            data:good_num_data
        	        },
        	        {
        	            name:'及格人数',
        	            type:'bar',
        	            data:pass_num_data
        	        },
        	        {
        	            name:'不及格人数',
        	            type:'bar',
        	            data:nopass_num_data
        	        },
        	        {
        	           
        	            name:'最高分',
        	            type:'line',
        	            yAxisIndex: 1,
        	            data:max_score_data
        	        },
        	        {
        	        	name:'最低分',
        	            type:'line',
        	            yAxisIndex: 1,
        	            data:min_score_data
        	        },
        	        {
        	            name:'平均分',
        	            type:'line',
        	            yAxisIndex: 1,
        	            data:avg_score_data
        	        },
        	    ]
        	};
        var myChart = echarts.init(document.getElementById('echarDiv'));
        myChart.setOption(option);

        
        function exportGroupGrade(){
        	var url = WEBROOT + '/admin/exam/statistic/exportGroupGrade?planId='+$("#planId").val();
        	window.open(url);
        }
    </script>

