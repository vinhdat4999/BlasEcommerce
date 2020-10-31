package com.blas.blasecommerce.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.blas.blasecommerce.model.ProductModel;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String category;
	private Date createDate;
	private byte[] image;
	private String name;
	private double price;
	private String description;

	public Product(ProductModel productModel) {
		this.id = productModel.getId();
		this.category = productModel.getCategory();
		this.createDate = productModel.getCreateDate();
		this.image = productModel.getImage().getBytes();
		this.name = productModel.getName();
		this.price = productModel.getPrice();
		this.description = productModel.getDescription();
	}
	
	@Id
	@Column(name = "id", length = 50, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "category", length = 100, nullable = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Lob
	@Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "name", length = 200, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "description", length = Integer.MAX_VALUE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", createDate=" + createDate + ", image="
				+ Arrays.toString(image) + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}
}
