package com.blas.blasecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private double price;
	private int quanity;
	private String orderId;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "productId", length = 50, nullable = false)
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "quanity", nullable = false)
	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	@Column(name = "orderId", length = 50, nullable = false)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", productId=" + productId + ", price=" + price + ", quanity=" + quanity
				+ ", orderId=" + orderId + "]";
	}

}
