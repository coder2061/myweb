package com.web.controller;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.model.Mail;

@Controller
@RequestMapping("/msg")
public class MessageController {
	
	@Resource
	private JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView send() {
		Mail mail = new Mail("1", "01", "02", "hello", "hello,world");
		sendMessage(mail);
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/commonTest");
		return model;
	}
	
	public void sendMessage(final Mail mail) {
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(mail);
			}
		});
	}

}
