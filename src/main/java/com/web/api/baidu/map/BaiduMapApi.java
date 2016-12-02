package com.web.api.baidu.map;

public class BaiduMapApi {

	public static final String str = "";
	
	public static final String SECURITY_KEY = "I3RT5ZPNQuzSSreKRclhuv6UCsZMA3hd";

	public static final String API_KEY = "M2IF8XIMwfQYZpwQ1xtGjwQYi6A3y5fy";
	
	/**
	 * Place API 是一类简单的接口，用于返回查询某个区域的某类POI数据，且提供单个POI的详情查询服务，10万次/天
	 */
	
	// 城市检索
	public static final String SEARCH_CITY_PLACE = "http://api.map.baidu.com/place/v2/search?query=%s&region=%s&city_limit=%s&output=json&ak=%s";
	// 矩形区域检索
	public static final String SEARCH_SQUARE_PLACE = "http://api.map.baidu.com/place/v2/search?query=%s&bounds=%s&output=json&ak=%s";
	// 圆形区域检索
	public static final String SEARCH_ROUND_PLACE = "http://api.map.baidu.com/place/v2/search?query=%s&location=%s&radius=%s&output=json&ak=%s";
	
	
	/**
	 * Place suggestion API 是一套提供匹配用户输入关键字辅助信息、提示服务的接口，10万次/天
	 */
	
	public static final String SUGGESTION_PLACE = "http://api.map.baidu.com/place/v2/suggestion?query=%s&region=%s&output=json&ak=%s";
	
	
	/**
	 * Geocoding API 是一类接口，用于提供从地址到经纬度坐标或者从经纬度坐标到地址的转换服务，6000次/天
	 */
	// 地理编码服务
	public static final String GEOCODER_LOCATION = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address=%s&city=%s&ak=%s";

	// 逆地理编码服务
	public static final String GEOCODER_ADDRESS = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=%s&output=json&pois=%s&ak=%s";

}
