package com.web.tools.mongodb.core;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoManager {
	private static MongoClient mongoClient;
	private static DB db;

	static {
		try {
			// 连接到 mongodb 服务
			mongoClient = new MongoClient("localhost", 27017);
			System.out.println("Connect to client successfully");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多服务
	 * 
	 * @param list
	 * @return void
	 */
	public static void multiServer(List<Map<String, String>> list) {
		List<ServerAddress> addresses = new ArrayList<ServerAddress>();
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		try {
			for (Map<String, String> map : list) {
				String addr = map.get("addr");
				String port = map.get("port");
				String userName = map.get("userName");
				String database = map.get("database");
				String password = map.get("password");
				if (port == null || "".equals(port)) {
					continue;
				}
				ServerAddress address = new ServerAddress(addr,
						Integer.parseInt(port));
				addresses.add(address);

				MongoCredential credential = MongoCredential.createCredential(
						userName, database, password.toCharArray());
				credentials.add(credential);
				// 连接到 mongodb 服务
				mongoClient = new MongoClient(addresses, credentials);
				System.out.println("Connect to client successfully");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接到数据库
	 * 
	 * @return void
	 */
	public static void useDatabase(String dbname) {
		db = mongoClient.getDB(dbname);
		System.out.println("Connect to database " + db + " successfully");
	}

	public static void main(String[] args) {
		useDatabase("test");
		System.out.println(db.getName());
	}

}
