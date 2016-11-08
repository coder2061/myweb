package com.web.tools.websocket.server;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.web.entity.Message;
import com.web.tools.websocket.coder.MessageDecoder;
import com.web.tools.websocket.coder.MessageEncoder;

@ServerEndpoint(value = "/obj", decoders = { MessageDecoder.class }, encoders = { MessageEncoder.class })
public class ObjectServer {
	private static final Logger log = LoggerFactory.getLogger(ObjectServer.class);
	private static final String STARTTIME = "StartTime";
	private Session msgSession;
	
	@OnOpen
	public void onOpen(Session session) {
		// 设置session
		this.msgSession = session;
		// 记录连接时间
		this.msgSession.getUserProperties().put(STARTTIME, System.currentTimeMillis());
		// 通知客户端连接成功
		this.sendMessage("info: open session success");
	}
	
	@OnMessage
	public Message onMessage (Message message) {
		return message;
	}
	

	@OnError
	public void onError(Throwable t) {
		// 发生异常时，如果连接还是打开状态，则通知客户端错误信息
		if (msgSession.isOpen()) {
			this.sendMessage("warning: error closeing session" + t.getMessage());
		}
	}

	@OnClose
	public void onClose() {
		// 关闭连接时，需要做的事情在该函数内完成，例如关闭数据库连接等
		log.info("close session success");
	}
	
	private void sendMessage(String message) {
		try {
			msgSession.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.error("method: sendMessage,info: error closeing session, " + e.getMessage());
		}
	}
}
