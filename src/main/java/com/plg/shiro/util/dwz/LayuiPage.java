/**
 * LayuiPage.java	  V1.0   2019年1月15日 上午9:34:19
 *
 * Copyright (c) 2019 AsiaInfo, All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.plg.shiro.util.dwz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

public class LayuiPage {
	public static final String ORDER_DIRECTION_ASC = "ASC";
	public static final String ORDER_DIRECTION_DESC = "DESC";
	public static final int DEFAULT_PAGE_SIZE = 10;
	protected int page = 1;
	protected int limit = DEFAULT_PAGE_SIZE;
	private String orderField;
	private String orderDirection;
	private int totalPage = 1;
	protected long totalCount = 0L;
	private List<PageOrder> orders = null;

	public int getPage() {
		if (this.page > this.totalPage) {
			this.page = this.totalPage;
		}
		return this.page;
	}

	public void setPage(int page) {
		//page==-1控制不分页查询，导出报表时候用
		this.page = ((page==-1 || page > 0) ? page : 1);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = (limit > 0 ? limit : 10);
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

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public long getTotalCount() {

		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {

		this.totalCount = totalCount;
		this.totalPage = ((int) (totalCount - 1L) / this.limit + 1);
	}
	
	public List<PageOrder> getOrders() {

		return this.orders;
	}

	public void setOrders(List<PageOrder> orders) {

		this.orders = orders;
	}
	
	public int limitStart() {
		return (page-1) * limit;
	}
	
	public int limitEnd() {
		return page * limit;
	}
	
	/**
	 * 
	 * @Description: 获取指定字段的排序子句。
	 * @return String 
	 * @throws
	 */
	public String getOrderByClause() {
		if (StringUtils.isNotBlank(getOrderField())) {
			if (getOrderDirection().equalsIgnoreCase("ASC")) {
				getOrderField().concat(" " + Sort.Direction.ASC);
			}
			return getOrderField().concat(" " + Sort.Direction.DESC);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Description: 获取多字段的排序子句。
	 * @return String 
	 * @throws
	 */
	public String getOrderByClauses() {
		if (getOrders() != null) {
			List<Sort.Order> orders = new ArrayList<Sort.Order>();
			for (PageOrder order : getOrders()) {
				if (order.getOrderDirection().equalsIgnoreCase("ASC")) {
					orders.add(new Sort.Order(Sort.Direction.ASC, order.getOrderField()));
				} else {
					orders.add(new Sort.Order(Sort.Direction.DESC, order.getOrderField()));
				}
			}
			return org.springframework.util.StringUtils.collectionToCommaDelimitedString(orders);
		}
		return null;
	}
}
