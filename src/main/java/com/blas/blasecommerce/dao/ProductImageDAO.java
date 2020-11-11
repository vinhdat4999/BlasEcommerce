package com.blas.blasecommerce.dao;

import java.util.List;

import com.blas.blasecommerce.entity.ProductImage;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductImageModel;

public interface ProductImageDAO {

	public ProductImage findProductImage(String imageId);

	public ProductImageModel findProductImageModel(String imageId);

	public List<ProductImageModel> getImageIdList(String productId);

	public void deleteImage(String imageId);

	public void addImage(ProductImage productImage);

	public PaginationResult<ProductImageModel> queryProductImages(int page, int maxResult, int maxNavigationPage);

	public PaginationResult<ProductImageModel> queryProductImages(int page, int maxResult, int maxNavigationPage,
			String likeName);
}
