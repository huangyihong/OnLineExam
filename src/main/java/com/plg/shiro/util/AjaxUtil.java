package com.plg.shiro.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class AjaxUtil {

    private static Log logger = LogFactory.getLog(AjaxUtil.class);


    public static <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
    
    public static <T> List<T> getJavaCollection(T clazz, String jsons,Map classMap) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass(),classMap);
                objs.add(obj);
            }
        }
        return objs;
    }
}
