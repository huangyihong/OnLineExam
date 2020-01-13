package com.plg.shiro.util.dwz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

public class Page {

	public static final String ORDER_DIRECTION_ASC = "ASC";
	public static final String ORDER_DIRECTION_DESC = "DESC";
	public static final int DEFAULT_PAGE_SIZE = 15;
	private int plainPageNum = 1;
	protected int pageNum = 1;
	protected int numPerPage = 15;
	private String orderField = "";
	private String orderDirection = "";
	private int totalPage = 1;
	private int prePage = 1;
	private int nextPage = 1;
	protected long totalCount = 0L;
	private List<?> userObjects;
	private List<PageOrder> orders = null;

	public int getPageNum() {

		if (this.pageNum > this.totalPage) {
			this.pageNum = this.totalPage;
		}
		return this.pageNum;
	}

	public void setPageNum(int pageNum) {
		//pageNum==-1控制不分页查询，导出报表时候用
		this.pageNum = ((pageNum==-1 || pageNum > 0) ? pageNum : 1);
		this.plainPageNum = this.pageNum;
	}

	public int getNumPerPage() {

		return this.numPerPage;
	}

	public void setNumPerPage(int numPerPage) {

		this.numPerPage = (numPerPage > 0 ? numPerPage : 10);
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

	public int getPrePage() {

		this.prePage = (this.pageNum - 1);
		if (this.prePage < 1) {
			this.prePage = 1;
		}
		return this.prePage;
	}

	public void setPrePage(int prePage) {

		this.prePage = prePage;
	}

	public int getNextPage() {

		this.nextPage = (this.pageNum + 1);
		if (this.nextPage > this.totalPage) {
			this.nextPage = this.totalPage;
		}
		return this.nextPage;
	}

	public void setNextPage(int nextPage) {

		this.nextPage = nextPage;
	}

	public long getTotalCount() {

		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {

		this.totalCount = totalCount;
		this.totalPage = ((int) (totalCount - 1L) / this.numPerPage + 1);
	}

	public int getPlainPageNum() {

		return this.plainPageNum;
	}

	public void setPlainPageNum(int plainPageNum) {

		this.plainPageNum = plainPageNum;
	}

	public List<?> getUserObjects() {

		return this.userObjects;
	}

	public void setUserObjects(List<?> userObjects) {

		this.userObjects = userObjects;
	}

	public List<PageOrder> getOrders() {

		return this.orders;
	}

	public void setOrders(List<PageOrder> orders) {

		this.orders = orders;
	}
	
	public int limitStart() {
		return (pageNum-1) * numPerPage;
	}
	
	public int limitEnd() {
		return pageNum * numPerPage;
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
			List<Sort.Order> orders = new ArrayList();
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
