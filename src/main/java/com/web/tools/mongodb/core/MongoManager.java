package com.web.tools.mongodb.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
	private static MongoClient mongoClient;
	private static MongoDatabase database;

	static {
		// 连接到 mongodb 服务
		mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connect to client successfully");
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
	}

	/**
	 * 连接到数据库
	 * 
	 * @return void
	 */
	public static void useDatabase(String dbname) {
		database = mongoClient.getDatabase(dbname);
		System.out.println("Connect to database " + dbname + " successfully");
	}

	public static void main(String[] args) {
		useDatabase("test");
		System.out.println(database.getName());
	}

}
