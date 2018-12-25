package com.imooc.o2o.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.imooc.o2o.entity.PersonInfo;

/**
 * 店家管理系统登录验证拦截器
 * 
 * 
 * @author 萝卜丁
 *
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 主要做事前拦截，即用户操作发生前，改写prehandle 里面的逻辑，进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从session中取出用户信息来
		Object userObject = request.getSession().getAttribute("user");
		if (userObject != null) {
			// 若用户信息不为空则将session里面的用户信息转换为PersonInfo实体类对象
			PersonInfo user = (PersonInfo) userObject;
			// 做控制判断，确保userId不为空并且该账号的可用状态为1，用户类型为商家
			if (user.getUserId() != null && user.getEnableStatus() == 1 && user.getUserId() > 0) {
				// 若通过验证则返回true，拦截器返回后，用户接下来的操作得以正常执行
				return true;
			}
		}
		// 若不满足登录验证，则直接跳转到帐号登录页面
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/local/login?usertype=2','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
}
