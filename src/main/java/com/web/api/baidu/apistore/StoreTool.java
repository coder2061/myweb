package com.web.api.baidu.apistore;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.api.baidu.apistore.model.Identity;
import com.web.api.baidu.apistore.model.Mobile;
import com.web.api.baidu.apistore.model.MobileNumber;
import com.web.api.baidu.apistore.model.Shouji;
import com.web.api.baidu.apistore.model.ShoujiResult;
import com.web.utils.HttpUtil;

public class StoreTool {
	private static Logger log = Logger.getLogger(StoreTool.class);
	private static final Gson GSON = new Gson();

	public static Mobile mobile(String mobile) {
		String url = String.format(StoreApi.HS_MOBILE, mobile,
				StoreApi.HS_API_KEY);
		try {
			String result = HttpUtil.sendGet(url);
			log.info("接口名称：StoreTool.mobile，手机号：" + mobile + ",返回结果：" + result);
			if (result != null && !"".equals(result)) {
				return GSON.fromJson(result, new TypeToken<Mobile>() {
				}.getType());
			}
		} catch (Exception e) {
			log.error("接口名称：StoreTool.mobile，手机号：" + mobile + ",返回结果：接口调用失败，"
					+ e.getMessage());
		}
		return null;
	}

	public static MobileNumber mobileNumber(String mobile) {
		String url = String.format(StoreApi.MOBILENUMBER, mobile);
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("apikey", StoreApi.API_KEY);
		try {
			String result = HttpUtil.sendGetWithHeader(url, header);
			log.info("接口名称：StoreTool.mobileNumber，手机号：" + mobile + ",返回结果："
					+ result);
			if (result != null && !"".equals(result)) {
				return GSON.fromJson(result, new TypeToken<MobileNumber>() {
				}.getType());
			}
		} catch (Exception e) {
			log.error("接口名称：StoreTool.mobileNumber，手机号：" + mobile
					+ ",返回结果：接口调用失败，" + e.getMessage());
		}
		return null;
	}
	
	public static Identity identity(String id)
			throws UnsupportedEncodingException {
		String url = String.format(StoreApi.IDENTITY, id);
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("apikey", StoreApi.API_KEY);
		String result = HttpUtil.sendGetWithHeader(url, header);
		log.info("接口名称：StoreTool.identity，身份证号：" + id + ",返回结果："
				+ result);
		return GSON.fromJson(result, new TypeToken<Identity>() {
		}.getType());
	}

	public static Shouji shouji(String mobile) {
		String url = String.format(StoreApi.ALI_SHOUJI, mobile);
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("Authorization", "APPCODE " + StoreApi.ALI_APPCODE);
		try {
			String result = HttpUtil.sendGetWithHeader(url, header);
			log.info("接口名称：StoreTool.queryMobile，手机号：" + mobile + ",返回结果："
					+ result);
			if (result != null && !"".equals(result)) {
				return GSON.fromJson(result, new TypeToken<Shouji>() {
				}.getType());
			}
		} catch (Exception e) {
			log.error("接口名称：StoreTool.queryMobile，手机号：" + mobile
					+ ",返回结果：接口调用失败，" + e.getMessage());
		}
		return null;
	}

	public static Shouji queryMobile(String mobile) {
		Shouji shouji = shouji(mobile);
		if (shouji == null) {
			Mobile mob = mobile(mobile);
			if (mob != null && mob.getResult() != null
					&& mob.getResult().getCity() != null) {
				ShoujiResult result = new ShoujiResult();
				result.setCity(mob.getResult().getCity());
				result.setProvince(mob.getResult().getProvince());

				shouji = new Shouji();
				shouji.setResult(result);
				shouji.setStatus("0");
				return shouji;
			}

			MobileNumber mobileNumber = mobileNumber(mobile);
			if (mobileNumber != null && mobileNumber.getRetData() != null
					&& mobileNumber.getRetData().getCity() != null) {
				ShoujiResult result = new ShoujiResult();
				result.setCity(mobileNumber.getRetData().getCity());
				result.setProvince(mobileNumber.getRetData().getProvince());

				shouji = new Shouji();
				shouji.setResult(result);
				shouji.setStatus("0");
				return shouji;
			}
		}
		return null;
	}

//	public static void main(String[] args) {
//		Shouji shouji = shouji("18779885539");
//		if (shouji != null) {
//			System.out.println(shouji.getResult().getCity());
//		}
//	}

}
