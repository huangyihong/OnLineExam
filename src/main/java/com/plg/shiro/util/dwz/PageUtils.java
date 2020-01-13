package com.plg.shiro.util.dwz;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtils {

	public static Pageable createPageable(Page page) {

		if (StringUtils.isNotBlank(page.getOrderField())) {
			if (page.getOrderDirection().equalsIgnoreCase("ASC")) {
				return (Pageable) new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage(), Sort.Direction.ASC,
						new String[] { page.getOrderField() });
			}
			return (Pageable) new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage(), Sort.Direction.DESC,
					new String[] { page.getOrderField() });
		}
		return (Pageable) new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage());
	}

	public static Pageable createPageables(Page page) {

		if (page.getOrders() != null) {
			List<Sort.Order> orders = new ArrayList();
			for (PageOrder order : page.getOrders()) {
				if (order.getOrderDirection().equalsIgnoreCase("ASC")) {
					orders.add(new Sort.Order(Sort.Direction.ASC, order.getOrderField()));
				} else {
					orders.add(new Sort.Order(Sort.Direction.DESC, order.getOrderField()));
				}
			}
			return (Pageable) new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage(), new Sort(orders));
		}
		return (Pageable) new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage());
	}

	public static void injectPageProperties(Page page, org.springframework.data.domain.Page<?> springDataPage) {

		Long totalCount = Long.valueOf(0L);
		if (null != springDataPage) {
			totalCount = Long.valueOf(springDataPage.getTotalElements());
		}
		page.setTotalCount(totalCount.longValue());
	}
}
