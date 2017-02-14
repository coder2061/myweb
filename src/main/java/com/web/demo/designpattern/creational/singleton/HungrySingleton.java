package com.web.demo.designpattern.creational.singleton;

/**
 * 饿汉式单例 
 * 是否 Lazy 初始化：否 
 * 是否多线程安全：是 
 * 实现难度：易 
 * 描述：这种方式比较常用，但容易产生垃圾对象。 
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。 
 * 它基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
 * 
 * @author jiangyf
 */
public class HungrySingleton {
	// 私有的静态实例
	private static HungrySingleton instance = new HungrySingleton();

	// 私有的构造函数，不会被其他类实例化
	private HungrySingleton() {
		System.out.println("-----------实例化-----------");
	}

	// 公有的获取静态实例函数，必须加锁 synchronized 才能保证单例，但加锁会影响效率
	public static HungrySingleton getInstance() {
		return instance;
	}

	private void showMsg() {
		System.out.println("-----------饿汉式单例-----------");
	}

	public static void main(String[] args) {
		HungrySingleton instance = HungrySingleton.getInstance();
		instance.showMsg();
		HungrySingleton instance2 = HungrySingleton.getInstance();
		instance2.showMsg();
	}

}
