package com.web.tools.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface MongoDao<T> {
	/**
	 * 创建collection
	 * 
	 * @param clazz
	 */
	void createCollection(Class<T> clazz);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @param clazz
	 * @return T
	 */
	T findById(String id, Class<T> clazz);

	/**
	 * 查询记录
	 * 
	 * @param query
	 * @param clazz
	 * @return List<T>
	 */
	List<T> findList(Query query, Class<T> clazz);

	/**
	 * 新增
	 * 
	 * @param t
	 */
	void insert(T t);

	/**
	 * 更新
	 * 
	 * @param query
	 * @param update
	 * @param clazz
	 */
	void update(Query query, Update update, Class<T> clazz);

	/**
	 * 统计数量
	 * 
	 * @param query
	 * @return long
	 */
	long count(Query query, String collectionName);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	void deleteById(String id, Class<T> clazz);

	/**
	 * 删除Collection
	 * 
	 * @param collection
	 */
	void deleteAll(String collection);

}
