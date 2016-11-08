package com.web.tools.websocket.coder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder.Text;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;
import com.web.entity.Message;

public class MessageEncoder implements Text<Message> {

	@Override
	public void init(EndpointConfig config) {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public String encode(Message message) throws EncodeException {
		return JSON.toJSONString(message);
	}

}
