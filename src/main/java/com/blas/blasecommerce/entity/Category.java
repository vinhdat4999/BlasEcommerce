package com.blas.blasecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String category;

	@Id
	@Column(name = "category", length = 100, nullable = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [category=" + category + "]";
	}

}
