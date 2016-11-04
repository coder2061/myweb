package com.web.tools.cxf.service.impl;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Component;

import com.web.tools.cxf.service.CxfService;

/**
 * 定制客户端请求WebService所需要的接口实现
 * WebService传递复杂对象，如JavaBean、Array、List、Map等
 * 
 * @author jiangyf
 */
@Component("cxfService")
@WebService
@SOAPBinding(style = Style.RPC)
public class CxfServiceImpl implements CxfService {
	/**
	 * @see http://127.0.0.1:8080/myweb/webservice/cxf/sayHi?wsdl
	 */
	@Override
	public String sayHi(@WebParam(name = "name")String name) {
		return "Hi, " + name;
	}

	/**
	 * @see http://127.0.0.1:8080/myweb/webservice/cxf/sayHello?wsdl
	 */
	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
