package com.blas.blasecommerce.model;

public class CategoryModel {
	private String category;

	public CategoryModel() {
		super();
	}

	public CategoryModel(String category) {
		super();
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategoryModel [category=" + category + "]";
	}

}
