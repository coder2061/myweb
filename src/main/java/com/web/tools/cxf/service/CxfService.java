package com.web.tools.cxf.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 定制客户端请求WebService所需要的接口
 * 
 * @author jiangyf
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface CxfService {
	// @WebParam(name="arg0")可有可无，为了增强可读性 
	String sayHi(@WebParam(name = "name")String name);

	String sayHello(String name);

}
