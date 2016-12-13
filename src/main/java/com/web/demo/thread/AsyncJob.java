package com.web.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * 异步执行业务方法，继承Thread类，不返回执行结果
 * 
 * @author jiangyf
 */
public class AsyncJob extends Thread {
	// 执行任务名称
	private String jobName;
	// 执行任务时间
	private long jobTime;
	// 线程同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
	private CountDownLatch latch;

	public AsyncJob(String jobName, long jobTime, CountDownLatch latch) {
		super();
		this.jobName = jobName;
		this.jobTime = jobTime;
		this.latch = latch;
	}

	public void run() {
		// 任务开始时间
		long begin = System.currentTimeMillis();

		System.out.println(jobName + " 任务开始....");

		// 执行具体业务
		try {
			Thread.sleep(jobTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(jobName + " 任务结束....");

		// 任务结束时间
		long end = System.currentTimeMillis();
		jobTime = (end - begin) / 1000;
		System.out.println(jobName + "任务用时：" + jobTime + "秒");
		if (latch != null) {
			// 任务完成，计数器减一
			latch.countDown();
		}

	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// 任务开始时间
		long begin = System.currentTimeMillis();

		// 初始化计数器
		CountDownLatch latch = new CountDownLatch(2);
		// 初始化线程
		AsyncJob job = new AsyncJob("running", 5, latch);
		AsyncJob job2 = new AsyncJob("walking", 2, latch);
		job.start();
		job2.start();
		// 全部任务执行完成前，会一直阻塞当前线程，直到计时器的值为0
		latch.await();
		
		// 任务结束时间
		long end = System.currentTimeMillis();
		System.out.println("任务总用时：" + ((end - begin) / 1000) + "秒");
	}

}