package com.blas.blasecommerce.dao;

import java.util.List;

import com.blas.blasecommerce.model.CartModel;

public interface CartDAO {
	
	public List<CartModel> getAllItemInCartByUser(String username);
	public void updateQuantityInCart(int[] quanitity, String username);
	public void updateItemInCart(String productId, int quantity, String username);
	public void deleteItemInCart(String id);
	public double getTotalAmount(String username);
	public List<Double> getTotalAmountItem(String username);
	public void deleteAllItemsInCartByUser(String username);
}
