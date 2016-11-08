package com.web.utils;

public class RandomUtil {
	private static final String CHARACTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int CHARACTER_LENGTH = CHARACTER.length();

	public static String randomStr(int length) {
		StringBuffer buffer = new StringBuffer(CHARACTER);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt((int) (Math.random() * CHARACTER_LENGTH)));
		}
		return sb.toString().toUpperCase();
	}

}
