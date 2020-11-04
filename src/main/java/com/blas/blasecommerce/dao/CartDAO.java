package com.blas.blasecommerce.dao;

import java.util.List;

import com.blas.blasecommerce.model.CartModel;

public interface CartDAO {
	public List<CartModel> getAllItemInCartByUser(String username);

	public void updateQuantityInCart(int[] quanitity, String username);
}
