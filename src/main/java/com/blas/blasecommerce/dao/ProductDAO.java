package com.blas.blasecommerce.dao;

import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;
import com.blas.blasecommerce.model.ProductModel;

public interface ProductDAO {
	
	public Product findProduct(String productId);
	
	public ProductModel findProductModel(String productId);
	
	public PaginationResult<ProductModel> queryProducts(int page, int maxResult, int maxNavigationPage);
	
	public PaginationResult<ProductModel> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

	public PaginationResult<ProductModel> queryProductsByCategory(int page, int maxResult, int maxNavigationPage,
			String likeName);
	
	public PaginationResult<ProductModel> queryProductsByCategoryPriceDes(int page, int maxResult, int maxNavigationPage,
			String likeName);
	
	public PaginationResult<ProductModel> queryProductsByCategoryPriceInc(int page, int maxResult, int maxNavigationPage,
			String likeName);

	public PaginationResult<ProductModel> searchProduct(int page, int maxResult, int maxNavigationPage,
			String likeName);
	
	public PaginationResult<ProductModel> searchProductPriceDes(int page, int maxResult, int maxNavigationPage,
			String likeName);
	
	public PaginationResult<ProductModel> searchProductPriceInc(int page, int maxResult, int maxNavigationPage,
			String likeName);
	
	public void save(ProductModel ProductModel);
}
