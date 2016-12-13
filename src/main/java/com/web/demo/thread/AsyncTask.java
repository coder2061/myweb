package com.web.demo.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 异步执行业务方法，实现Callable接口，返回执行结果
 * 
 * @author jiangyf
 */
public class AsyncTask implements Callable<List<Map<String, Object>>> {
	// 执行任务名称
	private String taskName;
	// 执行任务时间
	private long taskTime;
	// 线程同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
	private CountDownLatch latch;

	// 任务执行结果
	List<Map<String, Object>> resultList;
	private Map<String, Object> resultMap;

	public AsyncTask(String taskName, long taskTime, CountDownLatch latch) {
		super();
		this.taskName = taskName;
		this.taskTime = taskTime;
		this.latch = latch;
	}

	@Override
	public List<Map<String, Object>> call() throws Exception {
		resultList = new ArrayList<Map<String, Object>>();
		resultMap = new HashMap<String, Object>();
		// 任务开始时间
		long begin = System.currentTimeMillis();

		System.out.println(taskName + " 任务开始....");
		
		// 执行具体业务
		Thread.sleep(taskTime * 1000);
		
		System.out.println(taskName + " 任务结束....");

		// 任务结束时间
		long end = System.currentTimeMillis();
		taskTime = (end - begin) / 1000;
		resultMap.put("taskName", taskName);
		resultMap.put("taskTime", taskTime);
		resultList.add(resultMap);
		System.out.println(taskName + "任务用时：" + taskTime + "秒");
		if (latch != null) {
			// 任务完成，计数器减一
			latch.countDown();
		}
		return resultList;
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// 任务开始时间
		long begin = System.currentTimeMillis();

		// 初始化计数器
		CountDownLatch latch = new CountDownLatch(2);
		// 初始化线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		// 初始化线程
		Future<List<Map<String, Object>>> future = executorService
				.submit(new AsyncTask("running", 2, latch));
		Future<List<Map<String, Object>>> future2 = executorService
				.submit(new AsyncTask("walking", 5, latch));
		executorService.shutdown();
		// 全部任务执行完成前，会一直阻塞当前线程，直到计时器的值为0
		latch.await();
		List<Map<String, Object>> result = future.get();
		List<Map<String, Object>> result2 = future2.get();
		result.addAll(result2);
		System.out.println(result.size());
		
		// 任务结束时间
		long end = System.currentTimeMillis();
		System.out.println("任务总用时：" + ((end - begin) / 1000) + "秒");
	}

}
