package com.plg.shiro.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

public static void main(String[] args) {
	String begin_time = "2019-09-22 20:53:00";
	Date beginDate = DateUtil.string2Date(begin_time, "yyyy-MM-dd HH:mm:ss");
	if(beginDate.before(new Date())){
		System.out.println("过了当前时间");
	}else{
		System.out.println("未到当前时间");
	}
	
	
//		//测试数据准备
//		//1.标题
//		String title = "t1heluosh1-使用POI导出Word";
//		//2.段落数据
//		String font_song_four_red = "这里是宋体四号红色字体";
//		String font_black_three_black = "这里是黑体三号号黑色字体";
//		String font_micro_five_red = "这里是微软雅黑五号红色字体";
//		
//		//存放段落数据
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("title", title);
//		map.put("font_song_four_red", font_song_four_red);
//		map.put("font_black_three_black", font_black_three_black);
//		map.put("font_micro_five_red", font_micro_five_red);
//		
//		//3.表格数据
//		List<Map<String,String>> excelMapList = new ArrayList<Map<String,String>>();
//		Map<String,String> excelMapTemp = null;
//		for (int i=1;i<11;i++) {
//			excelMapTemp = new HashMap<String,String>();
//			excelMapTemp.put("excel.no1", "one-"+i);
//			excelMapTemp.put("excel.no2", "two-"+i);
//			excelMapTemp.put("excel.no3", "three-"+i);
//			excelMapTemp.put("excel.no4", "four-"+i);
//			excelMapTemp.put("excel.no5", "five-"+i);
//			excelMapList.add(excelMapTemp);
//		}
//		
//		//模板存放位置
//		String demoTemplate = "D:/demoTemplate.docx";
//		//生成文档存放位置
//		String targetPath = "D:/target.doc";
//		
//		//初始化导出
//		WordExport export = new WordExport(demoTemplate);
//        try {
//            export.init();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		try {
//            export.export(map);
//            //0为表格的下标，第一个表格下标为0，以此类推
//            export.export(excelMapList, 0,1);
//            export.deleteTable(1);
//            export.export(excelMapList, 2,1);
//            export.deleteTable(3);
//            export.export(excelMapList, 4,1);
//            
//            
//           
//            
//            
//           
//           
//            export.generate(targetPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
	}

}
