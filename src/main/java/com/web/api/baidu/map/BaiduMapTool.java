package com.web.api.baidu.map;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.api.baidu.map.model.GeocoderAddress;
import com.web.api.baidu.map.model.GeocoderLocation;
import com.web.api.baidu.map.model.SearchPlace;
import com.web.api.baidu.map.model.SuggestionPlace;
import com.web.utils.GsonUtil;
import com.web.utils.HttpUtil;

public class BaiduMapTool {
	private static final Gson GSON = new Gson();

	public static String sign() throws UnsupportedEncodingException,
			NoSuchAlgorithmException {
		// 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。

		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		paramsMap.put("address", "百度大厦");
		paramsMap.put("output", "json");
		paramsMap.put("ak", "yourak");

		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = toQueryString(paramsMap);

		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/geocoder/v2/?" + paramsStr + "yoursk");

		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

		// 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
		return MD5(tempStr);
	}

	// 对Map内所有value作utf8编码，拼接返回结果
	public static String toQueryString(Map<?, ?> data)
			throws UnsupportedEncodingException {
		StringBuffer queryString = new StringBuffer();
		for (Entry<?, ?> pair : data.entrySet()) {
			queryString.append(pair.getKey() + "=");
			queryString.append(URLEncoder.encode((String) pair.getValue(),
					"UTF-8") + "&");
		}
		if (queryString.length() > 0) {
			queryString.deleteCharAt(queryString.length() - 1);
		}
		return queryString.toString();
	}

	/**  
	* 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	* @param md5
	* @return String 
	*/
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * 城市内检索
	 * 
	 * @param query
	 *            关键字
	 * @param region
	 *            检索区域（市级以上行政区域），如果取值为“全国”或某省份，则返回指定区域的POI及数量
	 * @param city_limit
	 *            是否只返回指定region（城市）内的POI，true(是)，false（否）
	 * @throws UnsupportedEncodingException
	 * @return SearchPlace
	 */
	public static SearchPlace searchCity(String query, String region,
			String city_limit) throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.SEARCH_CITY_PLACE,
				URLEncoder.encode(query, "UTF-8"),
				URLEncoder.encode(region, "UTF-8"), city_limit,
				BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		return GSON.fromJson(result, new TypeToken<SearchPlace>() {
		}.getType());
	}

	/**
	 * 矩形区域检索
	 * 
	 * @param query
	 *            关键字
	 * @param bounds
	 *            区域范围,lat,lng(左下角坐标),lat,lng(右上角坐标)
	 * @throws UnsupportedEncodingException
	 * @return SearchPlace
	 */
	public static SearchPlace searchSquare(String query, String bounds)
			throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.SEARCH_SQUARE_PLACE,
				URLEncoder.encode(query, "UTF-8"), bounds, BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		return GSON.fromJson(result, new TypeToken<SearchPlace>() {
		}.getType());
	}

	/**
	 * 圆形区域检索
	 * 
	 * @param query
	 *            关键字
	 * @param location
	 *            周边检索中心点，不支持多个点，lat<纬度>,lng<经度>
	 * @param radius
	 *            周边检索半径，单位为米
	 * @throws UnsupportedEncodingException
	 * @return SearchPlace
	 */
	public static SearchPlace searchRound(String query, String location,
			String radius) throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.SEARCH_ROUND_PLACE,
				URLEncoder.encode(query, "UTF-8"), location, radius,
				BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		return GSON.fromJson(result, new TypeToken<SearchPlace>() {
		}.getType());
	}

	/**
	 * 使用本接口请求，可调出匹配用户输入的关键字的建议列表，可配合Place API使用
	 * 
	 * @param query
	 *            关键字
	 * @param region
	 *            检索区域（市级以上行政区域），如果取值为“全国”或某省份，则返回指定区域的POI及数量
	 * @throws UnsupportedEncodingException
	 * @return SearchPlace
	 */
	public static SuggestionPlace suggestionPlace(String query, String region)
			throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.SUGGESTION_PLACE,
				URLEncoder.encode(query, "UTF-8"),
				URLEncoder.encode(region, "UTF-8"), BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		return GSON.fromJson(result, new TypeToken<SuggestionPlace>() {
		}.getType());
	}

	/**
	 * 地理编码服务，不带回调函数的值没有renderOption&&renderOption() 特别说明：
	 * 若解析status字段为OK，但结果内容为空，原因分析及可尝试方法： 1、地址库里无此数据，本次结果为空； 2、加入city字段重新解析；
	 * 3、将过于详细或简单的地址更改至省市区县街道重新解析。
	 * 
	 * @param address
	 *            指定地址
	 * @param city
	 *            地址所在的城市名
	 * @throws UnsupportedEncodingException
	 * @return GeocoderLocation
	 */
	public static GeocoderLocation geocoderLocation(String address, String city)
			throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.GEOCODER_LOCATION,
				URLEncoder.encode(address, "UTF-8"),
				URLEncoder.encode(city, "UTF-8"), BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		if (BaiduMapApi.GEOCODER_LOCATION.indexOf("callback") != -1) {
			result = result.substring(result.indexOf("{"),
					result.lastIndexOf("}") + 1);
		}
		return GSON.fromJson(result, new TypeToken<GeocoderLocation>() {
		}.getType());
	}

	/**
	 * 逆地理编码服务 特别说明： 1、因为Geocoding和反Geocoding使用的门址数据以及算法都不是一样的，所以会出现不能一一对应的现象。
	 * 2、逆地址解析location参数传入的参数格式是(纬度lat，经度lng)。
	 * 
	 * @param location
	 *            经纬度坐标，lat<纬度>,lng<经度>
	 * @param pois
	 *            是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，显示周边100米内的poi
	 * @throws UnsupportedEncodingException
	 * @return SearchPlace
	 */
	public static GeocoderAddress geocoderAddress(String location, String pois)
			throws UnsupportedEncodingException {
		String url = String.format(BaiduMapApi.GEOCODER_ADDRESS, location,
				pois, BaiduMapApi.API_KEY);
		String result = HttpUtil.sendGet(url);
		if (BaiduMapApi.GEOCODER_LOCATION.indexOf("callback") != -1) {
			result = result.substring(result.indexOf("{"),
					result.lastIndexOf("}") + 1);
		}
		return GSON.fromJson(result, new TypeToken<GeocoderAddress>() {
		}.getType());
	}

	public static void main(String[] args) {
		try {
			System.out.println(GsonUtil.bean2Json(searchCity("百度大厦", "北京",
					"true")));
			System.out.println(GsonUtil.bean2Json(searchSquare("银行",
					"39.915,116.404,39.975,116.414")));
			System.out.println(GsonUtil.bean2Json(searchRound("银行",
					"39.915,116.404", "true")));
			System.out.println(GsonUtil
					.bean2Json(suggestionPlace("天安门", "131")));
			System.out.println(GsonUtil.bean2Json(geocoderLocation("百度大厦",
					"北京市")));
			System.out.println(GsonUtil.bean2Json(geocoderAddress(
					"39.983424,116.322987", "1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
