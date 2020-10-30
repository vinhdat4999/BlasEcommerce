package com.blas.blasecommerce.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.blas.blasecommerce.entity.ProductImage;

public class ProductImageModel {

	private String id;
	private String productId;
	private CommonsMultipartFile image;

	public ProductImageModel() {
		super();
	}

	public ProductImageModel(String id, String productId, CommonsMultipartFile image) {
		super();
		this.id = id;
		this.productId = productId;
		this.image = image;
	}

	public ProductImageModel(ProductImage productImage) {
		this.id = productImage.getId();
		this.productId = productImage.getProductId();
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

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductImageModel [id=" + id + ", productId=" + productId + ", image=" + image + "]";
	}

}
