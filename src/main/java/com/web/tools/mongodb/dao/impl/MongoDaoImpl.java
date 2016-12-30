package com.web.tools.mongodb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.web.tools.mongodb.dao.MongoDao;

@Repository("mongoDao")
public class MongoDaoImpl<T> implements MongoDao<T> {
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public void createCollection(Class<T> clazz) {
		if (!mongoTemplate.collectionExists(clazz)) {
			mongoTemplate.createCollection(clazz);
		}
	}

	@Override
	public T findById(String id, Class<T> clazz) {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(id));
		return mongoTemplate.findOne(query, clazz);
	}

	@Override
	public List<T> findList(Query query, Class<T> clazz) {
		return mongoTemplate.find(query, clazz);
	}

	@Override
	public void insert(T t) {
		mongoTemplate.insert(t);
	}

	@Override
	public void update(Query query, Update update, Class<T> clazz) {
		this.mongoTemplate.updateFirst(query, update, clazz);
	}

	@Override
	public long count(Query query, String collectionName) {
		return mongoTemplate.count(query, collectionName);
	}

	@Override
	public void deleteById(String id, Class<T> clazz) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, clazz);
	}

	@Override
	public void deleteAll(String collection) {
		mongoTemplate.dropCollection(collection);
	}

}
