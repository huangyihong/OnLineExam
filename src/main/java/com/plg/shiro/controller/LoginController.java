package com.plg.shiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmUser;
import com.plg.shiro.util.dwz.AjaxObject;

/**
 * 登录控制器
 * 
 * @author Thinkpad
 *
 */
@RequestMapping({ "/admin/login" })
@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 进入登录页面
	 */
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String login() {
		Subject currentUser = SecurityUtils.getSubject();
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		if(omUser!=null&&currentUser!= null&&currentUser.isAuthenticated()){
			return "redirect:/admin/index";
		}
		return "login";
	}
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.GET }, params = { "ajax=true" })
	@ResponseBody
	public String loginDialog2AJAX() {

		return loginDialog();
	}

	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.HEAD }, headers = { "x-requested-with=XMLHttpRequest" })
	@ResponseBody
	public String loginDialog() {

		return AjaxObject.newTimeout("会话超时，请重新登录。").toString();
	}

	@RequestMapping(value = { "/timeout" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String timeout() {

		return "admin/index/loginDialog";
	}

	@RequestMapping(value = { "/timeout/success" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String timeoutSuccess() {

		return AjaxObject.newOk("登录成功。").toString();
	}


	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String fail(@RequestParam("username") String username, HttpServletRequest request, Model model) {
		String msg = parseException(request);
		model.addAttribute("msg", msg);
		model.addAttribute("loginName", username);
		model.addAttribute("username", username);
		return "login";
	}
	
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.POST,
			org.springframework.web.bind.annotation.RequestMethod.HEAD }, headers = { "x-requested-with=XMLHttpRequest" })
	@ResponseBody
	public String failDialog(HttpServletRequest request) {

		String msg = parseException(request);
		AjaxObject ajaxObject = new AjaxObject(msg);
		ajaxObject.setStatusCode(300);
		ajaxObject.setCallbackType("");
		return ajaxObject.toString();
	}

	
	private String parseException(HttpServletRequest request) {
		String errorString = (String) request.getAttribute("shiroLoginFailure");
		Class<?> error = null;
		try {
			if (errorString != null) {
				error = Class.forName(errorString);
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.toString());
		}
		String msg = "用户名或密码错误！";
		if (error != null) {
			if (error.equals(UnknownAccountException.class)) {
				logger.error("用户不存在");
				msg = "用户不存在";
			} else if (error.equals(IncorrectCredentialsException.class)) {
				logger.error("密码不正确");
				msg =  "密码不正确";
			} else if (error.equals(LockedAccountException.class)) {
				logger.error("用户被禁用");
				msg =  "用户被禁用";
			} else if (error.equals(ExcessiveAttemptsException.class)){
				logger.error("请求次数过多，用户被锁定");
			    msg = "请求次数过多，用户被锁定";
		    }else if (error.equals(Exception.class)) {
		    	logger.error("未知错误，无法完成登录");
				msg =  "未知错误，无法完成登录";
			}
		}
		return msg;
	}

	/**
	 * 错误页面
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/403")
	public String error(HttpServletRequest request, HttpServletResponse response) {
		logger.info("========403=======");
		return "403";
	}
}
