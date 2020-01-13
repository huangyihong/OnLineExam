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
    <span style="font-size: 20px;font-weight: 600;">${bean.planName }
    </span> 
    
       
    <div style="font-size: 14px;float: right;">
       <table>
       	<tr>
       		<td width="150px"><span class="span_lable">总分：&nbsp;</span>${bean.paperScore }</td>
       		<td width="150px"><span class="span_lable">及格分：&nbsp;</span>
       			<c:if test="${bean.passingType=='1' }">${bean.passingScore }</c:if>
       			<c:if test="${bean.passingType=='2' }"><fmt:formatNumber type="number" value="${(bean.paperScore * bean.passingScore)/100 }" maxFractionDigits="0"/></c:if>
       		</td>
       		<td width="250px"><span class="span_lable">开考时间：&nbsp;</span>${bean.beginTime }</td>
       	</tr>
       	<tr>
       		<td><span class="span_lable">考试人数：&nbsp;</span>${detail.userNum }</td>
       		<td><span class="span_lable">及格人数：&nbsp;</span>${detail.passNum }</td>
       		<td><span class="span_lable">及格率：&nbsp;</span><fmt:formatNumber type="number" value="${(detail.passNum * 100)/detail.userNum }" pattern="0.00" maxFractionDigits="2"/>%</td>
       	</tr>
       	<tr>
       		<td><span class="span_lable">最高分：&nbsp;</span><span style="color: deepskyblue">${maxMinMap.MAX_SCORE }</span></td>
       		<td><span class="span_lable">最低分：&nbsp;</span><span style="color: red">${maxMinMap.MIN_SCORE }</span></td>
       		<td><span class="span_lable">平均分：&nbsp;</span><span style="color: mediumpurple"><fmt:formatNumber type="number" value="${maxMinMap.AVG_SCORE }" pattern="0.00" maxFractionDigits="2"/></span></td>
       	</tr>
       </table>
        <br>
    </div>
    <div>
    <button class="layui-btn layui-btn-sm" style="margin:10px;" onclick="alert(111)">导出Excel</button>
    </div>
</div>
<div id="echarDiv" style="position: absolute;top: 80px;bottom: 0;left: 2%;right: 2%"></div>

    <script type="text/javascript">
        var bean = JSON.parse('${detail}')
        var dataAxis = bean.dataAxis;
        var data = bean.data; 

        //var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
        //var data = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
        option = {
            xAxis: {
                data: dataAxis,
                type: 'category',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                },
            },
            yAxis: {
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: '姓名：{b0} <br />分数：{c0}'
            },
            series: [
                { // For shadow
                    type: 'bar',
                    itemStyle: {
                        normal: {color: 'rgba(0,0,0,0.05)'}
                    },
                    barGap: '-100%',
                    barCategoryGap: '40%',
                    // data: dataShadow,
                    animation: false
                },
                {
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    },
                    data: data
                }
            ]
        };

        var myChart = echarts.init(document.getElementById('echarDiv'));
        myChart.setOption(option);

    </script>

