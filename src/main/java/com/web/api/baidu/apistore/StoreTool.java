package com.web.api.baidu.apistore;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.api.baidu.apistore.model.Identity;
import com.web.api.baidu.apistore.model.Mobile;
import com.web.api.baidu.apistore.model.MobileNumber;
import com.web.utils.HttpUtil;

public class StoreTool {
	private static final Gson GSON = new Gson();
	
	public static Mobile mobile(String mobile)
			throws UnsupportedEncodingException {
		String url = String.format(StoreApi.HS_MOBILE, mobile,
				StoreApi.HS_API_KEY);
		String result = HttpUtil.sendGet(url);
		return GSON.fromJson(result, new TypeToken<Mobile>() {
		}.getType());
	}

	public static MobileNumber mobileNumber(String mobile)
			throws UnsupportedEncodingException {
		String url = String.format(StoreApi.MOBILENUMBER, mobile);
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("apikey", StoreApi.API_KEY);
		String result = HttpUtil.sendGetWithHeader(url, header);
		return GSON.fromJson(result, new TypeToken<MobileNumber>() {
		}.getType());
	}
	
	public static Identity identity(String id)
			throws UnsupportedEncodingException {
		String url = String.format(StoreApi.IDENTITY, id);
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("apikey", StoreApi.API_KEY);
		String result = HttpUtil.sendGetWithHeader(url, header);
		return GSON.fromJson(result, new TypeToken<Identity>() {
		}.getType());
	}

	public static void main(String[] args) {
		try {
			Mobile mobile = mobile("18779885539");
			System.out.println(mobile.getError_code()+"---"+mobile.getReason());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
