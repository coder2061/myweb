package com.web.tools.activemq.client;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProducer {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int NUM = 10;

	/**
	 * 发送消息
	 * 
	 * @param msg
	 * @param queueName
	 */
	public static void sendMsg(String msg, String queueName) {
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
			// 消息的目的地——创建消息队列，queueName须在在ActiveMq的console配置
			Destination destination = session.createQueue(queueName);
			// 消息生产者
			MessageProducer producer = session.createProducer(destination);
			// 设置不持久化
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 发送消息
			sendMsg(msg, session, producer);
			// 提交事务
			session.commit();
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

	/**
	 * 发送消息
	 * 
	 * @param msg
	 * @param session
	 * @param messageProducer
	 */
	public static void sendMsg(String msg, Session session,
			MessageProducer producer) {
		for (int i = 0; i < NUM; i++) {
			try {
				TextMessage textMessage = session.createTextMessage("消息内容："
						+ msg + "，第" + (i + 1) + "条");
				System.out.println("发送消息：" + msg + "，第" + (i + 1) + "条");
				producer.send(textMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		sendMsg("Hello", "FirstQueue");
	}
}
