package com.web.demo.designpattern.creational.singleton;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：较复杂
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 * 
 * @author jiangyf
 */
public class DCLSingleton {
	private volatile static DCLSingleton instance;
	
	private DCLSingleton() {
		System.out.println("-----------实例化-----------");
	}
	
	public static DCLSingleton getInstance() {
		if (instance == null) {
			// 同步块；在多线程访问时，只能有一个线程能够使用synchronized 修饰的方法 或者 代码块
			synchronized (DCLSingleton.class) {
				if (instance == null) {
					instance = new DCLSingleton();
				}
			}
		}
		return instance;
	}
	
	private void showMsg() {
		System.out.println("-----------双检锁/双重校验锁式单例-----------");
	}
	
	public static void main(String[] args) {
		DCLSingleton instance = DCLSingleton.getInstance();
		instance.showMsg();
		DCLSingleton instance2 = DCLSingleton.getInstance();
		instance2.showMsg();
	}

}
