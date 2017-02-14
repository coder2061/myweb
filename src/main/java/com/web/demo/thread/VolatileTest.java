package com.web.demo.thread;

/**
 * Volatile关键字 
 * 线程在每次使用volatile关键字修饰的变量的时候，都会读取变量修改后的最的值。
 * volatile很容易被误用，用来进行原子性操作。
 * 
 * @author jiangyf
 */
public class VolatileTest {

	public volatile static int count = 0;

	public static void inc() {
		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		count++;
	}

	public static void main(String[] args) {
		// 同时启动100个线程，去进行i++计算，看看实际结果
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					VolatileTest.inc();
				}
			}).start();
		}

		// 这里每次运行的值都有可能不同,可能为100
		System.out.println("运行结果:Counter.count=" + VolatileTest.count);
	}
}
