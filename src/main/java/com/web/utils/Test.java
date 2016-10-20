package com.web.utils;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  

import com.web.core.HibernateSessionFactory;
import com.web.entity.User;
  
  
public class Test {  
  
    public static void main(String[] args) {  
        SessionFactory factory  = HibernateSessionFactory.getSessionFactory();
        Session session = factory.openSession();  
        session.beginTransaction();  
        User user = new User();  
        user.setName("jade");
        user.setPswd("123456");
        session.save(user);  
        session.getTransaction().commit();  
    }  
}  
