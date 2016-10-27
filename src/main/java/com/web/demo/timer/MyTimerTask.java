package com.web.demo.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.web.utils.DateUtils;

/**
 * java.util.Timer 
 * --工作原理：将处理模型放入到单线程队列中,在加入队列的时候对模型进行标记,之后通过线程实现查找最近执行目标进行执行。
 * --周期执行任务 
 * --串行执行,若之前任务出现异常则周期任务停止 
 * --好像是使用junit测试环境是不好使
 */
public class MyTimerTask extends TimerTask {

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// 线程延迟
		Thread thread = new Thread();  
        try {  
            thread.sleep(10000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
		System.out.println("Timer定时任务，时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
//		timer.schedule(new MyTimerTask(), 5000, 10000);
		timer.scheduleAtFixedRate(new MyTimerTask(), 5000, 10000);
	}

}
