package com.web.demo.designpattern.creational.singleton;

/**
 * 懒汉式单例
 * 是否 Lazy 初始化：是
 * 是否多线程安全：否
 * 实现难度：易
 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 * 
 * @author jiangyf
 */
public class LazySingleton {
	// 私有的静态实例
	private static LazySingleton instance;
	
	// 私有的构造函数，不会被其他类实例化
	private LazySingleton() {
		System.out.println("-----------实例化-----------");
	}
	
	// 公有的获取静态实例函数，必须加锁 synchronized 才能保证单例，但加锁会影响效率
	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
	
	private void showMsg() {
		System.out.println("-----------懒汉式单例-----------");
	}

	public static void main(String[] args) {
		LazySingleton instance = LazySingleton.getInstance();
		instance.showMsg();
		LazySingleton instance2 = LazySingleton.getInstance();
		instance2.showMsg();
	}

}
