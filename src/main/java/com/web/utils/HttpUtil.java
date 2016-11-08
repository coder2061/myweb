package com.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	public static Map<String, String> getRequestParams(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String thisName = params.nextElement().toString();
			String thisValue = request.getParameter(thisName);
			map.put(thisName, thisValue);
		}
		return map;
	}

}
