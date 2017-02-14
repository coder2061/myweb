package com.web.demo.designpattern.creational.singleton;

/**
 * 懒汉式单例(线程安全)
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 * 优点：第一次调用才初始化，避免内存浪费。
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
 * 
 * @author jiangyf
 */
public class SyncLazySingleton {
	// 私有的静态实例
	private static SyncLazySingleton instance;
	
	// 私有的构造函数，不会被其他类实例化
	private SyncLazySingleton() {
		System.out.println("-----------实例化-----------");
	}
	
	/**  
	* 公有的获取静态实例函数，必须加锁 synchronized 才能保证单例，但加锁会影响效率；
	* synchronized:在多线程访问时，只能有一个线程能够使用synchronized 修饰的方法 或者 代码块
	*/
	public static synchronized SyncLazySingleton getInstance() {
		if (instance == null) {
			instance = new SyncLazySingleton();
		}
		return instance;
	}
	
	private void showMsg() {
		System.out.println("-----------懒汉式单例-----------");
	}

	public static void main(String[] args) {
		SyncLazySingleton instance = SyncLazySingleton.getInstance();
		instance.showMsg();
		SyncLazySingleton instance2 = SyncLazySingleton.getInstance();
		instance2.showMsg();
	}

}
