package com.blas.blasecommerce.model;

public class CartDetailModel {
	private String id;
	private String productId;
	private String productName;
	private int quantity;
	private double price;
	private String username;

	public CartDetailModel() {
		super();
	}

	public CartDetailModel(String id, String productId, String productName, int quantity, double price,
			String username) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.username = username;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CartDetailModel [id=" + id + ", productId=" + productId + ", productName=" + productName + ", quantity="
				+ quantity + ", price=" + price + ", username=" + username + ", getId()=" + getId()
				+ ", getProductId()=" + getProductId() + ", getProductName()=" + getProductName() + ", getQuantity()="
				+ getQuantity() + ", getPrice()=" + getPrice() + ", getUsername()=" + getUsername() + "]";
	}

}
