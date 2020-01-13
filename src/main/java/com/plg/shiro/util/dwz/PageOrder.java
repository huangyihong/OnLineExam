package com.plg.shiro.util.dwz;

public class PageOrder {

	private String orderField;
	private String orderDirection;

	public PageOrder() {

	}

	public PageOrder(String orderField, String orderDirection) {

		this.orderField = orderField;
		this.orderDirection = orderDirection;
	}

	public String getOrderField() {

		return this.orderField;
	}

	public void setOrderField(String orderField) {

		this.orderField = orderField;
	}

	public String getOrderDirection() {

		return this.orderDirection;
	}

	public void setOrderDirection(String orderDirection) {

		this.orderDirection = orderDirection;
	}
}
