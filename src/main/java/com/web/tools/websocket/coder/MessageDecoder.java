package com.web.tools.websocket.coder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;
import com.web.entity.Message;

public class MessageDecoder implements Text<Message> {

	@Override
	public void init(EndpointConfig config) {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public Message decode(String message) throws DecodeException {
		return JSON.parseObject(message, Message.class);
	}

	@Override
	public boolean willDecode(String message) {
		return true;
	}

}
