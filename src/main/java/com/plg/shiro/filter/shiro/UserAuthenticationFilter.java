package com.plg.shiro.filter.shiro;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 自定义基于用户名与密码认证过滤器
 * @author Thinkpad
 *	FormAuthenticationFilter集成自javax.servlet.Filter
 */
public class UserAuthenticationFilter extends FormAuthenticationFilter {
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationFilter.class);
	
	/**
	 * 拦截获取到登录用户名/密码
	 */
    @SuppressWarnings("unchecked")
	@Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
    	logger.info("自定义基于用户名与密码认证过滤器");
        String username = null;
        String password = null;
        String contentType = request.getContentType();

        try {
            if (contentType.contains("application/json")) {
                String jsonContent = getJsonData((HttpServletRequest) request);
                Map<String, Object> contentMap = JSON.parseObject(jsonContent, Map.class);
                username = String.valueOf(contentMap.get("username"));
                password = String.valueOf(contentMap.get("password"));
            } else {
                username = getUsername(request);
                password = getPassword(request);
            }
            
            logger.info("自定义基于用户名与密码认证过滤器，用户名：{}，密码：{}", username, password);
        } catch (Exception e) {
            logger.error("获取用户名与密码参数异常!", e);
        }

        return createToken(username, password, request, response);
    }
    
    private String getJsonData(HttpServletRequest request) throws IOException {
        byte buffer[] = getRequestBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
    
    private byte[] getRequestBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        request.getInputStream().read(buffer, 0, contentLength);
        return buffer;
    }
}
