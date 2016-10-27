package com.web.tools.quartz.core;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * 自定义JobFactory，替换Spring调度工厂SchedulerFactoryBean默认使用的AdaptableJobFactory
 * 
 * @author jiangyf
 */
public class MyJobFactory extends AdaptableJobFactory {
	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	/**  
	* 重写createJobInstance方法，用于Job实例化
	* 
	* @param bundle
	* @throws Exception
	*/
	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		// 调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		// 进行Spring注入,可参考Spring的API
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}

}
