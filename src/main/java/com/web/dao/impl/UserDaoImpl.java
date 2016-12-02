package com.web.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.dao.UserDao;
import com.web.dao.base.BaseDao;
import com.web.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BaseDao baseDao;
	
	public User selectOne(Integer id) {
		return baseDao.findObjectBySql("select * from user where id=?", id);
	}

	@Override
	public User getUser(Integer id) {
		String hsql = "from User u where u.id=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hsql);
		query.setParameter(0, id);
		return (User) query.uniqueResult();
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public boolean delUser(String id) {
		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

	public boolean updateUser(User user) {
		String hql = "update User u set u.name=? where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getName());
		query.setInteger(1, user.getId());
		return (query.executeUpdate() > 0);
	}

}
