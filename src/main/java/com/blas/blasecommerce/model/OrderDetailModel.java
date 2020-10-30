package com.blas.blasecommerce.model;

public class OrderDetailModel {

	private String id;
	private String productId;
	private double price;
	private int quanity;
	private String orderId;

	public OrderDetailModel() {
		super();
	}

	public OrderDetailModel(String id, String productId, double price, int quanity, String orderId) {
		super();
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.quanity = quanity;
		this.orderId = orderId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderDetailModel [id=" + id + ", productId=" + productId + ", price=" + price + ", quanity=" + quanity
				+ ", orderId=" + orderId + "]";
	}

}
