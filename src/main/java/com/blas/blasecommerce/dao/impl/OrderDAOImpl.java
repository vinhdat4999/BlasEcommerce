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

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Orders;
import com.blas.blasecommerce.entity.OrderDetail;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.OrderModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;

public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private ProductDAO productDAO;

	@Override
	public String saveOrder(String username,  String receiverInfoId) {
		// TODO Auto-generated method stub
		Orders order = new Orders();
		String orderId = UUID.randomUUID().toString();
		order.setId(orderId);
		order.setUsername(username);
		order.setReceiverInfoId(receiverInfoId);
		order.setOrderTime(LocalDateTime.now());
		order.setStatus("Đặt hàng thành công");
		order.setExpectShipDate(new Date());
		order.setCode("");
		order.setCoupon("");
		order.setCoins(0);
		order.setTotal(cartDAO.getTotalAmount(username));
		List<CartModel> cartList = cartDAO.getAllItemInCartByUser(username);
		ProductModel productModel2 = productDAO.findProductModel(cartList.get(0).getProductId());
		if (cartList.size() == 1) {
			order.setDescription(productModel2.getName());
		} else {
			order.setDescription(productModel2.getName() + " và " + (cartList.size() - 1) + " sản phẩm khác");
		}
		Session session = sessionFactory.getCurrentSession();
		System.out.println("order: " + order.toString());
		session.persist(order);
		OrderDetail orderDetail = null;
		for (CartModel cartInfo : cartList) {
			ProductModel productModel = productDAO.findProductModel(cartInfo.getProductId());
			orderDetail = new OrderDetail();
			orderDetail.setId(UUID.randomUUID().toString());
			orderDetail.setProductId(cartInfo.getProductId());
			orderDetail.setPrice(productModel.getPrice());
			orderDetail.setQuantity(cartInfo.getQuantity());
			orderDetail.setOrderId(orderId);
			session.persist(orderDetail);
		}
		return orderId;
	}

	@Override
	public PaginationResult<OrderModel> listOrderModel(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		String sql = "select new " + OrderModel.class.getName() + "(ord.id, ord.username,"
				+ " ord.receiverInfoId, ord.orderTime, ord.status, ord.expectShipDate, ord.description, ord.code, ord.coupon, ord.coins, ord.total) from "
				+ Orders.class.getName() + " ord order by ord.orderTime desc";
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		List<OrderModel> list = query.list();
		return new PaginationResult<OrderModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<OrderModel> listOrderModelByUser(int page, int maxResult, int maxNavigationPage,
			String userName) {
		// TODO Auto-generated method stub
		String sql = "select new " + OrderModel.class.getName() + "(ord.id, ord.username,"
				+ " ord.receiverInfoId, ord.orderTime, ord.status, ord.expectShipDate, ord.description, ord.code, ord.coupon, ord.coins, ord.total) from "
				+ Orders.class.getName() + " ord where ord.username= '" + userName + "' order by ord.orderTime desc";
		System.out.println("sql: " + sql);
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		List<OrderModel> list = query.list();
		return new PaginationResult<OrderModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public OrderModel getOrderModel(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Orders.class);
		crit.add(Restrictions.eq("id", id));
		Orders order = (Orders) crit.uniqueResult();
		if (order == null)
			return null;
		return new OrderModel(order);
	}

	@Override
	public List<OrderDetailModel> listOrderDetailModels(String id) {
		// TODO Auto-generated method stub
		String sql = "select new " + OrderDetailModel.class.getName()
				+ "(ord.id, ord.productId, ord.price, ord.quantity, ord.orderId) from " + OrderDetail.class.getName()
				+ " ord where ord.orderId ='" + id + "'";
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		List<OrderDetailModel> list = query.list();
		return list;
	}
}
