# myweb

声明：
	该Java工程主要介绍开源的轻量级ORM框架hibernate的开发使用。为方便理解，注释尽可能完善，欢迎参考使用，如有不当之处，尽请批评指正，以便共同进步，谢谢！
	
个人博客：
	http://my.oschina.net/jiangyf/blog

IDE：
	Eclipse，MyEclipse
	
管理工具：
	Maven，GIT，GitHub
	
涉及技术：
1）集成框架
	Spring
	SpringMVC
	Hibernate
	Quartz
	Lucene
	Shiro
	WebService
	WebSocket

2）模板引擎
	JSP

3）前端框架
	JQuery
	BootStrap

4）数据源
	MySql
	Druid
	C3P0
	
5）其他组件
	日志组件：slf4j-log4j,slf4j-logback
	测试组件：JUnit，JMockit
	Office组件：POI
	
探讨总结：

quartz 

问题
一、无法更新任务时参数问题（删除任务，重新创建）；
二、同步或异步在定时任务运行时修改是不能改变的（）；
三、在定时任务运行时修改，可能会该让任务长时间处于线程阻塞状态，即BLOCKED状态，即使你的任务中只有简单的一行System.out输出。要使它恢复也很简单，删除重建即可；
四、定时任务运行两次的问题，目前主要有两个说法：
1 spring配置文件加载了多次，导致quartz的bean被实例化多次而导致任务多次执行；
2 tomcat的webapps目录问题，tomcat运行时加载了两次配置文件导致任务多次执行；
3 quartz本身存在bug；
备注：
其实在我个人看来，这个问题是无关紧要的，为什么说无关紧要呢？这就涉及到你项目业务设计的是否完善，代码是否健壮了。
一个设计良好的业务方法，特别是那些供外部调用的接口或方法，应该都支持幂等性，何为幂等性？即这个方法同样的参数至少在一个时间区间内，我调用1次和调用10次100次，结果都是一样的。支持了幂等性，前面说的运行两次的情况是不是就无关紧要了？在有些定时任务为分布式设计的系统中，为了确保定时任务的执行甚至会故意人为的去调用两次。当然支持幂等性最好是在进入方法时就判断，发现已经执行过时就立即返回而不是真的再去同样的结果再执行一遍，以节省资源。 
	