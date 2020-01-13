package com.plg.shiro.util;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.plg.shiro.common.json.IgnoreFieldPropertyFilterImpl;
import com.plg.shiro.common.json.MyDateJsonValueProcessor;
import com.plg.shiro.common.json.NotIgnoreFieldPropertyFilterImpl;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/**
 * Created with IntelliJ IDEA. User: chengzj Date: 15-2-6 Time: 下午5:10 To change
 * this template use File | Settings | File Templates.
 */
public class JsonUtil {

	/**
	 * 将对象所有的非空属性转换为json格式的字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return toJson(obj, null);
	}


	/**
	 * 将某一个对象中的 一部分属性 转换为json格式的字符串
	 * 
	 * @param obj
	 * @param pros
	 *            需要转换为json的属性
	 */
	public static String toJson(Object obj, String[] pros) {
		if (obj == null) {
			return null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new MyDateJsonValueProcessor());
		IgnoreFieldPropertyFilterImpl f = new IgnoreFieldPropertyFilterImpl(pros);
		jsonConfig.setJsonPropertyFilter(f);
		String jsonStr = null;
		if (obj instanceof Collection) {
			jsonStr = JSONArray.fromObject(obj, jsonConfig).toString();
		} else {
			jsonStr = JSONObject.fromObject(obj, jsonConfig).toString();
		}
		return jsonStr;
	}


	/**
	 * 将某一个对象中的 一部分属性 转换为json格式的字符串
	 * 
	 * @param obj
	 * @param pros
	 *            不需要转换为json的属性
	 */
	public static String toJsonNot(Object obj, String[] pros) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new MyDateJsonValueProcessor());
		NotIgnoreFieldPropertyFilterImpl f = new NotIgnoreFieldPropertyFilterImpl(pros);
		jsonConfig.setJsonPropertyFilter(f);
		String jsonStr = null;
		if (obj instanceof Collection) {
			jsonStr = JSONArray.fromObject(obj, jsonConfig).toString();
		} else {
			jsonStr = JSONObject.fromObject(obj, jsonConfig).toString();
		}
		return jsonStr;
	}


	/**
	 * 将json字符串转换为对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static Object toBean(String jsonStr, Class clazz) {
		JSONObject jsonPerson = JSONObject.fromObject(jsonStr);
		String[] dateFormats = new String[] { "yyyyMMdd" }; // 处理Date类型字段
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		Object obj = JSONObject.toBean(jsonPerson, clazz);
		return obj;
	}


	/**
	 * 将json字符串转换为对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static Object toBean(String jsonStr, Class clazz, Map m) {
		JSONObject jsonPerson = JSONObject.fromObject(jsonStr);
		String[] dateFormats = new String[] { "yyyyMMdd" }; // 处理Date类型字段
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		Object obj = JSONObject.toBean(jsonPerson, clazz, m);
		return obj;
	}


	/**
	 * 转换JSON字符串为对象
	 * 
	 * @param jsonString
	 * @param cla
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object toObject(String jsonString, Class cla) {
		return JSONObject.toBean(JSONObject.fromObject(jsonString), cla);
	}


	public static void main(String args[]) {
		String json = "	{ 'Package': { 'Head': { ' TradeName': 'PartPrescriptionHisDetail', ' TradeType': '03' }, 'Body': { 'InHospitalNumber': '34321', 'GetPreDate': '2012-09-12', 'InHospitalDate': '2012-09-01' } } } ";
		Map map = JSON.parseObject(json, java.util.Map.class);
		JSONObject jo = (JSONObject) map.get("Package");
	
		System.out.println(map.get("Package").getClass());
	}

}
