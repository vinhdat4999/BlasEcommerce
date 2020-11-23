package com.blas.blasecommerce.dao;

import java.util.List;

import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.OrderModel;
import com.blas.blasecommerce.model.PaginationResult;

public interface OrderDAO {
	public String saveOrder(String username, String receiverInfoId);

	public PaginationResult<OrderModel> listOrderModel(int page, int maxResult, int maxNavigationPage);

	public PaginationResult<OrderModel> listOrderModelByUser(int page, int maxResult, int maxNavigationPage,
			String userName);

	public OrderModel getOrderModel(String orderId);

	public List<OrderDetailModel> listOrderDetailModels(String orderId);
}
