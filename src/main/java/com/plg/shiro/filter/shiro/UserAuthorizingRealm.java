package com.plg.shiro.filter.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.common.BaseConstant;
import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IMenuService;
import com.plg.shiro.service.IRoleService;
import com.plg.shiro.service.IUserService;
import com.plg.shiro.util.Md5;

/**
 * 自定义认证、授权
 * 
 * @author Thinkpad 参考：yunnex.saofu.web.service.shiro.AuthShiroRealm
 */
@Service
public class UserAuthorizingRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(UserAuthorizingRealm.class);

	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IMenuService menuService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * 比如遇到@RequiresPermissions注解时，会调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("授权查询回调函数");

		// 获取登录时输入的用户名
		//String username = (String) principals.getPrimaryPrincipal();

		Session session = SecurityUtils.getSubject().getSession();
		OmUser omUser = (OmUser) session.getAttribute(BaseConstant.USER_SESSION_KEY);
		
		// 权限信息对象，用来存放查出的用户的所有的角色（role）及权限（permission）等
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if (null != omUser) {
			authorizationInfo.setRoles(this.getUserRoles(omUser.getUserId()));
			authorizationInfo.setStringPermissions(this.getUserPermissions(omUser.getUserId()));
		}

		return authorizationInfo;
	}

	/**
	 * 认证回调函数,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("认证回调函数");

		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		OmUser omUser = null;
		try {
			omUser = userService.selectByUserName(authcToken.getUsername());
		} catch (Exception e) {
			logger.error("身份认证发生异常", e);
			throw new AuthenticationException("身份认证发生异常");
		}

		if (null == omUser) {
			logger.warn("身份认证失败，登录名不存在");
			throw new UnknownAccountException("身份认证失败，登录名不存在");
		}
		
		if (1 != omUser.getStatus()) {
			logger.warn("身份认证失败，用户已被禁用");
			throw new LockedAccountException("身份认证失败，用户已被禁用");
		}

		// 密码验证
		if (!omUser.getPassword().equals(Md5.getMD5ofStrByLowerCase(String.valueOf(authcToken.getPassword())))) {
			logger.warn("身份认证失败，登录密码不正确");
			throw new IncorrectCredentialsException("身份认证失败，登录密码不正确");
		}

		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(BaseConstant.USER_SESSION_KEY, omUser);

		return new SimpleAuthenticationInfo(authcToken.getUsername(), authcToken.getPassword(), getName());
	}

	///////////////////////////////////////// private////////////////////////////////////
	/**
	 * 获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	private Set<String> getUserRoles(String userId) {
		List<OmRole> orRoleList = roleService.selectByUserId(userId);

		Set<String> roles = new HashSet<>();
		if (CollectionUtils.isEmpty(orRoleList)) {
			return roles;
		}

		for (OmRole or : orRoleList) {
			roles.add(or.getRoleCode());
		}

		return roles;
	}

	/**
	 * 获取用户权限
	 * 
	 * @param userId
	 * @return
	 */
	private Set<String> getUserPermissions(String userId) {
		List<OmMenu> menuList = menuService.queryUseMenu(userId);
		Set<String> stringPermissions = new HashSet<>();
		if (CollectionUtils.isEmpty(menuList)) {
			return stringPermissions;
		}
		for (OmMenu menu: menuList) {
			stringPermissions.add(menu.getMenuCode());
		}
		return stringPermissions;
	}

}
