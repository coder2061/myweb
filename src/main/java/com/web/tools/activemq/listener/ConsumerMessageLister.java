package com.web.tools.activemq.listener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.web.model.Mail;

public class ConsumerMessageLister implements
		SessionAwareMessageListener<Message> {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private JmsTemplate jmsTemplate;

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		log.info("==>receive message:" + message);

		ActiveMQObjectMessage msg = (ActiveMQObjectMessage) message;
		Mail mail = (Mail) msg.getObject();

		log.info("subject:" + mail.getSubject());
	}
}
