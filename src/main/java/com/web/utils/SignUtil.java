package com.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 签名校验工具
 * 
 * @author jiangyf
 */
public class SignUtil {
	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String accessToken,
			String timestamp, String nonce) {
		String[] arr = new String[] { accessToken, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		MessageDigest md = null;
		String tmpStr = null;
		try {
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = new String(digest, "utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
}
