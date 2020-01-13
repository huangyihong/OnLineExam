package com.plg.shiro.util.dwz;

public class AjaxObject {

	public static final int STATUS_CODE_SUCCESS = 200;

	public static final int STATUS_CODE_FAILURE = 300;

	public static final int STATUS_CODE_TIMEOUT = 301;

	public static final int STATUS_CODE_FORBIDDEN = 403;

	public static final String CALLBACK_TYPE_CLOSE_CURRENT = "closeCurrent";

	public static final String CALLBACK_TYPE_FORWARD = "forward";

	private int statusCode = 200;

	private String message = "";

	private String navTabId = "";

	private String forwardUrl = "";

	private String rel = "";

	private String callbackType = "closeCurrent";

	private static final String CALLBACK_DATA = "data";

	private Object data = "";//JSON数据

	private int code;

	/**
	 * 记录条数
	 */
	private long total;

	public int getCode() {
		return statusCode;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public AjaxObject() {

	}


	public AjaxObject(String message) {

		this.message = message;
	}


	public AjaxObject(int statusCode) {

		this.statusCode = statusCode;
	}


	public AjaxObject(int statusCode, String message) {

		this.statusCode = statusCode;
		this.message = message;
	}


	public AjaxObject(int statusCode, String message, String callbackType) {

		this.statusCode = statusCode;
		this.message = message;
		this.callbackType = callbackType;
	}


	public AjaxObject(int statusCode, String message, String navTabId, String forwardUrl, String rel,
			String callbackType) {

		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.forwardUrl = forwardUrl;
		this.rel = rel;
		this.callbackType = callbackType;
	}


	public int getStatusCode() {

		return this.statusCode;
	}


	public AjaxObject setStatusCode(int statusCode) {

		this.statusCode = statusCode;
		return this;
	}


	public String getMessage() {

		return this.message;
	}


	public AjaxObject setMessage(String message) {

		this.message = message;
		return this;
	}


	public String getForwardUrl() {

		return this.forwardUrl;
	}


	public AjaxObject setForwardUrl(String forwardUrl) {

		this.forwardUrl = forwardUrl;
		return this;
	}


	public String getRel() {

		return this.rel;
	}


	public AjaxObject setRel(String rel) {

		this.rel = rel;
		return this;
	}


	public String getCallbackType() {

		return this.callbackType;
	}


	public AjaxObject setCallbackType(String callbackType) {

		this.callbackType = callbackType;
		return this;
	}


	public String getNavTabId() {

		return this.navTabId;
	}


	public AjaxObject setNavTabId(String navTabId) {

		this.navTabId = navTabId;
		return this;
	}


	public static AjaxObject newOk(String message) {

		return new AjaxObject(200, message);
	}


	public static AjaxObject newOk(String message, Object data) {
		AjaxObject object = new AjaxObject(200, message);
		object.data = data;
		return object;
	}
	

	public static AjaxObject newError(String message) {

		return new AjaxObject(300, message);
	}


	public static AjaxObject newTimeout(String message) {

		return new AjaxObject(301, message);
	}


	public static AjaxObject newForbidden(String message) {

		return new AjaxObject(301, message);
	}


	public static AjaxObject newRefreshNavtab(String navTabId, String message) {

		AjaxObject ajaxObject = new AjaxObject(message);
		ajaxObject.navTabId = navTabId;
		return ajaxObject;
	}


	public static AjaxObject newRefreshRel(String rel, String message) {

		AjaxObject ajaxObject = new AjaxObject(message);
		ajaxObject.rel = rel;
		return ajaxObject;
	}


	public static AjaxObject newForward(String forwardUrl) {

		AjaxObject ajaxObject = new AjaxObject("forward");
		ajaxObject.forwardUrl = forwardUrl;
		return ajaxObject;
	}


	public String toString() {
//		return JsonUtil.toJson(this);
		return "";
	}


	public Object getData() {

		return data;
	}


	public void setData(Object data) {

		this.data = data;
	}


	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
