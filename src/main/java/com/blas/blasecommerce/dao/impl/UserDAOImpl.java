package com.blas.blasecommerce.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.entity.User;
import com.blas.blasecommerce.model.UserModel;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}

	@Override
	public UserModel findUserModel(String username) {
		// TODO Auto-generated method stub
		User user = findUser(username);
		if (user != null) {
			return new UserModel(user);
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		boolean isNew = false;
		User userTemp = findUser(user.getUsername());
		if (userTemp == null) {
			isNew = true;
		}
		if (!isNew) {
			userTemp.setPassword(user.getPassword());
		} else {
			sessionFactory.getCurrentSession().persist(user);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveUser(User user, ReceiverInfo receiverInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
		session.persist(receiverInfo);
	}

}
