package com.web.utils;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  

import com.web.core.factory.HibernateSessionFactory;
import com.web.entity.User;
  
public class Test {
	
	public static void testHibernate() {
		SessionFactory factory  = HibernateSessionFactory.getSessionFactory();
        Session session = factory.openSession();  
        session.beginTransaction();  
        User user = new User();  
        user.setName("jade");
        user.setPswd("123456");
        session.save(user);  
        session.getTransaction().commit();
	}
	
	public static void testThread() {
		StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
		
		for (StackTraceElement s : callStack) {
			System.out.println("s.getClassName() -> " + s.getClassName());
			System.out.println("s.getFileName() -> " + s.getFileName());
			System.out.println("s.getMethodName() -> " + s.getMethodName());
			System.out.println("s.isNativeMethod() -> " + s.isNativeMethod());
			System.out.println("s.getLineNumber() -> " + s.getLineNumber());
			System.out.println("-----------------------------------------");
		}
	}
  
    public static void main(String[] args) {
    	testThread();
    }  
}  
