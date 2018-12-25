package com.imooc.o2o.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.imooc.o2o.entity.Shop;

/**
 * 
 * 
 * @author 萝卜丁
 *
 */
public class ShopPermissionInrerceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从session中获取当前选择的店铺
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		@SuppressWarnings("unchecked")
		// 从session中获取当前用户可操作的店铺列表
		List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
		if (currentShop != null && shopList != null) {
			// 遍历可操作的店铺列表
			for (Shop shop : shopList) {
				// 如果当前店铺在可操作的列表里则返回true，进行接下来的操作
				if (shop.getShopId() == currentShop.getShopId()) {
					return true;
				}
			}
		}
		// 若不满足则返回false，终止用户的操作
		return false;
	}
}
