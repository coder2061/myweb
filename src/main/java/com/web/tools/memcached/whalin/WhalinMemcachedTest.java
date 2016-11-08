package com.web.tools.memcached.whalin;

import com.whalin.MemCached.MemCachedClient;  
import com.whalin.MemCached.SockIOPool;  

public class WhalinMemcachedTest {
    
    private static WhalinMemcachedTest cache = null;
    
    private WhalinMemcachedTest() {
    	
    }
    
    /**
     * 单例模式
     * 
     * @return
     */
    public static WhalinMemcachedTest getInstance(){
        if (cache == null) {
            cache = new WhalinMemcachedTest();
        }
        return cache;
    }
    
    /* 单例模式 */  
    protected static MemCachedClient mcc = new MemCachedClient();  
      
    /* 配置服务器组 */  
    static {  
        /* 定义IP地址和端口 */  
        String[] servers = { "127.0.0.1:11211" };  
          
        /* 设置缓存大小 */  
        Integer[] weights = { 2 };  
          
        /* 拿到一个连接池的实例 */  
        SockIOPool pool = SockIOPool.getInstance();  
          
        /* 注入服务器组信息 */  
        pool.setServers(servers);  
        pool.setWeights(weights);
        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
          
        /* 配置缓冲池的一些基础信息 */  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(250);  
        pool.setMaxIdle(1000 * 60 * 60 * 6);  
          
        /* 设置线程休眠时间 */  
        pool.setMaintSleep(30);  
          
        /* 设置关于TCP连接 */  
        pool.setNagle(false);// 禁用nagle算法  
        pool.setSocketConnectTO(0);  
        pool.setSocketTO(3000);// 3秒超时  
          
        /* 初始化 */  
        pool.initialize();  
    }  
    
    public static Object get(String key) {  
    	return mcc.get(key);  
    } 
      
    public static boolean set(String key, Object obj) {  
        return mcc.set(key, obj);  
    }
    
    public static void main(String[] args) {
		set("name", "jack");
		System.out.println(get("name"));
	}
}