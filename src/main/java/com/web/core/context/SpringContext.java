package com.web.core.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring上下文操作
 * 
 * @author jiangyf
 */
public class SpringContext implements ApplicationContextAware {
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	private SpringContext() {

	}

	private static class ApplicationContextHolder {
		// 单例变量
		private static ApplicationContext AC = new FileSystemXmlApplicationContext(
				"classpath:spring-context.xml");
	}

	// 获取单例对象实例
	public static ApplicationContext getInstance() {
		if (ApplicationContextHolder.AC == null) {
			ApplicationContextHolder.AC = new FileSystemXmlApplicationContext(
					"classpath:spring-context.xml");
		}
		return ApplicationContextHolder.AC;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class<?> requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name
	 * @return String[]
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

	public static void main(String[] args) {
		// 获取spring装配的bean个数
		String[] beans = SpringContext.getInstance()
				.getBeanDefinitionNames();
		// 逐个打印出spring自动装配的bean。根据我的测试，类名第一个字母小写即bean的名字
		for (int i = 0; i < beans.length; i++) {
			System.out.println(beans[i]);
		}
	}
}
