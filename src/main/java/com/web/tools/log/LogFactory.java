package com.web.tools.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类 
 * 自定义输出格式：[className].[methodName]() Line: [fileLine]
 * 
 * @author jiangyf
 */
public class LogFactory {
	/**
	 * 获取最原始被调用的堆栈信息
	 */
	private static StackTraceElement getOriginStackTrace() {
		// 获取堆栈信息，该方法返回一个代表该线程的堆栈转储堆栈跟踪元素的数组。如果该线程尚未启动或已经终止,这将返回一个零长度数组，
		StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
		if (null == callStack)
			return null;

		// 最原始被调用的堆栈信息
		StackTraceElement caller = null;
		// 日志类名称
		String logClassName = LogFactory.class.getName();
		// 循环遍历到日志类标识
		boolean isEachLogClass = false;

		// 遍历堆栈信息，获取出最原始被调用的方法信息
		for (StackTraceElement s : callStack) {
			// 遍历到日志类
			if (logClassName.equals(s.getClassName())) {
				isEachLogClass = true;
			}
			// 下一个非日志类的堆栈，就是最原始被调用的方法
			if (isEachLogClass) {
				if (!logClassName.equals(s.getClassName())) {
					isEachLogClass = false;
					caller = s;
					break;
				}
			}
		}

		return caller;
	}

	/**
	 * 自动匹配请求类名，生成logger对象
	 */
	private static Logger logger() {
		// 最原始被调用的堆栈对象
		StackTraceElement caller = getOriginStackTrace();
		if (null == caller) {
			return LoggerFactory.getLogger(LogFactory.class);
		} else {
			return LoggerFactory.getLogger(caller.getClassName() + "."
					+ caller.getMethodName() + "() Line: "
					+ caller.getLineNumber());
		}
	}

	public static void trace(String msg) {
		trace(msg, null);
	}

	public static void trace(String msg, Throwable e) {
		logger().trace(msg, e);
	}

	public static void debug(String msg) {
		debug(msg, null);
	}

	public static void debug(String msg, Throwable e) {
		logger().debug(msg, e);
	}

	public static void info(String msg) {
		info(msg, null);
	}

	public static void info(String msg, Throwable e) {
		logger().info(msg, e);
	}

	public static void warn(String msg) {
		warn(msg, null);
	}

	public static void warn(String msg, Throwable e) {
		logger().warn(msg, e);
	}

	public static void error(String msg) {
		error(msg, null);
	}

	public static void error(String msg, Throwable e) {
		logger().error(msg, e);
	}

}