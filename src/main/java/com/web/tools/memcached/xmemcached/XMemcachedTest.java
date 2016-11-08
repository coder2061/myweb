package com.web.tools.memcached.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemcachedTest {
	public static void test() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("127.0.0.1:11211"), new int[] { 2 });

		// 设置连接池大小，即客户端个数
		builder.setConnectionPoolSize(50);

		// 宕机报警
		builder.setFailureMode(true);

		// 使用二进制文件
		builder.setCommandFactory(new BinaryCommandFactory());

		MemcachedClient memcachedClient = null;
		try {
			memcachedClient = builder.build();
			try {
				System.out.println(memcachedClient.getName());
				// 设置/获取
				memcachedClient.set("running", 10000, "set/get");
				System.out.println("set/get---"
						+ memcachedClient.get("running"));

				// 替换
				memcachedClient.replace("running", 10000, "replace");
				System.out.println("replace---"
						+ memcachedClient.get("running"));

				// 移除
				memcachedClient.delete("running");
				System.out.println(memcachedClient.get("running"));
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (memcachedClient != null) {
				try {
					memcachedClient.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		test();
	}
}