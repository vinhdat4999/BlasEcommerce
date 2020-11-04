package com.blas.blasecommerce.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Cart;
import com.blas.blasecommerce.model.CartModel;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<CartModel> getAllItemInCartByUser(String username) {
		// TODO Auto-generated method stub
		String sql = "select new " + CartModel.class.getName() + "(c.id, c.productId, c.quantity, c.username) from "
				+ Cart.class.getName() + " c where c.username = '" + username + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<CartModel> cartList = query.list();
		return cartList;
	}

	@Override
	public void updateQuantityInCart(int[] quantity, String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<CartModel> cartList = getAllItemInCartByUser(username);
		for (int i = 0; i < cartList.size(); i++) {
			String sql = "UPDATE " + Cart.class.getName() + " set quanity = " + quantity[i] + " WHERE username = '"
					+ username + "' and productId= '" + cartList.get(i).getProductId() + "'";
			Query query = session.createQuery(sql);
			query.executeUpdate();
		}
	}

}
