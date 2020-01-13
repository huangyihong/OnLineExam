package com.plg.shiro.common.json;

import java.sql.Timestamp;

import net.sf.json.JsonConfig;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class WebJsonConfig extends JsonConfig {

	public static WebJsonConfig getInstance() {
		return instance;
	}

	private static WebJsonConfig instance = new WebJsonConfig();

	private WebJsonConfig() {
		this.registerJsonValueProcessor(java.util.Date.class, new MyDateJsonValueProcessor());
		this.registerJsonValueProcessor(java.sql.Date.class, new MyDateJsonValueProcessor());
		this.registerJsonValueProcessor(Timestamp.class, new MyDateJsonValueProcessor());
        IgnoreFieldPropertyFilterImpl filter = new IgnoreFieldPropertyFilterImpl(null);
        this.setJsonPropertyFilter(filter);
	}

}
