package com.plg.shiro.filter.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义shiro异常处理
 * 
 * @author Thinkpad
 *
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(MyExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// 如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
		logger.error("异常信息：" + ex);

		if (ex instanceof UnauthorizedException) {
			ModelAndView mv = new ModelAndView("403");
			mv.addObject("errorMsg", "您无权访问！");
			return mv;
		}

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorMsg", "您无权访问！");
		return mv;
	}

}
