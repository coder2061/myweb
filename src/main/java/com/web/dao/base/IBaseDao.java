package com.web.dao.base;

import java.util.List;

public interface IBaseDao<T> {
	public void save(Object object);

	public void delete(Object object);

	public void update(Object object);

	public void saveOrUpdate(Object object);

	public Object selectSingle(String targetName, String propertyName,
			Object value);

	public List<Object> selectByProperty(String targetName,
			String propertyName, Object value);

	List<Object> selectByPropertyList(String targetName,
			List<String> propertyName, List<Object> value);

	public Object get(int id);

	public Integer selectCount(String targetName);

	public List<Object> selectLimitedByOrder(String targetName,
			String propertyName, int num, String order);

	public List<Object> selectAll(String targetName);

	public List<Object> selectAllByOrder(String targetName,
			String propertyName, String order);

}
