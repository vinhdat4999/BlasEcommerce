package com.blas.blasecommerce.model;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.blas.blasecommerce.entity.Product;

public class ProductModel {
	private String id;
	private String category;
	private Date createDate;
	private CommonsMultipartFile image;
	private String name;
	private double price;
	private String description;

	public ProductModel() {
		super();
	}

	public ProductModel(String id, String category, Date createDate, CommonsMultipartFile image, String name,
			double price, String description) {
		super();
		this.id = id;
		this.category = category;
		this.createDate = createDate;
		this.image = image;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public ProductModel(String id, String category, Date createDate, String name, double price, String description) {
		super();
		this.id = id;
		this.category = category;
		this.createDate = createDate;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public ProductModel(Product product) {
		this.id = product.getId();
		this.category = product.getCategory();
		this.createDate = product.getCreateDate();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", category=" + category + ", createDate=" + createDate + ", image=" + image
				+ ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}

}
