package com.blas.blasecommerce.model;

import com.blas.blasecommerce.entity.Cart;

public class CartModel {

	private String id;
	private String productId;
	private int quantity;
	private String username;

	public CartModel() {
		super();
	}

	public CartModel(String id, String productId, int quantity, String username) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.username = username;
	}

	public CartModel(Cart cart) {
		this.id = cart.getId();
		this.productId = cart.getProductId();
		this.quantity = cart.getQuantity();
		this.username = cart.getUsername();
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CartModel [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", username=" + username
				+ "]";
	}

}
