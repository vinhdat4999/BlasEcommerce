package com.blas.blasecommerce.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "productimage")
public class ProductImage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private byte[] image;

	public ProductImage() {
		super();
	}

	public ProductImage(String id, String productId, byte[] image) {
		super();
		this.id = id;
		this.productId = productId;
		this.image = image;
	}

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

	@Lob
	@Column(name = "image", length = Integer.MAX_VALUE)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductImage [id=" + id + ", productId=" + productId + ", image=" + Arrays.toString(image) + "]";
	}
}
