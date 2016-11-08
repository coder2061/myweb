package com.web.tools.memcached.spymemcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.CASResponse;
import net.spy.memcached.MemcachedClient;

public class SpyMemcachedTest {
	
	private static final String hostname = "127.0.0.1";
	private static final int port = 11211;
	
	private static MemcachedClient mcc;
	
	static {
		try {
			// 本地连接 Memcached 服务
			mcc = new MemcachedClient(new InetSocketAddress(hostname, port));
			System.out.println("Connection to server sucessful.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		mcc.set("myname", 120, "jade");
		System.out.println("set---get---" + mcc.get("myname"));
		
		mcc.add("yourname", 120, "jack");
		System.out.println("add---get---" + mcc.get("yourname"));
		
		mcc.replace("myname", 120, "hello");
		System.out.println("replace---get---" + mcc.get("myname"));
		
		mcc.append(0, "myname", " jack");
		System.out.println("append---get---" + mcc.get("myname"));
		
		mcc.prepend(0, "yourname", " jack");
		System.out.println("prepend---get---" + mcc.get("yourname"));
		
		mcc.set("myname", 120, "jade");
		System.out.println(mcc.gets("myname")+"---"+mcc.gets("myname").getCas());
		CASResponse casresp = mcc.cas("myname", mcc.gets("myname").getCas(), "hello");
		System.out.println("CAS Response - " + casresp);
		System.out.println("cas---get---" + mcc.get("myname"));
		
		mcc.add("yourname", 120, "jack");
        Future<Boolean> fo = mcc.delete("yourname");
        System.out.println("delete status:" + fo.get());
        System.out.println("delete---get---" + mcc.get("yourname"));
		
        Future<?> fo2 = mcc.set("number", 900, "1000");
        System.out.println("set status:" + fo2.get());
        System.out.println("value in cache - " + mcc.get("number"));
        System.out.println("value in cache after increment - " + mcc.incr("number", 111));
        System.out.println("value in cache after decrement - " + mcc.decr("number", 112));
		
		shutdown();
	}
	
	public static void shutdown() {
		// 关闭连接
		mcc.shutdown();
	}

}