package com.blas.blasecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private int quantity;
	private String username;

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

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "username", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", username=" + username
				+ "]";
	}

}
