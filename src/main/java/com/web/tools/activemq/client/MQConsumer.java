package com.web.tools.activemq.client;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.web.tools.activemq.listener.MQListener;

import javax.jms.*;

public class MQConsumer {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void receiveMsg(String queueName) {
		// 创建会话工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				USERNAME, PASSWORD, BROKERURL);
		Connection connection = null;
		try {
			// 创建连接
			connection = connectionFactory.createConnection();
			// 启动连接
			connection.start();
			// 创建会话 AUTO_ACKNOWLEDGE，常用的接收方式
			Session session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			// 消息的目的地——创建消息队列
			Destination destination = session.createQueue(queueName);
			// 消息的生产者
			MessageConsumer consumer = session.createConsumer(destination);
			// 设置消息队列监听器
//			consumer.setMessageListener(new MQListener());
			receiveMsg(consumer, 100000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void receiveMsg(MessageConsumer consumer, long timeout)
			throws JMSException {
		while (true) {
			// 设置接收者接收消息的时间，为了便于测试，这里设定为100s
			TextMessage message = (TextMessage) consumer.receive(timeout);
			if (null != message) {
				System.out.println("收到消息，" + message.getText());
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		receiveMsg("FirstQueue");
	}
}
