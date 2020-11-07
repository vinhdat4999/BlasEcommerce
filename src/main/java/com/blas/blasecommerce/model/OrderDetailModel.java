package com.blas.blasecommerce.model;

import com.blas.blasecommerce.entity.OrderDetail;

public class OrderDetailModel {

	private String id;
	private String productId;
	private String name;
	private double price;
	private int quantity;
	private String orderId;

	public OrderDetailModel() {
		super();
	}

	public OrderDetailModel(String id, String productId, String name, double price, int quantity, String orderId) {
		super();
		this.id = id;
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	public OrderDetailModel(String id, String productId, double price, int quantity, String orderId) {
		super();
		this.id = id;
		this.productId = productId;
		this.name = "";
		this.price = price;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	public OrderDetailModel(OrderDetail orderDetail) {
		this.id = orderDetail.getId();
		this.productId = orderDetail.getProductId();
		this.price = orderDetail.getPrice();
		this.quantity = orderDetail.getQuantity();
		this.orderId = orderDetail.getOrderId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderDetailModel [id=" + id + ", productId=" + productId + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", orderId=" + orderId + "]";
	}

}
