package com.web.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	public static Gson gson = null;

	static {
		gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
				.create();
	}

	public static Gson getGson() {
		return gson;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object json2Bean(String json, Class cls)
			throws JsonSyntaxException, UnsupportedEncodingException {
		return gson.fromJson(java.net.URLDecoder.decode(json, "utf-8"), cls);
	}

	public static String bean2Json(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 将json转换成Map<String, String>
	 * 
	 * @return Map<String, Integer>
	 */
	public static Map<String, Integer> jsonToMap(String data) {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		Map<String, Integer> map = g.fromJson(data,
				new TypeToken<Map<String, Integer>>() {
				}.getType());
		return map;
	}
}
