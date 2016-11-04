package com.web.tools.cxf.core;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.web.tools.cxf.service.CxfService;
import com.web.tools.cxf.service.impl.CxfServiceImpl;

/**
 * 启动WebService
 * 
 * @author jiangyf
 */
public class WebServiceServer {

	/**
	 * 手动启动WebService接口
	 * 
	 * @param address
	 *            接口地址
	 * @param service
	 *            接口类
	 * @param serviceImpl
	 *            实现类对象
	 */
	public static void startWebService(String address, Class<?> service,
			Object serviceImpl) {
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
		factoryBean.setAddress(address); // 设置接口地址
		factoryBean.setServiceClass(service); // 设置接口类
		factoryBean.setServiceBean(serviceImpl); // 设置接口实现类
		factoryBean.create(); // 创建WebService接口
	}

	public static void main(String[] args) {
		System.out.println("WebService启动中。。。");

		String address = "http://127.0.0.1:8080/myweb/ws/cxf";
		startWebService(address, CxfService.class, new CxfServiceImpl());

		System.out.println("WebService启动成功。。。");
	}
}
