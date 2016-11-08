package com.web.tools.activemq.client;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActivemqClient {
	public final static String QUEUE_ACTIVITY = "ling.activity";
	public final static String QUEUE_DELETE = "ling.delete";

	@Resource
	private JmsTemplate jmsTemplate;

	/**
	 * 发送文本消息
	 *
	 * @param destination
	 *            队列名称
	 * @param message
	 *            文本消息内容
	 */
	public void sendMessage(String destination, final String message) {
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	/**
	 * 消费者监听器方法。监听队列messageQueue1
	 *
	 * @param message
	 */
	public void messageQueue1(String message) {
		System.out.println("接收到消息-messageQueue1： " + message);
	}

	/**
	 * 消费者监听器方法。监听队列 messageQueue2
	 *
	 * @param message
	 */
	public void messageQueue2(String message) {
		System.out.println("接收到消息-messageQueue2： " + message);
	}
}
