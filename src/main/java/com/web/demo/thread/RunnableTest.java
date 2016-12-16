package com.web.demo.thread;

/**
 * 实现Runnable接口
 * 
 * @author jiangyf
 */
public class RunnableTest implements Runnable {
	private int count = 10;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("count= " + count--);
		}
	}
	
	public static void main(String[] args) {
		RunnableTest test1 = new RunnableTest();
		RunnableTest test2 = new RunnableTest();
		new Thread(test1).start();
		new Thread(test2).start();
	}
}
