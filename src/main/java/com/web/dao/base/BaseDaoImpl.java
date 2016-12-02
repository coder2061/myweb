package com.web.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findObject(java.lang.String)
	 */
	@Override
	public <T> T findObject(String hql) {
		List<T> list = findList(hql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findObject(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public <T> T findObject(String hql, Object... objects) {
		List<T> list = findList(hql, objects);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findObject(java.lang.Class,
	 * java.io.Serializable)
	 */
	@Override
	public <T> T findObject(Class<T> cls, Serializable id) {
		return (T) getSession().get(cls, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findObjectBySql(java.lang.String)
	 */
	@Override
	public <T> T findObjectBySql(String sql) {
		List<T> list = findListBySql(sql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findObjectBySql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public <T> T findObjectBySql(String sql, Object... objects) {
		List<T> list = findListBySql(sql, objects);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findList(java.lang.String)
	 */
	@Override
	public <T> List<T> findList(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findList(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public <T> List<T> findList(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findList(java.lang.Class)
	 */
	@Override
	public <T> List<T> findList(Class<T> cls) {
		String hql = "FROM " + cls.getName();
		return findList(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findListBySql(java.lang.String)
	 */
	@Override
	public <T> List<T> findListBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findListBySql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public <T> List<T> findListBySql(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#saveObject(java.lang.Object)
	 */
	@Override
	public <T> void saveObject(T obj) {
		getSession().save(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#updateObject(java.lang.Object)
	 */
	@Override
	public <T> void updateObject(T obj) {
		getSession().update(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#saveOrUpdateObject(java.lang.Object)
	 */
	@Override
	public <T> void saveOrUpdateObject(T obj) {
		getSession().saveOrUpdate(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#executeSql(java.lang.String)
	 */
	@Override
	public int executeSql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#executeSql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int executeSql(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#coutObjects(java.lang.String)
	 */
	@Override
	public int coutObjects(String hql) {
		Query query = getSession().createQuery(hql);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#countObjects(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int countObjects(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findPageList(java.lang.String, int, int)
	 */
	@Override
	public <T> PageList<T> findPageList(String hql, int page, int rows) {
		Query query = getSession().createQuery(hql);
		return findPageList(query, page, rows);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.web.core.base.BaseDao#findPageList(java.lang.String, int, int,
	 * java.lang.Object[])
	 */
	@Override
	public <T> PageList<T> findPageList(String hql, int page, int rows,
			Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return findPageList(query, page, rows);
	}

	public <T> PageList<T> findPageList(Query query, int page, int rows) {
		ScrollableResults sr = query.scroll();
		sr.last();
		int count = sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(page * rows);
		return new PageList<T>(page, rows, count, query.list());
	}

	public void setParameter(Query query, Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
	}
}