package com.blas.blasecommerce.dao.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.AuthenticationDAO;
import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Orders;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.entity.Authentication;
import com.blas.blasecommerce.entity.Cart;
import com.blas.blasecommerce.entity.OrderDetail;
import com.blas.blasecommerce.model.AuthenticationModel;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.OrderModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;

@Transactional
public class AuthenticationDAOImpl implements AuthenticationDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Authentication findAuthentication(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Authentication.class);
		crit.add(Restrictions.eq("username", username));
		return (Authentication) crit.uniqueResult();
	}

	@Override
	public AuthenticationModel findAuthenticationModel(String username) {
		Authentication authentication = findAuthentication(username);
		if (authentication == null) {
			return null;
		}
		return new AuthenticationModel(authentication);
	}

	@Override
	public void save(String username, String code) {
		// TODO Auto-generated method stub
		boolean isNew = false;
		Authentication authentication = findAuthentication(username);
		if (authentication == null) {
			isNew = true;
			LocalDateTime timeExpire = LocalDateTime.now().plusMinutes(20);
			authentication = new Authentication(username, code, timeExpire);
		}
		if (!isNew) {
			authentication.setCode(code);
			LocalDateTime timeExpire = LocalDateTime.now().plusMinutes(20);
			authentication.setTimeExpire(timeExpire);
		} else {
			sessionFactory.getCurrentSession().persist(authentication);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public boolean isValidAuthentication(String username, String code) {
		// TODO Auto-generated method stub
		AuthenticationModel authenticationModel = findAuthenticationModel(username);
		boolean validCode = code.equals(authenticationModel.getCode());
		boolean validTime = !(LocalDateTime.now().isAfter(authenticationModel.getTimeExpire()));
		if(validCode && validTime) {
			return true;
		}
		return false;
	}

	@Override
	public void save(String username, LocalDateTime expire) {
		// TODO Auto-generated method stub
		boolean isNew = false;
		Authentication authentication = findAuthentication(username);
		if (authentication == null) {
			isNew = true;
			authentication = new Authentication(username, "", expire);
		}
		if (!isNew) {
			authentication.setCode("");
			authentication.setTimeExpire(expire);
		} else {
			sessionFactory.getCurrentSession().persist(authentication);
		}
		sessionFactory.getCurrentSession().flush();
	}
}
