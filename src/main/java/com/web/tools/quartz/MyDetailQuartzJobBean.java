package com.web.tools.quartz;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，是不支持序列化的， 因此在把
 * QUARTZ 的 TASK 序列化进入数据库时就会抛错。 所以我们要自己实现MethodInvokingJobDetailFactoryBean
 * 的功能，然后用MyDetailQuartzJobBean 替换。
 */
public class MyDetailQuartzJobBean extends QuartzJobBean {
	private static final Log logger = LogFactory.getLog(MyDetailQuartzJobBean.class);
	private String targetObject;
	private String targetMethod;
	private ApplicationContext ctx;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			logger.info("execute [" + targetObject + "] at once>>>>>>");
			Object otargetObject = ctx.getBean(targetObject);
			Method m = null;

			try {
				m = otargetObject.getClass().getMethod(targetMethod,
						new Class[] { JobExecutionContext.class });
				m.invoke(otargetObject, new Object[] { context });
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			}
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}
