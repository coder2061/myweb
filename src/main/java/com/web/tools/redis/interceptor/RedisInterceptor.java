package com.web.tools.redis.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.web.tools.redis.core.RedisManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * redis数据缓存
 */
public class RedisInterceptor implements MethodInterceptor {
	private Logger logger = Logger.getLogger(RedisInterceptor.class);

	@Resource
	private RedisManager<?> redisManager;

	private List<String> targetNameList; // 需要缓存的类
	private List<String> methodNameList; // 需要缓存的方法
	private Map<String, Long> expireTimeMap; // 缓存有效时间

	/**
	 * 初始化读取不需要缓存的类和方法
	 */
	public RedisInterceptor() {
		try {
			Properties prop = new Properties();
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream("redis.properties");
			prop.load(new InputStreamReader(is, "UTF-8"));

			String[] targetNames = prop.getProperty("targetNames").split(",");
			String[] methodNames = prop.getProperty("methodNames").split(",");
			String[] expireTimes = prop.getProperty("expireTimes").split(",");
			int num = targetNames.length;
			targetNameList = new ArrayList<String>(num);
			methodNameList = new ArrayList<String>(num);
			// 将不需要缓存的类名和方法名添加到list中
			for (int i = 0; i < num; i++) {
				targetNameList.add(targetNames[i]);
				methodNameList.add(methodNames[i]);
				expireTimeMap.put(targetNames[i] + "." + methodNames[i],
						Long.parseLong(expireTimes[i]));
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object value = null;
		try {
			String targetName = invocation.getThis().getClass().getName();
			String methodName = invocation.getMethod().getName();
			Object[] arguments = invocation.getArguments();
			// 判断是否加入缓存
			if (!isAddCache(targetName, methodName)) {
				// 返回执行结果
				return invocation.proceed();
			}
			// 创建缓存key
			String key = getCacheKey(targetName, methodName, arguments);
			// 判断缓存中是否有对应的key
			if (redisManager.exists(key)) {
				return redisManager.get(key);
			}
			value = invocation.proceed();
			if (value != null) {
				String expireTimeKey = targetName + "." + methodName;
				final Long expireTime = expireTimeMap.get(expireTimeKey);
				final String tkey = key;
				final Object tvalue = value;
				new Thread(new Runnable() {
					@Override
					public void run() {
						redisManager.set(tkey, tvalue, expireTime);
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (value == null) {
				return invocation.proceed();
			}
		}
		return value;
	}

	/**
	 * 是否加入缓存
	 * 
	 * @param targetName
	 * @param methodName
	 * @return boolean
	 */
	private boolean isAddCache(String targetName, String methodName) {
		boolean flag = false;
		if (targetNameList.contains(targetName)
				&& methodNameList.contains(methodName)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 创建缓存key
	 *
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append("_").append(methodName);
		if (arguments != null && arguments.length != 0) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append("_").append(arguments[i]);
			}
		}
		return sb.toString();
	}
}