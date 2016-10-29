package com.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * 集合操作
 * 
 * @author jiangyf   
 */
public class CollectionUtil {
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 将返回结果封装到map中
	 * 
	 * @param ifSuccess
	 *            处理是否成功
	 * @param message
	 *            描述信息
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static Map<String, Object> toRecordMap(String ifSuccess,
			String message, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ifSuccess);
		map.put("message", message);
		map.put("data", data);
		return map;
	}

}
