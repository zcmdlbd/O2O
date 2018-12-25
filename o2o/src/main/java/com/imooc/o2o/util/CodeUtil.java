package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String veritfyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String veritfyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (veritfyCodeActual == null || !veritfyCodeActual.equals(veritfyCodeExpected)) {
			return false;
		}
		return true;
	}
}
