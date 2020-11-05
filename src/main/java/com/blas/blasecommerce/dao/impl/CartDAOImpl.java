package com.blas.blasecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Cart;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.ProductModel;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartDAO cartDAO;

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
			String sql = "UPDATE " + Cart.class.getName() + " set quantity = " + quantity[i] + " WHERE username = '"
					+ username + "' and productId= '" + cartList.get(i).getProductId() + "'";
			Query query = session.createQuery(sql);
			query.executeUpdate();
		}
	}

	@Override
	public void updateItemInCart(String productId, int quantity, String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Cart.class);
		Criterion usernameCrit = Restrictions.eq("username", username);
		Criterion productIdCrit = Restrictions.eq("productId", productId);
		LogicalExpression andExp = Restrictions.and(usernameCrit, productIdCrit);
		criteria.add(andExp);
		Cart cart = (Cart) criteria.uniqueResult();
		if (cart == null) {
			String id = UUID.randomUUID().toString();
			CartModel cartModel = new CartModel(id, productId, quantity, username);
			session.persist(new Cart(cartModel));
		} else {
			String sql = "UPDATE " + Cart.class.getName() + " set quantity = " + (cart.getQuantity() + quantity)
					+ " WHERE username = '" + username + "' and productId= '" + productId + "'";
			Query query = session.createQuery(sql);
			query.executeUpdate();
		}
	}

	@Override
	public void deleteItemInCart(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from " + Cart.class.getName() + " where id='" + id + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.executeUpdate();	
	}

	@Override
	public double getTotalAmount(String username) {
		// TODO Auto-generated method stub
		double total = 0;
		List<CartModel> cartList = getAllItemInCartByUser(username);
		for (CartModel i : cartList) {
			ProductModel productModel = productDAO.findProductModel(i.getProductId());
			total += productModel.getPrice() * i.getQuantity();
		}
		return total;
	}

	@Override
	public List<Double> getTotalAmountItem(String username) {
		// TODO Auto-generated method stub
		List<Double> amountList = new ArrayList<Double>();
		List<CartModel> cartList = getAllItemInCartByUser(username);
		for (CartModel i : cartList) {
			ProductModel productInfo = productDAO.findProductModel(i.getProductId());
			double total = productInfo.getPrice() * i.getQuantity();
			amountList.add(total);
		}
		return amountList;
	}

	@Override
	public void deleteAllItemsInCartByUser(String username) {
		// TODO Auto-generated method stub
		String sql = "delete from " + Cart.class.getName() + " where username ='" + username + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.executeUpdate();
	}

}
