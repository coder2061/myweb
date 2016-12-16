package com.web.demo.thread;

/**
 * 继承Thread类
 * 
 * @author jiangyf
 */
public class ThreadTest extends Thread {
	private int count = 10;
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("count= " + count--);
		}
	}
	
	public static void main(String[] args) {
		ThreadTest test1 = new ThreadTest();
		ThreadTest test2 = new ThreadTest();
		test1.start();
		test2.start();
	}

}
